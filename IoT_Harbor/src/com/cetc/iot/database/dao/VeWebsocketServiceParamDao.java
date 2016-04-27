package com.cetc.iot.database.dao;

import java.util.List;
import com.cetc.iot.database.model.VeWebsocketServiceParam;

public interface VeWebsocketServiceParamDao {
	
	String update(VeWebsocketServiceParam veWebsocketServiceParam);
	
	String add(VeWebsocketServiceParam veWebsocketServiceParam);
	
	String delete(VeWebsocketServiceParam veWebsocketServiceParam);
	
	List<VeWebsocketServiceParam> query(
			VeWebsocketServiceParam veWebsocketServiceParam, int page, int size);

	int queryCOUNTNUM(VeWebsocketServiceParam veWebsocketServiceParam);
}
