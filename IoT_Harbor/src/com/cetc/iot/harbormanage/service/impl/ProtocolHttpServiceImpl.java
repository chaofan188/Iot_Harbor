package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.ProtocolHttpDao;
import com.cetc.iot.database.model.ProtocolHttp;
import com.cetc.iot.harbormanage.service.ProtocolHttpService;

@Service("protocolHttpService")
public class ProtocolHttpServiceImpl implements ProtocolHttpService{

	@Autowired
	ProtocolHttpDao protocolHttpDao;
	
	@Override
	public List<Map<String, Object>> query(ProtocolHttp protocolHttp, int page,
			int size) {
		// TODO Auto-generated method stub
		return protocolHttpDao.query(protocolHttp, page, size);
	}

	@Override
	public String add(ProtocolHttp protocolHttp) {
		// TODO Auto-generated method stub
		return protocolHttpDao.add(protocolHttp);
	}

	@Override
	public List<Map<String, Object>> queryAll(ProtocolHttp protocolHttp) {
		// TODO Auto-generated method stub
		return protocolHttpDao.query(protocolHttp, -1, -1);
	}

}
