package com.cetc.iot.harbormanage.service;

import java.util.List;
import java.util.Map;

public interface PEInterfaceKeyService {
	public int queryForIsExsit(String peID, String interfaceID, String key);
	public String addKey(String peID, String interfaceID, String key);
	public String delete(String peID);
	/**
	 * this method is used to query pe interface key information by peID
	 * @param peID
	 * @return list contains pe interface key information
	 */
	public List<Map<String,Object>> queryByPEID(String peID);
	/**
	 * this method is used to query pe interface key information by peIDList
	 * @param peIDList
	 * @return list contains pe interface key information
	 */
	public List<Map<String,Object>> queryByPEIDList(List<String> peIDList);
}
