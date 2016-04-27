package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

import com.cetc.iot.accesssystem.downinterface.socket.handling.MsgRecvHandling;
import com.cetc.iot.accesssystem.downinterface.socket.service.SocketPeService;
import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.servicesystem.util.GetBean;
/**
 * Datagram Socket Server Utils based on netty structure
 * @author xzc
 * Create Time: 2014-09-18
 * Author: xzc
 * Details: Add read function for packets reading
 *   		Add sendInfo function for packets sending
 * Update Time: 2014-11-24
 * Author: xzc
 * Details: Add peInfoCheckDatabase function for pe Info checking(whether the pe has logged in)
 *          Update read function for pe Info checking
 * Update Time: 2014-11-25
 * Author: xzc
 * Details: Add messageCheck function for messages checking(whether the message from the same IP address of pe)
 */
public class DatagramServerUtil {
	
	private SocketPeService socketPeService = (SocketPeService)GetBean.getBean("socketPeService");
	
	/**
	 * MsgSendToUpper class for message sending to upper layer
	 * @author xzc
	 * Create Time: 2014-09-18
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
			System.out.println("Message Send to Upper! ");
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
	private static DatagramServerUtil datagramServerUtil = new DatagramServerUtil();
	
	
	
	/**
	 * Datagram Socket Server Read Function For Handling Receiving Messages
	 * @param ctx handling ChannelHandlerContext
	 * @param recv received message
	 * @param address InetSocketAddress which receives messages
	 */
	public static void read(ChannelHandlerContext ctx,String recv,InetSocketAddress address){
		MsgRecvHandling msgRecvHandling = new MsgRecvHandling(recv);
		
		//Check whether the messages are safety
		DatagramServerUtil.messageCheck(msgRecvHandling, address);
		
		if (msgRecvHandling.isSafe()){
			String peInfoCheck = datagramServerUtil.peInfoCheckDatabase(msgRecvHandling.getPEID());
			if (peInfoCheck.startsWith("SUCCESS")){
				exec.execute(new Thread(datagramServerUtil.new MsgSendToUpper(msgRecvHandling.msgToPEData())));
			}
			if ((msgRecvHandling.requireResponse())||!(peInfoCheck.startsWith("SUCCESS"))){
				JSONObject respJsonObject = new JSONObject();
				respJsonObject.put("Address", msgRecvHandling.getPEID());
				respJsonObject.put("IFID", msgRecvHandling.getInterfaceID());
				respJsonObject.put("Version", "1.0");
				respJsonObject.put("IsResponse", true);
				respJsonObject.put("SessionID", msgRecvHandling.getSessionID());
				respJsonObject.put("IsData", true);
				respJsonObject.put("RequireResponse", false);
				JSONObject dataJsonObject = new JSONObject();
				dataJsonObject.put("Result", peInfoCheck);
				respJsonObject.put("Data", dataJsonObject);
				
				DatagramServerUtil.sendInfo(msgRecvHandling.getPEID(), ctx, address, respJsonObject.toString());
			}
		}
		else {
			JSONObject respJsonObject = new JSONObject();
			respJsonObject.put("Address", msgRecvHandling.getPEID());
			respJsonObject.put("IFID", msgRecvHandling.getInterfaceID());
			respJsonObject.put("Version", "1.0");
			respJsonObject.put("IsResponse", true);
			respJsonObject.put("SessionID", msgRecvHandling.getSessionID());
			respJsonObject.put("IsData", true);
			respJsonObject.put("RequireResponse", false);
			JSONObject dataJsonObject = new JSONObject();
			dataJsonObject.put("Result", "ERROR: pe not login! ");
			respJsonObject.put("Data", dataJsonObject);
			
			DatagramServerUtil.sendInfo(msgRecvHandling.getPEID(), ctx, address, respJsonObject.toString());
		}
	}
	
	/**
	 * Check whether received messages are safety
	 * @param msgRecvHandling
	 * @param address InetSocketAddress receiving messages
	 */
	private static void messageCheck(MsgRecvHandling msgRecvHandling, InetSocketAddress address){
		ChannelHandlerContext peCtx = ServerUtil.getChannelHandlerContext(msgRecvHandling.getPEID());
		if (peCtx == null){
			msgRecvHandling.setIsSafe(false);
		}
		else {
			InetSocketAddress peSocketAddress = (InetSocketAddress)peCtx.channel().remoteAddress();
			if (!(peSocketAddress.getAddress().getHostAddress().equalsIgnoreCase(address.getAddress().getHostAddress()))){
				System.out.println("PE: "+msgRecvHandling.getPEID()+" has different TCP and UDP address! ");
				msgRecvHandling.setIsSafe(false);
			}
			else {
				System.out.println("PE: "+msgRecvHandling.getPEID()+" has same TCP and UDP address! ");
			}
		}
	}
	
	/**
	 * Check whether the pe status is correct
	 * @param peID
	 * @return check result
	 */
	private  String peInfoCheckDatabase(String peID){
		Pe pe = socketPeService.getPeByPeID(peID);
		if (pe != null){
			int state = pe.getPeState();
			if (state == 1){
				return "SUCCESS";
			}
			else {
				return "ERROR: pe state error! ";
			}
		}
		else {
			return "ERROR: pe not exists! ";
		}
	}
	
	/**
	 * Send Information to PE
	 * @param peID the peID that information send to 
	 * @param ctx handler ChannelHandlerContext
	 * @param address InetSocketAddress which needs to send messages
	 * @param msg messages to send
	 */
	private static void sendInfo(String peID, ChannelHandlerContext ctx, InetSocketAddress address,String msg){
		ByteBuf temp = Unpooled.copiedBuffer(msg.getBytes());
		synchronized(ctx){
			ctx.writeAndFlush(new DatagramPacket(temp,address));
		}
		System.out.println("UDP Send to PEID: "+peID+" , messages: "+msg);
	}
	
}
