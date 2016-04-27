package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayOutputStream;
import java.util.List;
/**
 * this class is used for receive message decoder
 * @author xzc
 * @deprecated this class is not used because Netty has Decoder
 */
@Deprecated
public class MyByteToStringDecoder extends ByteToMessageDecoder{
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		int length = in.readableBytes();
		if (length > 0){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (length > 0){
				byte tempByte = in.readByte();
				baos.write(tempByte);
				length--;
			}
			out.add(new String(baos.toByteArray(),"ISO-8859-1"));
		}
		/*int length = in.readableBytes();
		if (length > 0){
			StringBuilder temp = new StringBuilder("");
			while (length > 0){
				byte tempByte = in.readByte();
				temp.append((char)(tempByte&0xff));
				length--;
			}
			out.add(new String(temp));
		}*/
	}
}
