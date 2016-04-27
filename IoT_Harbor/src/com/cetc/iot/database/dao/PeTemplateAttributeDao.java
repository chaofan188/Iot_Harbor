package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeTemplateAttribute;

public interface PeTemplateAttributeDao {


	public List<Map<String, Object>> query(PeTemplateAttribute peTemplateAttribute,int page,int size);
	public String delete(PeTemplateAttribute peTemplateAttribute);
	public String add(PeTemplateAttribute peTemplateAttribute);
	public String update(PeTemplateAttribute peTemplateAttribute);
}
