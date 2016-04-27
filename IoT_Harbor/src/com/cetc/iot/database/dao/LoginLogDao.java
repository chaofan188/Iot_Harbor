package com.cetc.iot.database.dao;

import com.cetc.iot.database.model.LoginLog;

import java.util.*;


public interface LoginLogDao {
	public void insertLoginLog(LoginLog loginLog);
	public List<Map<String, Object>> getLoginLog (Integer page, Integer rows);
	public int getLoginLogCount() ;
}
