package com.cetc.iot.servicesystem.util;

import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.VETemplateService;

public class VeUtil {
	
	public static String getTplClassName(String veid) {
		VEService vs = (VEService) GetBean.getBean("veService");
		VE ve = new VE();
		ve.setVe_id(veid);
		ve = vs.query(ve, 0, 1).get(0);
		VETemplateService vts = (VETemplateService) GetBean.getBean("veTplService");
		//VEWebsocketServiceService ss = (VEWebsocketServiceService) GetBean.getBean("veWebsocketServiceService");
		VETemplate vetemplate = new VETemplate();
		vetemplate.setTemplate_id(ve.getTemplate_id());
		vetemplate = vts.query(vetemplate, 0, 1).get(0);
		
		return vetemplate.getTpl_classpath();
	}
}
