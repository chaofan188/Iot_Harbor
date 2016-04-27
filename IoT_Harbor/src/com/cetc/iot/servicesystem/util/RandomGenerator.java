package com.cetc.iot.servicesystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomGenerator {
	public static String getRandomString(int length){
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++){
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	public static String getRandomStringByDateTime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		format.setLenient(false);
		return getRandomString(10) + format.format(new Date());
	}
	
	public static int getRandomInt(int start, int end){
		Random random = new Random();
		return random.nextInt(end - start) + start; 
	}
	
	public static int getRandomInt(){
		Random random = new Random();
		return random.nextInt();
	}
	
}