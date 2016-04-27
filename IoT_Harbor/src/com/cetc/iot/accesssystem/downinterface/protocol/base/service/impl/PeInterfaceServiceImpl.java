package com.cetc.iot.accesssystem.downinterface.protocol.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.accesssystem.downinterface.protocol.base.service.PeInterfaceService;
import com.cetc.iot.database.dao.PeInterfaceDao;
import com.cetc.iot.database.model.PeInterface;
@Service("protocolPeInterfaceService")
public class PeInterfaceServiceImpl implements PeInterfaceService{
	
	@Autowired
	private PeInterfaceDao peInterfaceDao;
	
	@Override
	public PeInterface query(String templateID, String interfaceID) {
		// TODO Auto-generated method stub
		PeInterface peInterface = new PeInterface();
		peInterface.setTemplateId(templateID);
		peInterface.setInterfaceId(interfaceID);
		peInterface.setInterfaceChannel(-1);
		peInterface.setInterfaceFashion(-1);
		peInterface.setInterfaceResponse(-1);
		List<Map<String, Object>> list = peInterfaceDao.query(peInterface, 0, 20);
		if (list.size() == 1){
			Map<String, Object> resultMap = list.get(0);
			PeInterface resultPeInterface = new PeInterface();
			resultPeInterface.setInterfaceId(resultMap.containsKey("interface_id") ? (String)resultMap.get("interface_id") : null);
			resultPeInterface.setInterfaceName(resultMap.containsKey("interface_name") ? (String)resultMap.get("interface_name") : null);
			resultPeInterface.setInterfaceType(resultMap.containsKey("interface_type") ? (String)resultMap.get("interface_type") : null);
			resultPeInterface.setInterfaceResponse(resultMap.containsKey("interface_response") ? (Integer)resultMap.get("interface_response") : -1);
			resultPeInterface.setInterfaceFashion(resultMap.containsKey("interface_fashion") ? (Integer)resultMap.get("interface_fashion") : -1);
			resultPeInterface.setInterfaceChannel(resultMap.containsKey("interface_channel") ? (Integer)resultMap.get("interface_channel") : -1);
			resultPeInterface.setTemplateId(resultMap.containsKey("template_id") ? (String)resultMap.get("template_id") : null);
			resultPeInterface.setInterfaceRemark(resultMap.containsKey("interface_remark") ? (String)resultMap.get("interface_remark") : null);
			resultPeInterface.setInterfaceContent(resultMap.containsKey("interface_content") ? (String)resultMap.get("interface_content") : null);
			resultPeInterface.setParameterType(resultMap.containsKey("parameter_type") ? (String)resultMap.get("parameter_type") : null);
			resultPeInterface.setResult(resultMap.containsKey("result") ? (String)resultMap.get("result") : null);
			resultPeInterface.setResultType(resultMap.containsKey("result_type") ? (String)resultMap.get("result_type") : null);
			return resultPeInterface;
		}
		else {
			return null;
		}
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
	public String delete(PeInterface peInterface) {
		// TODO Auto-generated method stub
		return peInterfaceDao.delete(peInterface);
	}

	@Override
	public List<Map<String, Object>> queryPeInterfacesByPeTemplateId(String templateId) {
		if(templateId.equals("")||templateId == null){
			return null;
		}
		PeInterface peInterface = new PeInterface();
		peInterface.setTemplateId(templateId);
		return peInterfaceDao.query(peInterface, 0, 0);
	}

}
