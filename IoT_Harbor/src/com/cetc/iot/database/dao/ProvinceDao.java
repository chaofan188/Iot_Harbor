package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.Province;

public interface ProvinceDao {
	public List<Map<String, Object>> query(Province province,int page,int size);
	public String delete(Province province);
	public String add(Province province);
	public String update(Province province);

}
