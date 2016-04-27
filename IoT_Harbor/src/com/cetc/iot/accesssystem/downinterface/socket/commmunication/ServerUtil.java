package com.cetc.iot.accesssystem.downinterface.socket.commmunication;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.sf.json.JSONObject;

import com.cetc.iot.accesssystem.downinterface.socket.handling.MsgRecvHandling;
import com.cetc.iot.accesssystem.downinterface.socket.service.SocketPeService;
import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.servicesystem.util.GetBean;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
/**
 * Socket Server Utils based on netty structure
 * @author xzc
 * Create Time: 2014-09-09
 * Author: xzc
 * Details: Add read function for messages reading, 
 * 			Add remove(ChannelHandlerContext) function for ChannelHandlerContext removing, 
 * 			Add sendInfo(ChannelHandlerContext,message) function for messages sending to ChannelHandlerContext, 
 * 			Add remove(peID) function for peID removing, 
 * 			Add sendInfo(peID,message) function for messages sending to PE, 
 * 			Add gatewayPEInfoChangeOperation function for platform handling messages from gateways, 
 * 			Add ctxPEInfoChangeOperation function for changing connected information between ChannelHandlerContext and PE, 
 * 			Add databasePEInfoChangeOperation function for changing information in database, 
 * 			Add register function for PE information registering with ChannelHandlerContext. 
 * Update Time: 2014-11-25
 * Author: xzc
 * Details: Add some functions for further check.
 * 			Avoid reLogin.
 * 			Add support for directly communicating to pe devices. (Do not use gateway)
 * @deprecated this class is not used since 2nd project, use ServerUtilNew instead
 */
@Deprecated
public class ServerUtil {
	


	private SocketPeService socketPeService = (SocketPeService)GetBean.getBean("socketPeService");
	
	
	/**
	 * MsgSendToUpper class for messages sending to upper layer
	 * @author xzc
	 * Create Time: 2014-09-17
	 * Author: xzc
	 * Details: this class implements Runnable interface, used to send received messages to upper layer
	 *
	 */
	private class MsgSendToUpper implements Runnable {
		private PEData peData = null;
		public MsgSendToUpper(PEData peData){
			this.peData = peData;
		}
		public void run(){
//			UpperOperation.msgReceive(peData);
		}
	}
	/**
	 * thread pool
	 */
	private static ExecutorService exec = Executors.newCachedThreadPool();
	/**
	 * this instance is used for MsgSendToUpper class sending messages
	 */
	private static ServerUtil serverUtil = new ServerUtil();
	
	/**
	 * this map keeps information of PEID and ChannelHandlerContext
	 */
	private static Map<String,ChannelHandlerContext> serverPEIDChannelHandlerContextMap = Collections.synchronizedMap(new HashMap<String, ChannelHandlerContext>());
	/**
	 * this multiMap keeps information of ChannelHandlerContext and PEID
	 */
	private static Multimap<ChannelHandlerContext,String> serverChannelHandlerContextPEIDMap = ArrayListMultimap.create();
	/**
	 * this ReadWriteLock is used for map visiting
	 */
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	
	
	public static ReadWriteLock getLock(){
		return lock;
	}
	
	public static Map<String,ChannelHandlerContext> getServerPEIDChannelHandlerContextMap(){
		return serverPEIDChannelHandlerContextMap;
	}
	
	public static Multimap<ChannelHandlerContext, String> getServerChannelHandlerContextPEIDMap(){
		return serverChannelHandlerContextPEIDMap;
	}
	
