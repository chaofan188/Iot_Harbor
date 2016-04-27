package com.cetc.iot.accesssystem.downinterface.protocol.base;

import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.downinterface.protocol.base.service.PeProtocolConfigService;
import com.cetc.iot.accesssystem.downinterface.socket.commmunication.ServerUtilNew;
import com.cetc.iot.accesssystem.downinterface.socket.service.SocketPeService;
import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.communicate.PESession;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.database.model.PeProtocolConfig;
import com.cetc.iot.middle.PEOperationForActiveMQ;
import com.cetc.iot.middle.PEOperationForAtomicVE;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.util.StringOperation;
import com.cetc.iot.util.aes.AESCoder;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * this class is PE AutoMachine
 * @author xzc
 * CreateTime: 2015-04-05
 * Author: xzc
 * Details: Add getPeAutoMachine function for getting object
 *          Add two action functions for upper and terminal
 *
 */
public class AutoMachine {
	private static Logger logger = Logger.getLogger(AutoMachine.class.getName());
	private SocketPeService socketPeService = (SocketPeService)GetBean.getBean("socketPeService");
	private PeProtocolConfigService peProtocolConfigService = (PeProtocolConfigService)GetBean.getBean("peProtocolConfigService");
	private static ExecutorService exec = Executors.newCachedThreadPool();
	/**
	 * how long will the timer check the overtime devices
	 */
	private static final int TIME_LIMIT = 60;
	/**
	 * how long will the send sequence timer check the send sequence map
	 */
	private static final int SEND_TIMEOUT_LIMIT = 5;
	private static final String ENCODING = "ISO-8859-1";
	/**
	 * the device has not logged in
	 */
	private static final int STATE_OFF = 0;
	/**
	 * running state
	 */
	private static final int STATE_ON = 1;
	/**
	 * waiting for logout_ack
	 */
	private static final int STATE_LOGOUT_HOLDING = 2;
	/**
	 * padding zero
	 */
	private static String zeroSessionKeyAfterEncrypt = "";
	/**
	 * the default heart_beat_time
	 */
	private static final int MAX_HEART_BEAT_TIME = 60;
	
	
	/**
	 * map for information between peID and autoMachine object
	 */
	private static Map<String,AutoMachine> pe_autoMachine_map = new ConcurrentHashMap<String, AutoMachine>();
	/**
	 * map for information between pe and sendMessageSeq
	 */
	private static Multimap<String, String> pe_sendSeq_map = ArrayListMultimap.create();
	/**
	 * ReadWriteLock used for the safety of multimap pe_sendSeq_map
	 */
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	/**
	 * map for information between peAndSeq and sendMessageSeqSession
	 */
	private static Map<String, PESession> peAndSeq_session_map = new ConcurrentHashMap<String, PESession>();
	/**
	 * device overtime timer
	 */
	private static Timer timer = null;
	/**
	 * send sequence timer
	 */
	private static Timer sendSeqTimer = null;
	/**
	 * whether the timer is inited
	 */
	private static boolean isInit = false;
	/**
	 * peID
	 */
	private String peID;
	/**
	 * recv seq number for sending messages
	 */
	private String recvSeq = StringOperation.hexStringToString("000000000000");
	/**
	 * heat beat timeout time limit
	 */
	private int heartbeatTime;
	/**
	 * default state off
	 */
	private int state = STATE_OFF;
	/**
	 * default timestamp is 0x00000000
	 */
	private String timeStamp = StringOperation.hexStringToString("00000000");
	private String sessionKey;
	private boolean isSecret = true;
	private int timeLastUsed = (int)System.currentTimeMillis();

	static {
		for (int i=1;i<=48;i++){//sessionkey length will be 48 after encrypt
			zeroSessionKeyAfterEncrypt+="00";
		}
	}
	
	/**
	 * private constructor
	 * @param peID
	 */
	private AutoMachine(String peID){
		this.peID = peID;
	}
	
