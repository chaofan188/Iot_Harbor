package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeData;

public interface PeDataDao {

	public List<Map<String, Object>> query(PeData peData,int page,int size);
	public String delete(PeData peData);
	public String add(PeData peData);
	public String update(PeData peData);
}
