package com.cetc.iot.servicesystem.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.communicate.PEConnector4VE;
import com.cetc.iot.servicesystem.service.VeObject;
import com.cetc.iot.servicesystem.service.impl.CallCenterSession;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class DataLoopReceiverNew {
	private static Logger logger = Logger.getLogger(DataLoopReceiverNew.class.getName());
	
	public  static Multimap<String, String> veidsubscribeIDMappingMap=ArrayListMultimap.create();
	public  static Map<String, String> subscribeIDveidMappingMap =new HashMap<String,String>();

	public static String subscribe(String veID ,String peId,String interfaceId,final String clientId){
		try {
			logger.info("VE订阅 PEID: "+peId + " interfaceId: "+interfaceId);
			  // DataLoopReceiverNew aa = new DataLoopReceiverNew();
			String subscribeID = PEConnector4VE.subscribe(peId, interfaceId, new MessageListener() {
				
				@Override
				public void onMessage(Message msg) {
					// TODO Auto-generated method stub
					ObjectMessage message = (ObjectMessage) msg;
					PEData data = null;
					try {
						data = (PEData) message.getObject();
						//String Message2Broswer = "当前时间："+GetFormatDate.getFormatDate(new java.util.Date())+"数据："+data.data;
						String Message2Broswer = data.data;
						CallCenterSession.send(clientId, Message2Broswer);
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logger.info("VE接收到订阅数据Message: "+data.data);
				}
			} );
			veidsubscribeIDMappingMap.put(veID,subscribeID);
			subscribeIDveidMappingMap.put(subscribeID, veID);
			return subscribeID;
		} catch (JMSException e) {
			e.printStackTrace();
			return "error";
		}
	}
	public static String eventSubscribe(String veID ,String peId,String interfaceId,final String clientId,final JSONObject param){
		try {
			logger.info("VE订阅 peID： "+peId + " interfaceId: "+interfaceId);
			String subscribeID = PEConnector4VE.subscribe(peId, interfaceId, new MessageListener() {
				@Override
				public void onMessage(Message msg) {
					// TODO Auto-generated method stub
					ObjectMessage message = (ObjectMessage) msg;
					PEData data = null;
					try {
						data = (PEData) message.getObject();
						float temperature = Float.parseFloat((JSONObject.fromObject(data.data).get("temperature").toString()));
						//EventSubscriber.eventSubscribeHelper(param,temperature);
						if(EventSubscriber.eventSubscribeHelper(param,temperature)){
							CallCenterSession.send(clientId, "温度已到达订阅区间！");
							CallCenterSession.socketList.remove(clientId);
							unsubscribe(clientId);
						}
						
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logger.info("VE接收到订阅数据Message: "+data.data);
				}
			} );
			veidsubscribeIDMappingMap.put(veID,subscribeID);
			subscribeIDveidMappingMap.put(subscribeID, veID);
			return subscribeID;
		} catch (JMSException e) {
			e.printStackTrace();
			return "error";
		}
	}
	public static void unsubscribe(String clientId){
		try {
			String veid = CallCenterSession.ClientIdveidMappingMap.get(clientId);
			Collection<String> subscribeIDCollection = veidsubscribeIDMappingMap.get(veid);
			
			CallCenterSession.ClientIdveidMappingMap.remove(clientId);
			CallCenterSession.veidClientIdMappingMap.remove(veid, clientId);
			logger.info("off 后 veidClientIdMappingMap: "+CallCenterSession.ClientIdveidMappingMap.toString());
			if(subscribeIDCollection!=null){
				
				if(CallCenterSession.veidClientIdMappingMap.containsKey(veid)){
					CallCenterSession.socketList.remove(clientId);
				}else{
					for(String subscribeID:subscribeIDCollection){
						PEConnector4VE.unsubscribe(subscribeID);
						subscribeIDveidMappingMap.remove(subscribeID);
						veidsubscribeIDMappingMap.remove(veid, subscribeID);
					}
			    }
				
			}
			 
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public static void send(String peID, String interfaceId, String data){
		logger.info("xzy send data!!!!");
		logger.info("peID: "+peID);
		logger.info("interfaceID: "+interfaceId);
		logger.info("data: "+data);
		try {
			PEConnector4VE.send(peID, interfaceId, data);
			logger.info("Control command has been issued! ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String sendWithResponse(String peID, String interfaceId, String data){
		logger.info("xzy send data2!!!");
		logger.info("peID: "+peID);
		logger.info("interfaceID: "+interfaceId);
		logger.info("data: "+data);
		try {
			PEData p = PEConnector4VE.sendWithResponse(peID, interfaceId, data);
			if(p != null){
				logger.info("VE接收数据在PE: "+p.peID + ":" + p.data);
				return p.data;
			} else {
				logger.error("PEData is empty! ");
				return "error";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	public static String receiveData(String veID ,String peId,String interfaceId,final VeObject veObject){
		try {
			logger.info("VE订阅 peID: "+peId + " interfaceId: "+interfaceId);
			   //DataLoopReceiverNew aa = new DataLoopReceiverNew();
			String subscribeID = PEConnector4VE.subscribe(peId, interfaceId, new MessageListener() {
				@Override
				public void onMessage(Message msg) {
					// TODO Auto-generated method stub
					ObjectMessage message = (ObjectMessage) msg;
					PEData data = null;
					try {
						Map<String,Object> map =new HashMap<String,Object>();
						data = (PEData) message.getObject();
						map.put("peID", data.peID);
						map.put("IFID", data.interfaceID);
						map.put("Data", data.data);
						JSONObject json = JSONObject.fromObject(map);
						//String Message2Broswer = "当前时间："+GetFormatDate.getFormatDate(new java.util.Date())+"数据："+data.data;
						//String Message2Broswer = data.data;
						//CallCenterSession.send(clientId, Message2Broswer);
						veObject.receive(json.toString());
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logger.info("VE接收到订阅数据Message: "+data.data);
				}
			} );
			veidsubscribeIDMappingMap.put(veID,subscribeID);
			subscribeIDveidMappingMap.put(subscribeID, veID);
			return subscribeID;
		} catch (JMSException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
}
