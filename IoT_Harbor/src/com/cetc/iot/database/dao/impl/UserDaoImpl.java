package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.UserDao;
import com.cetc.iot.database.model.User;
@Repository
public class UserDaoImpl implements UserDao {
	
	
	private static Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbc_user_db;
	
	
	private static User mapToUser (Map<String,Object> resultMap){
		User user = new User();
		user.setUserName(resultMap.containsKey("user_name") ? (String)resultMap.get("user_name") : null);
		user.setUserPassword(resultMap.containsKey("user_password") ? (String)resultMap.get("user_password") : null);
		user.setUserRealName(resultMap.containsKey("user_realname") ? (String)resultMap.get("user_realname") : null);
		user.setEmail(resultMap.containsKey("user_email") ? (String)resultMap.get("user_email") : null);
		user.setAddress(resultMap.containsKey("user_address") ? (String)resultMap.get("user_address") : null);
		user.setTel(resultMap.containsKey("user_tel") ? (String)resultMap.get("user_tel") : null);
		user.setState(resultMap.containsKey("user_state") ? (String)resultMap.get("user_state") : null);
		user.setRegisterTime(resultMap.containsKey("user_registertime") ? (String)resultMap.get("user_registertime") : null);
		return user;
	}
	
	@Override
	public List<User> queryAll(){
		String sqlString = "select * from iot_user";
		logger.info(sqlString);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = jdbc_user_db.queryForList(sqlString);
		List<User> userList = new ArrayList<User>();
		for (Map<String,Object> tempMap:list){
			if (tempMap.containsKey("user_name")){
				if ("admin".equalsIgnoreCase((String)tempMap.get("user_name"))){
					
				}
				else {
					userList.add(UserDaoImpl.mapToUser(tempMap));
				}
				logger.info(tempMap);
			}
		}
		return userList;
	}
	
	
	@Override
	public User query(String userName) {
		// TODO Auto-generated method stub
		String sqlString = "select * from iot_user where";
		if ((userName == null)||("".equalsIgnoreCase(userName))){
			logger.info("ERROR: userName null! ");
			return null;
		}
		sqlString += " user_name = '"+userName+"'";
		logger.info(sqlString);
		Map<String,Object> resultMap = jdbc_user_db.queryForMap(sqlString);
		logger.info(resultMap);
		if (resultMap.isEmpty()){
			logger.info("Not Found! ");
			return null;
		}
		return UserDaoImpl.mapToUser(resultMap);
	}

	@Override
	public boolean loginResult(String userName, String password) {
		// TODO Auto-generated method stub
		String sqlString = "select count(*) from iot_user where user_name = '"+userName+"' and user_password = '"+password+"'";
		logger.info(sqlString);
		int result = jdbc_user_db.queryForInt(sqlString);
		return result == 1 ? true : false ;
	}

