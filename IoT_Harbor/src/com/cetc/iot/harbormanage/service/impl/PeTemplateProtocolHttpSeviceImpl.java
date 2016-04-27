package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.PeTemplateProtocolHttpDao;
import com.cetc.iot.database.model.PeTemplateProtocolHttp;
import com.cetc.iot.harbormanage.service.PeTemplateProtocolHttpSevice;

@Service("peTemplateProtocolHttpSevice")
public class PeTemplateProtocolHttpSeviceImpl implements
		PeTemplateProtocolHttpSevice {

	@Autowired
	PeTemplateProtocolHttpDao peTemplateProtocolHttpDao;
	@Override
	public List<Map<String, Object>> query(
			PeTemplateProtocolHttp peTemplateProtocolHttp, int page, int size) {
		// TODO Auto-generated method stub
		return peTemplateProtocolHttpDao.query(peTemplateProtocolHttp, page, size);
	}

	@Override
	public String add(PeTemplateProtocolHttp peTemplateProtocolHttp) {
		// TODO Auto-generated method stub
		return peTemplateProtocolHttpDao.add(peTemplateProtocolHttp);
	}

}
