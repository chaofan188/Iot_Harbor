package com.cetc.iot.servicesystem.service;

import java.util.List;
import com.cetc.iot.database.model.VePeBind;



public interface VePeBindService {
	
	public List<VePeBind> query(VePeBind vePeBind, int page, int size);

	public String delete(VePeBind vePeBind);

	public String add(VePeBind vePeBind);

	public String update(VePeBind vePeBind);
	
	public int queryCOUNTNUM(VePeBind vePeBind);
}
