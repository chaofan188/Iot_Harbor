package com.cetc.iot.servicesystem.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cetc.iot.util.BaseUtil;

/**
 * VE相关所有ID的生成
 * @author chengk
 *
 */

public class BuildIDUtil {
	
	private static DateFormat df = new SimpleDateFormat("YYMMddHHmmss");
	private static Date date = new Date();
	private static String ip = BaseUtil.getLocalIP();
	private static int start = 1000;
	private static int seq = 9999;
	
	public static String build(String key){
		
		String ips = ip.replace(".", "");
		String business = GetProperty.getValue(key);
		StringBuffer sb = new StringBuffer();
		sb.append(business);
		sb.append(df.format(date));
		sb.append(ips);
		if(start == seq){
			start = 1000;
			sb.append(start);
		}else {
			start++;
			sb.append(start);
		}
	//System.out.println(sb);
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		//BuildIDUtil a = new BuildIDUtil();
		System.out.println(BuildIDUtil.build("bind"));
		System.out.println(BuildIDUtil.build("bind"));
		System.out.println(BuildIDUtil.build("bind"));
		System.out.println(BuildIDUtil.build("bind"));
		System.out.println(BuildIDUtil.build("bind"));
		System.out.println(BuildIDUtil.build("bind"));
		System.out.println(BuildIDUtil.build("bind"));
		System.out.println(BuildIDUtil.build("bind"));
		BuildIDUtil.build("bind");
		BuildIDUtil.build("ve");
		BuildIDUtil.build("bind");
		BuildIDUtil.build("ve");
		BuildIDUtil.build("bind");
		BuildIDUtil.build("bind");
		BuildIDUtil.build("ve");
	}
}
