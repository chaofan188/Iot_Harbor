package com.cetc.iot.harbormanage.service;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeParam;

public interface PEParamService {
public List<Map<String, Object>> query(PeParam peParam,int page,int size);
	
	public String add(PeParam peParam);
	public List<Map<String, Object>> queryAll(PeParam peParam);
}
