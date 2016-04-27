package com.cetc.test.xiruitest.commonstest.base64;

import org.apache.commons.codec.binary.Base64;

public class Base64CoderTest {
	public static void main (String [] args) throws Exception{
//		String inputString = "abced67890123asdfaa0123456789012";
//		System.out.println("原文："+inputString);
//		System.out.println("length: "+inputString.length());
//		String code = Base64Coder.encode(inputString);
//		System.out.println("编码后："+code);
//		System.out.println("length: "+code.length());
//		String outputSting = Base64Coder.decode(code);
//		System.out.println("解码后："+outputSting);
//		System.out.println(inputString.equals(outputSting));
//		//System.out.println(Base64.encodeBase64String(inputString.getBytes()));
		String input = "czAwOGcxeVMxSGhhQnl8MTEyMTMyMzcwfEFuZHJvaWRfMy4zNzNfMzYwLjM2MC4wLWhhbGw4LjM2MC5yaWNofDg=";
		System.out.println(Base64Coder.decode(input));
	}
}
