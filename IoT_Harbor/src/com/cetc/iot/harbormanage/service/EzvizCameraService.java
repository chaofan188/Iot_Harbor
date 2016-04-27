package com.cetc.iot.harbormanage.service;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.EzvizCamera;

public interface EzvizCameraService {
	public List<Map<String, Object>> query(EzvizCamera cam,int page,int size);
	public String delete(EzvizCamera cam);
	public String add(EzvizCamera cam);
	public String update(EzvizCamera cam);

}
