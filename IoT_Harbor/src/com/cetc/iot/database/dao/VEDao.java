package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;
import com.cetc.iot.database.model.VE;


public interface VEDao {
	
	public List<VE> query(VE ve, int page, int size);
	
	public List<VE> queryVEALLINFO(VE ve, int page, int size);
	
	public String delete(VE ve);
	
	public String add(VE ve);
	
	public String update(VE ve);
	
	public int queryCOUNTNUM(VE ve);
	
	public List<Map<String,Object>> queryALLVeForMap();
	
}