	/**
	 * Allow other places to get ChannelHandlerContext info through peID
	 * @param peID
	 * @return
	 */
	public static ChannelHandlerContext getChannelHandlerContext(String peID){
		lock.readLock().lock();
		ChannelHandlerContext ctx = serverPEIDChannelHandlerContextMap.get(peID);
		lock.readLock().unlock();
		return ctx;
	}
	
	
	/**
	 * Socket Server read function
	 * @param msg received messages
	 * @param ctx ChannelHandlerContext which received messages
	 */
	public static void read(String msg,ChannelHandlerContext ctx){
		MsgRecvHandling msgRecvHandling = new MsgRecvHandling(msg);
		//Check the safety of the message
		ServerUtil.securityCheck(msgRecvHandling, ctx);
		
		
		if (msgRecvHandling.isSafe()){
			
			//Check whether the messages are gateway messages
			if (("gateway").equalsIgnoreCase(msgRecvHandling.getPEID())){
				//Use gatewayPEInfoChangeOperation for further operation
				ServerUtil.gatewayPEInfoChangeOperation(msgRecvHandling.getData(), ctx);
			}
			//Check whether the messages are register messages from smart devices
			else if ("register".equalsIgnoreCase(msgRecvHandling.getPEID())){
			}
			else {
				
				if ("heart_beat".equalsIgnoreCase(msgRecvHandling.getStatus())){
					if (msgRecvHandling.requireResponse()){
						JSONObject respJson = new JSONObject();
						respJson.put("Address", msgRecvHandling.getPEID());
						respJson.put("Version", "1.0");
						respJson.put("IsResponse", true);
						respJson.put("RequireResponse", false);
						respJson.put("IsData", false);
						respJson.put("SessionID",msgRecvHandling.getSessionID());
						respJson.put("Status", "heart_beat");
						ServerUtil.sendInfo(msgRecvHandling.getPEID(), respJson.toString());
					}
				}
				else {
					JSONObject dataJson = msgRecvHandling.getData();
					String status = dataJson.containsKey("status") ? dataJson.getString("status") : null;
					if (status != null){
						//pe state change messages from pe
						//Use peInfoChangeOperation for further operation
						ServerUtil.peInfoChangeOperation(msgRecvHandling.getPEID(), status, ctx);
					}
					else {
						//Start a thread to send the message to upper layer
						exec.execute(new Thread(serverUtil.new MsgSendToUpper(msgRecvHandling.msgToPEData())));
					}
				}
			}
		}
		else {
			//messages are not safety, do nothing
		}
	}
	
	/**
	 * when receive pe state change messages from pe, do this function
	 * @param peID 
	 * @param status the status pe changed to 
	 * @param ctx
	 */
	private static void peInfoChangeOperation(String peID, String status, ChannelHandlerContext ctx){
		JSONObject respDataJson = new JSONObject();
		if (("null".equalsIgnoreCase(status))||("".equalsIgnoreCase(status))){
			System.out.println("ERROR: status null! ");
			respDataJson.put("Result", "ERROR: status null! ");
		}
		else {
			//Call the function databasePEInfoChangeOperation for further operation
			String result = serverUtil.databasePEInfoChangeOperation(peID, status);
			//Call the function ctxPEInfoChangeOperation for further operation
		    ServerUtil.ctxPEInfoChangeOperation(peID, status, ctx,result);
			//Put operation results into JSONObject for sending back to pe
			respDataJson.put("Result", result);
		}
		
		//Packet the results sending to pe
		JSONObject respJson = new JSONObject();
		respJson.put("Address", peID);
		respJson.put("Version", "1.0");
		respJson.put("IsData", false);
		respJson.put("IsResponse", true);
		respJson.put("RequireResponse", false);
		respJson.put("Data",respDataJson);
		//Call the function sendInfo(ChannelHandlerContext,message) to send results to pe
		ServerUtil.sendInfo(ctx,respJson.toString());
		
	}
	
	
	/**
	 * Check whether the message is safety
	 * @param msgRecvHandling
	 * @param ctx
	 */
	private static void securityCheck(MsgRecvHandling msgRecvHandling,ChannelHandlerContext ctx){
		if ("gateway".equalsIgnoreCase(msgRecvHandling.getPEID())){
			//pe state change messages from gateway, do nothing
		}
		else {
			JSONObject dataJson = msgRecvHandling.getData();
			if (dataJson == null){
				
			}
			else {
				String status = dataJson.containsKey("Status") ? dataJson.getString("Status") : null;
				if (status != null) {
					// pe state change messages from pe, do nothing
				} else {
					lock.readLock().lock();
					ChannelHandlerContext reCtx = serverPEIDChannelHandlerContextMap.get(msgRecvHandling.getPEID());
					lock.readLock().unlock();
					if ((reCtx == null) || (reCtx.hashCode() != ctx.hashCode())) {
						msgRecvHandling.setIsSafe(false);
					} else {
						// correct pe messages, do nothing
					}
				}
			}
		}
	}
	
	
	
