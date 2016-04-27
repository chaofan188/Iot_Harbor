package com.cetc.iot.servicesystem.service.impl;

import javax.websocket.*;  
import javax.websocket.server.ServerEndpoint;  
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.middle.PEOperationForAtomicVE;
import com.cetc.iot.servicesystem.service.ServiceProcessor;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;
import com.cetc.iot.servicesystem.service.VePeBindService;
import com.cetc.iot.servicesystem.service.impl.VeProcessor;
import com.cetc.iot.servicesystem.util.ConvertVeId;
import com.cetc.iot.servicesystem.util.DataLoopReceiverNew;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.servicesystem.util.MyException;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.IOException;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/websocketInterface") 

public class CallCenterSession {
	
	public static  Map<String,Session> socketList = new HashMap<String,Session>();
	public  static Multimap<String, String> veidClientIdMappingMap=ArrayListMultimap.create();
	public  static Multimap<String, String> atomVeidClientIdMappingMap=ArrayListMultimap.create();
	//多值map赋初值 防止出现null
		static{
			veidClientIdMappingMap.put("1", "1");
			atomVeidClientIdMappingMap.put("2", "2");
		}
		public  static Map<String, String> ClientIdveidMappingMap =new HashMap<String,String>();

		private String clientId;
		private static ServiceProcessor serviceProcessor;
	    private static Logger logger = Logger.getLogger(CallCenterSession.class.getName());
		public JSONObject globleMessage;
	    private Session session;
	    public static  boolean send(String clientId,String message){
		 Session session = socketList.get(clientId);
		 try {
			if(session==null){
				message ="messageInbond does not exit,maybe you has refresh your broswer or has closed it";
			
			} 
			 session.getBasicRemote().sendText(message);
			return true;
		} catch (Exception e) {
			logger.error("the Usage FROM clientId"+clientId+" throws an Exception:"+e.getMessage());
			e.printStackTrace();
			PEOperationForAtomicVE.closeData(clientId);
			return false;
		}
	 }
	    @OnOpen  
	    public void open(Session session) { 
	    	this.session = session;
		    clientId = new String(Double.toString(Math.random()));
		    logger.info("web socket open! "+" this connection's clientId:"+clientId);
			socketList.put(clientId, session);
			logger.info(clientId + " socket open! ");
	         
	    }  
	      

	    @OnMessage  
	    public void OnMessage(String message) throws IOException {
	        			
	        JSONObject jsonMessage = JSONObject.fromObject(message);
	        globleMessage = jsonMessage;
	        logger.info("!!!!!: "+globleMessage.toString());
	        			
	        String veId = (String) jsonMessage.get("veId");
	        veId = ConvertVeId.simplifyVeId(veId);
	        //Make veid relate to clientId ,which used to shut down ve service through clientId
	        if(veidClientIdMappingMap.containsEntry(veId, clientId)||atomVeidClientIdMappingMap.containsEntry(veId, clientId)){
	        	//do nothing
	        }else{
	        if(jsonMessage.get("is_atom").equals("1")){
	        	atomVeidClientIdMappingMap.put(veId, clientId);
	        		}else{
	       	veidClientIdMappingMap.put(veId, clientId);
	        		}
	      logger.info("关浏览器前的MAP: "+veidClientIdMappingMap.toString()+atomVeidClientIdMappingMap.toString());
	        				ClientIdveidMappingMap.put(clientId,veId);		
	        			}
	        			
	        			
	    jsonMessage.accumulate("clientId", clientId);
	  		jsonMessage.put("veId", veId);
	    	String messageFromJson = jsonMessage.toString();
	      	
	       	//消息将走filter
	       	try {
	       		session.getBasicRemote().sendText("diao!!!!!!");
	      		serviceProcessor =(VeProcessor)GetBean.getBean("ServiceProcessor");
	   		serviceProcessor.process(messageFromJson);
	       	} catch (MyException e) {
	      		//System.out.println(e.getMessage());
	        	String exceptionMessage = e.getMessage();
	        	session.getBasicRemote().sendText(exceptionMessage);
	      	}
	        		
	    }  
	      
	    @OnClose  
	    public void Onclose() {
			logger.info("the connection of clentId:"+clientId+" web socket closed ");
			PEOperationForAtomicVE.closeData(clientId);
			//这里关闭原子VE的数据推送
			if(globleMessage!=null&&globleMessage.containsKey("veId")&&globleMessage.containsKey("seviceId")){
				String veId = globleMessage.getString("veId");
				String serviceId = globleMessage.getString("serviceId");
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
					String a[] = servicename.split("_");
					String interfaceId = a[0];
					PEOperationForAtomicVE.closeData(clientId, peId,interfaceId);
			}
			
			//关闭原子VE的数据推送结束
			DataLoopReceiverNew.unsubscribe(clientId);
			//保留socketList.remove(clientId) 因为操控类不走unsubscribe方法
			socketList.remove(clientId);
			if(ClientIdveidMappingMap.containsKey(clientId)){
				String veid = ClientIdveidMappingMap.get(clientId);
				atomVeidClientIdMappingMap.remove(veid, clientId);
				veidClientIdMappingMap.remove(veid, clientId);
				ClientIdveidMappingMap.remove(clientId);
				logger.info("关浏览器后的MAP: "+veidClientIdMappingMap.toString()+atomVeidClientIdMappingMap.toString());
			}
			logger.info(clientId + " socket close! ");
		  }
		}  
	      
	 
	

}

