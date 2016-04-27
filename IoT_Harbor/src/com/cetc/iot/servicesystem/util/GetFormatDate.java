package com.cetc.iot.servicesystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GetFormatDate {
	public static String getFormatDate(Date time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINESE);
		String str="";
		if(time!=null){
			str = sdf.format(time);
		}
		return str;
	}
}