	/**
	 * get autoMachine object by peID
	 * @param peID
	 * @return autoMachine object
	 */
	public static AutoMachine getPeAutoMachine (String peID){
		if (pe_autoMachine_map.containsKey(peID)){
			return pe_autoMachine_map.get(peID);
		} else {
			AutoMachine autoMachine = new AutoMachine(peID);
			if (!isInit){
				timer = new Timer();
				timer.schedule(autoMachine.new OverTimeTimer(), TIME_LIMIT*1000, TIME_LIMIT*1000);
				sendSeqTimer = new Timer();
				sendSeqTimer.schedule(autoMachine.new SendSeqOverTimeTimer(), TIME_LIMIT*1000, TIME_LIMIT*1000);
				isInit = true;
			}
			pe_autoMachine_map.put(peID, autoMachine);
			return autoMachine;
		}
	}
	
	/**
	 * this method is used for upper to get automachine object through peID
	 * @param peID
	 * @return automachine object
	 */
	public static AutoMachine getPeAutoMachineUpper(String peID){
		if (pe_autoMachine_map.containsKey(peID)){
			return pe_autoMachine_map.get(peID);
		}
		return null;
	}
	
	/**
	 * autoMachine action by received upper message
	 * @param message
	 * @return if success, return true; if fail, return false
	 */
	public synchronized boolean action(String message, PESession session){
		if (state == STATE_OFF){
			//terminal not log in
			return false;
		}
		else if (state == STATE_ON){
			//transparent_data
			String safeWordsHex = isSecret ? "c0" : "80";
			PacketCreate pc = new PacketCreate(peID, "0004", StringOperation.hexStringToString(safeWordsHex), "00", message);
			if (pc.create(sessionKey, timeStamp, "",null)){
				//send message
				if (new ServerUtilNew().send(pc.message, peID)){
					//after send success, add the information between peID and send sequence into the multi-map
					lock.writeLock().lock();
					pe_sendSeq_map.put(peID, pc.seq);
					lock.writeLock().unlock();
					logger.info("session: "+session);
					if (session != null){
						peAndSeq_session_map.put(peID+"#"+pc.seq, session);
					}
				}
				else {
					logger.info("ChannelHandlerContext Not Found! Maybe PE not Log in or connection exception! ");
					ServerUtilNew.removePeCtxInfoByPeID(peID);
					return false;
				}
			}
			else {
				logger.info("Packet Create Error! ");
				return false;
			}
		}
		else if (state == STATE_LOGOUT_HOLDING){
			//terminal waiting for log out
			return false;
		}
		else {
			logger.info("State Error! ");
			return false;
		}
		return true;
	}
	
