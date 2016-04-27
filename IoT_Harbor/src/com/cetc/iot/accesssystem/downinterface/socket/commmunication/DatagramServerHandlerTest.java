package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class DatagramServerHandlerTest extends ChannelHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object packet)
			throws Exception {
		DatagramPacket recvPacket = (DatagramPacket)packet;
		String recv = recvPacket.content().toString(CharsetUtil.UTF_8);
		new DatagramServerUtilTest().read(ctx, recv, recvPacket.sender());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}
}
