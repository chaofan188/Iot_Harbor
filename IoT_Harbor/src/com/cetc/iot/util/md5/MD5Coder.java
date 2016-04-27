package com.cetc.iot.util.md5;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class MD5Coder {
	public static String encodeMD5Hex(String data){
		try {
			return DigestUtils.md5Hex(data.getBytes("ISO-8859-1"));
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
