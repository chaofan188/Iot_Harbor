package com.cetc.iot.harbormanage.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.VePeBindDao;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.harbormanage.service.VEPEBindService;
@Service("vePeService")
public class VEPEBindServiceImpl implements VEPEBindService {

	@Autowired
	VePeBindDao vePeBindDao;

	@Override
	public List<VePeBind> query(VePeBind vepebind, int page, int size) {
		// TODO Auto-generated method stub
		return vePeBindDao.query(vepebind, page, size);
	}
	@Override
	public String update(VePeBind vepebind) {
		// TODO Auto-generated method stub
		return vePeBindDao.update(vepebind);
	}

}
