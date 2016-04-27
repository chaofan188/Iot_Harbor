package com.cetc.iot.servicesystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.servicesystem.service.VETemplateService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class VeTemplateStateChangedManagement {
//状态码  001 正常 002异常  003冻结
	public static Multimap<String, String> reasonableVETemplateStateTransformMap=ArrayListMultimap.create();
	static{
		reasonableVETemplateStateTransformMap.put("001", "002");
		reasonableVETemplateStateTransformMap.put("001", "003");
		reasonableVETemplateStateTransformMap.put("003", "001");
		reasonableVETemplateStateTransformMap.put("003", "002");
	}
	@Autowired
	private static VETemplateService veTemplateService; 

	public static boolean CheckVeTemplateStateChangedIsReasonable(String veTemplateId,String newState){
		VETemplate templateMap =veTemplateService.getTemplateByTemplateId(veTemplateId);
		String oldState = (String) templateMap.getTpl_state();
		
		if(reasonableVETemplateStateTransformMap.containsEntry(oldState, newState)){
			
			return true;
			
		}
		else 
			
			return false;
	}
	


}
