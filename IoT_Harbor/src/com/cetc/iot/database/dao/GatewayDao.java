package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.Gateway;

public interface GatewayDao {

	public List<Map<String, Object>> query(Gateway gateWay,int page,int size);
	public String delete(Gateway gateWay);
	public String add(Gateway gateWay);
	public String update(Gateway gateWay);
}
