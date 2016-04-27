package com.cetc.iot.accesssystem.util;

import java.security.MessageDigest;
import java.util.Random;
import java.security.SecureRandom;

public class SessionIDGenerator {

	 static char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F'};
	 public static String generateSessionID(){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//suggested by wanghu for security on20160328
		//new SecureRandom().nextInt();
		int rand = new Random().nextInt();
		md.update(Integer.toString(rand).getBytes());
	
		byte[] buf = md.digest();
		int j = buf.length;
		char str[] = new char[j*2];
		int k = 0;
		for(int i = 0; i<j; i++){
			byte byte0 = buf[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}  
		return new String(str);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String result = SessionIDGenerator.generateSessionID();
		// TODO Auto-generated method stub
		System.out.println(result);
	}

}
