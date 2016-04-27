package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Datagram Socket Handler 
 * @author xzc
 * Create Time: 2014-09-11
 * Author: xzc
 * Details: Add channelRead function for packet reading
 *          Add exceptionCaught function for exception handling
 */
public class DatagramServerHandler extends ChannelHandlerAdapter {

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object packet)
			throws Exception {
		DatagramPacket recvPacket = (DatagramPacket)packet;
		String recv = recvPacket.content().toString(CharsetUtil.UTF_8);
		System.out.println(FORMAT.format(new Date())+" UDP Server Receive Message: "+recv+ctx.name()+"@"+ctx.hashCode());
		DatagramServerUtil.read(ctx, recv, recvPacket.sender());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		System.out.println(FORMAT.format(new Date())+" UDP Server Caught Exception! ctx info: "+ctx.name()+"@"+ctx.hashCode());
		cause.printStackTrace();
		ctx.close();
	}
}
