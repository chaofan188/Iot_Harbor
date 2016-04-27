package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.City;

public interface CityDao {

	public List<Map<String, Object>> query(City city,int page,int size);
	public String delete(City city);
	public String add(City city);
	public String update(City city);
	
}
