package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;
import com.cetc.iot.database.model.OnvifCamera;;

public interface OnvifCameraDao {
	public List<Map<String, Object>> query(OnvifCamera cam,int page,int size);
	public String delete(OnvifCamera cam);
	public String add(OnvifCamera cam);
	public String update(OnvifCamera cam);
}
