package com.cetc.iot.communicate;

import javax.jms.JMSException;
import javax.jms.MessageListener;

import com.cetc.iot.accesssystem.upinterface.PEData;

/* This Class is the interface Class which used to accomplish communication
 * Job between PE and VE.
 * 
 * @author wanghan
 * 
 * Create Time:4/2/2015
 */

public class PEConnector4VE {

	static PEConnectorManager manager = PEConnectorManager.getInstance();// TODO:改为用spring注入

//	PEConnectorManager manager;
	
	/*
	 * 
	 * 
	 */
	public static PEData sendWithResponse(String peID, String interfaceID, String data)
			throws JMSException {

		return manager.send(peID, interfaceID, data);
		
	};

	public static void send(String peID, String interfaceID, String data){
		manager.sendNoResponse(peID, interfaceID, data);
	}
	/*
	 * 
	 */
	public static String subscribe(String peID, String interfaceID,
			MessageListener listener) throws JMSException {

		return manager.subscribe(peID, interfaceID, listener);
	}
	
	public static int unsubscribe(String subscribeID) throws JMSException{
		
		manager.unsubscribe(subscribeID);
		
		return 0;
	}
}
