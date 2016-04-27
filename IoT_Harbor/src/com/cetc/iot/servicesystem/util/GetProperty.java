package com.cetc.iot.servicesystem.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;




public class GetProperty {

	private final static String  context = "esp.properties";
	private static Properties prop = new Properties();
	
	
	static {
		InputStream is = GetProperty.class.getClassLoader().getResourceAsStream(context);
		try {
			prop.load(is);
			is.close();
		} catch (IOException e) {
			System.out.println("No properties defined error!");
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return prop.getProperty(key);
	}
	
	
	
	
	
//	public  String getProperty(String key){
////		InputStream is = this.getClass().getClassLoader().getResourceAsStream(context);
//		try {
//			prop.load(is);
//			is.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return prop.getProperty(key);
//	}

 }


