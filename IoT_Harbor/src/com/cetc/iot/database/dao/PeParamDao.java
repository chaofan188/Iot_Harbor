package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeParam;

public interface PeParamDao {
	public List<Map<String, Object>> query(PeParam param,int page,int size);
	//public String delete(PeParam param);
	public String add(PeParam param);
	//public String update(PeParam param);
}
