package com.cetc.iot.servicesystem.util;

import net.sf.json.JSONObject;

public class EventSubscriber {
	public static boolean eventSubscribeHelper(JSONObject param,Float temperature){
		if(param.containsKey("minValue")&&!param.containsKey("maxValue")){
			float minValue = Float.parseFloat(((String)param.get("minValue")));
			if(temperature<minValue){
				return true;
			}
		}else if(!param.containsKey("minValue")&&param.containsKey("maxValue")){
			float maxValue = Float.parseFloat(((String)param.get("maxValue")));
			if(temperature>maxValue){
				return true;
			}
		}else if(param.containsKey("minValue")&&param.containsKey("maxValue")){
			float minValue = Float.parseFloat(((String)param.get("minValue")));
			float maxValue = Float.parseFloat(((String)param.get("maxValue")));
			if(temperature>minValue&&temperature<maxValue){
				return true;
			}
		}else{
			return false;
		}
		return false;
		
	}
}
