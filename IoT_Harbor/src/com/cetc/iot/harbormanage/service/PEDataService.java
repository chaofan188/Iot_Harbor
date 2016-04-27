package com.cetc.iot.harbormanage.service;

import java.util.List;
import java.util.Map;

public interface PEDataService {
	List<Map<String, Object>> getPEDatabyPEId(String peID, int page, int size);
	/*
	 * 返回值为null代表失败，正常放回数据ID——dataId
	 */
	String addPEData(String peID,String interfaceID, String content, int detail);
	String addControlResp(String dataId, String value);
}
