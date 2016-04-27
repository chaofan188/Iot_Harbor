package com.cetc.iot.accesssystem.downinterface.protocol.base.service;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.PeInterface;

public interface PeInterfaceService {
	public PeInterface query(String templateID,String interfaceID);
	
	/**
	 * @Title: queryPeInterfacesByPeTemplateId 
	 * @Description: 根据PE模板Id查询该模板下所有的interface
	 * @param @param templateId
	 * @param @return 
	 * @return List<Map<String,Object>>  
	 * @throws
	 */
	public List<Map<String, Object>> queryPeInterfacesByPeTemplateId(String templateId);
	
	public String add(PeInterface peInterface);
	public String update(PeInterface peInterface);
	public String delete(PeInterface peInterface);
}
