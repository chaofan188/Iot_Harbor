package com.cetc.iot.accesssystem.downinterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.downinterface.protocol.base.AutoMachine;
import com.cetc.iot.accesssystem.downinterface.protocol.base.service.PeInterfaceService;
import com.cetc.iot.accesssystem.downinterface.protocol.ezviz.EzvizCam;
import com.cetc.iot.accesssystem.downinterface.protocol.ezviz.EzvizCamInitializer;
import com.cetc.iot.accesssystem.downinterface.protocol.http.HttpAdaptor;
import com.cetc.iot.accesssystem.downinterface.protocol.http.HttpInitializer;
import com.cetc.iot.accesssystem.downinterface.protocol.http.hikvision.HikvisionCam;
import com.cetc.iot.accesssystem.downinterface.protocol.http.hikvision.HikvisionCamKey;
import com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam;
import com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCamInitializer;
import com.cetc.iot.accesssystem.downinterface.socket.commmunication.ServerUtil;
import com.cetc.iot.accesssystem.downinterface.socket.service.SocketPeService;
import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.communicate.PESession;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.database.model.PeInterface;
import com.cetc.iot.servicesystem.util.GetBean;

/**
 * this class is used for upper layer to send messages to pe
 * @author xzc
 * Create Time: 2014-09-05
 * Author: xzc
 * Details: Add msgSend function to send messages to pe
 * Update Time: 2015-04-27
 * Author: xzc
 * Details: Add msgSend(PeData,Session) function to send messages to devices
 */
public class MsgOperation {
	
	private static Logger logger = Logger.getLogger(MsgOperation.class.getName());
	
	private SocketPeService socketPeService = (SocketPeService)GetBean.getBean("socketPeService");
	private PeInterfaceService peInterfaceService = (PeInterfaceService)GetBean.getBean("protocolPeInterfaceService");
	
	private static MsgOperation msgOperation = new MsgOperation();
	private static ExecutorService exec = Executors.newCachedThreadPool();

	/**
	 * this function is used to send messages to pe
	 * @param peData 
	 * @deprecated this method is not used since the lower protocol changes
	 */
	@Deprecated
	public static void msgSend(PEData peData){
		logger.info("new message to send, destination PEID: "+peData.peID);
		
		//Create json String
		JSONObject msg = new JSONObject();
		msg.put("Address", peData.peID);
		msg.put("Version", "1.0");
		msg.put("IFID", peData.interfaceID);
		msg.put("IsResponse", peData.isResponse);
		msg.put("SessionID",peData.sessionID);
		msg.put("IsData", peData.isData);
		msg.put("RequireResponse",peData.requireResponse);
		msg.put("Length", peData.length);
		msg.put("Data", JSONObject.fromObject(peData.data));
	
		//Call sendInfo function to send messages
		ServerUtil.sendInfo(peData.peID, msg.toString());
	}
	
	//TODO: Notice These section of downinterface code NEED REFACTOR!!
	//Define public interface ,use adaptor pattern.
	/**
	 * this method is used to send message to device
	 * @param peData contains peID, interfaceID, data
	 * @param session 
	 * @return true: send ok; false: send fail
	 */
	public static boolean msgSend(final PEData peData, final PESession session){
		logger.info("Send Information to Lower, Data: "+peData.data);
		//check the protocol to send messages
		String protocol = msgOperation.protocolCheckString(peData);
		if ("pe not found!".equals(protocol)){
			logger.info("ERROR: pe not found in database! ");
			return false;
		}
		if ("interface not found!".equals(protocol)){
			logger.info("ERROR: interface not found in database! ");
			return false;
		}
		if ("StandardProtocol".equalsIgnoreCase(protocol)){
			//the device uses standard protocol to communicate
			logger.info("Protocol StandardProtocol! ");
			JSONObject json = new JSONObject();
			json.put("IFID", peData.interfaceID);
			json.put("Data", JSONObject.fromObject(peData.data));
			logger.info("Upper send info to peID: "+peData.peID+" message: "+json.toString());
			AutoMachine autoMachine = AutoMachine.getPeAutoMachineUpper(peData.peID);
			if (autoMachine == null){
				logger.info("pe: "+peData.peID+" not log in! ");
				return false;
			}
			boolean result = autoMachine.action(json.toString(),session);
			return result;
		}
		
		
		if ("HTTP-Hikvision".equalsIgnoreCase(protocol)){
			//the device uses http to communicate
			System.out.println("Protocol HTTP!");
			if ("cam_control".equals(peData.interfaceID)){
				JSONObject jsonData = JSONObject.fromObject(peData.data);
				Iterator it = jsonData.keys();
				int count = 0;
				String key = null;
				int value = 0;
				while (it.hasNext()){
					count++;
					if (count > 1){
						break;
					}
					key = String.valueOf(it.next());
					value = (Integer)jsonData.get(key);
				}
				if (count == 1){
					if (!new HikvisionCamKey().checkKey(key)){
						return false;
					}
					if (value == 0){
						new HikvisionCam().controlByGet(key);
					}
					else {
						new HikvisionCam().controlByGet(key, ""+value);
					}
					return true;
				}
				return false;
			}
			return false;
		}
		
		/*
		 * adaptor for HTTP Protocol
		 */
		if("HTTP".equalsIgnoreCase(protocol)){
			HttpAdaptor adaptor = HttpAdaptor.getAdaptor(peData.peID, peData.interfaceID);
			if(adaptor == null){
				new HttpInitializer(peData.peID, peData.interfaceID);
				adaptor = HttpAdaptor.getAdaptor(peData.peID,  peData.interfaceID);
			}
			
			exec.execute(adaptor.new HttpThread(peData.data, session));
			return true;
		}
		
		if ("ONVIF".equalsIgnoreCase(protocol)){ //TODO: 用反射改为动态接口
			
			OnvifCam cam = OnvifCam.getCam(peData.peID, peData.interfaceID);
			if(cam == null){
				new OnvifCamInitializer(peData.peID, peData.interfaceID);
				cam = OnvifCam.getCam(peData.peID, peData.interfaceID);
			}
			
			exec.execute(cam.new OnvifCamThread(peData.data, session));
			
			return true;
		}
		
		
		///////////////////////////////////////////////////////////////
	    if ("EZVIZ".equalsIgnoreCase(protocol)){ //TODO: 用反射改为动态接口
			
	    	EzvizCam ezvizcam = EzvizCam.getCam(peData.peID, peData.interfaceID);
			if(ezvizcam == null){
				new EzvizCamInitializer(peData.peID, peData.interfaceID);
				ezvizcam = EzvizCam.getCam(peData.peID, peData.interfaceID);
			}
			
			exec.execute(ezvizcam.new EzvizCamThread(peData.data, session));

			return true;
		}
		///////////////////////////////////////////////////////////////
	    logger.info("ERROR: communication protocol not found! ");
		return false;
	}
	
	public String protocolCheckString(PEData peData){
		Pe pe = socketPeService.getPeByPeID(peData.peID);
		if (pe == null){
			return "pe not found!";
		}
		PeInterface peInterface = peInterfaceService.query(pe.getTemplateId(), peData.interfaceID);
		System.out.println("template: "+pe.getTemplateId());
		System.out.println("interface: "+peData.interfaceID);
		if (peInterface == null){
			return "interface not found!";
		}
		return peInterface.getInterfaceType();
	}
	
}
