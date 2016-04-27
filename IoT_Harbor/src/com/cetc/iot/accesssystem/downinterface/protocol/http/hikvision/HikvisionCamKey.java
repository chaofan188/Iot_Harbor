package com.cetc.iot.accesssystem.downinterface.protocol.http.hikvision;

import com.cetc.iot.accesssystem.downinterface.protocol.interfacekey.InterfaceOperationKey;

/**
 * this class is used to check the params
 * @author xzc
 * Create Time: 2015-05-28
 * Author: xzc
 * Details: implements checkKey method to check the params
 */
public class HikvisionCamKey implements InterfaceOperationKey{
	private static final String[] keyStrings = {"zoom_up","zoom_down","focus_up","focus_down",
			"iris_up","iris_down","view_up","view_down","view_left","view_right",
			"view_up_left","view_down_left","view_up_right","view_down_right",
			"set_speed","set_delay"}; 
	
	@Override
	public boolean checkKey(String key){
		if (key == null){
			return false;
		}
		for (String temp : keyStrings){
			if (key.equals(temp)){
				return true;
			}
		}
		return false;
	}
}
