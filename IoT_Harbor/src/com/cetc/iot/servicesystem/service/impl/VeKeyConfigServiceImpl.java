package com.cetc.iot.servicesystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.VeKeyConfigDao;
import com.cetc.iot.database.model.VeKeyConfig;
import com.cetc.iot.servicesystem.service.VeKeyConfigService;

@Service("veKeyConfigService")
public class VeKeyConfigServiceImpl implements VeKeyConfigService {

	@Autowired
	private VeKeyConfigDao veKeyConfigDao;
	
	@Override
	public String add(VeKeyConfig veKeyConfig) {
		
		if(veKeyConfig != null){
			return veKeyConfigDao.add(veKeyConfig);
		}else{
			return "fail";
		}
		
	}

	@Override
	public String delete(VeKeyConfig veKeyConfig) {
		
		if(veKeyConfig != null){
			return veKeyConfigDao.delete(veKeyConfig);
		}else{
			return "fail";
		}
	}

	@Override
	public String update(VeKeyConfig veKeyConfig) {
		
		if(veKeyConfig != null){
			return veKeyConfigDao.update(veKeyConfig);
		}else{
			return "fail";
		}
	}

	@Override
	public List<VeKeyConfig> query(VeKeyConfig veKeyConfig, int page, int size) {
		
		List<VeKeyConfig> list = new ArrayList<VeKeyConfig>();
		
		if(veKeyConfig != null){
			
			list = veKeyConfigDao.query(veKeyConfig, page, size);
			
			return list;
		}else{
			return list;
		}
	}

}
