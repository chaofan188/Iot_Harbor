package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeGeolocation;

public interface PeGeolocationDao {

	public List<Map<String, Object>> query(PeGeolocation peGeolocation,int page,int size);
	public String delete(PeGeolocation peGeolocation);
	public String add(PeGeolocation peGeolocation);
	public String update(PeGeolocation peGeolocation);

}
