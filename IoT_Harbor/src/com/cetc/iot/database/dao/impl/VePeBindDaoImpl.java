package com.cetc.iot.database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.VePeBindDao;
import com.cetc.iot.database.model.VePeBind;

@Repository
public class VePeBindDaoImpl implements VePeBindDao {

	private static Logger logger = Logger.getLogger(VePeBindDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// private String bind_id;
	// private String identify_id;
	// private String ve_id;
	// private String pe_id;
	// private String service_id;
	// private String state;
	// private String bind_type;

	Timestamp timeStamp = null;
	List<VePeBind> list = new ArrayList<VePeBind>();

	/*
	 * public void preOperation(VePeBind vePeBind) { this.bind_id =
	 * vePeBind.getBind_id(); this.identify_id = vePeBind.getIdentify_id();
	 * this.ve_id = vePeBind.getVe_id(); this.pe_id =vePeBind.getPe_id();
	 * this.service_id = vePeBind.getService_id(); this.state
	 * =vePeBind.getState(); this.bind_type =vePeBind.getBind_type();
	 * 
	 * }
	 */

	@Override
	public List<VePeBind> query(VePeBind vePeBind, int page, int size) {
		// preOperation(vePeBind);
		String sqlStr = "select * from iot_ve_pe_bind where 1=1";
		if (vePeBind.getBind_id() != null) {
			sqlStr += " and bind_id = " + "'" + vePeBind.getBind_id() + "'";
		}
		if (vePeBind.getIdentify_id() != null) {
			sqlStr += " and identify_id = " + "'" + vePeBind.getIdentify_id()
					+ "'";
		}

		if (vePeBind.getPe_id() != null) {

			sqlStr += " and pe_id = " + "'" + vePeBind.getPe_id() + "'";

		}
		if (vePeBind.getVe_id() != null) {

			sqlStr += " and ve_id = " + "'" + vePeBind.getVe_id() + "'";

		}
		if (vePeBind.getService_id() != null) {

			sqlStr += " and service_id = " + "'" + vePeBind.getService_id()
					+ "'";
		}
		if (vePeBind.getState() != null) {

			sqlStr += " and state = " + "'" + vePeBind.getState() + "'";

		}
		if (vePeBind.getBind_type() != null) {

			sqlStr += " and bind_type = " + "'" + vePeBind.getBind_type() + "'";

		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.query(sqlStr, new VePeBindRowMapper());
		logger.info(list);
		return list;
	}

	@Override
	public String delete(VePeBind vePeBind) {

		// preOperation(vePeBind);
		String sqlStr1 = "delete from iot_ve_pe_bind where 1=1 ";
		String sqlStr2 = "select count(*) from iot_ve_pe_bind where 1=1 ";
		String sqlStr = "";
		if (vePeBind.getBind_id() != null) {
			sqlStr += " and bind_id = " + "'" + vePeBind.getBind_id() + "'";
		}
		if (vePeBind.getIdentify_id() != null) {
			sqlStr += " and identify_id = " + "'" + vePeBind.getIdentify_id()
					+ "'";
		}

		if (vePeBind.getPe_id() != null) {

			sqlStr += " and pe_id = " + "'" + vePeBind.getPe_id() + "'";

		}
		if (vePeBind.getVe_id() != null) {

			sqlStr += " and ve_id = " + "'" + vePeBind.getVe_id() + "'";

		}
		if (vePeBind.getService_id() != null) {

			sqlStr += " and service_id = " + "'" + vePeBind.getService_id()
					+ "'";
		}
		if (vePeBind.getState() != null) {

			sqlStr += " and state = " + "'" + vePeBind.getState() + "'";

		}
		if (vePeBind.getBind_type() != null) {

			sqlStr += " and bind_type = " + "'" + vePeBind.getBind_type() + "'";

		}

		try {
			logger.info(sqlStr2 + sqlStr);
			int i = jdbcTemplate.queryForInt(sqlStr2 + sqlStr);
			if (i != 0) {
				logger.info(sqlStr1 + sqlStr);
				jdbcTemplate.execute(sqlStr1 + sqlStr);
			} else {
				return "success";
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	@Override
	public String add(VePeBind vePeBind) {

		// preOperation(vePeBind);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_ve_pe_bind(";
		if (vePeBind.getBind_id() != null) {

			sqlStr1 += "bind_id";
			sqlStr2 += "'" + vePeBind.getBind_id() + "'";
		}

		if (vePeBind.getIdentify_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "identify_id";
				sqlStr2 += "'" + vePeBind.getIdentify_id() + "'";
			} else {
				sqlStr1 += ",identify_id";
				sqlStr2 += ",'" + vePeBind.getIdentify_id() + "'";
			}

		}
		if (vePeBind.getPe_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_id";
				sqlStr2 += "'" + vePeBind.getPe_id() + "'";
			} else {
				sqlStr1 += ",pe_id";
				sqlStr2 += ",'" + vePeBind.getPe_id() + "'";
			}

		}

		if (vePeBind.getVe_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_id";
				sqlStr2 += "'" + vePeBind.getVe_id() + "'";
			} else {
				sqlStr1 += ",ve_id";
				sqlStr2 += ",'" + vePeBind.getVe_id() + "'";
			}

		}
		if (vePeBind.getService_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "service_id";
				sqlStr2 += "'" + vePeBind.getService_id() + "'";
			} else {
				sqlStr1 += ",service_id";
				sqlStr2 += ",'" + vePeBind.getService_id() + "'";
			}

		}
		if (vePeBind.getState() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "state";
				sqlStr2 += "'" + vePeBind.getState() + "'";
			} else {
				sqlStr1 += ",state";
				sqlStr2 += ",'" + vePeBind.getState() + "'";
			}

		}
		if (vePeBind.getBind_type() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "bind_type";
				sqlStr2 += "'" + vePeBind.getBind_type() + "'";
			} else {
				sqlStr1 += ",bind_type";
				sqlStr2 += ",'" + vePeBind.getBind_type() + "'";
			}

		}

