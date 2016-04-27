package com.cetc.iot.servicesystem.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertVeId {
	public static String simplifyVeId(String veId){
		String complexVeId = veId;
		if(!complexVeId.contains("/")){
			return complexVeId;
		}else{
			String a[] = complexVeId.split("/");
			if(a.length>2){
				String regEx="[^0-9]";   
				Pattern p = Pattern.compile(regEx);   
				Matcher m = p.matcher(complexVeId);   
				return  m.replaceAll("").trim();
				}else{
					return a[1];
				}
			}
		}
		
	
}
