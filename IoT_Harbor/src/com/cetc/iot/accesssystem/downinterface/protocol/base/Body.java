package com.cetc.iot.accesssystem.downinterface.protocol.base;

import com.cetc.iot.util.SystemConfig;

/**
 * this class is used for analyzing the body message
 * @author xzc
 *
 */
public class Body {
	
	private static final int HEAD_LENGTH = Integer.parseInt(SystemConfig.getValue("HEAD_LENGTH"));
	private static final int CHECK_LENGTH = Integer.parseInt(SystemConfig.getValue("CHECK_LENGTH"));
	
	public String bodyMessage ;
	
	public Body(String message,Head head){
		char safeTag = head.safeWords.charAt(0);
		if ((safeTag&128) == 0){//if the message do not have tail
			this.bodyMessage = message.substring(HEAD_LENGTH, head.totalLength);
		}
		else {
			this.bodyMessage = message.substring(HEAD_LENGTH,head.totalLength-CHECK_LENGTH);
		}
	}
}
