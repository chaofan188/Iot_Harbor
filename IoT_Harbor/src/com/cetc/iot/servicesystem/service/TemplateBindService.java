package com.cetc.iot.servicesystem.service;

import java.util.List;

import com.cetc.iot.database.model.TemplateBind;


public interface TemplateBindService {
	
	public List<TemplateBind> query(TemplateBind templateBind, int page, int size);
	
	public List<String> queryWithVETemplateIdDistinction(TemplateBind templateBind, int page, int size);

	public String delete(TemplateBind templateBind);

	public String add(TemplateBind templateBind);

	public String update(TemplateBind templateBind);
	
	public int queryCOUNTNUM(TemplateBind templateBind);
}
