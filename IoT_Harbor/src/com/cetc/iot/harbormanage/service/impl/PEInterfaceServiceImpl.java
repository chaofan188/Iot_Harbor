package com.cetc.iot.harbormanage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.PeInterfaceDao;
import com.cetc.iot.database.model.PeInterface;
import com.cetc.iot.harbormanage.service.PEInterfaceService;
@Service("peInterfaceService")
public class PEInterfaceServiceImpl implements PEInterfaceService {
	@Autowired
	PeInterfaceDao peInterfaceDao;
	@Override
	public List<Map<String, Object>> query(PeInterface peInterface, int page,
			int size) {
		// TODO Auto-generated method stub
		return peInterfaceDao.query(peInterface, page, size);
	}

	@Override
	public String delete(PeInterface peInterface) {
		// TODO Auto-generated method stub
		return peInterfaceDao.delete(peInterface);
	}

	@Override
	public String add(PeInterface peInterface) {
		// TODO Auto-generated method stub
		return peInterfaceDao.add(peInterface);
	}

	@Override
	public String update(PeInterface peInterface) {
		// TODO Auto-generated method stub
		return peInterfaceDao.update(peInterface);
	}

	@Override
	public List<Map<String, Object>> queryall(PeInterface peInterface) {
		// TODO Auto-generated method stub
		return peInterfaceDao.queryall(peInterface);
	}

}
