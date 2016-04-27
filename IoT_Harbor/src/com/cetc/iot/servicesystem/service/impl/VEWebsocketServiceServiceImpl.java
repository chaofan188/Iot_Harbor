package com.cetc.iot.servicesystem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cetc.iot.database.dao.VeWebsocketServiceDao;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceParamService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;
import com.cetc.iot.servicesystem.util.BuildIDUtil;
@Service("veWebsocketServiceService")
public class VEWebsocketServiceServiceImpl implements VEWebsocketServiceService {
	@Autowired
	private VEWebsocketServiceParamService vesps;
	@Autowired
	private VeWebsocketServiceDao vespDao;
	@Override
	public List<VeWebsocketService> query(
			VeWebsocketService veWebsocketService, int page, int size) {
		// TODO Auto-generated method stub
		return vespDao.query(veWebsocketService, page, size);
	}
	@Override
	public String delete(VeWebsocketService veWebsocketService) {
		// TODO Auto-generated method stub
		return vespDao.delete(veWebsocketService);
	}
	@Override
	public String add(VeWebsocketService veWebsocketService) {
		String service_id = BuildIDUtil.build("service");
		veWebsocketService.setService_id(service_id);
		return vespDao.add(veWebsocketService);
	}
	@Override
	public String update(VeWebsocketService veWebsocketService) {
		// TODO Auto-generated method stub
		return vespDao.update(veWebsocketService);
	}
	@Override
	public int queryCOUNTNUM(VeWebsocketService veWebsocketService) {
		// TODO Auto-generated method stub
		return vespDao.queryCOUNTNUM(veWebsocketService);
	}
	


}
