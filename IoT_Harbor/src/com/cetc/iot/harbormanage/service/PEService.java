package com.cetc.iot.harbormanage.service;
import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.Pe;


public interface PEService {

	public List<Map<String, Object>> query(Pe pe,int page,int size);
	public String delete(Pe pe);
	public String add(Pe pe);
	public String update(Pe pe);
	public String deleteAll(Pe pe);
	public List<Map<String, Object>> getPeByOwner(String owner, int page, int size);
	public List<Map<String, Object>> getPeFilter(String template_id,
			String pe_state, String pe_lifecycle, int page, int size);
	public List<Map<String, Object>> getTemplate(String parameter, int page, int size);
	public List<Map<String, Object>> getPeById(String peId);
	public List<Map<String, Object>> getAuthById(String peId);
	public List<Map<String, Object>> getDataById(String peId);
	public List<Map<String,Object>> queryByPEID(String peID);
}
