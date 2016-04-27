package com.cetc.iot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * this class is used to load information from config.properties
 * @author xzc
 * Create Time: 2015-04-01
 * Author: xzc
 * Details: this class is used to get properties information
 */
public class SystemConfig {
	
	private static Logger logger = Logger.getLogger(SystemConfig.class.getName());
	
	private static final String CONTEXT = "config.properties";
	private static Properties pro = new Properties();
	static {
		InputStream is = SystemConfig.class.getClassLoader().getResourceAsStream(CONTEXT);
		try {
			pro.load(is);
			is.close();
		} catch (IOException e){
			logger.error("Load Properties Error! ");
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return pro.getProperty(key);
	}
	
}
