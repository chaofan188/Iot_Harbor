package com.cetc.iot.servicesystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.VePeBindInterfaceDao;
import com.cetc.iot.database.model.VePeBindInterface;
import com.cetc.iot.servicesystem.service.VePeBindInterfaceService;

@Service
public class VePeBindInterfaceInterfaceImpl implements VePeBindInterfaceService {

	@Autowired
	private VePeBindInterfaceDao bindInterfaceDao;
	
	@Override
	public String add(VePeBindInterface bindInterface) {
		return bindInterfaceDao.add(bindInterface);
	}

	@Override
	public List<Map<String, Object>> query(VePeBindInterface bindInterface) {
		return bindInterfaceDao.query(bindInterface);
	}

	@Override
	public List<Map<String, Object>> queryPeList(String veId, String serviceId,
			String peTemlapteId) {
		return bindInterfaceDao.queryPeList(veId, serviceId, peTemlapteId);
	}

}
