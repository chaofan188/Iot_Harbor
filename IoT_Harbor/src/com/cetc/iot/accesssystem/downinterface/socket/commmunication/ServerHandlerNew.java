package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.cetc.iot.util.StringOperation;

/**
 * TCP socket handler for new protocol
 * @author xzc
 * Create Time: 2015-04-05
 * Details: all function will call function in ServerUtilNew
 *
 */
public class ServerHandlerNew extends ChannelHandlerAdapter{
	
	private static ExecutorService exec = Executors.newFixedThreadPool(500);
	
	private static Logger logger = Logger.getLogger(ServerHandlerNew.class.getName());
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String recvMsg = (String) msg;
		logger.info("TCP Receive Hex Message: "+StringOperation.stringToHexString(recvMsg));
		new ServerUtilNew().read(recvMsg,ctx);
//		exec.execute(new MessageHandler(recvMsg,ctx));
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx){
		InetSocketAddress peSocketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
		logger.info("TCP Connected! Address: "+peSocketAddress.getAddress()+" Port: "+peSocketAddress.getPort());
		logger.info("ctx info: "+ctx.hashCode());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx){
		logger.info("TCP Removed! ctx info: "+ctx.hashCode());
		ServerUtilNew.removePeCtxInfoByCtx(ctx);
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		logger.info("TCP Exception Caught! ctx info: "+ctx.hashCode());
		cause.printStackTrace();
		ServerUtilNew.removePeCtxInfoByCtx(ctx);
		ctx.close();
	} 
	
	private class MessageHandler implements Runnable {
		private String message;
		private ChannelHandlerContext ctx;
		public MessageHandler (String message,ChannelHandlerContext ctx) {
			this.message = message;
			this.ctx = ctx;
		}
		public void run() {
			new ServerUtilNew().read(message,ctx);
		}
	}
}
