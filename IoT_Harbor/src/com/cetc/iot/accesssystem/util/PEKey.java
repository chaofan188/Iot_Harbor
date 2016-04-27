package com.cetc.iot.accesssystem.util;

import java.util.UUID;


public class PEKey {
	
	public static String genKey(){
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		String reString = genKey();
		System.out.println(reString);
	}
}
