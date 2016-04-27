package com.cetc.iot.middle;

import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.communicate.PESession;

public class PEOperationForActiveMQ implements Runnable{
	
	private static Logger logger = Logger.getLogger(PEOperationForActiveMQ.class.getName());
	
	private PEData peData;
	private PESession peSession;
	public PEOperationForActiveMQ(PEData peData, PESession peSession){
		this.peData = peData;
		this.peSession = peSession;
	}
	public void run(){
		try {
			new UpperOperation().msgReceive(peData,peSession);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Exception At Sending Information to ActiveMQ! ");
			e.printStackTrace();
		}
	}
}
