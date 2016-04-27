package com.cetc.iot.servicesystem.util;

import java.util.HashMap;
import java.util.Map;

public class developerHelper {
	//存veid peid的对应关系  static表示现在又多少veid在被使用
	private  static Map<String, String> veidpeidRelation = new HashMap<String, String>();
	//正在使用的peidveid对应表
	private  static Map<String, String> onusing = new HashMap<String, String>();
		//1.查veid对应有多少个peid 将veid-peid 存入map或者多值map
	public void control(){
		
	}
}
