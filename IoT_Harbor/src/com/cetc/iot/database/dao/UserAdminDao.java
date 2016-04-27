package com.cetc.iot.database.dao;

import java.util.*;


import com.cetc.iot.database.model.UserAdmin;


public interface UserAdminDao {
	public int getMatchCount(String username, String password);
	
	public UserAdmin getUserAdminByName(final String username);
	
	public void updateLoginInfo(UserAdmin userAdmin);
	
	public List<Map<String, Object>> getAdminList();
	
	public int getAdminCount();

	public void updateAdminPassword(String user_id, String new_pass);

	public void delAdmin(String user_id);
}
