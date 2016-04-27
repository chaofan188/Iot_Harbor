package com.cetc.iot.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;


public class PeUtil {
	public static boolean valueCheckPeLifecycle(String lifeCycle) {
		if (lifeCycle == "0" || lifeCycle == "1" || lifeCycle == "2") {
			return true;
		} else {
			return false;
		}
	}

	public static boolean valueCheckPeState(String pe_state) {
		if (pe_state == "0" || pe_state == "1" || pe_state == "2") {
			return true;
		} else {
			return false;
		}
	}
	
	public static String listToJson(List list) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rows", list);
		map.put("count", list.size());
		JSONObject json = JSONObject.fromObject(map);

		return json.toString();
	}
}
