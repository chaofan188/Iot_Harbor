package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import org.apache.log4j.Logger;

/**
 * Monitor TCP Server Class
 * @author xzc
 * Create Time: 2014-12-09
 * Author: xzc
 * Details: Monitor TCP Server based on netty structure
 *
 */
public class MonitorSocket {
	
	private static Logger logger = Logger.getLogger(MonitorSocket.class.getName());
	
	public void bind(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel sc) throws Exception {
							sc.pipeline().addLast(new LineBasedFrameDecoder(1024));
							sc.pipeline().addLast(new StringDecoder());
							sc.pipeline().addLast(new MonitorSocketHandler());
						}
					});

			ChannelFuture f = b.bind(port).sync();
			logger.info("Monitor TCP Server Starts At Port: "+port);
			f.channel().closeFuture().sync();
			
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
