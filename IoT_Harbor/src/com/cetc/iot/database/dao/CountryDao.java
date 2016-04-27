package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.Country;

public interface CountryDao {

	public List<Map<String, Object>> query(Country country,int page,int size);
	public String delete(Country country);
	public String add(Country country);
	public String update(Country country);
}