	/**
	 * remove function for removing the ChannelHandlerContext information
	 * @param ctx ChannelHandlerContext to removed
	 */
	public void remove(ChannelHandlerContext ctx){
		System.out.println("ChanelHandlerContext Removing Starts!");
		lock.readLock().lock();
		boolean isExists = serverChannelHandlerContextPEIDMap.containsKey(ctx);
		lock.readLock().unlock();
		if (isExists){
			System.out.println("PE Removing Starts!");
			lock.writeLock().lock();
			Collection<String> needRemove = serverChannelHandlerContextPEIDMap.get(ctx);
			for (String value : needRemove){
				Pe pe = socketPeService.getPeByPeID(value);
				int state = pe.getPeState();
				if (state == 1){
					pe.setPeState(0);
					String result = socketPeService.updatePe(pe);
					if ("success".equalsIgnoreCase(result)){
						System.out.println("Pe: "+pe.getPeId()+" turns off! ");
					}
					else {
						System.out.println("DataBase Operation Error: Pe Info Update Error! ");
					}
				}
				else {
					System.out.println("PE: "+pe.getPeId()+" has already logout! Do not need to remove! ");
				}	
				
				serverPEIDChannelHandlerContextMap.remove(value);
				System.out.println("peID: "+value+" removed");
			}
			serverChannelHandlerContextPEIDMap.removeAll(ctx);
			lock.writeLock().unlock();
		}
		else {
			System.out.println("Do not need to remove! "+ctx.name()+" already not exists!");
		}
	}
	
	
	/**
	 * Send information to PE
	 * @param peID destination to send
	 * @param msg messages to send
	 */
	public static void sendInfo(String peID,String msg){
		lock.readLock().lock();
		ChannelHandlerContext ctx = serverPEIDChannelHandlerContextMap.get(peID);
		lock.readLock().unlock();
		if (ctx != null){
			msg+="\n";
			ByteBuf resp = Unpooled.copiedBuffer(msg.getBytes());
			synchronized(ctx){
				ctx.writeAndFlush(resp);
			}
			System.out.println("TCP messages sent to "+peID+", message: "+msg);
		}
		else {
			System.out.println("PEID Not Exists in Communication Channel! (Maybe PE Has Not Log in) ");
		}
	}
	
	/**
	 * Remove the connection between PE and ChannelHandlerContext through PEID
	 * @param peID PEID to remove
	 */
	private void remove(String peID){
		System.out.println("PE Removing Operation! ");
		Pe pe = socketPeService.getPeByPeID(peID);
		if (pe != null) {
			int state = pe.getPeState();
			if (state == 1) {
				pe.setPeState(0);
				String result = socketPeService.updatePe(pe);
				if ("success".equalsIgnoreCase(result)) {
					System.out.println("Pe: " + pe.getPeId() + " turns off! ");
				} else {
					System.out.println("DataBase Operation Error: Pe Info Update Error! ");
				}
			} else {
				System.out.println("PE: " + pe.getPeId() + " has already logout! Do not need to remove! ");
			}
		}
		else {
			System.out.println("Do not need to Update! PE: "+peID+" Not Exists! ");
		}
		lock.readLock().lock();
		boolean isExists = serverPEIDChannelHandlerContextMap.containsKey(peID);
		lock.readLock().unlock();
		if (isExists){
			System.out.println("PE Starts To Remove! ");
			lock.writeLock().lock();
			ChannelHandlerContext ctx = serverPEIDChannelHandlerContextMap.get(peID);
			serverPEIDChannelHandlerContextMap.remove(peID);
			serverChannelHandlerContextPEIDMap.remove(ctx, peID);
			lock.writeLock().unlock();
			System.out.println("peID: "+peID+" is removed! ");
		}
		else {
			System.out.println("peID: "+peID+" do not need to remove! Not exists! ");
		}
	}
	
	
	
