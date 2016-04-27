package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.OnvifCameraDao;
import com.cetc.iot.database.model.OnvifCamera;
import com.cetc.iot.harbormanage.service.OnvifCameraService;

@Service("onvifCameraService")
public class OnvifCameraServiceImpl implements OnvifCameraService{

	@Autowired
	OnvifCameraDao camDao;
	
	@Override
	public List<Map<String, Object>> query(OnvifCamera cam, int page, int size) {

		return camDao.query(cam, page, size);
	}

	@Override
	public String delete(OnvifCamera cam) {
		// TODO Auto-generated method stub
		return camDao.delete(cam);
	}

	@Override
	public String add(OnvifCamera cam) {
		// TODO Auto-generated method stub
		return camDao.add(cam);
	}

	@Override
	public String update(OnvifCamera cam) {
		// TODO Auto-generated method stub
		return camDao.update(cam);
	}

}
