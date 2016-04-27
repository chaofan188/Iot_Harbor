package com.cetc.iot.servicesystem.service;

import java.util.List;
import java.util.Map;


import com.cetc.iot.database.model.UserAdmin;


public interface UserAdminService {
	
	public Boolean hasMatchAdmin(String username, String password);
	
	public UserAdmin getUserAdminByName(String username);
	
	public void loginSuccess(UserAdmin userAdmin);
	
	public List<Map<String, Object>> getAdminList();
	
	public int getAdminCount();
	
	public void updateAdminPassword(String user_id, String new_pass);
	
	public void delAdmin(String user_id);
}
