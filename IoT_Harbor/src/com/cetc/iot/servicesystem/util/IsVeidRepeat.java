package com.cetc.iot.servicesystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cetc.iot.servicesystem.service.VEService;

@Component
public class IsVeidRepeat {
	@Autowired
	private static VEService veService;
	
	
	public static boolean isVeidRepeat(String veid){
		if(veService.getVeByVeId(veid)!=null){
			return false;
		}
		return true;
	}
}
