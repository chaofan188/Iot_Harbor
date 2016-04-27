package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.LoginLogDao;
import com.cetc.iot.database.model.LoginLog;


@Repository
public class LoginLogDaoImpl implements LoginLogDao{
	
	private static Logger logger = Logger.getLogger(LoginLogDaoImpl.class.getName());
	
	@Autowired
	private JdbcTemplate jdbc_ve_db; //ע�����Դ
	
	///function д���¼��־
	///return 
	///param ��¼��־ģ��
	public void insertLoginLog(LoginLog loginLog) {
		String sqlStr = "insert into iot_login_log(user_id, user_name, ip, login_datetime) values (?, ?, ?, ?)";
		Object[] args = {loginLog.getUserID(), loginLog.getUserName(), loginLog.getUserIP(), loginLog.getLoginDate()};
		logger.info(sqlStr);
		jdbc_ve_db.update(sqlStr, args);
	}
	
	///function ��ȡ��¼��־
	///return ��¼��־�б�
	///param ��ǰ��ҳ
	///param ��ҳ����
	public List<Map<String, Object>> getLoginLog (Integer page, Integer rows) {
		List<Map<String, Object>> rdao = new ArrayList<Map<String, Object>>();
		String sqlStr = "select user_id, user_name, ip, login_datetime from iot_login_log limit " + String.valueOf((page - 1) * rows) + ", " + String.valueOf(rows);
		logger.info(sqlStr);
		rdao = jdbc_ve_db.queryForList(sqlStr);
		return rdao;
	}
	
	///function ��ȡ��¼��־����
	///return ��¼��־����
	public int getLoginLogCount() {
		String sqlStr = "select count(*) from iot_login_log";
		logger.info(sqlStr);
		return jdbc_ve_db.queryForInt(sqlStr);
	}
}
