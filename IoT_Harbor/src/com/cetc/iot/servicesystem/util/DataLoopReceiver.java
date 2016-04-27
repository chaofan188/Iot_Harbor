package com.cetc.iot.servicesystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.cetc.iot.servicesystem.service.impl.CallCenterSession;
import com.cetc.iot.util.GetData;

public class DataLoopReceiver implements Runnable {
	
	private static Logger logger = Logger.getLogger(DataLoopReceiver.class.getName());
	
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
	 private String peID; 
	 private String clientId;
	 private  JSONObject MessageFromBroswer;
	public DataLoopReceiver(String clientId,String peID,JSONObject MessageFromBroswer){    
		this.peID = peID;
		this.clientId = clientId;
		this.MessageFromBroswer=MessageFromBroswer;
	}
	
	
	
	@Override
	public void run() {
		EventPusher eventPusher = new EventPusher(this.MessageFromBroswer);
		while(true){
			String receiveMessage  = GetData.getDataAndFlush(peID, clientId);
			if (receiveMessage == null){
				logger.info("null!!!!!!!!!!!!!");
				continue;
			}
			else if (receiveMessage.startsWith("null")){
				logger.info("exception!!!!!!!");
				GetData.closeData(peID, clientId);
				break;
			}
			JSONObject Message2Broswer = JSONObject.fromObject(receiveMessage);
			logger.info("Receive Message: "+receiveMessage);
			Message2Broswer.put("time", FORMAT.format(new Date()));
			//增加 事件 机制        
			eventPusher.eventPushDealer(Message2Broswer);
			//向前台推数据
        CallCenterSession.send(clientId, Message2Broswer.toString());
		}
	}

}
