package com.cetc.test.xiruitest;

public class TestByte {
	public static void test(byte[] hello){
		hello = "98765".getBytes();
		for (byte i:hello){
			System.out.println(i);
		}
	}
	
	public static void main (String[] args){
		byte[] test = "12345".getBytes();
		System.out.println(test);
		for (byte i:test){
			System.out.println(i);
		}
		TestByte.test(test);
		System.out.println(test);
		for (byte i:test){
			System.out.println(i);
		}
	}
}
