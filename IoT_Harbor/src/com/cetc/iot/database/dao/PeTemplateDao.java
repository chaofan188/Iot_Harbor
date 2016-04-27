package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeTemplate;

public interface PeTemplateDao {

	public List<Map<String, Object>> query(PeTemplate peTemplate,int page,int size);
	public String delete(PeTemplate peTemplate);
	public String add(PeTemplate peTemplate);
	public String update(PeTemplate peTemplate);
	/**
	 * this method is used to query all pe_model information
	 * @return pe_model information list
	 */
	public List<Map<String,Object>> queryAll();
}
