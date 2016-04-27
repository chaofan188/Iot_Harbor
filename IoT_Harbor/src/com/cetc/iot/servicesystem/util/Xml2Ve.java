package com.cetc.iot.servicesystem.util;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import com.cetc.iot.database.model.VE;


public class Xml2Ve {
	public static VE xml2Ve(String vePsdl){
		VE ve = new VE();
		xml2Doc(vePsdl);
		return ve;
	}
	
	public static List<String>  xml2VEContains(String vePsdl){
		List<String> veidList = new ArrayList<String>();
		return veidList;
	}
	private static Document xml2Doc(String vePsdl){
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(vePsdl);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
