package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.PeInterfaceKeyDao;
import com.cetc.iot.database.model.PeInterfaceKey;
import com.cetc.iot.harbormanage.service.PEInterfaceKeyService;

@Service("peInterfaceKeyService")
public class PEInterfaceKeyServiceImpl implements PEInterfaceKeyService {

	@Autowired
	private PeInterfaceKeyDao peInterfaceKeyDao;
	@Override
	public int queryForIsExsit(String peID, String interfaceID, String key) {
		return peInterfaceKeyDao.queryForIsExsit(peID, interfaceID, key);
	}

	@Override
	public String addKey(String peID, String interfaceID, String key) {
		PeInterfaceKey peInterfaceKey  = new PeInterfaceKey();
		peInterfaceKey.setInterfaceID(interfaceID);
		peInterfaceKey.setInterfaceIDKey(key);
		peInterfaceKey.setPeID(peID);
		
		peInterfaceKeyDao.add(peInterfaceKey); 
		
		return null;
	}

	@Override
	public List<Map<String, Object>> queryByPEID(String peID) {
		// TODO Auto-generated method stub
		return peInterfaceKeyDao.query(peID);
	}

	@Override
	public List<Map<String, Object>> queryByPEIDList(List<String> peIDList) {
		// TODO Auto-generated method stub
		return peInterfaceKeyDao.query(peIDList);
	}

	@Override
	public String delete(String peID) {
		// TODO Auto-generated method stub
		return peInterfaceKeyDao.delete(peID);
	}
}
