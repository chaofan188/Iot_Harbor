package com.cetc.iot.servicesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.TemplateBindDao;
import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.servicesystem.service.TemplateBindService;
import com.cetc.iot.servicesystem.util.BuildIDUtil;
@Service("templateBindService")
public class TemplateBindServiceImpl implements TemplateBindService{
	@Autowired
	private TemplateBindDao tbinDao;

	@Override
	public List<TemplateBind> query(TemplateBind templateBind, int page,
			int size) {
		// TODO Auto-generated method stub
		return tbinDao.query(templateBind, page, size);
	}

	@Override
	public String delete(TemplateBind templateBind) {
		// TODO Auto-generated method stub
		return tbinDao.delete(templateBind);
	}

	@Override
	public String add(TemplateBind templateBind) {
		String template_bind_id = BuildIDUtil.build("templateBind");
		templateBind.setTemplate_bind_id(template_bind_id);
		String result = tbinDao.add(templateBind);
		if(result.equals("success")){
			return template_bind_id;
		}
		return "fail"; 
	}

	@Override
	public String update(TemplateBind templateBind) {
		// TODO Auto-generated method stub
		return tbinDao.update(templateBind);
	}

	@Override
	public List<String> queryWithVETemplateIdDistinction(TemplateBind templateBind,
			int page, int size) {
		return tbinDao.queryWithVETemplateIdDistinction(templateBind, page, size);
	}

	@Override
	public int queryCOUNTNUM(TemplateBind templateBind) {
		return tbinDao.queryCOUNTNUM(templateBind);
	}

	

}
