package com.cetc.iot.database.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.EzvizCameraDao;
import com.cetc.iot.database.model.EzvizCamera;

@Repository
public class EzvizCameraDaoImpl implements EzvizCameraDao {

	private static Logger logger = Logger.getLogger(EzvizCameraDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// private String peId;
	// private String key;
	// private String secret;
	// private String cameraName;
	// private String m3u8Url;
	// private String interfaceId;

	/*
	 * public void preOperation(EzvizCamera cam) { this.peId = cam.getPe_id();
	 * this.key = cam.getKey(); this.secret = cam.getSecret(); this.cameraName =
	 * cam.getCameraname(); this.m3u8Url = cam.getM3u8Url(); this.interfaceId =
	 * cam.getInterface_id(); }
	 */

	@Override
	public List<Map<String, Object>> query(EzvizCamera cam, int page, int size) {
		// TODO Auto-generated method stub
		// preOperation(cam);
		String sqlStr = "select * from ezviz_camera where 1=1";
		if (cam.getPe_id() != null) {
			sqlStr += " and pe_id = " + "'" + cam.getPe_id() + "'";
		}
		if (cam.getInterface_id() != null) {
			sqlStr += " and interface_id = " + "'" + cam.getInterface_id()
					+ "'";
		}
		if (cam.getKey() != null) {
			sqlStr += " and ezviz_key = " + "'" + cam.getKey() + "'";
		}
		if (cam.getSecret() != null) {
			sqlStr += " and secret = " + "'" + cam.getSecret() + "'";
		}
		if (cam.getCameraname() != null) {
			sqlStr += " and cameraname = " + "'" + cam.getCameraname() + "'";
		}
		if (cam.getM3u8Url() != null) {
			sqlStr += " and m3u8Url = " + "'" + cam.getM3u8Url() + "'";
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
	public String delete(EzvizCamera cam) {
		// TODO Auto-generated method stub
		// preOperation(cam);
		String sqlStr1 = "delete from ezviz_camera where";
		String sqlStr2 = "select count(*) from ezviz_camera where";
		String sqlStr = "delete from ezviz_camera where";
		boolean last_flag = true;
		if (cam.getPe_id() != null) {
			sqlStr += " pe_id = " + "'" + cam.getPe_id() + "'";
			last_flag = false;
		}
		if (cam.getInterface_id() != null) {

			if (last_flag) {
				sqlStr += " interface_id = " + "'" + cam.getInterface_id()
						+ "'";
			} else {
				sqlStr += " and interface_id = " + "'" + cam.getInterface_id()
						+ "'";
			}
		}
		if (cam.getCameraname() != null) {

			if (last_flag) {
				sqlStr += " cameraname = " + "'" + cam.getCameraname() + "'";
			} else {
				sqlStr += " and cameraname = " + "'" + cam.getCameraname()
						+ "'";
			}
		}
		if (cam.getKey() != null) {
			if (last_flag) {
				sqlStr += " ezviz_key = " + "'" + cam.getKey() + "'";
			} else {
				sqlStr += " and ezviz_key = " + "'" + cam.getKey() + "'";
			}
		}
		if (cam.getSecret() != null) {
			if (last_flag) {
				sqlStr += " secret = " + "'" + cam.getSecret() + "'";
			} else {
				sqlStr += " and secret = " + "'" + cam.getSecret() + "'";
			}
		}
		if (cam.getM3u8Url() != null) {
			if (last_flag) {
				sqlStr += " m3u8Url = " + "'" + cam.getM3u8Url() + "'";
			} else {
				sqlStr += " and m3u8Url = " + "'" + cam.getM3u8Url() + "'";
			}
		}
		logger.info(sqlStr);
		// try {
		// //int i = jdbcTemplate.queryForInt(sqlStr2 + sqlStr);
		// //if (i != 0) {
		// jdbcTemplate.execute(sqlStr);
		// //} else {
		// //return "success";
		// }
		//
		// } catch (DataAccessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// return "fail";
		// }
		// return "success";
		try {
			jdbcTemplate.execute(sqlStr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return "fail";
		}
		return "success";
	}

	@Override
	public String add(EzvizCamera cam) {

		// preOperation(cam);
		String sqlStr = "";
		String sqlStr2 = "";
		String sqlStr1 = "insert into ezviz_camera(";
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
			if (cam.getCameraname() != null) {
				char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
				if (lastChar == '(') {
					sqlStr1 += "cameraname";
					sqlStr2 += "'" + cam.getCameraname() + "'";
				} else {
					sqlStr1 += ",cameraname";
					sqlStr2 += ",'" + cam.getCameraname() + "'";
				}
			}
			if (cam.getKey() != null) {
				char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
				if (lastChar == '(') {
					sqlStr1 += "ezviz_key";
					sqlStr2 += "'" + cam.getKey() + "'";
				} else {
					sqlStr1 += ",ezviz_key";
					sqlStr2 += ",'" + cam.getKey() + "'";
				}
			}
			if (cam.getSecret() != null) {
				char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
				if (lastChar == '(') {
					sqlStr1 += "secret";
					sqlStr2 += "'" + cam.getSecret() + "'";
				} else {
					sqlStr1 += ",secret";
					sqlStr2 += ",'" + cam.getSecret() + "'";
				}
			}
			if (cam.getM3u8Url() != null) {
				char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
				if (lastChar == '(') {
					sqlStr1 += "m3u8Url";
					sqlStr2 += "'" + cam.getM3u8Url() + "'";
				} else {
					sqlStr1 += ",m3u8Url";
					sqlStr2 += ",'" + cam.getM3u8Url() + "'";
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
	public String update(EzvizCamera cam) {
		// preOperation(cam);
		String sqlStr = "update ezviz_camera set";
		if (cam.getPe_id() == null || cam.getInterface_id() == null)
			return "Id can not be null";
		else {

			if (cam.getKey() != null) {
				String lastString = sqlStr.substring(sqlStr.length() - 3);
				if (lastString.equals("set")) {
					sqlStr += " ezviz_key = " + "'" + cam.getKey() + "'";
				} else {
					sqlStr += " , ezviz_key = " + "'" + cam.getKey() + "'";
				}
			}
			if (cam.getSecret() != null) {
				String lastString = sqlStr.substring(sqlStr.length() - 3);
				if (lastString.equals("set")) {
					sqlStr += " secret = " + "'" + cam.getSecret() + "'";
				} else {
					sqlStr += " , secret = " + "'" + cam.getSecret() + "'";
				}
			}
			if (cam.getCameraname() != null) {
				String lastString = sqlStr.substring(sqlStr.length() - 3);
				if (lastString.equals("set")) {
					sqlStr += " cameraname = " + "'" + cam.getCameraname()
							+ "'";
				} else {
					sqlStr += " , cameraname = " + "'" + cam.getCameraname()
							+ "'";
				}
			}
			if (cam.getM3u8Url() != null) {
				String lastString = sqlStr.substring(sqlStr.length() - 3);
				if (lastString.equals("set")) {
					sqlStr += " m3u8Url = " + "'" + cam.getM3u8Url() + "'";
				} else {
					sqlStr += " , m3u8Url = " + "'" + cam.getM3u8Url() + "'";
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
			System.out.println("11");
			return "success";
		}
	}
}