	@Override
	public String update(User user) {
		// TODO Auto-generated method stub
		if ((user.getUserName() == null)||("".equalsIgnoreCase(user.getUserName()))){
			return "Error: UserName null";
		}
		String sqlString = "update iot_user set";
		if (!((user.getUserPassword() == null)||("".equalsIgnoreCase(user.getUserPassword())))){
			String lastString = sqlString.substring(sqlString.length() - 3);
			if ("set".equalsIgnoreCase(lastString)){
				sqlString += " user_password = '" + user.getUserPassword() + "'";
			}
			else {
				sqlString += " , user_password = '" + user.getUserPassword() + "'";
			}
		}
		if (!((user.getUserRealName() == null)||("".equalsIgnoreCase(user.getUserRealName())))){
			String lastString = sqlString.substring(sqlString.length() - 3);
			if ("set".equalsIgnoreCase(lastString)){
				sqlString += " user_realname = '" + user.getUserRealName() + "'";
			}
			else {
				sqlString += " , user_realname = '" + user.getUserRealName() + "'";
			}
		}
		if (!((user.getEmail() == null)||("".equalsIgnoreCase(user.getEmail())))){
			String lastString = sqlString.substring(sqlString.length() - 3);
			if ("set".equalsIgnoreCase(lastString)){
				sqlString += " user_email = '" + user.getEmail() + "'";
			}
			else {
				sqlString += " , user_email = '" + user.getEmail() + "'";
			}
		}
		if (!((user.getTel() == null)||("".equalsIgnoreCase(user.getTel())))){
			String lastString = sqlString.substring(sqlString.length() - 3);
			if ("set".equalsIgnoreCase(lastString)){
				sqlString += " user_tel = '" + user.getTel() + "'";
			}
			else {
				sqlString += " , user_tel = '" + user.getTel() + "'";
			}
		}
		if (!((user.getAddress() == null)||("".equalsIgnoreCase(user.getAddress())))){
			String lastString = sqlString.substring(sqlString.length() - 3);
			if ("set".equalsIgnoreCase(lastString)){
				sqlString += " user_address = '" + user.getAddress() + "'";
			}
			else {
				sqlString += " , user_address = '" + user.getAddress() + "'";
			}
		}
		if (!((user.getState() == null)||("".equalsIgnoreCase(user.getState())))){
			String lastString = sqlString.substring(sqlString.length() - 3);
			if ("set".equalsIgnoreCase(lastString)){
				sqlString += " user_state = '" + user.getState() + "'";
			}
			else {
				sqlString += " , user_state = '" + user.getState() + "'";
			}
		}
		if (!((user.getRegisterTime() == null)||("".equalsIgnoreCase(user.getRegisterTime())))){
			String lastString = sqlString.substring(sqlString.length() - 3);
			if ("set".equalsIgnoreCase(lastString)){
				sqlString += " user_registertime = '" + user.getRegisterTime() + "'";
			}
			else {
				sqlString += " , user_registertime = '" + user.getRegisterTime() + "'";
			}
		}
		String lastString = sqlString.substring(sqlString.length() - 3);
		if ("set".equalsIgnoreCase(lastString)){
			return "Error: User Info Wrong!";
		}
		sqlString += " where user_name = '"+user.getUserName()+"'";
		logger.info(sqlString);
		try {
			int sqlResult = jdbc_user_db.update(sqlString);
			return (sqlResult == 1 || sqlResult == 0) ? "success" : "fail" ;
		} catch (DataAccessException e){
			return "fail";
		}
	}

	@Override
	public String delete(User user) {
		// TODO Auto-generated method stub
		if ((user.getUserName() == null)||("".equalsIgnoreCase(user.getUserName()))){
			return "Error: UserName null";
		}
		String sqlString = "delete from iot_user where user_name = '"+user.getUserName()+"'";
		logger.info(sqlString);
		try {
			jdbc_user_db.execute(sqlString);
		} catch (DataAccessException e){
			return "fail";
		}
		return "success";
	}

	@Override
	public String add(User user) {
		// TODO Auto-generated method stub
		if ((user.getUserName() == null)||("".equalsIgnoreCase(user.getUserName()))){
			return "Error: UserName null";
		}
		String sqlString = "insert into iot_user (";
		String sqlString1 = "user_name";
		String sqlString2 = "'" + user.getUserName() +"'";
		if (!((user.getUserPassword() == null)||("".equalsIgnoreCase(user.getUserPassword())))){
			sqlString1 += " , user_password";
			sqlString2 += " , '"+user.getUserPassword()+"'";
		}else {
			return "Error: Password null";
		}
		if (!((user.getUserRealName() == null)||("".equalsIgnoreCase(user.getUserRealName())))){
			sqlString1 += " , user_realname";
			sqlString2 += " , '"+user.getUserRealName()+"'";
		}
		if (!((user.getEmail() == null)||("".equalsIgnoreCase(user.getEmail())))){
			sqlString1 += " , user_email";
			sqlString2 += " , '"+user.getEmail()+"'";
		}
		if (!((user.getTel() == null)||("".equalsIgnoreCase(user.getTel())))){
			sqlString1 += " , user_tel";
			sqlString2 += " , '"+user.getTel()+"'";
		}
		if (!((user.getAddress() == null)||("".equalsIgnoreCase(user.getAddress())))){
			sqlString1 += " , user_address";
			sqlString2 += " , '"+user.getAddress()+"'";
		}
		if (!((user.getState() == null)||("".equalsIgnoreCase(user.getState())))){
			sqlString1 += " , user_state";
			sqlString2 += " , '"+user.getState()+"'";
		}
		if (!((user.getRegisterTime() == null)||("".equalsIgnoreCase(user.getRegisterTime())))){
			sqlString1 += " , user_registertime";
			sqlString2 += " , '"+user.getRegisterTime()+"'";
		}
		sqlString += sqlString1 + " ) values ( "+sqlString2+" )";
		logger.info(sqlString);
		try {
			int sqlResult = jdbc_user_db.update(sqlString);
			return (sqlResult == 1 || sqlResult == 0) ? "success" : "fail";
		} catch (DataAccessException e){
			return "fail";
		}
	}

	

}
