package com.cetc.test.xiruitest;

import com.cetc.iot.util.StringOperation;

public class TestLong {
	public static void main(String[] args) throws Exception{
		long time = System.currentTimeMillis();
//		String aaa = Long.toHexString(time);
//		String temp = null;
//		if (aaa.length()>=8){
//			temp = aaa.substring(aaa.length()-8,aaa.length());
//		}
//		System.out.println(time);
//		System.out.println(aaa);
//		System.out.println(temp);
//		String input = StringOperation.hexStringToString(temp);
//		System.out.println(input);
//		String output = StringOperation.stringToHexString(input);
//		System.out.println(output);
//		long result = StringOperation.stringToAscIILong(input);
//		System.out.println(result);
//		String hexTime = Long.toHexString(time);
//		hexTime = "0"+hexTime;
//		String timeString = StringOperation.hexStringToString(hexTime);
//		long result = StringOperation.stringToAscIILong(timeString);
//		System.out.println(time);
//		System.out.println(hexTime);
//		System.out.println(timeString);
//		System.out.println(result);
		System.out.println(time);

		System.out.println(Long.toHexString(time));
		System.out.println((int)'a');
	}
}
