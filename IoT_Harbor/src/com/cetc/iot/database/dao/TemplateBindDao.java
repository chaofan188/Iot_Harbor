package com.cetc.iot.database.dao;

import java.util.List;
import com.cetc.iot.database.model.TemplateBind;

public interface TemplateBindDao {
	

	public String update(TemplateBind templateBind);

	public String add(TemplateBind templateBind);

	public String delete(TemplateBind templateBind);

	public List<TemplateBind> query(TemplateBind templateBind, int page,
			int size);
	
	public List<String> queryWithVETemplateIdDistinction(TemplateBind templateBind, int page,
			int size);
	
	public int queryCOUNTNUM(TemplateBind templateBind);
}
