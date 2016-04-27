package com.cetc.iot.servicesystem.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cetc.iot.database.model.PeParam;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.harbormanage.service.PEParamService;
import com.cetc.iot.middle.PEOperationForAtomicVE;
import com.cetc.iot.servicesystem.service.TemplateBindService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;
import com.cetc.iot.servicesystem.service.VePeBindService;
import com.cetc.iot.servicesystem.util.DataLoopReceiverForAtomVe;
import com.cetc.iot.servicesystem.util.GetBean;

public class AtomVeExecute {
	
	private static Logger logger = Logger.getLogger(AtomVeExecute.class.getName());
	
	/*@Autowired
	private VePeBindService vepebindservice;*/
	@Autowired
	private TemplateBindService templateBindService;
	@Autowired
	private PEParamService peParamService;
	public JSONObject message;
	public AtomVeExecute(JSONObject message){
		this.message = message;
	}
   
	//可以写成单例模式
	public void doService(){
		String veId = message.getString("veId");
		String serviceId = message.getString("serviceId");
		String clientId = message.getString("clientId");
		//veid找peid
		VePeBind vpb = new VePeBind();
		vpb.setVe_id(veId);
		 VePeBindService vepebindservice = (VePeBindService) GetBean.getBean("bindService");
		//VePeBindService vepebindservice=new VePeBindServiceImpl();
		List<VePeBind> list1 = vepebindservice.query(vpb, 0, 1);
		if(list1.size()==0){
			CallCenterSession.send(clientId, "veid not found in the vepebind table");
			return;
		}
		VePeBind v = list1.get(0);
		String peId = v.getPe_id(); 
		//tongguoserviceId找该
		VeWebsocketService veWebsocketService = new VeWebsocketService();
		veWebsocketService.setService_id(serviceId);
		VEWebsocketServiceService vs = (VEWebsocketServiceService) GetBean.getBean("veWebsocketServiceService");
		List<VeWebsocketService> list = vs.query(veWebsocketService, 0, 1);
		if(list.size()==0){
			CallCenterSession.send(clientId, "serviceId not found");
			return;
		}else{
			
			VeWebsocketService temp = list.get(0);
			String servicename = temp.getService_name();
			if(!servicename.contains("_")){
				if("getPeAllParameters".equalsIgnoreCase(servicename)){
					//调用汪少接口
					PeParam peparam = new PeParam();
					peparam.setPe_id(peId);
					peparam.setContent(null);
					peparam.setKey(null);
					peparam.setPe_param_id(-1);
					peparam.setTemplate_param_id(null);
					List<Map<String,Object>> peparamList = peParamService.query(peparam, 0, 100);
				    JSONArray json = JSONArray.fromObject(peparamList);
					String returnmessage = json.toString();
					CallCenterSession.send(clientId, returnmessage);
				}else if("getPeSpecificParameter".equalsIgnoreCase(servicename)){
					//调用汪少接口
					JSONObject param = (JSONObject) message.getJSONObject("param");
					String key = param.getString("key");
					PeParam peparam = new PeParam();
					peparam.setPe_id(peId);
					peparam.setContent(null);
					peparam.setKey(key);
					peparam.setPe_param_id(-1);
					peparam.setTemplate_param_id(null);
					List<Map<String,Object>> peparamList = peParamService.query(peparam, 0, 100);
					 JSONArray json = JSONArray.fromObject(peparamList);
						String returnmessage = json.toString();
						CallCenterSession.send(clientId, returnmessage);
				
				}else{
					return;
				}
			}else{

				String a[] = servicename.split("_");
				String flag = a[a.length-1];
				String interfaceId = servicename.substring(0, servicename.length()-flag.length()-1);
				JSONObject param = (JSONObject) message.getJSONObject("param");
				
				if(flag.equalsIgnoreCase("up")){
					//数据上报
					if("on".equals(param.get("subscribe"))){
						logger.info("VE receive open channel command! ");
						
						if (PEOperationForAtomicVE.openData(clientId,peId,interfaceId).equalsIgnoreCase("success")){
							logger.info("=====VE OPEN DATA OK=====");
						    new DataLoopReceiverForAtomVe(clientId, peId,interfaceId).start();
						} else {
							logger.info("=====VE OPEN DATA ERROR=====");
							CallCenterSession.send(clientId, "subscribe fail!");
						}
					} else if("off".equals(param.get("subscribe"))){
						logger.info("VE receive close channel command!");
						if (PEOperationForAtomicVE.closeData(clientId, peId,interfaceId).equalsIgnoreCase("success")){
							CallCenterSession.send(clientId, "unsubscribe success!");
						} else {
							CallCenterSession.send(clientId, "unsubscribe fail!");
						}
					}

				}else if(flag.equalsIgnoreCase("down")){
					//操控
						PEOperationForAtomicVE.deviceControl(peId, interfaceId, param.toString());
						//CallCenterSession.send(clientId, "运行成功!");
				}else if(flag.equalsIgnoreCase("bidirection")){
				String message =	PEOperationForAtomicVE.deviceControlRequestResponse(peId, interfaceId, param.toString());
				CallCenterSession.send(clientId, message);
					
				}else{
					CallCenterSession.send(clientId, "interface_channel is null or some other mistake");
				}	
			}
			
			//else 结束
		}
	}
	
}

