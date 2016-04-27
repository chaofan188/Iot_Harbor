package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;


import com.cetc.iot.database.model.Datail;

public interface DatailDao {

	public List<Map<String, Object>> query(Datail datail,int page,int size);
	public String delete(Datail datail);
	public String add(Datail datail);
	public String update(Datail datail);
}
