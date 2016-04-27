package com.cetc.iot.accesssystem.downinterface.protocol.base;

import com.cetc.iot.util.SystemConfig;

/**
 * this class is used for analyzing the tail message
 * @author xzc
 *
 */
public class Tail {
	
	private static final int CHECK_LENGTH = Integer.parseInt(SystemConfig.getValue("CHECK_LENGTH"));
	
	public String check;
	
	public Tail(String message,Head head){
		char safeTag = head.safeWords.charAt(0);
		if ((safeTag&128) == 0){// if the message has no tail
			this.check = "";
		}
		else {
			this.check = message.substring(head.totalLength-CHECK_LENGTH, head.totalLength);
		}
	}
	
	
}
