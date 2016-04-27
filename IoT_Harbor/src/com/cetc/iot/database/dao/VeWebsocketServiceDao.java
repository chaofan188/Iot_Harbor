package com.cetc.iot.database.dao;

import java.util.List;
import com.cetc.iot.database.model.VeWebsocketService;

public interface VeWebsocketServiceDao {
	
	String update(VeWebsocketService veWebsocketService);

	String add(VeWebsocketService veWebsocketService);

	String delete(VeWebsocketService veWebsocketService);

	List<VeWebsocketService> query(VeWebsocketService veWebsocketService,
			int page, int size);

	int queryCOUNTNUM(VeWebsocketService veWebsocketService);
}
