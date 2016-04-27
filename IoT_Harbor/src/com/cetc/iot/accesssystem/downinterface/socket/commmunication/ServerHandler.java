package com.cetc.iot.accesssystem.downinterface.socket.commmunication;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Socket Handler
 * @author xzc
 * Create Time: 2014-09-09
 * Author: xzc
 * Details: Add channelRead function for messages reading
 * 			Add handlerAdded function for new connections detected handling
 * 			Add handlerRemoved function for connections removed handling
 * 			Add exceptionCaught function for exception caught handling
 * @deprecated this class is not used since 2nd project, use ServerHandlerNew instead
 *
 */
@Deprecated 
public class ServerHandler extends ChannelHandlerAdapter{
	
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String recvMsg = (String) msg;
		System.out.println(FORMAT.format(new Date())+" TCP Server Received Message: "+recvMsg+ctx.name()+"@"+ctx.hashCode());
		ServerUtil.read(recvMsg,ctx);
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx){
		System.out.println(FORMAT.format(new Date())+" TCP New Connection Detected! ctx info: "+ctx.name()+"@"+ctx.hashCode());
		InetSocketAddress peSocketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
		System.out.println("Address: "+peSocketAddress.getAddress()+" Port: "+peSocketAddress.getPort());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx){
		System.out.println(FORMAT.format(new Date())+" TCP Connection Removed Detected! ctx info: "+ctx.name()+"@"+ctx.hashCode());
		new ServerUtil().remove(ctx);
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		System.out.println(FORMAT.format(new Date())+" TCP Exception Caught! ctx info: "+ctx.name()+"@"+ctx.hashCode());
		cause.printStackTrace();
		new ServerUtil().remove(ctx);
		ctx.close();
	}
}
