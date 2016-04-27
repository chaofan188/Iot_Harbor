package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeInterfaceKey;

public interface PeInterfaceKeyDao {
	public PeInterfaceKey query(String peID,String interfaceID);
	public int queryForIsExsit(String peID,String interfaceID,String key);
	public String add(PeInterfaceKey peInterfaceKey);
	public String update(PeInterfaceKey peInterfaceKey);
	public String delete(PeInterfaceKey peInterfaceKey);
	public String delete(String peID);
	/**
	 * this method is used to query pe interface key information by peID
	 * @param peID
	 * @return 
	 */
	public List<Map<String,Object>> query(String peID);
	/**
	 * this method is used to query pe interface key information by peIDList
	 * @param peIDList
	 * @return
	 */
	public List<Map<String,Object>> query(List<String> peIDList);
}
