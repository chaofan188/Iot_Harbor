package com.cetc.iot.harbormanage.service;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeTemplateProtocolHttp;

public interface PeTemplateProtocolHttpSevice {

	public List<Map<String, Object>> query(PeTemplateProtocolHttp peTemplateProtocolHttp,int page,int size);
	
	public String add(PeTemplateProtocolHttp peTemplateProtocolHttp);
}
