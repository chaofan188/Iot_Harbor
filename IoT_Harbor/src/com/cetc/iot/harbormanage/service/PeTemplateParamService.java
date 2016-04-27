package com.cetc.iot.harbormanage.service;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeTemplateParam;

public interface PeTemplateParamService {
	
	public List<Map<String, Object>> query(PeTemplateParam peTemplateParam,int page,int size);
	
	public String add(PeTemplateParam peTemplateParam);
	public List<Map<String, Object>> queryAll(PeTemplateParam peTemplateParam);

}
