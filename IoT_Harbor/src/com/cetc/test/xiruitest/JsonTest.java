package com.cetc.test.xiruitest;

import java.util.Set;

import net.sf.json.JSONObject;

public class JsonTest {
	public static void main (String[] args){
		/*Map<String,String> tempMap = new LinkedHashMap<String,String>();
		tempMap.put("11111", "on");
		tempMap.put("22222","off");
		Map<String,Object> anotherMap = new LinkedHashMap<String,Object>();
		JSONObject jsonObject = JSONObject.fromObject(tempMap);
		System.out.println(jsonObject.toString());
		anotherMap.put("Address","gateway");
		anotherMap.put("Data", jsonObject);
		JSONObject json = JSONObject.fromObject(anotherMap);
		System.out.println(json.toString());
		System.out.println(json.get("Data"));*/
		JSONObject testJson = new JSONObject();
		testJson.put("Address","gateway");
		JSONObject testJson111 = new JSONObject();
		testJson111.put("111111", "on");
		testJson111.put("222222", "off");
		testJson.put("Data", testJson111);
		testJson.put("IsData",true);
		System.out.println(testJson);
		System.out.println(testJson.getString("Address"));
		System.out.println(testJson.getJSONObject("Data"));
		System.out.println(testJson.getBoolean("IsData"));
		@SuppressWarnings("unchecked")
		Set<JSONObject.Entry<String,String>> tempSet = testJson111.entrySet();
		for (JSONObject.Entry<String,String> temp:tempSet){
			System.out.println(temp.getKey());
			System.out.println(temp.getValue());
		}
	}
}
