package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.PeTemplateDao;
import com.cetc.iot.database.model.PeTemplate;
import com.cetc.iot.harbormanage.service.PETemplateService;

@Service("peTemplateService")
public class PETemplateServiceImpl implements PETemplateService {

	@Autowired
	PeTemplateDao templateDao;
	@Override
	public List<Map<String, Object>> query(PeTemplate peTemplate, int page,
			int size) {
		// TODO Auto-generated method stub
		return templateDao.query(peTemplate, page, size);
	}

	@Override
	public String delete(PeTemplate peTemplate) {
		// TODO Auto-generated method stub
		return templateDao.delete(peTemplate);
	}

	@Override
	public String add(PeTemplate peTemplate) {
		// TODO Auto-generated method stub
		return templateDao.add(peTemplate);
	}

	@Override
	public String update(PeTemplate peTemplate) {
		// TODO Auto-generated method stub
		return templateDao.update(peTemplate);
	}

	@Override
	public List<Map<String, Object>> queryAll() {
		// TODO Auto-generated method stub
		return templateDao.queryAll();
	}

}
