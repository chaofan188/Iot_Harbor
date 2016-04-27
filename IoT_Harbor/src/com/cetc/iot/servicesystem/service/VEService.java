package com.cetc.iot.servicesystem.service;

import java.util.List;
import java.util.Map;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VEAndTplInfo;
import com.cetc.iot.database.model.VeGeoLocation;

public interface  VEService {
	
	public List<VE> query(VE ve, int page, int size);

	public String delete(String veId);

	public String add(VE ve , VeGeoLocation veGeolocation);
//veGeolocation可以为空
	public String update(VE ve,VeGeoLocation veGeolocation);
	
	public int queryCOUNTNUM(VE ve);
	
	public VE getVeByVeId(String veid);
	
	public String updateState(String veId,String newState);
	
	public List<VE> getVeByTemplateId(String templateId);
	
	public List<VEAndTplInfo> getVEAndTplInfoByCreatorID(String userId);
	
	public List<Map<String,Object>> queryALLVeForMap(); 
	
}
