package com.cetc.iot.accesssystem.downinterface.socket.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.accesssystem.downinterface.socket.service.SocketPeService;
import com.cetc.iot.database.dao.PeDao;
import com.cetc.iot.database.model.Pe;
@Service("socketPeService")
public class SocketPeServiceImpl implements SocketPeService {

	
	private static Logger logger = Logger.getLogger(SocketPeServiceImpl.class.getName());
	
	@Autowired
	private PeDao peDao;
	@Override
	public List<Pe> getAllPe(){
		Pe pe = new Pe();
		pe.setPeLifecycle(-1);
		pe.setPeState(-1);
		pe.setPeTime(-1);
		List<Map<String,Object>> queryList = peDao.query(pe, 0, 20);
		if (queryList.size() > 0){
			List<Pe> resultList = new ArrayList<Pe>();
			for (Map<String, Object> resultMap : queryList){
				Pe resultPe = new Pe();
				resultPe.setPeId(resultMap.containsKey("pe_id") ? (String)resultMap.get("pe_id") : null);
				resultPe.setPeName(resultMap.containsKey("pe_name") ? (String)resultMap.get("pe_name") : null);
				resultPe.setPeOwner(resultMap.containsKey("pe_owner") ? (String)resultMap.get("pe_owner") : null);
				resultPe.setPeUser(resultMap.containsKey("pe_user") ? (String)resultMap.get("pe_user") : null);
				resultPe.setPePictureUrl(resultMap.containsKey("pe_picture_url") ? (String)resultMap.get("pe_picture_url") : null);
				resultPe.setPeKey(resultMap.containsKey("pe_key") ? (String)resultMap.get("pe_key") : null);
				resultPe.setPeState(resultMap.containsKey("pe_state") ? (Integer)resultMap.get("pe_state") : -1);
				resultPe.setPeGeolocationId(resultMap.containsKey("pe_geolocation_id") ? (String)resultMap.get("pe_geolocation_id") : null);
				resultPe.setPeLifecycle(resultMap.containsKey("pe_lifecycle") ? (Integer)resultMap.get("pe_lifecycle") : -1);
				resultPe.setPeLastTime(resultMap.containsKey("pe_lastTime") ? (Date)resultMap.get("pe_lastTime") : null);
				resultPe.setPeTime(resultMap.containsKey("pe_time") ? (Integer)resultMap.get("pe_time") : -1);
				resultPe.setTemplateId(resultMap.containsKey("template_id") ? (String)resultMap.get("template_id") : null);
				resultList.add(resultPe);
			}
			return resultList;
		}
		else {
			return null;
		}
	}
	@Override
	public Pe getPeByPeID(String peID) {
		// TODO Auto-generated method stub
		Pe pe = new Pe();
		pe.setPeId(peID);
		pe.setPeLifecycle(-1);
		pe.setPeState(-1);
		pe.setPeTime(-1);
		List<Map<String,Object>> queryList = peDao.query(pe, 0, 20);
		if (queryList.size() <= 1){
			if (queryList.size() == 1){
				Map<String,Object> resultMap = queryList.get(0);
				Pe resultPe = new Pe();
				resultPe.setPeId(resultMap.containsKey("pe_id") ? (String)resultMap.get("pe_id") : null);
				resultPe.setPeName(resultMap.containsKey("pe_name") ? (String)resultMap.get("pe_name") : null);
				resultPe.setPeOwner(resultMap.containsKey("pe_owner") ? (String)resultMap.get("pe_owner") : null);
				resultPe.setPeUser(resultMap.containsKey("pe_user") ? (String)resultMap.get("pe_user") : null);
				resultPe.setPePictureUrl(resultMap.containsKey("pe_picture_url") ? (String)resultMap.get("pe_picture_url") : null);
				resultPe.setPeKey(resultMap.containsKey("pe_key") ? (String)resultMap.get("pe_key") : null);
				resultPe.setPeState(resultMap.containsKey("pe_state") ? (Integer)resultMap.get("pe_state") : -1);
				resultPe.setPeGeolocationId(resultMap.containsKey("pe_geolocation_id") ? (String)resultMap.get("pe_geolocation_id") : null);
				resultPe.setPeLifecycle(resultMap.containsKey("pe_lifecycle") ? (Integer)resultMap.get("pe_lifecycle") : -1);
				resultPe.setPeLastTime(resultMap.containsKey("pe_lastTime") ? (Date)resultMap.get("pe_lastTime") : null);
				resultPe.setPeTime(resultMap.containsKey("pe_time") ? (Integer)resultMap.get("pe_time") : -1);
				resultPe.setTemplateId(resultMap.containsKey("template_id") ? (String)resultMap.get("template_id") : null);
				return resultPe;
				
			}
			else {
				logger.info("ERROR: Pe Not Found! ");
				return null;
			}
		}
		else {
			logger.info("ERROR: Repeated PE! ");
			return null;
		}
	}

	@Override
	public String updatePe(Pe pe) {
		// TODO Auto-generated method stub
		return peDao.update(pe);
	}

	@Override
	public String deletePe(String peID) {
		// TODO Auto-generated method stub
		Pe pe = new Pe();
		pe.setPeId(peID);
		pe.setPeLifecycle(-1);
		pe.setPeState(-1);
		pe.setPeTime(-1);
		return peDao.delete(pe);
	}
	
	@Override
	public void closeAll(List<Pe> list){
		//xzc 20150522
		if (list == null){
			return ;
		}
		//xzc 20150522
		if (list.size() > 0){
			for (Pe pe : list){
				if (pe.getPeState() != 0){
					pe.setPeState(0);
					peDao.update(pe);
					logger.info("PE: "+pe.getPeId()+"Closed! ");
				}
			}
		}
	}

}
