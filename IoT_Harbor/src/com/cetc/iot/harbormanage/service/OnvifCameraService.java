package com.cetc.iot.harbormanage.service;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.OnvifCamera;

public interface OnvifCameraService {
	public List<Map<String, Object>> query(OnvifCamera cam,int page,int size);
	public String delete(OnvifCamera cam);
	public String add(OnvifCamera cam);
	public String update(OnvifCamera cam);
}
