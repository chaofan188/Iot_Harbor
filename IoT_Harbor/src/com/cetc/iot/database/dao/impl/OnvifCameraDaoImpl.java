package com.cetc.iot.database.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.OnvifCameraDao;
import com.cetc.iot.database.model.OnvifCamera;

@Repository
public class OnvifCameraDaoImpl implements OnvifCameraDao {

	private static Logger logger = Logger.getLogger(OnvifCameraDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// String peId;
	// String username;
	// String ipv4;
	// String password;
	// String interfaceId;

	/*
	 * public void preOperation(OnvifCamera cam) { this.peId = cam.getPe_id();
	 * this.interfaceId = cam.getInterface_id(); this.ipv4 = cam.getIpv4();
	 * this.password = cam.getPassword(); this.username = cam.getUsername(); }
	 */

	@Override
	public List<Map<String, Object>> query(OnvifCamera cam, int page, int size) {
		// TODO Auto-generated method stub
		// preOperation(cam);
		String sqlStr = "select * from onvif_camera where 1=1";
		if (cam.getPe_id() != null) {
			sqlStr += " and pe_id = " + "'" + cam.getPe_id() + "'";
		}
		if (cam.getInterface_id() != null) {
			sqlStr += " and interface_id = " + "'" + cam.getInterface_id()
					+ "'";
		}
		if (cam.getIpv4() != null) {
			sqlStr += " and ipv4 = " + "'" + cam.getIpv4() + "'";
		}
		if (cam.getUsername() != null) {
			sqlStr += " and username = " + "'" + cam.getUsername() + "'";
		}
		if (cam.getPassword() != null) {
			sqlStr += " and password = " + "'" + cam.getPassword() + "'";
		}

		if (page != -1 && size != -1) {
			sqlStr += " limit " + page + "," + size + "";
		}
		logger.info(sqlStr);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String delete(OnvifCamera cam) {

		/*
		 * preOperation(cam); String sqlStr1 = "delete from onvif_camera where";
		 * String sqlStr2 = "select count(*) from onvif_camera where"; String
		 * sqlStr = ""; boolean last_flag = true; if (peId != null) { sqlStr +=
		 * " pe_id = " + "'" + peId + "'"; last_flag = false; } if (interfaceId
		 * != null) {
		 * 
		 * if (last_flag) { sqlStr += " interface_id = " + "'" + interfaceId +
		 * "'"; } else { sqlStr += " and interface_id = " + "'" + interfaceId +
		 * "'"; } } if (ipv4 != null) { if (last_flag) { sqlStr += " ipv4 = " +
		 * "'" + ipv4 + "'"; } else { sqlStr += " and ipv4 = " + "'" + ipv4 +
		 * "'"; } } if (username != null) { if (last_flag) { sqlStr +=
		 * " username = " + "'" + username + "'"; } else { sqlStr +=
		 * " and username = " + "'" + username + "'"; } } if (password != null)
		 * { if (last_flag) { sqlStr += " password = " + "'" + password + "'"; }
		 * else { sqlStr += " and password = " + "'" + password + "'"; } }
		 * System.out.println(sqlStr); try { System.out.println(sqlStr2+sqlStr);
		 * int i = jdbcTemplate.queryForInt(sqlStr2+sqlStr); if(i!=0){
		 * jdbcTemplate.execute(sqlStr); }else{ return "success"; }
		 * 
		 * } catch (DataAccessException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); return "fail"; } return "success";
		 */

		// TODO Auto-generated method stub
		// preOperation(cam);
		String sqlStr = "delete from onvif_camera where";
		if (cam.getPe_id() != null) {
			sqlStr += " pe_id = " + "'" + cam.getPe_id() + "'";
		}
		if (cam.getInterface_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_id = " + "'" + cam.getInterface_id()
						+ "' ";
			} else {
				sqlStr += " and interface_id = " + "'" + cam.getInterface_id()
						+ "' ";
			}
		}
		if (cam.getUsername() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " username = " + "'" + cam.getUsername() + "' ";
			} else {
				sqlStr += " and username = " + "'" + cam.getUsername() + "' ";
			}
		}
		if (cam.getPassword() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " password = " + "'" + cam.getPassword() + "' ";
			} else {
				sqlStr += " and password = " + "'" + cam.getPassword() + "' ";
			}
		}

