package com.cetc.iot.harbormanage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.accesssystem.util.DataIDGenerator;
import com.cetc.iot.database.dao.DatailDao;
import com.cetc.iot.database.dao.PeDataDao;
import com.cetc.iot.database.model.Datail;
import com.cetc.iot.database.model.PeData;
import com.cetc.iot.harbormanage.service.PEDataService;

@Service
public class PEDataServiceImpl implements PEDataService {

	@Autowired
	PeDataDao peDataDao;

	@Autowired
	DatailDao dataDetailDao;

	@Override
	public List<Map<String, Object>> getPEDatabyPEId(String peID, int page,
			int size) {
		PeData peData = new PeData();
		peData.setPeId(peID);

		return peDataDao.query(peData, page, size);
	}

	@Override
	public String addPEData(String peId, String interfaceId, String content,
			int detail) {

		PeData peData = new PeData();
		String result = null;

		peData.setDataId(DataIDGenerator.generateDataID());
		peData.setDataTime(new Date());
		peData.setPeId(peId);
		peData.setPeInterfaceId(interfaceId);
		peData.setDataContent(content);
		peData.setDatail(detail);

		result = peDataDao.add(peData);

		if (result == "sucess") {
			return peData.getDataId();
		} else {
			return null;
		}
	}

	public String addDetail(String dataId, String key, String value) {

		Datail detail = new Datail();

		detail.setDataId(dataId);
		detail.setDatailKey(key);
		detail.setDatailValue(value);

		return dataDetailDao.add(detail);
	}

	@Override
	public String addControlResp(String dataId, String value) {
		return addDetail(dataId, "resq", value);
	}
}
