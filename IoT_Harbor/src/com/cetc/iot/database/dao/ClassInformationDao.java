package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.ClassInformation;

public interface ClassInformationDao {

	public List<Map<String, Object>> query(ClassInformation classInformation,int page,int size);
	public String delete(ClassInformation classInformation);
	public String add(ClassInformation classInformation);
	public String update(ClassInformation classInformation);
}
