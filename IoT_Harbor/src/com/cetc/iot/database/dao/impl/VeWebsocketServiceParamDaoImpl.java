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

import com.cetc.iot.database.dao.VeWebsocketServiceParamDao;
import com.cetc.iot.database.model.VeWebsocketServiceParam;

@Repository
public class VeWebsocketServiceParamDaoImpl implements
		VeWebsocketServiceParamDao {

	private static Logger logger = Logger
			.getLogger(VeWebsocketServiceParamDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// private String param_id;
	// private String service_id;
	// private String param_type;
	// private String param_name;
	// private String param_value;

	Timestamp timeStamp = null;
	List<VeWebsocketServiceParam> list = new ArrayList<VeWebsocketServiceParam>();

	/*
	 * public void preOperation(VeWebsocketServiceParam veWebsocketServiceParam)
	 * { this.param_id = veWebsocketServiceParam.getParam_id(); this.service_id
	 * = veWebsocketServiceParam.getService_id(); this.param_type =
	 * veWebsocketServiceParam.getParam_type(); this.param_name
	 * =veWebsocketServiceParam.getParam_name(); this.param_value =
	 * veWebsocketServiceParam.getParam_value(); }
	 */

	@Override
	public List<VeWebsocketServiceParam> query(
			VeWebsocketServiceParam veWebsocketServiceParam, int page, int size) {
		// preOperation(veWebsocketServiceParam);
		String sqlStr = "select * from iot_ve_websocket_service_param where 1=1";
		if (veWebsocketServiceParam.getParam_id() != null) {
			sqlStr += " and param_id = " + "'"
					+ veWebsocketServiceParam.getParam_id() + "'";
		}
		if (veWebsocketServiceParam.getService_id() != null) {
			sqlStr += " and service_id = " + "'"
					+ veWebsocketServiceParam.getService_id() + "'";
		}

		if (veWebsocketServiceParam.getParam_type() != null) {

			sqlStr += " and param_type = " + "'"
					+ veWebsocketServiceParam.getParam_type() + "'";

		}
		if (veWebsocketServiceParam.getParam_name() != null) {

			sqlStr += " and param_name = " + "'"
					+ veWebsocketServiceParam.getParam_name() + "'";

		}
		if (veWebsocketServiceParam.getParam_name() != null) {

			sqlStr += "and param_value = " + "'"
					+ veWebsocketServiceParam.getParam_name() + "'";

		}

		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.query(sqlStr,
				new VeWebsocketServiceParamRowMapper());
		logger.info(list);
		return list;
	}

	@Override
	public String delete(VeWebsocketServiceParam veWebsocketServiceParam) {

		// preOperation(veWebsocketServiceParam);
		String sqlStr = "delete from iot_ve_websocket_service_param where 1=1 ";
		if (veWebsocketServiceParam.getParam_id() != null) {
			sqlStr += " and param_id = " + "'"
					+ veWebsocketServiceParam.getParam_id() + "'";
		}
		if (veWebsocketServiceParam.getService_id() != null) {
			sqlStr += " and service_id = " + "'"
					+ veWebsocketServiceParam.getService_id() + "'";
		}

		if (veWebsocketServiceParam.getParam_type() != null) {

			sqlStr += " and param_type = " + "'"
					+ veWebsocketServiceParam.getParam_type() + "'";

		}
		if (veWebsocketServiceParam.getParam_name() != null) {

			sqlStr += " and param_name = " + "'"
					+ veWebsocketServiceParam.getParam_name() + "'";

		}
		if (veWebsocketServiceParam.getParam_name() != null) {

			sqlStr += "and param_value = " + "'"
					+ veWebsocketServiceParam.getParam_name() + "'";

		}
		logger.info(sqlStr);
		try {
			jdbcTemplate.execute(sqlStr);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	@Override
	public String add(VeWebsocketServiceParam veWebsocketServiceParam) {

		// preOperation(veWebsocketServiceParam);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_ve_websocket_service_param(";
		if (veWebsocketServiceParam.getParam_id() != null) {

			sqlStr1 += "param_id";
			sqlStr2 += "'" + veWebsocketServiceParam.getParam_id() + "'";
		}

		if (veWebsocketServiceParam.getService_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "service_id";
				sqlStr2 += "'" + veWebsocketServiceParam.getService_id() + "'";
			} else {
				sqlStr1 += ",service_id";
				sqlStr2 += ",'" + veWebsocketServiceParam.getService_id() + "'";
			}

		}
		if (veWebsocketServiceParam.getParam_type() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "param_type";
				sqlStr2 += "'" + veWebsocketServiceParam.getParam_type() + "'";
			} else {
				sqlStr1 += ",param_type";
				sqlStr2 += ",'" + veWebsocketServiceParam.getParam_type() + "'";
			}

		}
		if (veWebsocketServiceParam.getParam_name() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "param_name";
				sqlStr2 += "'" + veWebsocketServiceParam.getParam_name() + "'";
			} else {
				sqlStr1 += ",param_name";
				sqlStr2 += ",'" + veWebsocketServiceParam.getParam_name() + "'";
			}

		}
		if (veWebsocketServiceParam.getParam_name() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "param_value";
				sqlStr2 += "'" + veWebsocketServiceParam.getParam_name() + "'";
			} else {
				sqlStr1 += ",param_value";
				sqlStr2 += ",'" + veWebsocketServiceParam.getParam_name() + "'";
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
	public String update(VeWebsocketServiceParam veWebsocketServiceParam) {
		// preOperation(veWebsocketServiceParam);
		String sqlStr = "update iot_ve_websocket_service_param set";
		if (veWebsocketServiceParam.getService_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " service_id = " + "'"
						+ veWebsocketServiceParam.getService_id() + "'";
			} else {
				sqlStr += " , service_id = " + "'"
						+ veWebsocketServiceParam.getService_id() + "'";
			}
		}
		if (veWebsocketServiceParam.getParam_type() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " param_type = " + "'"
						+ veWebsocketServiceParam.getParam_type() + "'";
			} else {
				sqlStr += " , param_type = " + "'"
						+ veWebsocketServiceParam.getParam_type() + "'";
			}
		}
		if (veWebsocketServiceParam.getParam_name() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " param_name = " + "'"
						+ veWebsocketServiceParam.getParam_name() + "'";
			} else {
				sqlStr += " , param_name = " + "'"
						+ veWebsocketServiceParam.getParam_name() + "'";
			}
		}
		if (veWebsocketServiceParam.getParam_name() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " param_value = " + "'"
						+ veWebsocketServiceParam.getParam_name() + "'";
			} else {
				sqlStr += " , param_value = " + "'"
						+ veWebsocketServiceParam.getParam_name() + "'";
			}
		}

		sqlStr += " where param_id = " + "'"
				+ veWebsocketServiceParam.getParam_id() + "'";

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
	public int queryCOUNTNUM(VeWebsocketServiceParam veWebsocketServiceParam) {
		// preOperation(veWebsocketServiceParam);
		String sqlStr = "select count(*) from iot_ve_websocket_service_param where 1=1";
		if (veWebsocketServiceParam.getParam_id() != null) {
			sqlStr += " and param_id = " + "'"
					+ veWebsocketServiceParam.getParam_id() + "'";
		}
		if (veWebsocketServiceParam.getService_id() != null) {
			sqlStr += " and service_id = " + "'"
					+ veWebsocketServiceParam.getService_id() + "'";
		}

		if (veWebsocketServiceParam.getParam_type() != null) {

			sqlStr += " and param_type = " + "'"
					+ veWebsocketServiceParam.getParam_type() + "'";

		}
		if (veWebsocketServiceParam.getParam_name() != null) {

			sqlStr += " and param_name = " + "'"
					+ veWebsocketServiceParam.getParam_name() + "'";

		}
		if (veWebsocketServiceParam.getParam_value() != null) {

			sqlStr += " and param_value = " + "'"
					+ veWebsocketServiceParam.getParam_value() + "'";

		}
		logger.info(sqlStr);
		int n = jdbcTemplate.queryForInt(sqlStr);
		return n;
	}

	class VeWebsocketServiceParamRowMapper implements
			RowMapper<VeWebsocketServiceParam> {
		@Override
		public VeWebsocketServiceParam mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			VeWebsocketServiceParam veWebsocketServiceParam = new VeWebsocketServiceParam();
			veWebsocketServiceParam.setParam_id(rs.getString("param_id"));
			veWebsocketServiceParam.setParam_name(rs.getString("param_name"));
			veWebsocketServiceParam.setParam_type(rs.getString("param_type"));
			veWebsocketServiceParam.setService_id(rs.getString("service_id"));
			veWebsocketServiceParam.setParam_value(rs.getString("param_value"));
			return veWebsocketServiceParam;
		}
	}

}
