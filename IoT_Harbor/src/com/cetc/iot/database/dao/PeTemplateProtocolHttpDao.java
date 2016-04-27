package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeTemplateProtocolHttp;

public interface PeTemplateProtocolHttpDao {

	public List<Map<String, Object>> query(PeTemplateProtocolHttp peTemplateProtocolHttp,int page,int size);
	//public String delete(PeTemplateParam param);
	public String add(PeTemplateProtocolHttp peTemplateProtocolHttp);
	//public String update(PeParam param);
}
