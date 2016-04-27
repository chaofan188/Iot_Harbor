package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.EzvizCamera;

public interface EzvizCameraDao {
	public List<Map<String, Object>> query(EzvizCamera cam,int page,int size);
	public String delete(EzvizCamera cam);
	public String add(EzvizCamera cam);
	public String update(EzvizCamera cam);
}
