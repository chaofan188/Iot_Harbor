package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.PeParamDao;
import com.cetc.iot.database.model.PeParam;
import com.cetc.iot.harbormanage.service.PEParamService;

@Service("peParamService")
public class PEParamServiceImpl implements PEParamService{

	@Autowired
	PeParamDao peParamDao;
	
	@Override
	public List<Map<String, Object>> query(PeParam peParam, int page, int size) {
		
		// TODO Auto-generated method stub
		return peParamDao.query(peParam, page, size);
	}

	@Override
	public String add(PeParam peParam) {
		// TODO Auto-generated method stub
		return peParamDao.add(peParam);
	}

	@Override
	public List<Map<String, Object>> queryAll(PeParam peParam) {
		// TODO Auto-generated method stub
		return peParamDao.query(peParam, -1, -1);
	}

}
