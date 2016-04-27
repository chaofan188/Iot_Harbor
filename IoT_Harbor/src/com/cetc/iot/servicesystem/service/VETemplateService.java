package com.cetc.iot.servicesystem.service;

import java.util.List;
import java.util.Map;
import com.cetc.iot.database.model.CompleteTpl;
import com.cetc.iot.database.model.VETemplate;

public interface VETemplateService {
	
	public List<VETemplate> query(VETemplate veTemplate, int page, int size);

	public String delete(VETemplate veTemplate);

	public String add(VETemplate veTemplate);

	public String update(VETemplate veTemplate);
	
	public VETemplate getTemplateByTemplateId(String templateId);
	
	public String updateState(String templateId,String newState);
	
	public boolean insertCompleteTpl(CompleteTpl completeTpl,Map<String,Object> methodParam);
	
	public boolean insertCompleteTplForAtomVE(CompleteTpl completeTpl,Map<String,Object> methodParam);
	
}
