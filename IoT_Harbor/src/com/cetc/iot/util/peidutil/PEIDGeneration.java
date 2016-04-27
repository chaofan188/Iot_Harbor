package com.cetc.iot.util.peidutil;

import org.apache.log4j.Logger;

import com.cetc.iot.servicesystem.service.impl.AtomicRegister;
import com.cetc.iot.util.ESPConfig;
import com.cetc.iot.util.md5.MD5Coder;

/**
 * this class is used to generate peid 
 * @author xzc
 * Create Time: 2015-06-23
 * Author: xzc
 * Details: Add generate function to generate peID by deviceID and harborName
 *
 */
public class PEIDGeneration {
	
	private static Logger logger = Logger.getLogger(PEIDGeneration.class.getName());
	
	/**
	 * this function is used to generate peID by deviceID and harborName
	 * @param deviceID
	 * @param harborName
	 * @return peID generated
	 */
	public static String generate(String deviceID, String harborName){
		String peID = MD5Coder.encodeMD5Hex(deviceID+"@"+harborName);
		logger.info(peID);
		return peID;
	}
	public static void main (String[] args){
		PEIDGeneration.generate("testluanma", ESPConfig.getValue("harborName"));
		System.out.println(ESPConfig.getValue("harborName"));
	}
}