		sqlStr1 += ") values (";
		sqlStr2 += ")";
		sqlStr = sqlStr1 + sqlStr2;

		logger.info(sqlStr);
		try {
			int num = jdbcTemplate.update(sqlStr);
			if (num != 0) {
				return "success";
			} else {
				return "fail";
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "fail";
		}

	}

	@Override
	public String update(VePeBind vePeBind) {
		// preOperation(vePeBind);
		String sqlStr = "update iot_ve_pe_bind set";
		if (vePeBind.getIdentify_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " identify_id = " + "'" + vePeBind.getIdentify_id()
						+ "'";
			} else {
				sqlStr += " , identify_id = " + "'" + vePeBind.getIdentify_id()
						+ "'";
			}
		}
		if (vePeBind.getPe_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_id = " + "'" + vePeBind.getPe_id() + "'";
			} else {
				sqlStr += " , pe_id = " + "'" + vePeBind.getPe_id() + "'";
			}
		}
		if (vePeBind.getVe_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_id = " + "'" + vePeBind.getVe_id() + "'";
			} else {
				sqlStr += " , ve_id = " + "'" + vePeBind.getVe_id() + "'";
			}
		}
		if (vePeBind.getService_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " service_id = " + "'" + vePeBind.getService_id()
						+ "'";
			} else {
				sqlStr += " , service_id = " + "'" + vePeBind.getService_id()
						+ "'";
			}
		}
		if (vePeBind.getState() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " state = " + "'" + vePeBind.getState() + "'";
			} else {
				sqlStr += " , state = " + "'" + vePeBind.getState() + "'";
			}
		}
		if (vePeBind.getBind_type() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " bind_type = " + "'" + vePeBind.getBind_type() + "'";
			} else {
				sqlStr += " , bind_type = " + "'" + vePeBind.getBind_type()
						+ "'";
			}
		}

		sqlStr += " where bind_id = " + "'" + vePeBind.getBind_id() + "'";
		logger.info(sqlStr);
		try {
			int num = jdbcTemplate.update(sqlStr);
			if (num != 0) {
				return "success";
			} else {
				return "fail";
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "fail";
		}

	}

	@Override
	public int queryCOUNTNUM(VePeBind vePeBind) {
		// preOperation(vePeBind);
		String sqlStr = "select count(*) from iot_ve_pe_bind where 1=1";
		if (vePeBind.getBind_id() != null) {
			sqlStr += " and bind_id = " + "'" + vePeBind.getBind_id() + "'";
		}
		if (vePeBind.getIdentify_id() != null) {
			sqlStr += " and identify_id = " + "'" + vePeBind.getIdentify_id()
					+ "'";
		}

		if (vePeBind.getPe_id() != null) {

			sqlStr += " and pe_id = " + "'" + vePeBind.getPe_id() + "'";

		}
		if (vePeBind.getVe_id() != null) {

			sqlStr += " and ve_id = " + "'" + vePeBind.getVe_id() + "'";

		}
		if (vePeBind.getService_id() != null) {

			sqlStr += " and service_id = " + "'" + vePeBind.getService_id()
					+ "'";

		}
		if (vePeBind.getState() != null) {

			sqlStr += " and state = " + "'" + vePeBind.getState() + "'";

		}
		if (vePeBind.getBind_type() != null) {

			sqlStr += " and bind_type = " + "'" + vePeBind.getBind_type() + "'";

		}
		logger.info(sqlStr);
		int n = jdbcTemplate.queryForInt(sqlStr);
		return n;
	}

	class VePeBindRowMapper implements RowMapper<VePeBind> {
		@Override
		public VePeBind mapRow(ResultSet rs, int rowNum) throws SQLException {
			VePeBind vePeBind = new VePeBind();
			vePeBind.setBind_id(rs.getString("bind_id"));
			vePeBind.setBind_type(rs.getString("bind_type"));
			vePeBind.setIdentify_id(rs.getString("identify_id"));
			vePeBind.setPe_id(rs.getString("pe_id"));
			vePeBind.setState(rs.getString("state"));
			vePeBind.setVe_id(rs.getString("ve_id"));
			vePeBind.setService_id(rs.getString("service_id"));

			return vePeBind;
		}
	}
}
