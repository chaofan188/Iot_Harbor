package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import org.apache.log4j.Logger;

/**
 * netty: Datagram Socket Server class
 * 
 * @author xzc 
 * Create Time: 2014-09-11 
 * Author: xzc 
 * Details: Datagram socket server based on netty structure.
 * 
 */
public class DatagramServer {
	
	private static Logger logger = Logger.getLogger(DatagramServer.class.getName());
	
	public void run(int port) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioDatagramChannel.class)
					.option(ChannelOption.SO_BROADCAST, true)
					.handler(new DatagramServerHandlerTest());
			logger.info("UDP Server Starts At Port: "+port);
			b.bind(port).sync().channel().closeFuture().await();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 8801;
		new DatagramServer().run(port);
	}
}
