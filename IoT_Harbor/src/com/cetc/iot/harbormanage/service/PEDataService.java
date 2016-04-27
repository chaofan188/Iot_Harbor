package com.cetc.iot.harbormanage.service;

import java.util.List;
import java.util.Map;

public interface PEDataService {
	List<Map<String, Object>> getPEDatabyPEId(String peID, int page, int size);
	/*
	 * ����ֵΪnull����ʧ�ܣ������Ż�����ID����dataId
	 */
	String addPEData(String peID,String interfaceID, String content, int detail);
	String addControlResp(String dataId, String value);
}
