package com.cetc.iot.accesssystem.downinterface.protocol.base;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.downinterface.protocol.base.service.PeProtocolConfigService;
import com.cetc.iot.database.model.PeProtocolConfig;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.util.StringOperation;
import com.cetc.iot.util.SystemConfig;
import com.cetc.iot.util.aes.AESCoder;
import com.cetc.iot.util.md5.MD5Coder;

/**
 * this class is used for creating packets.
 * @author xzc
 * Create Time: 2015-04-20
 * Author: xzc
 * Details: Add create function to create packet
 *          Add createSeq function to create seq
 */
public class PacketCreate {
	
	private static Logger logger = Logger.getLogger(PacketCreate.class.getName());
	
	private PeProtocolConfigService peProtocolConfigService = (PeProtocolConfigService)GetBean.getBean("peProtocolConfigService");
	
	private static final int TOTAL_LENGTH_LENGTH = Integer.parseInt(SystemConfig.getValue("TOTAL_LENGTH_LENGTH"));
	private static final int PEID_LENGTH = Integer.parseInt(SystemConfig.getValue("PEID_LENGTH"));
	private static final int VERSION_LENGTH = Integer.parseInt(SystemConfig.getValue("VERSION_LENGTH"));
	private static final int COMMANDID_LENGTH = Integer.parseInt(SystemConfig.getValue("COMMANDID_LENGTH"));
	private static final int SEQ_LENGTH = Integer.parseInt(SystemConfig.getValue("SEQ_LENGTH"));
	private static final int SAFE_WORDS_LENGTH = Integer.parseInt(SystemConfig.getValue("SAFE_WORDS_LENGTH"));
	private static final int KEEP_WORDS_LENGTH = Integer.parseInt(SystemConfig.getValue("KEEP_WORDS_LENGTH"));
	private static final int HEAD_LENGTH = Integer.parseInt(SystemConfig.getValue("HEAD_LENGTH"));
	private static final int CHECK_LENGTH = Integer.parseInt(SystemConfig.getValue("CHECK_LENGTH"));
	private static final String DEFAULT_VERSION = StringOperation.hexStringToString(SystemConfig.getValue("DEFAULT_VERSION"));
	private static final String ENCODING = "ISO-8859-1";

	private String peID;
	private String commandID;
	private String safeWords;
	private String keepWords;
	private String body;
    public String message;
	public String seq;

	/**
	 * constructor
	 * 
	 * @param peID
	 *            type string
	 * @param commandID
	 *            type hex string
	 * @param safeWords
	 *            type string
	 * @param keepWords
	 *            type hex string
	 * @param body
	 *            type string
	 */
	public PacketCreate(String peID, String commandID, String safeWords,
			String keepWords, String body) {
		this.peID = peID;
		this.commandID = commandID;
		this.safeWords = safeWords;
		this.keepWords = keepWords;
		this.body = body;
	}

	/**
	 * create the message
	 * @param sessionKey
	 * @param timeStamp
	 * @param seqString
	 * @param peProtocolConfig
	 * @return success or fail
	 */
	public boolean create(String sessionKey,String timeStamp,String seqString,PeProtocolConfig peProtocolConfig) {
			if (seqString == null ||"".equalsIgnoreCase(seqString) ){
				this.createSeq();
			}
			else {
				seq = seqString;
			}
			String head = peID + DEFAULT_VERSION
					+ StringOperation.hexStringToString(commandID) + seq
					+ safeWords + StringOperation.hexStringToString(keepWords);
			if (head.length() != (HEAD_LENGTH-TOTAL_LENGTH_LENGTH)){
				return false;
			}
			if (peProtocolConfig == null){
				peProtocolConfig = peProtocolConfigService.query(peID);
				if (peProtocolConfig == null){
					return false;
				}
			}
			char safeTag = safeWords.charAt(0);
			if (!(body == null||"".equalsIgnoreCase(body))){
				try {
					if ((safeTag&64) != 0){
						byte[] sessionKeyByte = Base64.decodeBase64(sessionKey);
						byte[] secret = AESCoder.encrypt(body.getBytes(ENCODING), sessionKeyByte);
						body = new String(secret,ENCODING);
					}
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			else {
				body = "";
			}
			String tail = "";
			if ((safeTag&128) != 0){//check whether the message has tail
				int totalLength = HEAD_LENGTH + body.length() + CHECK_LENGTH;
				String totalHexString = Integer.toHexString(totalLength);
				int length = totalHexString.length();
				switch (length){
				case 1:
					totalHexString = "000"+totalHexString;
					break;
				case 2:
					totalHexString = "00"+totalHexString;
					break;
				case 3:
					totalHexString = "0"+totalHexString;
					break;
				case 4:
					break;
				default :
					return false;
				}
				String totalLengthString = StringOperation.hexStringToString(totalHexString);
				head = totalLengthString + head;
				try {
					//logger.info("Under Check: "+StringOperation.stringToHexString(head+body+timeStamp+peProtocolConfig.getDownPassword()));
					String check = MD5Coder.encodeMD5Hex(head + body + timeStamp + peProtocolConfig.getDownPassword());
					tail = StringOperation.hexStringToString(check);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			else {
				int totalLength = HEAD_LENGTH + body.length();
				String totalHexString = Integer.toHexString(totalLength);
				int length = totalHexString.length();
				switch (length){
				case 1:
					totalHexString = "000"+totalHexString;
					break;
				case 2:
					totalHexString = "00"+totalHexString;
					break;
				case 3:
					totalHexString = "0"+totalHexString;
					break;
				case 4:
					break;
				default :
					return false;
				}
				String totalLengthString = StringOperation.hexStringToString(totalHexString);
				head = totalLengthString + head;
			}
			message = head + body + tail;
			return true;
	}

	/**
	 * this method is used to create seqNum for sending messages
	 * the result will be put in String seq
	 */
	private void createSeq() {
		long time = System.currentTimeMillis();
		String timeHex = Long.toHexString(time);
		String seqHex = null;
		if (timeHex.length() >= SEQ_LENGTH*2){
			seqHex = timeHex.substring(timeHex.length()-8,timeHex.length());
		}
		else {
			int temp = SEQ_LENGTH*2-timeHex.length();
			seqHex = timeHex;
			while (temp > 0){
				seqHex = "0"+seqHex;
				temp--;
			}
		}
		seq = StringOperation.hexStringToString(seqHex);
	}

}
