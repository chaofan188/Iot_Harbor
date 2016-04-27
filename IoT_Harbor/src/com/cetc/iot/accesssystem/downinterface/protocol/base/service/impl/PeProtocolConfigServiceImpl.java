package com.cetc.iot.accesssystem.downinterface.protocol.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.accesssystem.downinterface.protocol.base.service.PeProtocolConfigService;
import com.cetc.iot.database.dao.PeProtocolConfigDao;
import com.cetc.iot.database.model.PeProtocolConfig;
@Service("peProtocolConfigService")
public class PeProtocolConfigServiceImpl implements PeProtocolConfigService {

	@Autowired
	private PeProtocolConfigDao peProtocolConfigDao;
	
	@Override
	public PeProtocolConfig query(String peID) {
		// TODO Auto-generated method stub
		return peProtocolConfigDao.query(peID);
	}

	@Override
	public String add(PeProtocolConfig peProtocolConfig) {
		// TODO Auto-generated method stub
		return peProtocolConfigDao.add(peProtocolConfig);
	}

	@Override
	public String update(PeProtocolConfig peProtocolConfig) {
		// TODO Auto-generated method stub
		return peProtocolConfigDao.update(peProtocolConfig);
	}

	@Override
	public String delete(PeProtocolConfig peProtocolConfig) {
		// TODO Auto-generated method stub
		return peProtocolConfigDao.delete(peProtocolConfig);
	}

	@Override
	public String delete(String peID) {
		// TODO Auto-generated method stub
		return peProtocolConfigDao.delete(peID);
	}

}
