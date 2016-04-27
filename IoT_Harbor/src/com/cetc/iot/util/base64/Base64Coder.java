package com.cetc.iot.util.base64;

import org.apache.commons.codec.binary.Base64;
/**
 * this class is used for base64 algorithm
 * @author IotServer
 *
 */
public abstract class Base64Coder {
	public static final String ENCODING = "ISO-8859-1";
	/**
	 * base64 encode function
	 * @param data
	 * @return string after base64 encode
	 * @throws Exception
	 */
	public static String encode(String data) throws Exception {
		byte[] b = Base64.encodeBase64(data.getBytes(ENCODING));
		return new String(b,ENCODING);
	}
	
	public static String encode(byte[] data) {
		byte[] b = Base64.encodeBase64(data);
		try {
			String result = new String(b,ENCODING);
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String encodeSafe(String data) throws Exception {
		byte[] b = Base64.encodeBase64(data.getBytes(ENCODING),true);
		return new String(b,ENCODING);
	}
	/**
	 * base64 decode function
	 * @param data
	 * @return string after base64 decode
	 * @throws Exception
	 */
	public static String decode(String data) throws Exception {
		byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));
		return new String(b,ENCODING);
	}
}
