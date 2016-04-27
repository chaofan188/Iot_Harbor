package com.cetc.iot.accesssystem.downinterface.protocol.onvif;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.OnvifCamera;
import com.cetc.iot.harbormanage.service.OnvifCameraService;
import com.cetc.iot.servicesystem.util.GetBean;

public class OnvifCamInitializer {
	
	private OnvifCameraService onvifCameraService = (OnvifCameraService)GetBean.getBean("onvifCameraService");

	public OnvifCamInitializer(String peID, String interfaceID) {
		OnvifCamera cam = new OnvifCamera();
		cam.setPe_id(peID);
		cam.setInterface_id(interfaceID);
		List<Map<String, Object>> list = onvifCameraService.query(cam, -1, -1);
		
		OnvifCam.camList.put(peID + interfaceID, 
				new OnvifCam((String)list.get(0).get("ipv4"), 
				(String)list.get(0).get("username"), (String)list.get(0).get("password"), peID, interfaceID));
	}
}
