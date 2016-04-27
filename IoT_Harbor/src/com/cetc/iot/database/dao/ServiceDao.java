package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.VeService;


public interface ServiceDao {
	
	public boolean insert(VeService service);
	
	public boolean deleteAll();
	
	public boolean deleteByTplId(int tpl_id);
	
	public boolean deleteByServiceId(String service_ID);
	
	public boolean update(VeService service);
	
	public List<Map<String, Object>> getAllServices();
	
	public List<Map<String, Object>> getServiceByTplId(int tpl_id);
	
	public Map<String, Object> getServiceByServiceId(String service_ID);
}
