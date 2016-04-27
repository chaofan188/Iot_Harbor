package com.cetc.test.xiruitest.md5;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class MD5Coder {
	public static byte[] encodeMD5(String data) throws Exception {
		return DigestUtils.md5(data);
	}
	public static String encodeMD5Hex(String data) throws Exception {
		return DigestUtils.md5Hex(data.getBytes());
	}
}
