package com.cetc.iot.accesssystem.downinterface.socket.commmunication;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;

/**
 * netty: Socket Server class
 * @author xzc
 * Create Time: 2014-09-09
 * Author: xzc
 * Details: Socket server based on netty structure
 * Update Time: 2015-04-05
 * Author: xzc
 * Details: change netty decoders, use LengthFieldBasedFrameDecoder
 */
public class Server {
	
	private static Logger logger = Logger.getLogger(Server.class.getName());
	
	private static final EventExecutorGroup executor = new DefaultEventExecutorGroup(500);
	
	public void bind(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.option(ChannelOption.SO_RCVBUF, 65536)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel sc) throws Exception {
							sc.pipeline().addFirst("decoder",new LengthFieldBasedFrameDecoder(1000000, 0, 2, -2, 0));
							sc.pipeline().addLast("decoderstring",new StringDecoder(Charset.forName("ISO-8859-1")));
//							sc.pipeline().addLast(new ServerHandlerNew());
							sc.pipeline().addLast(executor, new ServerHandlerNew());
						}
					});
			ChannelFuture f = b.bind(port).sync();
			logger.info("TCP Server Starts At Port: "+port);
			ChannelConfig channelConfig = f.channel().config();
			System.out.println(channelConfig.getOptions());
			f.channel().closeFuture().sync();
			
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args)throws Exception{
		int port = 8800;
		new Server().bind(port);
	}
	
	
}
