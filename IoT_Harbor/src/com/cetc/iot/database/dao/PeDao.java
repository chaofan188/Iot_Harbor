package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;
import com.cetc.iot.database.model.Pe;

public interface PeDao {

	public List<Map<String, Object>> query(Pe pe,int page,int size);
	public String delete(Pe pe);
	public String add(Pe pe);
	public String update(Pe pe);
}
