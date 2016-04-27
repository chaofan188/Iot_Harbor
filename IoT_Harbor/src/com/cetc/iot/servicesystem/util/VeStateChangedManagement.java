package com.cetc.iot.servicesystem.util;


import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.impl.CallCenterSession;
import com.cetc.iot.util.GetData;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
@Component  
public  class VeStateChangedManagement {
	//虚拟物体状态(001为正常 002为未激活 003为未通过 004为异常 005为冻结)
	private static Multimap<String, String> reasonableVEStateTransformMap=ArrayListMultimap.create();
	@Autowired
	private static VEService veService; 
	static{
		reasonableVEStateTransformMap.put("002", "001");
		reasonableVEStateTransformMap.put("002", "003");
		reasonableVEStateTransformMap.put("001", "005");
		reasonableVEStateTransformMap.put("001", "004");
		reasonableVEStateTransformMap.put("005", "004");
		reasonableVEStateTransformMap.put("005", "001");
		reasonableVEStateTransformMap.put("001", "003");
		reasonableVEStateTransformMap.put("005", "003");
	}
	public static boolean CheckVeStateChangedIsReasonable(String veId,String newState){
		VE veMap =veService.getVeByVeId(veId);
		
		String oldState =  veMap.getVe_state();
		
		if(reasonableVEStateTransformMap.containsEntry(oldState, newState)){
			
			return true;
			
		}
		else 
			return false;
	}
	
	
	public static boolean checkVeIsInUse(String veId){
		return CallCenterSession.veidClientIdMappingMap.containsKey(veId);
	}
	
	
	public static void shutDownVeService(String veId){
		Collection<String> needDeleteClientId = CallCenterSession.veidClientIdMappingMap.get(veId);
		for (String temp : needDeleteClientId){
			GetData.close(temp);
			CallCenterSession.veidClientIdMappingMap.remove(veId, temp);
		}
	} 
}
