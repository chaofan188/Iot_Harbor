package com.cetc.iot.servicesystem.service;

import java.util.List;
import com.cetc.iot.database.model.VeWebsocketServiceReturnParam;

public interface VEWebsocketServiceReturnParamService {
	public List<VeWebsocketServiceReturnParam> query(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam, int page, int size);

	public String delete(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam);

	public String add(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam);

	public String update(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam);
	
	public int queryCOUNTNUM(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam);
}