	/**
	 * Send Information to ChannelHandlerContext
	 * @param ctx ChannelHandlerContext to send messages
	 * @param msg messages to send
	 */
	private static void sendInfo(ChannelHandlerContext ctx, String msg){
		msg+="\n";
		ByteBuf resp = Unpooled.copiedBuffer(msg.getBytes());
		synchronized(ctx){
			ctx.writeAndFlush(resp);
		}
		System.out.println("TCP Send to ChannelHandlerContext "+ctx.name()+" , messages: "+msg);
	}
	
	/**
	 * Operation of receiving gateway change information
	 * @param data received messages which is packet as JSONObject
	 * @param ctx ChannelHandlerContext which received the messages
	 */
	private static void gatewayPEInfoChangeOperation(JSONObject data,ChannelHandlerContext ctx){
		JSONObject respDataJson = new JSONObject();
		@SuppressWarnings("unchecked")
		Set<JSONObject.Entry<String,String>> entrySet = data.entrySet();
		for (JSONObject.Entry<String,String> entry : entrySet){
			//Check whether the status is null
			if ((null == entry.getValue())||("null".equalsIgnoreCase(entry.getValue()))||("".equalsIgnoreCase(entry.getValue()))){
				System.out.println("ERROR: status null! ");
				respDataJson.put(entry.getKey(), "ERROR: status null! ");
			}
			else {
				//String result = "SUCCESS:on:model-t-cetc-ztb-001";
				//Call the function databasePEInfoChangeOperation for further operation
				String result = serverUtil.databasePEInfoChangeOperation(entry.getKey(),entry.getValue());
				//Call the function ctxPEInfoChangeOperation for further operation
			    ServerUtil.ctxPEInfoChangeOperation(entry.getKey(), entry.getValue(), ctx,result);
				//Put operation results into JSONObject for sending back to gateway
				respDataJson.put(entry.getKey(), result);
			}
		}
		
		//Packet the results sending to gateway
		JSONObject respJson = new JSONObject();
		respJson.put("Address", "access_platform");
		respJson.put("Version", "1.0");
		respJson.put("IsData", false);
		respJson.put("IsResponse", true);
		respJson.put("RequireResponse", false);
		respJson.put("Data",respDataJson);
		//Call the function sendInfo(ChannelHandlerContext,message) to send results to gateway
		ServerUtil.sendInfo(ctx, respJson.toString());
	}
	
	
	/**
	 * change the connection between ChannelHandlerContext and PE
	 * @param peID PEID
	 * @param status destination of the status changed to 
	 * @param ctx ChannelHandlerContext
	 * @param result database operation result
	 */
	private static void ctxPEInfoChangeOperation(String peID, String status, ChannelHandlerContext ctx,String result){
		//If the status changed to on
		if ("on".equalsIgnoreCase(status)) {
			//If database operation ok, register the connection between ChannelHandlerContext and PE
			if (result.startsWith("SUCCESS")){
				ServerUtil.register(peID, ctx);
			}
			else {
				ServerUtil.sendLogoutInfo(peID);
				serverUtil.remove(peID);
			}
		//If the status changed to off,remove the connection between ChannelHandlerContext and PE
		} else if ("off".equalsIgnoreCase(status)) {
			ServerUtil.sendLogoutInfo(peID);
			serverUtil.remove(peID);
		}
	}
	
	/**
	 * Send Logout Info to pe which connecting to the platform
	 * reason: pe has logged in at another place.
	 *         pe has logged out at another place.
	 * @param peID
	 */
	private static void sendLogoutInfo(String peID){
		lock.readLock().lock();
		ChannelHandlerContext ctx = serverPEIDChannelHandlerContextMap.get(peID);
		lock.readLock().unlock();
		if (ctx != null){
			JSONObject respDataJson = new JSONObject();
			respDataJson.put("Logout", "logout");
			JSONObject respJson = new JSONObject();
			respJson.put("Address", peID);
			respJson.put("Version", "1.0");
			respJson.put("IsData", false);
			respJson.put("IsResponse", false);
			respJson.put("RequireResponse", false);
			respJson.put("Data", respDataJson);
			
			ServerUtil.sendInfo(ctx, respJson.toString());
		}
	}
	
