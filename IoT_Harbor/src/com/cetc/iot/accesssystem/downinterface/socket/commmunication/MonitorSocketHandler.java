package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * monitor TCP Server Handler
 * @author xzc
 * Create Time: 2014-12-09
 * Author: xzc
 * Details: add channelRead function for message reading
 *          add handlerAdded function for new connected request
 *          add handlerRemoved function for connection removed
 *
 */
public class MonitorSocketHandler extends ChannelHandlerAdapter {
private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String recvMsg = (String) msg;
		System.out.println(FORMAT.format(new Date())+" Monitor TCP Server Received Message: "+recvMsg+ctx.name()+"@"+ctx.hashCode());
		MonitorSocketUtil.read(recvMsg,ctx);
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx){
		System.out.println(FORMAT.format(new Date())+" Monitor TCP New Connection Detected! ctx info: "+ctx.name()+"@"+ctx.hashCode());
		InetSocketAddress socketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
		System.out.println("Address: "+socketAddress.getAddress()+" Port: "+socketAddress.getPort());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx){
		System.out.println(FORMAT.format(new Date())+" Monitor TCP Connection Removed Detected! ctx info: "+ctx.name()+"@"+ctx.hashCode());
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		System.out.println(FORMAT.format(new Date())+" TCP Exception Caught! ctx info: "+ctx.name()+"@"+ctx.hashCode());
		cause.printStackTrace();
		ctx.close();
	}
}
