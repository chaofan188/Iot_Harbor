package com.cetc.test.xiruitest;

import java.io.UnsupportedEncodingException;

public class StringChange {
	public static void main(String[] args) throws UnsupportedEncodingException{
		String test = new String("����������netty����ͨ��������ݽ��ա�".getBytes("UTF-8"),"GBK");
		System.out.println("result: "+test);
	}
}
