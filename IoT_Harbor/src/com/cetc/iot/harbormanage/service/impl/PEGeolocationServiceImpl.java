package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.PeGeolocationDao;
import com.cetc.iot.database.model.PeGeolocation;
import com.cetc.iot.harbormanage.service.PEGeolocationService;
@Service("peGeolocationService")
public class PEGeolocationServiceImpl implements PEGeolocationService {

	@Autowired
	PeGeolocationDao peGeolocationDao ;
	@Override
	public List<Map<String, Object>> query(PeGeolocation peGeolocation,
			int page, int size) {
		// TODO Auto-generated method stub
		return peGeolocationDao.query(peGeolocation, page, size);
	}

	@Override
	public String delete(PeGeolocation peGeolocation) {
		// TODO Auto-generated method stub
		return peGeolocationDao.delete(peGeolocation);
	}

	@Override
	public String add(PeGeolocation peGeolocation) {
		// TODO Auto-generated method stub
		return peGeolocationDao.add(peGeolocation);
	}

	@Override
	public String update(PeGeolocation peGeolocation) {
		// TODO Auto-generated method stub
		return peGeolocationDao.update(peGeolocation);
	}

}
