package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.downinterface.protocol.base.AutoMachine;
import com.cetc.iot.accesssystem.downinterface.protocol.base.Packet;
import com.cetc.iot.util.StringOperation;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


/**
 * this class is used to receive messages from terminals and send messages to terminals
 * @author xzc
 * Create Time: 2015-04-05
 * Author: xzc
 * Details: Add read function for receiving messages from terminals
 *          Add send function for sending messages to terminals
 *          Add addPeCtxInfo function for adding the relation between peID and ctx
 *          Add removePeCtxInfoByPeID function for deleting the relation between peID and ctx by peID
 *          Add removePeCtxInfoByCtx function for deleting the relation between peID and ctx by ctx
 *
 */
public class ServerUtilNew {
	
	private static final String ENCODING = "ISO-8859-1";
	private static Logger logger = Logger.getLogger(ServerUtilNew.class.getName());
	/**
	 * concurrentHashMap for peID and ctx
	 */
	private static Map<String,ChannelHandlerContext> pe_ctx_map = new ConcurrentHashMap<String, ChannelHandlerContext>();
	/**
	 * multimap for ctx and peID
	 */
	private static Multimap<ChannelHandlerContext, String> ctx_pe_map = ArrayListMultimap.create();
	/**
	 * ReadWriteLock used for the safety of multimap ctx_pe_map
	 */
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	
	/**
	 * this function is used to handle received messages
	 * @param message received messages
	 * @param ctx channel which received messages
	 */
	public void read(String message, ChannelHandlerContext ctx){
		Packet packet = new Packet(message);
		AutoMachine.getPeAutoMachine(packet.head.peID).action(packet,"TCPServer",ctx);
	}
	
	/**
	 * this function is used to send messages
	 * in this function, maybe exception will happen
	 * exception handling is in class ServerHandlerNew 
	 * @param message messages to send
	 * @param peID destination of the messages to send
	 * @return send ok or not 
	 */
	public boolean send(String message, String peID){
		ChannelHandlerContext ctx = pe_ctx_map.get(peID);
		if (ctx == null){
			return false;
		}
		try {
			ByteBuf resp = Unpooled.copiedBuffer(message.getBytes(ENCODING));
			synchronized(ctx){
				ctx.writeAndFlush(resp);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		logger.info("TCP Send to peID Hex String: "+StringOperation.stringToHexString(message));
		return true;
	}
	
	/**
	 * this function is used to add information between peID and ctx
	 * when terminals log in, the function will be called
	 * @param peID
	 * @param ctx
	 */
	public static void addPeCtxInfo(String peID,ChannelHandlerContext ctx){
		lock.writeLock().lock();
		pe_ctx_map.put(peID, ctx);
		ctx_pe_map.put(ctx, peID);
		lock.writeLock().unlock();
	}
	
	/**
	 * this function is used to remove information between peID and ctx by peID
	 * when terminals log out, the function will be called
	 * @param peID
	 */
	public static void removePeCtxInfoByPeID(String peID) {
		lock.writeLock().lock();
		ctx_pe_map.remove(pe_ctx_map.get(peID), peID);
		pe_ctx_map.remove(peID);
		lock.writeLock().unlock();
	}
	
	/**
	 * this function is used to remove information between peID and ctx by ctx
	 * when ctx removed or ctx exception, this function will be called
	 * @param ctx
	 */
	public static void removePeCtxInfoByCtx(ChannelHandlerContext ctx){
		lock.writeLock().lock();
		Collection<String> collection = ctx_pe_map.get(ctx);
		Iterator<String> it = collection.iterator();
		while (it.hasNext()){
			String peID = it.next();
			pe_ctx_map.remove(peID);
		}
		ctx_pe_map.removeAll(ctx);
		lock.writeLock().unlock();
	}
	
	
}