		if (cam.getIpv4() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " ipv4 = " + "'" + cam.getIpv4() + "' ";
			} else {
				sqlStr += " and ipv4 = " + "'" + cam.getIpv4() + "' ";
			}
		}
		logger.info(sqlStr);
		try {
			jdbcTemplate.execute(sqlStr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return "fail";
		}
		return "success";
	}

	@Override
	public String add(OnvifCamera cam) {

		// preOperation(cam);
		String sqlStr = "";
		String sqlStr2 = "";
		String sqlStr1 = "insert into onvif_camera(";
		if (cam.getPe_id() == null || cam.getInterface_id() == null)
			return "Id can not be null";
		else {
			if (cam.getPe_id() != null) {
				sqlStr1 += "pe_id";
				sqlStr2 += "'" + cam.getPe_id() + "'";
			}

			if (cam.getInterface_id() != null) {
				char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
				if (lastChar == '(') {
					sqlStr1 += "interface_id";
					sqlStr2 += "'" + cam.getInterface_id() + "'";
				} else {
					sqlStr1 += ",interface_id";
					sqlStr2 += ",'" + cam.getInterface_id() + "'";
				}
			}
			if (cam.getIpv4() != null) {
				char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
				if (lastChar == '(') {
					sqlStr1 += "ipv4";
					sqlStr2 += "'" + cam.getIpv4() + "'";
				} else {
					sqlStr1 += ",ipv4";
					sqlStr2 += ",'" + cam.getIpv4() + "'";
				}
			}
			if (cam.getUsername() != null) {
				char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
				if (lastChar == '(') {
					sqlStr1 += "username";
					sqlStr2 += "'" + cam.getUsername() + "'";
				} else {
					sqlStr1 += ",username";
					sqlStr2 += ",'" + cam.getUsername() + "'";
				}
			}
			if (cam.getPassword() != null) {
				char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
				if (lastChar == '(') {
					sqlStr1 += "password";
					sqlStr2 += "'" + cam.getPassword() + "'";
				} else {
					sqlStr1 += ",password";
					sqlStr2 += ",'" + cam.getPassword() + "'";
				}
			}
			sqlStr1 += ") values (";
			sqlStr2 += ")";
			sqlStr += sqlStr1 + sqlStr2;
			logger.info(sqlStr);
			try {
				jdbcTemplate.update(sqlStr);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				return "fail";
			}
			return "success";
		}
	}

	@Override
	public String update(OnvifCamera cam) {
		// preOperation(cam);
		String sqlStr = "update onvif_camera set";
		if (cam.getPe_id() == null || cam.getInterface_id() == null)
			return "Id can not be null";
		else {

			if (cam.getIpv4() != null) {
				String lastString = sqlStr.substring(sqlStr.length() - 3);
				if (lastString.equals("set")) {
					sqlStr += " ipv4 = " + "'" + cam.getIpv4() + "'";
				} else {
					sqlStr += " , ipv4 = " + "'" + cam.getIpv4() + "'";
				}
			}
			if (cam.getUsername() != null) {
				String lastString = sqlStr.substring(sqlStr.length() - 3);
				if (lastString.equals("set")) {
					sqlStr += " username = " + "'" + cam.getUsername() + "'";
				} else {
					sqlStr += " , username = " + "'" + cam.getUsername() + "'";
				}
			}
			if (cam.getPassword() != null) {
				String lastString = sqlStr.substring(sqlStr.length() - 3);
				if (lastString.equals("set")) {
					sqlStr += " username = " + "'" + cam.getUsername() + "'";
				} else {
					sqlStr += " , interface_id = " + "'"
							+ cam.getInterface_id() + "'";
				}
			}
			sqlStr += " where pe_id = " + "'" + cam.getPe_id()
					+ "' and interface_id = " + "'" + cam.getInterface_id()
					+ "'";
			logger.info(sqlStr);
			try {
				jdbcTemplate.update(sqlStr);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				return "fail";
			}
			return "success";
		}
	}

}
