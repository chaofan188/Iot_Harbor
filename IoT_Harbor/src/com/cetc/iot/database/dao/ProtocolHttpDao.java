package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.ProtocolHttp;

public interface ProtocolHttpDao {
	public List<Map<String, Object>> query(ProtocolHttp http,int page,int size);
	//public String delete(PeTemplateParam param);
	public String add(ProtocolHttp http);
	//public String update(PeParam param);
}
