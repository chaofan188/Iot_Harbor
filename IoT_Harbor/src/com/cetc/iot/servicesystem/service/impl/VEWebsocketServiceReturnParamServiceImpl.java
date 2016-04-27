package com.cetc.iot.servicesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.VeWebsocketServiceReturnParamDao;
import com.cetc.iot.database.model.VeWebsocketServiceReturnParam;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceReturnParamService;
import com.cetc.iot.servicesystem.util.BuildIDUtil;
@Service("veWebsocketServiceReturnParamService")
public class VEWebsocketServiceReturnParamServiceImpl implements
		VEWebsocketServiceReturnParamService {

	@Autowired
	private VeWebsocketServiceReturnParamDao vesrpDao;

	@Override
	public List<VeWebsocketServiceReturnParam> query(
			VeWebsocketServiceReturnParam veWebsocketServiceReturnParam, int page, int size) {
		// TODO Auto-generated method stub
		return vesrpDao.query(veWebsocketServiceReturnParam, page, size);
	}

	@Override
	public String delete(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam) {
		// TODO Auto-generated method stub
		return vesrpDao.delete(veWebsocketServiceReturnParam);
	}

	@Override
	public String add(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam) {
		String returnParam_id = BuildIDUtil.build("serviceReturnParam");
		veWebsocketServiceReturnParam.setReturnParam_id(returnParam_id);
		return vesrpDao.add(veWebsocketServiceReturnParam);
	}

	@Override
	public String update(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam) {
		// TODO Auto-generated method stub
		return vesrpDao.update(veWebsocketServiceReturnParam);
	}

	@Override
	public int queryCOUNTNUM(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam) {
		// TODO Auto-generated method stub
		return vesrpDao.queryCOUNTNUM(veWebsocketServiceReturnParam);
	}
	
	
}
