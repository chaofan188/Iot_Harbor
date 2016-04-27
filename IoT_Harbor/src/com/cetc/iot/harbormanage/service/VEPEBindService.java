package com.cetc.iot.harbormanage.service;

import java.util.List;

import com.cetc.iot.database.model.VePeBind;

public interface VEPEBindService {
	public List<VePeBind> query(VePeBind pevebind,int page,int size);
	public String update(VePeBind vepebind);
}
