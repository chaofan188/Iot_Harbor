package com.cetc.test.xiruitest;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ThreadLocalRandom;

public class ChineseProverbServerHandler extends
		SimpleChannelInboundHandler<DatagramPacket> {
	private static final String[] DICTIONARY = { "ֻҪ���������ĥ���롣",
			"��ʱ��л��ǰ�࣬����Ѱ�����ռҡ�", "�������������ʣ�һƬ�����������", "һ�����һ��𣬴������������",
			"����������־��ǧ���ʿĺ�꣬׳�Ĳ��ѣ�" };

	private String nextQuote() {
		int quoteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
		return DICTIONARY[quoteId];
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, DatagramPacket packet)
			throws Exception {
		String req = packet.content().toString(CharsetUtil.UTF_8);
		System.out.println(req);
		if ("�����ֵ��ѯ��".equals(req)) {
			ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(
					"�����ѯ�����" + nextQuote(), CharsetUtil.UTF_8), packet
					.sender()));
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
		cause.printStackTrace();
	}

}
