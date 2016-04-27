package com.cetc.iot.accesssystem.downinterface.socket.handling;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.upinterface.PEData;
/**
 *  this class is used to format receving messages
 * @author xzc
 * Create Time: 2014-09-09
 * Author: xzc
 * Details: Add constructor to format receving messages
 *          Add msgToPeData function for changing messages to PeData format
 */
public class MsgRecvHandling {
	
	private static Logger logger = Logger.getLogger(MsgRecvHandling.class.getName());
	
	/**
	 * messages safe tag
	 */
	private boolean isSafe = false;
	/**
	 * peID
	 */
	private String peID = null;
	/**
	 * protocol version
	 */
	private String version = null;
	/**
	 * interfaceID
	 */
	private String interfaceID = null;
	/**
	 * tells whether the message is a response message
	 */
	private boolean isResponse = false;
	/**
	 * sessionID (if the message needs response message, then sessionID is useful)
	 */
	private String sessionID = null;
	/**
	 * tells whether the message is a data message
	 */
	private boolean isData = false;
	/**
	 * tells whether the message needs response message
	 */
	private boolean requireResponse = false;
	/**
	 * status
	 */
	private String status = null;
	/**
	 * location information
	 */
	private String location = null;
	/**
	 * operation
	 */
	private String operation = null;
	/**
	 * object
	 */
	private String object = null;
	/**
	 * date
	 */
	private String date = null;
	/**
	 * message length
	 */
	private int length = 0;
	/**
	 * the real data information
	 */
	private JSONObject data = null;
	
	public String getPEID(){
		return this.peID;
	}
	
	public String getVersion(){
		return this.version;
	}
	
	public boolean isSafe(){
		return this.isSafe;
	}
	
	public void setIsSafe(boolean isSafe){
		this.isSafe = isSafe;
	}
	
	public String getInterfaceID(){
		return this.interfaceID;
	}
	
	public boolean isResponse(){
		return this.isResponse;
	}
	
	public String getSessionID(){
		return this.sessionID;
	}
	
	public boolean isData(){
		return this.isData;
	}
	
	public boolean requireResponse(){
		return this.requireResponse;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public String getOperation(){
		return this.operation;
	}
	
	public String getObject(){
		return this.object;
	}
	public int getLength(){
		return this.length;
	}
	public String getDate(){
		return this.date;
	}
	public JSONObject getData(){
		return this.data;
	}
	/**
	 * constructor: format received messages
	 * @param msg received messages
	 */
	public MsgRecvHandling(String msg){
		this.isSafe = true;
		try {
			JSONObject recvJson = JSONObject.fromObject(msg);
			this.peID = recvJson.getString("Address");
			if ((null == peID)||("null".equalsIgnoreCase(peID))){
				logger.info("peID null in message! ");
				throw new Exception("ERROR: peID null");
			}
			//safe tag needs checking here
			

			//Check whether the message is from gateway or pe
			if (peID.equalsIgnoreCase("gateway")) {
				this.data = recvJson.containsKey("Data")? recvJson.getJSONObject("Data"):null;
			} else {
				this.version = recvJson.containsKey("Version")? recvJson.getString("Version"):null;
				this.interfaceID = recvJson.containsKey("IFID")? recvJson.getString("IFID"):null;
				this.length = recvJson.containsKey("Length")? recvJson.getInt("Length"):0;
				this.isData = recvJson.containsKey("IsData")? recvJson.getBoolean("IsData"):false;
				this.isResponse = recvJson.containsKey("IsResponse")? recvJson.getBoolean("IsResponse"):false;
				this.sessionID = recvJson.containsKey("SessionID")? recvJson.getString("SessionID"):null;
				this.requireResponse = recvJson.containsKey("RequireResponse")? recvJson.getBoolean("RequireResponse"):false;
				this.status = recvJson.containsKey("Status")? recvJson.getString("Status"):null;
				this.location = recvJson.containsKey("Location")? recvJson.getString("Location"):null;
				this.operation = recvJson.containsKey("Operation")? recvJson.getString("Operation"):null;
				this.object = recvJson.containsKey("Object")? recvJson.getString("Object"):null;
				this.date = recvJson.containsKey("Date")? recvJson.getString("Date"):null;
				this.data = recvJson.containsKey("Data")? recvJson.getJSONObject("Data"):null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("WARNING: wrong type message received, message is: "+msg);
			this.isSafe = false;
		}

	}
	
	/**
	 * packet the msgRecvHandling Object to PEData Object
	 * @return
	 */
	public PEData msgToPEData(){
		PEData result = new PEData();
		result.data = this.getData().toString();
		result.interfaceID = this.getInterfaceID();
		result.isData = this.isData();
		result.isResponse = this.isResponse();
		result.length = this.getLength();
		result.peID = this.getPEID();
		result.requireResponse = this.requireResponse();
		result.sessionID = this.getSessionID();
		return result;
	}
	
}
