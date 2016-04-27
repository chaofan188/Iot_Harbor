package com.cetc.iot.servicesystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.LoginLogDao;
import com.cetc.iot.database.dao.UserAdminDao;
import com.cetc.iot.database.model.LoginLog;
import com.cetc.iot.database.model.UserAdmin;
import com.cetc.iot.servicesystem.service.UserAdminService;


@Service
public class UserAdminServiceImpl implements UserAdminService{
	@Autowired
	private UserAdminDao userAdminDao;
	
	@Autowired
	private LoginLogDao loginLogDao;
	
	public Boolean hasMatchAdmin(String username, String password) {
		Integer matchCount = userAdminDao.getMatchCount(username, password);
		return matchCount > 0;
	}
	
	public UserAdmin getUserAdminByName(String username) {
		return userAdminDao.getUserAdminByName(username);
	}
	
	public void loginSuccess(UserAdmin userAdmin) {
		LoginLog loginLog = new LoginLog();
		loginLog.setUserID(userAdmin.getUser_ID());
		loginLog.setUserName(userAdmin.getUser_name());
		loginLog.setUserIP(userAdmin.getUser_lastip());
		loginLog.setLoginDate(userAdmin.getUser_lastvisit());
		
		userAdminDao.updateLoginInfo(userAdmin);
		loginLogDao.insertLoginLog(loginLog);
	}
	
	public List<Map<String, Object>> getAdminList() {
		return userAdminDao.getAdminList();
	}
	
	public int getAdminCount() {
		return userAdminDao.getAdminCount();
	}
	
	public void updateAdminPassword(String user_id, String new_pass) {
		userAdminDao.updateAdminPassword(user_id, new_pass);
	}
	
	public void delAdmin(String user_id) {
		userAdminDao.delAdmin(user_id);
	}
}
