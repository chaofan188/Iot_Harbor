package com.cetc.iot.accesssystem.downinterface.protocol.ezviz;

import java.util.List;
import java.util.Map;

import com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam;
import com.cetc.iot.database.model.EzvizCamera;
import com.cetc.iot.harbormanage.service.EzvizCameraService;
import com.cetc.iot.servicesystem.util.GetBean;

public class EzvizCamInitializer {
	
	private EzvizCameraService ezvizCameraService = (EzvizCameraService)GetBean.getBean("ezvizCameraService");

	public EzvizCamInitializer(String peID, String interfaceID) {
		EzvizCamera ezvizCamera = new EzvizCamera();
		ezvizCamera.setPe_id(peID);
		ezvizCamera.setInterface_id(interfaceID);
		List<Map<String, Object>> list = ezvizCameraService.query(ezvizCamera, -1, -1);
		
		EzvizCam.camList.put(peID + interfaceID, 
				new EzvizCam(peID, interfaceID) );
	}
}
