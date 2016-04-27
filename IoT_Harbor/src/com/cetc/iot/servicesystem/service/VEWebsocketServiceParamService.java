package com.cetc.iot.servicesystem.service;

import java.util.List;
import com.cetc.iot.database.model.VeWebsocketServiceParam;



public interface VEWebsocketServiceParamService {
	
	public List<VeWebsocketServiceParam> query(VeWebsocketServiceParam veWebsocketServiceParam, int page, int size);

	public String delete(VeWebsocketServiceParam veWebsocketServiceParam);

	public String add(VeWebsocketServiceParam veWebsocketServiceParam);

	public String update(VeWebsocketServiceParam veWebsocketServiceParam);
	
	public int queryCOUNTNUM(VeWebsocketServiceParam veWebsocketServiceParam);
}
