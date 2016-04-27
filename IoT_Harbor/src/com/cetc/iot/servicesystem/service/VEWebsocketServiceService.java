package com.cetc.iot.servicesystem.service;

import java.util.List;
import com.cetc.iot.database.model.VeWebsocketService;

public interface VEWebsocketServiceService {
	
	public List<VeWebsocketService> query(VeWebsocketService veWebsocketService, int page, int size);

	public String delete(VeWebsocketService veWebsocketService);

	public String add(VeWebsocketService veWebsocketService);

	public String update(VeWebsocketService veWebsocketService);
	
	public int queryCOUNTNUM(VeWebsocketService veWebsocketService);
}
