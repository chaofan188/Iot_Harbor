package com.cetc.iot.servicesystem.util;

import java.util.UUID;

public class VeServiceKey {

	public static String getKey(){
		return UUID.randomUUID().toString();
	}
}
