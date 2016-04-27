package com.cetc.test.xiruitest.commonstest.base64;

import org.apache.commons.codec.binary.Base64;

public abstract class Base64Coder {
	public static final String ENCODING = "ISO-8859-1";
	public static String encode(String data) throws Exception {
		byte[] b = Base64.encodeBase64(data.getBytes(ENCODING));
		return new String(b,ENCODING);
	}
	public static String encodeSafe(String data) throws Exception {
		byte[] b = Base64.encodeBase64(data.getBytes(ENCODING),true);
		return new String(b,ENCODING);
	}
	public static String decode(String data) throws Exception {
		byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));
		return new String(b,ENCODING);
	}
}
