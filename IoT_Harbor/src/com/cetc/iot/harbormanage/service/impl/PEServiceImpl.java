package com.cetc.iot.harbormanage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.OnvifCameraDao;
import com.cetc.iot.database.dao.PeDao;
import com.cetc.iot.database.dao.PeGeolocationDao;
import com.cetc.iot.database.dao.PeInterfaceKeyDao;
import com.cetc.iot.database.dao.PeTemplateDao;
import com.cetc.iot.database.model.OnvifCamera;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.database.model.PeGeolocation;
import com.cetc.iot.database.model.PeTemplate;
import com.cetc.iot.harbormanage.service.PEService;
import com.cetc.iot.util.PeUtil;

@Service("peService")
public class PEServiceImpl implements PEService {

	@Autowired
	PeDao peDao;

	@Autowired
	PeTemplateDao templateDao;
	
	@Autowired
	PeInterfaceKeyDao peInterfaceKeyDao;
	
	@Autowired
	PeGeolocationDao peGeolocationDao;
	
	@Autowired
	OnvifCameraDao cameraDao;

	@Override
	public List<Map<String, Object>> query(Pe pe,int page,int size) {
		// TODO Auto-generated method stub
		return peDao.query(pe,page,size);
	}

	@Override
	public String delete(Pe pe) {
		// TODO Auto-generated method stub
		return peDao.delete(pe);
	}

	@Override
	public String add(Pe pe) {
		// TODO Auto-generated method stub
		return peDao.add(pe);
	}

	@Override
	public String update(Pe pe) {
		// TODO Auto-generated method stub
		return peDao.update(pe);
	}

	@Override
	public List<Map<String, Object>> getPeByOwner(String owner, int page, int size) {
		Pe pe = new Pe();
		pe.setPeOwner(owner);

		pe.setPeState(-1);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);

		return peDao.query(pe, page, size);
	}

	@Override
	public List<Map<String, Object>> getPeFilter(String template_id,
			String pe_state, String pe_lifecycle, int page, int size) {
		Pe pe = new Pe();

		if (template_id != null) {
			pe.setTemplateId((String) template_id);
		}
		if (PeUtil.valueCheckPeState((String) pe_state)) {
			pe.setPeState(Integer.parseInt((String) pe_state));
		} else {
			pe.setPeState(-1);
		}

		if (PeUtil.valueCheckPeLifecycle((String) pe_lifecycle)) {
			pe.setPeLifecycle(Integer.parseInt((String) pe_lifecycle));
		} else {
			pe.setPeLifecycle(-1);
		}
		pe.setPeTime(-1);

		return peDao.query(pe, page, size);

	}

	@Override
	public List<Map<String, Object>> getTemplate(String templateType, int page, int size) {

		PeTemplate peTemplate = new PeTemplate();

		peTemplate.setTemplateType(templateType);

		return templateDao.query(peTemplate, page, size);
	}

	@Override
	public List<Map<String, Object>> getPeById(String peId) {
		
		Pe pe = new Pe();
		pe.setPeId(peId);

		pe.setPeState(-1);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);

		return peDao.query(pe, 0, 1);
	}

	@Override
	public List<Map<String, Object>> getAuthById(String peId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getDataById(String peId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryByPEID(String peID) {
		// TODO Auto-generated method stub
		if (peID == null){
			return new ArrayList<Map<String,Object>>();
		}
		Pe pe = new Pe();
		pe.setPeId(peID);
		pe.setPeLifecycle(-1);
		pe.setPeState(-1);
		pe.setPeTime(-1);
		return peDao.query(pe, 0, 100);
	}

	@Override
	/*
	 * 删除与PE相关的所有表中的内容
	 * */
	public String deleteAll(Pe pe) {
		// TODO Auto-generated method stub
		String result = null;
		
		result = peInterfaceKeyDao.delete(pe.getPeId());
		if(result.equalsIgnoreCase("fail")){
			return "fail";
		}
	
		//如果pe的协议为onvif协议的话，删除onvif表中的内容
		OnvifCamera cam = new OnvifCamera();
		cam.setPe_id(pe.getPeId());
		result = cameraDao.delete(cam);
		if(result.equalsIgnoreCase("fail")){
			return "fail";
		}
		
		PeGeolocation peGeolocation = new PeGeolocation();
		peGeolocation.setPeId(pe.getPeId());
		result = peGeolocationDao.delete(peGeolocation );
		if(result.equalsIgnoreCase("fail")){
			return "fail";
		}
		
		return peDao.delete(pe);
	}
}
