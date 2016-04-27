package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.PeTemplateParamDao;
import com.cetc.iot.database.model.PeTemplateParam;
import com.cetc.iot.harbormanage.service.PeTemplateParamService;

@Service("peTemplateParamService")
public class PeTemplateParamServiceImpl implements PeTemplateParamService{

	@Autowired
	PeTemplateParamDao peTemplateParamDao;
	
	@Override
	public List<Map<String, Object>> query(PeTemplateParam peTemplate,
			int page, int size) {
		// TODO Auto-generated method stub
		return peTemplateParamDao.query(peTemplate, page, size);
	}

	
	@Override
	public String add(PeTemplateParam param) {
		// TODO Auto-generated method stub
		return peTemplateParamDao.add(param);
	}

	@Override
	public List<Map<String, Object>> queryAll(PeTemplateParam peTemplate) {
		// TODO Auto-generated method stub
		return peTemplateParamDao.query(peTemplate, -1, -1);
	}

}
