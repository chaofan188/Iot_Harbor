package com.cetc.iot.servicesystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.cetc.iot.middle.PEOperationForAtomicVE;
import com.cetc.iot.servicesystem.service.impl.CallCenterSession;

public class DataLoopReceiverForAtomVe extends Thread {

	private static Logger logger = Logger
			.getLogger(DataLoopReceiverForAtomVe.class.getName());
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SS");
	private String peID;
	private String clientId;
	private String interfaceId;

	public DataLoopReceiverForAtomVe(String clientId, String peID,
			String interfaceId) {
		this.peID = peID;
		this.clientId = clientId;
		this.interfaceId = interfaceId;
	}

	@Override
	public void run() {
		if (PEOperationForAtomicVE.createNewThread(clientId, peID, interfaceId,
				this)) {
			System.out.println("this thread should not be created! ");
			return;
		}
		logger.info("Create a new thread: "+this.hashCode());

		while (true) {
			logger.info("thread: "+this.hashCode());
			String receiveMessage = PEOperationForAtomicVE.getDataAndFlush(
					clientId, peID, interfaceId);
			if (receiveMessage == null) {
				logger.info("==============null=============");
				break;
			} else if (receiveMessage.startsWith("null")) {
				logger.info("==========exception===========");
				PEOperationForAtomicVE.closeData(peID, clientId, interfaceId);
				break;
			}
			JSONObject Message2Broswer = JSONObject.fromObject(receiveMessage);
			logger.info("Receive Message: "+receiveMessage);
			Message2Broswer.put("time", FORMAT.format(new Date()));
			// 向前台推数据
			if (!CallCenterSession.send(clientId,
					Message2Broswer.toString())) {
				logger.info("=========== Thread Break==========");
				break;
			}
		}

	}

}
