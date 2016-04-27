package com.cetc.iot.database.dao;

import java.util.List;
import com.cetc.iot.database.model.VePeBind;

public interface VePeBindDao {
	public List<VePeBind> query(VePeBind vepebind,int page,int size);
	
	public String delete(VePeBind vepebind);
	
	public String add(VePeBind vepebind);
	
	public String update(VePeBind vepebind);
	
	public int queryCOUNTNUM(VePeBind vepebind);
}
