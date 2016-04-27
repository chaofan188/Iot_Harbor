package com.cetc.iot.servicesystem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cetc.iot.database.dao.VeWebsocketServiceParamDao;
import com.cetc.iot.database.model.VeWebsocketServiceParam;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceParamService;
import com.cetc.iot.servicesystem.util.BuildIDUtil;

@Service("veWebsocketServiceParamService")
public class VEWebsocketServiceParamServiceImpl implements VEWebsocketServiceParamService {

	@Autowired
	private VeWebsocketServiceParamDao vespDao;

	@Override
	public List<VeWebsocketServiceParam> query(
			VeWebsocketServiceParam veWebsocketServiceParam, int page, int size) {
		// TODO Auto-generated method stub
		return vespDao.query(veWebsocketServiceParam, page, size);
	}

	@Override
	public String delete(VeWebsocketServiceParam veWebsocketServiceParam) {
		// TODO Auto-generated method stub
		return vespDao.delete(veWebsocketServiceParam);
	}

	@Override
	public String add(VeWebsocketServiceParam veWebsocketServiceParam) {
		String param_id = BuildIDUtil.build("serviceParam");
		veWebsocketServiceParam.setParam_id(param_id);
		return vespDao.add(veWebsocketServiceParam);
	}

	@Override
	public String update(VeWebsocketServiceParam veWebsocketServiceParam) {
		// TODO Auto-generated method stub
		return vespDao.update(veWebsocketServiceParam);
	}

	@Override
	public int queryCOUNTNUM(VeWebsocketServiceParam veWebsocketServiceParam) {
		// TODO Auto-generated method stub
		return vespDao.queryCOUNTNUM(veWebsocketServiceParam);
	}
	
	
}
