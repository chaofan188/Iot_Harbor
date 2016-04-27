package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeInterface;

public interface PeInterfaceDao {

	public List<Map<String, Object>> query(PeInterface peInterface,int page,int size);
	public List<Map<String, Object>> queryall(PeInterface peInterface);

	public String delete(PeInterface peInterface);
	public String add(PeInterface peInterface);
	public String update(PeInterface peInterface);
}
