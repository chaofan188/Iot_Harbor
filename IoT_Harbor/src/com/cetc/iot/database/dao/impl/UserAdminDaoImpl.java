package com.cetc.iot.database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.UserAdminDao;
import com.cetc.iot.database.model.UserAdmin;


@Repository
public class UserAdminDaoImpl implements UserAdminDao{
	
	private static Logger logger = Logger.getLogger(UserAdminDaoImpl.class.getName());
	
	@Autowired
	private JdbcTemplate jdbc_ve_db; 
	
	///function ��ȡƥ��Ĺ���Ա�û�
	///return ƥ�����Ա�û�����
	///param ��¼�û���
	///param ��¼����
	public int getMatchCount(String username, String password) {
		String sqlStr = "select count(*) from iot_user_admin where user_name=? and user_pass=? ";
		logger.info(sqlStr);
		return jdbc_ve_db.queryForInt(sqlStr, new Object[] {username, password});
	}
	
	///function ����û����ȡ�û���Ϣ
	///return ����Ա�û�ģ��
	///param �û���
	public UserAdmin getUserAdminByName(final String username) {
		String sqlStr = "select user_id, user_name from iot_user_admin where user_name = ?";
		logger.info(sqlStr);
		final UserAdmin userAdmin = new UserAdmin();
		jdbc_ve_db.query(sqlStr, new Object[] {username}, 
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						userAdmin.setUser_ID(rs.getInt("user_id"));
						userAdmin.setUser_name(username);
					}
		});
		return userAdmin;
	}
	
	///function ���¹���Ա�û���¼��Ϣ
	///return 
	///param ��ǰ�ѵ�¼����Ա�û�ģ��
	public void updateLoginInfo(UserAdmin userAdmin) {
		String sqlStr = "update iot_user_admin set user_lastvisit = ?, user_lastip = ? where user_id = ?";
		logger.info(sqlStr);
		jdbc_ve_db.update(sqlStr, new Object[] {userAdmin.getUser_lastvisit(), userAdmin.getUser_lastip(), userAdmin.getUser_ID()});
	}
	
	///function ��ȡ����Ա�б�
	///return ����Ա�б�
	public List<Map<String, Object>> getAdminList() {
		List<Map<String, Object>> rdao = new ArrayList<Map<String, Object>>();
		String sqlStr = "select user_id, user_name from iot_user_admin";
		logger.info(sqlStr);
		rdao = jdbc_ve_db.queryForList(sqlStr);
		return rdao;
	}
	
	///function ��ȡ����Ա����
	///return ����Ա����
	public int getAdminCount() {
		String sqlStr = "select count(*) from iot_user_admin";
		logger.info(sqlStr);
		return jdbc_ve_db.queryForInt(sqlStr);
	}
	
	///function ���¹���Ա����
	///return
	///param ����ԱID
	///param ����Ա������
	public void updateAdminPassword(String user_id, String new_pass) {
		String sqlStr = "update iot_user_admin set user_pass = ? where user_id = ?";
		logger.info(sqlStr);
		jdbc_ve_db.update(sqlStr, new Object[] {new_pass, user_id});
	}
	
	///function ɾ�����Ա
	///return
	///param ����ԱID
	public void delAdmin(String user_id) {
		String sqlStr = "delete from iot_user_admin where user_id = ?";
		logger.info(sqlStr);
		jdbc_ve_db.update(sqlStr, new Object[] {user_id});
	}
}
