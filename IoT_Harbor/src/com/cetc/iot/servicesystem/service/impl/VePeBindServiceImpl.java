package com.cetc.iot.servicesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.VePeBindDao;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.servicesystem.service.VePeBindService;
import com.cetc.iot.servicesystem.util.BuildIDUtil;

@Service("bindService")
public class VePeBindServiceImpl implements VePeBindService {
	
	@Autowired
	private VePeBindDao binDao;

	@Override
	public List<VePeBind> query(VePeBind vePeBind, int page, int size) {
		// TODO Auto-generated method stub
		return binDao.query(vePeBind, page, size);
	}

	@Override
	public String delete(VePeBind vePeBind) {
		// TODO Auto-generated method stub
		return binDao.delete(vePeBind);
	}

	@Override
	public String add(VePeBind vePeBind) {
		String bindId = BuildIDUtil.build("bind");
		vePeBind.setBind_id(bindId);
		return binDao.add(vePeBind);
	}

	@Override
	public String update(VePeBind vePeBind) {
		// TODO Auto-generated method stub
		return binDao.update(vePeBind);
	}

	@Override
	public int queryCOUNTNUM(VePeBind vePeBind) {
		return binDao.queryCOUNTNUM(vePeBind);
	}
}
