package com.cetc.iot.database.dao;


import java.util.List;

import com.cetc.iot.database.model.User;

public interface UserDao {
	public List<User> queryAll();
	public User query(String userName);
	public boolean loginResult(String userName,String password);
	public String update(User user);
	public String delete(User user);
	public String add(User user);
	
}
