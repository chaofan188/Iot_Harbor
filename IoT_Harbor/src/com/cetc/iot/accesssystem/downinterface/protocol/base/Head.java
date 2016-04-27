package com.cetc.iot.accesssystem.downinterface.protocol.base;

import com.cetc.iot.util.StringOperation;
import com.cetc.iot.util.SystemConfig;
/**
 * this class is head of the packet
 * @author xzc
 * Create Time: 2015-04-01
 *
 */
public class Head {
	
	private static final int TOTAL_LENGTH_LENGTH = Integer.parseInt(SystemConfig.getValue("TOTAL_LENGTH_LENGTH"));
	private static final int PEID_LENGTH = Integer.parseInt(SystemConfig.getValue("PEID_LENGTH"));
	private static final int VERSION_LENGTH = Integer.parseInt(SystemConfig.getValue("VERSION_LENGTH"));
	private static final int COMMANDID_LENGTH = Integer.parseInt(SystemConfig.getValue("COMMANDID_LENGTH"));
	private static final int SEQ_LENGTH = Integer.parseInt(SystemConfig.getValue("SEQ_LENGTH"));
	private static final int SAFE_WORDS_LENGTH = Integer.parseInt(SystemConfig.getValue("SAFE_WORDS_LENGTH"));
	private static final int KEEP_WORDS_LENGTH = Integer.parseInt(SystemConfig.getValue("KEEP_WORDS_LENGTH"));
	private static final int HEAD_LENGTH = Integer.parseInt(SystemConfig.getValue("HEAD_LENGTH"));
	
	public int totalLength ;
	public String peID ;
	public String version ;
	public String commandID ;
	public String seq ;
	public String safeWords ;
	public String keepWordS ;
	public String headMessage;
	
	public Head(String message){
		this.headMessage = message.substring(0,HEAD_LENGTH);
		int index = 0;
		String totalLengthString = message.substring(index,index+TOTAL_LENGTH_LENGTH);
		this.totalLength = StringOperation.stringToAscIIInt(totalLengthString);
		index = index + TOTAL_LENGTH_LENGTH;
		this.peID = message.substring(index,index+PEID_LENGTH);
		index = index + PEID_LENGTH;
		this.version = message.substring(index,index+VERSION_LENGTH);
		index = index + VERSION_LENGTH;
		this.commandID = message.substring(index,index+COMMANDID_LENGTH);
		index = index + COMMANDID_LENGTH;
		this.seq = message.substring(index,index+SEQ_LENGTH);
		index = index + SEQ_LENGTH;
		this.safeWords = message.substring(index,index+SAFE_WORDS_LENGTH);
		index = index + SAFE_WORDS_LENGTH;
		this.keepWordS = message.substring(index,index+KEEP_WORDS_LENGTH);
	}
}