	/**
	 * autoMachine action by received message from terminal
	 * @param packet
	 * @param source
	 * @param ctx
	 */
	public synchronized void action(Packet packet,String source,ChannelHandlerContext ctx){
		PeProtocolConfig peProtocolConfig = peProtocolConfigService.query(peID);
		packet.checkSeq(pe_sendSeq_map,recvSeq,lock);
		logger.info("Seq Check Result: "+packet.isSafe);
		packet.checkMD5(timeStamp,peProtocolConfig);
		logger.info("MD5 Check Result: "+packet.isSafe);
		logger.info("State Now: "+state);
		if (packet.isSafe) {
			if (packet.head.commandID.startsWith("8")){ //if the message is ack message, delete the information between peID and seq
				lock.writeLock().lock();
				pe_sendSeq_map.remove(peID, packet.head.seq);
				lock.writeLock().unlock();
			}
			timeLastUsed = (int)System.currentTimeMillis()/1000;
			String commandIDHex = null;
			try {
				commandIDHex = StringOperation.stringToHexString(packet.head.commandID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (state == STATE_OFF) {
				if ("0001".equalsIgnoreCase(commandIDHex)) {// log in message?
					recvSeq = packet.head.seq;
					Pe pe = socketPeService.getPeByPeID(peID);
					if (pe != null){
						if (pe.getPeState() == 0){//log in success
							pe.setPeState(1);
							if ("success".equalsIgnoreCase(socketPeService.updatePe(pe))){
								String loginBody = packet.body.bodyMessage;
								String heartbeatTimeString = loginBody.substring(0,1);
								int tempHeartBeatTime = StringOperation.stringToAscIIInt(heartbeatTimeString);
								if (tempHeartBeatTime > 0){
									heartbeatTime = tempHeartBeatTime;
								}
								else {
									heartbeatTime = MAX_HEART_BEAT_TIME;
								}
								if ("TCPServer".equals(source)){
									ServerUtilNew.addPeCtxInfo(peID, ctx);
								}
								//create timestamp
								timeStamp = createTimeStamp();
								
								//create secretKey
								String loginACKSessionKey = "";
								try {
									//init session key
									byte[] key = AESCoder.initKey();
									//change session key to base64 code
									sessionKey = Base64.encodeBase64String(key);
									//get base key
									byte[] baseKey = Base64.decodeBase64(peProtocolConfig.getBasePassword());
									//use base key encrypt session key
									byte[] encrySessionKey = AESCoder.encrypt(key, baseKey);
									loginACKSessionKey = new String(encrySessionKey,ENCODING);
								} catch (Exception e){
									e.printStackTrace();
								}
								String loginACKBody = StringOperation.hexStringToString("00") + timeStamp + loginACKSessionKey;
								PacketCreate pc = new PacketCreate(peID, "8001", packet.head.safeWords, "40", loginACKBody);
								if (pc.create(sessionKey, StringOperation.hexStringToString("00000000"),packet.head.seq,peProtocolConfig)){
									if ("TCPServer".equals(source)){//messages from TCP server
										//send messages
										if (new ServerUtilNew().send(pc.message, peID)){
											logger.info("Send OK!");
											state = STATE_ON;
											int loginTimes = peProtocolConfig.getLoginTimes();
											loginTimes++;
											peProtocolConfig.setLoginTimes(loginTimes);
											if ("fail".equalsIgnoreCase(peProtocolConfigService.update(peProtocolConfig))){
												logger.info("Data Access Error! ");
											}
										}
										else {
											logger.info("ChannelHandlerContext Not Found! Maybe PE not log in or connection exception! ");
											ServerUtilNew.removePeCtxInfoByPeID(peID);
										}
									}
									else if ("UDPServer".equals(source)){//messages from UDP server
										
									}
								}
								else {
									logger.info("Packet Create Error! ");
									pe.setPeState(0);
									if ("fail".equalsIgnoreCase(socketPeService.updatePe(pe))){
										logger.info("Data Access Error! ");
									}
								}
							}
							else {
								logger.info("Data Access Error! ");
							}
						}
						else {//already log in?
							pe.setPeState(0);
							if ("success".equalsIgnoreCase(socketPeService.updatePe(pe))){
								if ("TCPServer".equals(source)){
									ServerUtilNew.addPeCtxInfo(peID, ctx);
									String zeroTimsStamp = "00000000";
									String loginACKBodyHex = "02"+zeroTimsStamp+zeroSessionKeyAfterEncrypt;
									String loginACKBody = StringOperation.hexStringToString(loginACKBodyHex);
									PacketCreate pc = new PacketCreate(peID, "8001", packet.head.safeWords, "40", loginACKBody);
									if (pc.create(sessionKey, StringOperation.hexStringToString("00000000"), packet.head.seq,peProtocolConfig)){
										//send messages
										if (new ServerUtilNew().send(pc.message, peID)){
											logger.info("Send OK! ");
										}
										else {
											logger.info("Send Fail");
											ServerUtilNew.removePeCtxInfoByPeID(peID);
										}
									}
									else {
										logger.info("Packet Create Error! ");
									}
									ServerUtilNew.removePeCtxInfoByPeID(peID);
								}
							}
							else {
								logger.info("Packet Create Error! ");
							}
							deleteAutoMachine(peID);
						}
					}
					else {
						logger.info("PEID Null! ");
					}
				} else {
					//do nothing
					logger.info("PEID null?");
				}
			} else if (state == STATE_ON) {
				if ("0001".equalsIgnoreCase(commandIDHex)){
					recvSeq = packet.head.seq;
					Pe pe = socketPeService.getPeByPeID(peID);
					if (pe != null){
						pe.setPeState(0);
						if ("success".equalsIgnoreCase(socketPeService.updatePe(pe))){
							if ("TCPServer".equals(source)){
								ServerUtilNew.addPeCtxInfo(peID, ctx);
								String zeroTimsStamp = "00000000";
								String loginACKBodyHex = "02"+zeroTimsStamp+zeroSessionKeyAfterEncrypt;
								String loginACKBody = StringOperation.hexStringToString(loginACKBodyHex);
								PacketCreate pc = new PacketCreate(peID, "8001", packet.head.safeWords, "40", loginACKBody);
								if (pc.create(sessionKey, StringOperation.hexStringToString("00000000"), packet.head.seq,peProtocolConfig)){
									//send messages
									if (new ServerUtilNew().send(pc.message, peID)){
										logger.info("Send OK! ");
									}
									else {
										logger.info("Send Fail! ");
										ServerUtilNew.removePeCtxInfoByPeID(peID);
									}
								}
								else {
									logger.info("Packet Create Error! ");
								}
								ServerUtilNew.removePeCtxInfoByPeID(peID);
							}
						}
						else {
							logger.info("Data Access Error! ");
						}
						deleteAutoMachine(peID);
						state = STATE_OFF;
					}
					else {
						logger.info("PEID Null! ");
					}
				}
				else if ("0002".equalsIgnoreCase(commandIDHex)){//log out message
					if ("TCPServer".equals(source)){
						ServerUtilNew.addPeCtxInfo(peID, ctx);
						recvSeq = packet.head.seq;
						Pe pe = socketPeService.getPeByPeID(peID);
						if (pe != null){
							pe.setPeState(0);
							if ("success".equalsIgnoreCase(socketPeService.updatePe(pe))){
								PacketCreate pc = new PacketCreate(peID, "8002", packet.head.safeWords, "40", "");
								if (pc.create(sessionKey, timeStamp, packet.head.seq,peProtocolConfig)){
									//send message
									if (new ServerUtilNew().send(pc.message, peID)){
										logger.info("Send OK! ");
									}
									else {
										logger.info("Send Fail! ");
										ServerUtilNew.removePeCtxInfoByPeID(peID);
									}
								}
								else {
									logger.info("Packet Create Error! ");
								}
							}
							else {
								logger.info("Data Access Error! ");
							}
							state = STATE_OFF;
							ServerUtilNew.removePeCtxInfoByPeID(peID);
						}
					}
				}
				else if ("0003".equalsIgnoreCase(commandIDHex)){//heart_beat message
					if ("TCPServer".equals(source)){
						ServerUtilNew.addPeCtxInfo(peID, ctx);
						recvSeq = packet.head.seq;
						char keepWordsTag = packet.head.keepWordS.charAt(0);
						if ((keepWordsTag&64) != 0){
							logger.info("Do Not Need response! ");
						}
						else {
							PacketCreate pc = new PacketCreate(peID, "8003", packet.head.safeWords, "40", "");
							if (pc.create(sessionKey, timeStamp, packet.head.seq,peProtocolConfig)){
								//send messages
								if (new ServerUtilNew().send(pc.message, peID)){
									logger.info("Send OK! ");
								}
								else {
									logger.info("Send Fail! ");
									ServerUtilNew.removePeCtxInfoByPeID(peID);
								}
							}
							else {
								logger.info("Packet Create Error! ");
							}
						}
					}
				}
				else if ("0004".equalsIgnoreCase(commandIDHex)){//transparent_data message
					if ("TCPServer".equals(source)){
						ServerUtilNew.addPeCtxInfo(peID, ctx);
						recvSeq = packet.head.seq;
						String receiveMessage = null;
						char safeTag = packet.head.safeWords.charAt(0);
						if ((64&safeTag) == 0){
							receiveMessage = packet.body.bodyMessage;
						}
						else {
							try {
								byte[] receiveMessageByte = AESCoder.decrypt(packet.body.bodyMessage.getBytes(ENCODING), Base64.decodeBase64(sessionKey));
								receiveMessage = new String (receiveMessageByte,ENCODING);
							} catch (Exception e){
								e.printStackTrace();
							}
						}
						//send receiveMessage to upper layer
						if (receiveMessage == null || "".equalsIgnoreCase(receiveMessage)){
							PEData peData = new PEData();
							peData.peID = peID;
							peData.interfaceID = null;
							peData.data = null;
							try {
							    exec.execute(new PEOperationForActiveMQ(peData,null));
							    exec.execute(new PEOperationForAtomicVE().new GetDataFromDevice(peData.peID,peData.interfaceID,peData.data,null));
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
						else {
							try {
								JSONObject json = JSONObject.fromObject(receiveMessage);
								PEData peData = new PEData();
								peData.peID = peID;
								peData.interfaceID = json.containsKey("IFID") ? json.getString("IFID") : null;
								peData.data = json.containsKey("Data") ? json.getJSONObject("Data").toString() : null;
								exec.execute(new PEOperationForActiveMQ(peData,null));
								exec.execute(new PEOperationForAtomicVE().new GetDataFromDevice(peData.peID,peData.interfaceID,peData.data,null));
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
					    //send ack messages
						PacketCreate pc = new PacketCreate(peID, "8004", packet.head.safeWords, "40", StringOperation.hexStringToString("00"));
						if (pc.create(sessionKey, timeStamp, packet.head.seq,peProtocolConfig)){
							//send messages
							if (new ServerUtilNew().send(pc.message, peID)){
								logger.info("Send OK! ");
							}
							else {
								logger.info("Send Fail ");
								ServerUtilNew.removePeCtxInfoByPeID(peID);
							}
						}
						else {
							logger.info("Packet Create Error! ");
						}
					}
				}
				else if ("8004".equalsIgnoreCase(commandIDHex)){//transparent_data_ack message
					if ("TCPServer".equals(source)){
						ServerUtilNew.addPeCtxInfo(peID, ctx);
						String receiveMessage = null;
						char safeTag = packet.head.safeWords.charAt(0);
						if ((64&safeTag) == 0){
							receiveMessage = packet.body.bodyMessage;
						}
						else {
							try {
								byte[] receiveMessageByte = AESCoder.decrypt(packet.body.bodyMessage.getBytes(ENCODING), Base64.decodeBase64(sessionKey));
								receiveMessage = new String (receiveMessageByte,ENCODING);
							} catch (Exception e){
								e.printStackTrace();
							}
						}
						if ("".equalsIgnoreCase(receiveMessage)){
							receiveMessage = StringOperation.hexStringToString("00");
						}
						try {
							String receiveTag = receiveMessage.substring(0,1);
							if ("00".equals(StringOperation.stringToHexString(receiveTag))){
								logger.info("Receive Transparent_data_ack content ok! ");
							}
							else {
								logger.info("Receive Transparent_data_ack content wrong! ");
							}
							receiveMessage = receiveMessage.substring(1);
						} catch ( Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						//send receiveMessage to upper layer
						if (receiveMessage == null || "".equalsIgnoreCase(receiveMessage)){
							PEData peData = new PEData();
							peData.peID = peID;
							peData.interfaceID = null;
							peData.data = null;
							PESession peSession = peAndSeq_session_map.get(peID+"#"+packet.head.seq);
							if (peSession != null){
								try {
									if (peSession.isActiveMQ()){
										peSession.setTimeout(false);
										exec.execute(new PEOperationForActiveMQ(peData,peSession));
									}
									else {
										exec.execute(new PEOperationForAtomicVE().new GetDataFromDevice(peData.peID,peData.interfaceID,peData.data,peSession));
									}
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
						}
						else {
							try {
								JSONObject json = JSONObject.fromObject(receiveMessage);
								PEData peData = new PEData();
								peData.peID = peID;
								peData.interfaceID = json.containsKey("IFID") ? json.getString("IFID") : null;
								peData.data = json.containsKey("Data") ? json.getJSONObject("Data").toString() : null;
								PESession peSession =  peAndSeq_session_map.get(peID+"#"+packet.head.seq);
								if (peSession != null){
									if (peSession.isActiveMQ()){
										peSession.setTimeout(false);
										exec.execute(new PEOperationForActiveMQ(peData,peSession));
									}
									else {
										exec.execute(new PEOperationForAtomicVE().new GetDataFromDevice(peData.peID,peData.interfaceID,peData.data,peSession));
									}
								}
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
						//delete the information 
						lock.writeLock().lock();
						pe_sendSeq_map.remove(peID, packet.head.seq);
						lock.writeLock().unlock();
						peAndSeq_session_map.remove(peID+"#"+packet.head.seq);
					}
				}
			} else if (state == STATE_LOGOUT_HOLDING) {
				if ("8002".equalsIgnoreCase(commandIDHex)){//log out ack message
					if ("TCPServer".equals(source)){
						ServerUtilNew.addPeCtxInfo(peID, ctx);
						state = STATE_OFF;
						ServerUtilNew.removePeCtxInfoByPeID(peID);
						deleteAutoMachine(peID);
					}
				}
				else {
					//do nothing
				}
			}
		}
		else {
			//receive wrong packet! do nothing!
			logger.info("Receive Wrong Packet! ");
		}
	}
	
	/**
	 * this method is used to create 4 Byte timestamp for login_ack
	 * @return 4 Byte timestamp
	 */
	private static String createTimeStamp(){
		long timeStampLong = System.currentTimeMillis()/1000;
		String timeStampHex = Long.toHexString(timeStampLong);
		int length = timeStampHex.length();
		if (length > 8){
			return StringOperation.hexStringToString(timeStampHex.substring(length-8,length));
		}
		else {
			int temp = 8-length;
			String tempString = timeStampHex;
			while (temp > 0){
				tempString = "0"+tempString;
				temp--;
			}
			return StringOperation.hexStringToString(tempString);
		}
	}
	
	/**
	 * this method is used to delete automachine information by peID
	 * @param peID
	 */
	private static void deleteAutoMachine(String peID){
		pe_autoMachine_map.remove(peID);
		lock.writeLock().lock();
		pe_sendSeq_map.removeAll(peID);
		lock.writeLock().unlock();
	}
	
	/**
	 * this class is used for overtime operation
	 * when overtime occured, system will check all the pe with automachine
	 * @author xzc
	 *
	 */
	private class OverTimeTimer extends java.util.TimerTask {
		public void run(){
			logger.info("Timer Check Run! ********************");
			double total = (Runtime.getRuntime().totalMemory())/(1024.0*1024);
			double max = (Runtime.getRuntime().maxMemory())/(1024.0*1024);
			double free = (Runtime.getRuntime().freeMemory())/(1024.0*1024);
			logger.info("total memory: "+total+"MB");
			logger.info("max memory: "+max+"MB");
			logger.info("free memory: "+free+"MB");
			logger.info("available memory: "+(max-total+free)+"MB");
			Collection<AutoMachine> valueSet = pe_autoMachine_map.values();
			Iterator<AutoMachine> it = valueSet.iterator();
			int timeNow = (int)System.currentTimeMillis()/1000;
			while (it.hasNext()){
				AutoMachine temp = it.next();
				int timeLast = timeNow - temp.timeLastUsed;
				if (timeLast > temp.heartbeatTime){
					logger.info(temp.peID+" time out! pe info will delete! ");
					temp.state = STATE_OFF;
					Pe pe = socketPeService.getPeByPeID(temp.peID);
					if (pe == null ){
						logger.info("ERROR: PE Not Exists! ");
					}
					else {
						pe.setPeState(0);
						if ("fail".equalsIgnoreCase(socketPeService.updatePe(pe))){
							logger.info("ERROR: Data Access Error! ");
						}
						else {
							logger.info(temp.peID+" database info update! ");
						}
					}
					deleteAutoMachine(temp.peID);
				}
			}
		}
	}
	
	/**
	 * this class is used for send seq over time check 
	 * @author xzc
	 *
	 */
	private class SendSeqOverTimeTimer extends java.util.TimerTask {
		public void run(){
			try {
				logger.info("Send Seq Timer Check Run! ***************");
				lock.writeLock().lock();
				Collection<Entry<String,String>> collection = pe_sendSeq_map.entries();
				Iterator<Entry<String,String>> it = collection.iterator();
				long timeNow = System.currentTimeMillis();
				while (it.hasNext()){
					Entry<String,String> temp = it.next();
					long timeSend = StringOperation.stringToAscIILong(temp.getValue());
					long timeLast = timeNow - timeSend;
					if (timeLast > SEND_TIMEOUT_LIMIT*1000){
						logger.info("timeout: peID: "+temp.getKey()+" seq: "+StringOperation.stringToHexString(temp.getValue()));
						//send timeout information to upper layer
						PEData peData = new PEData();
						peData.peID = temp.getKey();
						PESession peSession = peAndSeq_session_map.get(temp.getKey()+"#"+temp.getValue());
						try {
							peSession.setTimeout(true);
							exec.execute(new PEOperationForActiveMQ(peData,peSession));
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						peAndSeq_session_map.remove(temp.getKey()+"#"+temp.getValue());
						pe_sendSeq_map.remove(temp.getKey(), temp.getValue());
					}
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			finally{
				lock.writeLock().unlock();
			}
		}
		
	}
}
