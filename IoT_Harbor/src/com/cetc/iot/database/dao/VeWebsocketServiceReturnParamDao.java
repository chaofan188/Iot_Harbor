package com.cetc.iot.database.dao;

import java.util.List;
import com.cetc.iot.database.model.VeWebsocketServiceReturnParam;

public interface VeWebsocketServiceReturnParamDao {
String update(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam);
	
	String add(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam);
	
	String delete(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam);
	
	List<VeWebsocketServiceReturnParam> query(
			VeWebsocketServiceReturnParam veWebsocketServiceReturnParam, int page, int size);

	int queryCOUNTNUM(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam);
}