	/**
	 * Database Operation when PE status Changed
	 * @param peID PEID
	 * @param status destination of the status changed to 
	 * @return  Database Operation
	 */
	private String databasePEInfoChangeOperation(String peID,String status){
		Pe pe = socketPeService.getPeByPeID(peID);
		//Check whether turn pe state to 'on'
		if ("on".equalsIgnoreCase(status)){
			//Check whether pe ok
			if (pe != null){
				String peModelID = pe.getTemplateId();
				//Check whether peModel ok
				if (peModelID == null || "".equalsIgnoreCase(peModelID)){
					return "ERROR:on: pe model not exists! ";
				}
				else {
					int state = pe.getPeState();
					//Check whether pe already login
					if (state == 1){
						//Set pe state to 0 (logout state)
						pe.setPeState(0);
						String result = socketPeService.updatePe(pe);
						//Check whether update operation successfully complete
						if ("success".equalsIgnoreCase(result)){
							System.out.println("Pe: "+pe.getPeId()+" turns off! ");
							return "ERROR:on: pe already login! ";
						} 
						else {
							System.out.println("DataBase Operation Error: Pe Info Update Error! ");
							return "ERROR:on: platform not available! ";
						}
					}
					else {
						pe.setPeState(1);
						String result = socketPeService.updatePe(pe);
						//Check whether update operation successfully complete
						if ("success".equalsIgnoreCase(result)){
							System.out.println("Pe: "+pe.getPeId()+" turns on! ");
							return "SUCCESS:on:"+peModelID;
							//return "SUCCESS:on";
						}
						else {
							System.out.println("DataBase Operation Error: Pe Info Update Error! ");
							return "ERROR:on: platform not available! ";
						}
					}
				}
			}
			else {
				return "ERROR:on: pe not exists! ";
			}
		}
		else if ("off".equalsIgnoreCase(status)){
			//Check whether pe ok
			if (pe != null){
				int state = pe.getPeState();
				//Check whether pe login
				if (state == 1){
					//Set pe state to 0 (logout state)
					pe.setPeState(0);
					String result = socketPeService.updatePe(pe);
					//Check whether update operation successfully complete
					if ("success".equalsIgnoreCase(result)){
						System.out.println("Pe: "+pe.getPeId()+" turns off! ");
						return "SUCCESS:off";
					}
					else {
						System.out.println("DataBase Operation Error: Pe Info Update Error! ");
						return "ERROR:off: platform not available! ";
					}
				}
				else {
					return "ERROR:off: pe already logout! ";
				}
			}
			else {
				return "ERROR:off: pe not exists! ";
			}
		}
		return "ERROR:off: status value error! ";
	}
	
	
	/**
	 * Add the connection between ChannelHandlerContext and PE
	 * @param peID PE
	 * @param ctx ChannelHandlerContext
	 */
	private static void register(String peID,ChannelHandlerContext ctx){
		System.out.println("New PE Connects To ChannelHandlerContext, PEID: " + peID);
		if ((null == peID) || ("null".equalsIgnoreCase(peID)) || ("".equalsIgnoreCase(peID))) {
			System.out.println("PEID Error! ");
		} else {
			lock.readLock().lock();
			boolean isExists = serverPEIDChannelHandlerContextMap.containsKey(peID);
			lock.readLock().unlock();
			if (!isExists) {
				System.out.println("Start to Register the connection");
				lock.writeLock().lock();
				serverPEIDChannelHandlerContextMap.put(peID, ctx);
				serverChannelHandlerContextPEIDMap.put(ctx, peID);
				lock.writeLock().unlock();
				System.out.println("PEID: "+peID+" is added! ");
			}
			else {
				System.out.println("peID: "+peID+" exists! Do not need to add! ");
			}
		}
		
	}
	
}
