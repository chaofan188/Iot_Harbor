package com.cetc.iot.harbormanage.service;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.ProtocolHttp;

public interface ProtocolHttpService {
	public List<Map<String, Object>> query(ProtocolHttp protocolHttp, int page,
			int size);

	public String add(ProtocolHttp protocolHttp);
	
	public List<Map<String, Object>> queryAll(ProtocolHttp protocolHttp);
}
