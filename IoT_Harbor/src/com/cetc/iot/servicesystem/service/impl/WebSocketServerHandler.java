package com.cetc.iot.servicesystem.service.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

	
	private WebSocketServerHandshaker handshaker;
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		//传统的HTTP接入
		if (msg instanceof FullHttpRequest){
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		}
		//WebSocket接入
		else if (msg instanceof WebSocketFrame){
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
		
	}
	
	@Override
	public void channelReadComplete (ChannelHandlerContext ctx) throws Exception {
		
		ctx.flush();
	}
	
	private void handleHttpRequest (ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
		
		//如果HTTP解码失败，返回HTTP异常
//		if (!req.getDecoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
//			
//			sendHttpResponse(ctx, req, new DefaultFullHttpRequest(HTTP, BAD_REQUEST));
//			return;
//		}
		
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost" + "websocketInterface", null, false);
		handshaker = wsFactory.newHandshaker(req);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), req);
		}
	}
	
	private void handleWebSocketFrame (ChannelHandlerContext ctx, WebSocketFrame frame) {
		
		//判断是否是关闭链路的指令
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		//判断是否是Ping消息
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
		}
		//本例子仅支持文本消息，不支持二进制消息
		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(String.format("%s fram types not supported", frame.getClass().getName()));
		}
		
		//返回应答消息
		String request = ((TextWebSocketFrame) frame).text();
		ctx.channel().write(new TextWebSocketFrame(request + " ,欢迎使用Netty Websocket服务，现在时刻："));
		
	}
	
	private static void sendHttpResponse (ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
		
		//返回应答给客户端
		if (res.status().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
			
		}
		
		//如果是非keep－Alive，关闭连接
	}
	
	public void exceptionCaught (ChannelHandlerContext ctx) throws Exception{
		ctx.close();
	}
	
	
}
