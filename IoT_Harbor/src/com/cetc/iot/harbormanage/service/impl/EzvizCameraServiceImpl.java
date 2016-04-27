package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.EzvizCameraDao;
import com.cetc.iot.database.model.EzvizCamera;
import com.cetc.iot.harbormanage.service.EzvizCameraService;
@Service("ezvizCameraService")
public class EzvizCameraServiceImpl implements EzvizCameraService {


	@Autowired
	EzvizCameraDao ezvizCameraDao;
	
	@Override
	public List<Map<String, Object>> query(EzvizCamera cam, int page, int size) {

		return ezvizCameraDao.query(cam, page, size);
	}

	@Override
	public String delete(EzvizCamera cam) {
		// TODO Auto-generated method stub
		return ezvizCameraDao.delete(cam);
	}

	@Override
	public String add(EzvizCamera cam) {
		// TODO Auto-generated method stub
		return ezvizCameraDao.add(cam);
	}

	@Override
	public String update(EzvizCamera cam) {
		// TODO Auto-generated method stub
		return ezvizCameraDao.update(cam);
	}
}
