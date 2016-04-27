package com.cetc.iot.database.dao;

import java.util.List;
import com.cetc.iot.database.model.VeKeyConfig;

public interface VeKeyConfigDao {
	
	//增
	public String add(VeKeyConfig veKeyConfig);
	
	//删
	public String delete(VeKeyConfig veKeyConfig);
	
	//改
	public String update(VeKeyConfig veKeyConfig);
	
	//查
	public List<VeKeyConfig> query(VeKeyConfig veKeyConfig,int page,int size);
}
