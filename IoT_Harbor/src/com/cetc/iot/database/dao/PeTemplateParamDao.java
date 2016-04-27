package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeTemplateParam;

public interface PeTemplateParamDao {
		public List<Map<String, Object>> query(PeTemplateParam param,int page,int size);
		//public String delete(PeTemplateParam param);
		public String add(PeTemplateParam param);
		//public String update(PeParam param);
	
}
