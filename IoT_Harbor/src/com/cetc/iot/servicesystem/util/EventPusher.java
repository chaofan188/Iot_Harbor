package com.cetc.iot.servicesystem.util;

import com.cetc.iot.util.BaseUtil;

import net.sf.json.JSONObject;

public class EventPusher {
	public JSONObject MessageFromBroswer = null;
	private static final int DataNotExist = -274;
	private boolean flag = true;
	public int minValue ;
	public int maxValue ;
	public boolean isAlways ;
	
	
	public EventPusher(JSONObject MessageFromBroswer){
		this.MessageFromBroswer = MessageFromBroswer;
		 this.minValue = BaseUtil.isNotEmpty((String) MessageFromBroswer.get("min"))?Integer.parseInt((String)(MessageFromBroswer.get("min"))):DataNotExist;
		 this.maxValue = BaseUtil.isNotEmpty((String) MessageFromBroswer.get("max"))?Integer.parseInt((String)(MessageFromBroswer.get("max"))):DataNotExist;
		
		//this.minValue =  Integer.parseInt((String)(MessageFromBroswer.get("min")));
		//this.maxValue =  Integer.parseInt((String)(MessageFromBroswer.get("max")));
		 if(MessageFromBroswer.get("isAlways")==null){
			 this.isAlways = false;
		 }else{
			 this.isAlways = !MessageFromBroswer.get("isAlways").equals("true")?true:false;
		 }
//		this.isAlways = !MessageFromBroswer.get("isAlways").equals("true")?true:false;
	}
	//判断区间是否合法
	public JSONObject eventPushDealer(JSONObject Message2Broswer){
		if(minValue!=DataNotExist&&maxValue!=DataNotExist&&minValue<maxValue){
			String temp = eventPushHelper(Message2Broswer);
			if(temp!=null)
				Message2Broswer.put("warning", temp);
		}
		return Message2Broswer;
	}
	//判断数据是否在区间内
	public  String eventPushHelper(JSONObject Message2Broswer){
		//获得当前值
	 int value = (Integer) Message2Broswer.get("temperature");
		//满足isAlways是true一直提醒，是false只提醒一次
		if(isAlways==true||flag!=isAlways){
			if(value>=minValue&&value<=maxValue){
				flag = false;
				return "数据已在定制范围之间！";
			}
		}
		return null;
	}
	
	
}
