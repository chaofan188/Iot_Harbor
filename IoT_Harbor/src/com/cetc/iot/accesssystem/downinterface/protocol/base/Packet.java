package com.cetc.iot.accesssystem.downinterface.protocol.base;

import java.util.concurrent.locks.ReadWriteLock;

import org.apache.log4j.Logger;

import com.cetc.iot.database.model.PeProtocolConfig;
import com.cetc.iot.util.StringOperation;
import com.cetc.iot.util.md5.MD5Coder;
import com.google.common.collect.Multimap;



public class Packet {
	
	private static Logger logger = Logger.getLogger(Packet.class.getName());

	
	
	public Head head;
	public Body body;
	public Tail tail;
	public boolean isSafe = true;
	
	public Packet(String message){
		this.head = new Head(message);
		this.body = new Body(message, head);
		this.tail = new Tail(message, head);
	}
	
	/**
	 * check the whether the MD5 is correct
	 * @param timeStamp
	 * @param peProtocolConfig
	 */
	public void checkMD5(String timeStamp,PeProtocolConfig peProtocolConfig){
		if (isSafe){
			if ("0001".equals(StringOperation.stringToHexString(head.commandID))){
				timeStamp = StringOperation.hexStringToString("00000000");
			}
			if (!("").equals(tail.check)){
				if (peProtocolConfig == null){
					isSafe = false;
				} else {
					String platformCheck = null;
					String tempCheckString  = head.headMessage+body.bodyMessage+timeStamp+peProtocolConfig.getUpPassword();
					//logger.info("MD5: "+StringOperation.stringToHexString(tempCheckString));
					platformCheck = MD5Coder.encodeMD5Hex(tempCheckString);
					if (!tail.check.equals(StringOperation.hexStringToString(platformCheck))){
						isSafe = false;
					}
				}
			} else {
				if (!("0003".equals(StringOperation.stringToHexString(head.commandID)))){//only heart_beat messages could have no tail
						isSafe = false;
				}		
			}
		}
	}
	
	/**
	 * check whether the sequence is correct
	 * @param pe_sendSeq_map
	 * @param recvSeq
	 * @param lock
	 */
	public void checkSeq(Multimap<String, String> pe_sendSeq_map,String recvSeq,ReadWriteLock lock){
		if (isSafe){
			String temp = StringOperation.stringToHexString(head.commandID);
			if (temp.startsWith("0")){
				if (StringOperation.stringToAscIILong(head.seq) <= StringOperation.stringToAscIILong(recvSeq)){
					isSafe = false;
				}
			} else if (temp.startsWith("8")){
				lock.readLock().lock();
				if (!pe_sendSeq_map.containsEntry(head.peID, head.seq)) {//check whether the seq is exists!
					isSafe = false;
				}
				lock.readLock().unlock();
			} else {
				isSafe = false;
			}
		}
	}
}
