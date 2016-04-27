package com.cetc.iot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.cetc.iot.servicesystem.service.impl.AtomicRegister;

/**
 * this class is used to get information from esp.properties
 * @author xzc
 * Create Time: 2015-06-23
 * Author: xzc
 * Details: Add this class to get properties information
 */
public class ESPConfig {
	private static Logger logger = Logger.getLogger(ESPConfig.class.getName());
	private static final String CONTEXT = "esp.properties";
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
