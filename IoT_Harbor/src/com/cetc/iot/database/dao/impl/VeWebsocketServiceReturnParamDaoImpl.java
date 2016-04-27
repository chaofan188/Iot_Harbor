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

import com.cetc.iot.database.dao.VeWebsocketServiceReturnParamDao;
import com.cetc.iot.database.model.VeWebsocketServiceReturnParam;
@Repository
public class VeWebsocketServiceReturnParamDaoImpl implements
		VeWebsocketServiceReturnParamDao {

	private static Logger logger = Logger
			.getLogger(VeWebsocketServiceReturnParamDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// private String returnParam_id;
	// private String service_id;
	// private String returnParam_type;
	// private String returnParam_name;
	// private String returnParam_description;

	Timestamp timeStamp = null;
	List<VeWebsocketServiceReturnParam> list = new ArrayList<VeWebsocketServiceReturnParam>();

	/*
	 * public void preOperation(VeWebsocketServiceReturnParam VeWebsocketServiceReturnParam)
	 * { this.returnParam_id = VeWebsocketServiceReturnParam.getReturnParam_id(); this.service_id
	 * = VeWebsocketServiceReturnParam.getService_id(); this.returnParam_type =
	 * VeWebsocketServiceReturnParam.getReturnParam_type(); this.returnParam_name
	 * =VeWebsocketServiceReturnParam.getReturnParam_name(); this.returnParam_description =
	 * VeWebsocketServiceReturnParam.getParam_value(); }
	 */

	@Override
	public List<VeWebsocketServiceReturnParam> query(
			VeWebsocketServiceReturnParam veWebsocketServiceReturnParam, int page, int size) {
		// preOperation(VeWebsocketServiceReturnParam);
		String sqlStr = "select * from iot_ve_websocket_service_returnParam where 1=1";
		if (veWebsocketServiceReturnParam.getReturnParam_id() != null) {
			sqlStr += " and returnParam_id = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_id() + "'";
		}
		if (veWebsocketServiceReturnParam.getService_id() != null) {
			sqlStr += " and service_id = " + "'"
					+ veWebsocketServiceReturnParam.getService_id() + "'";
		}

		if (veWebsocketServiceReturnParam.getReturnParam_type() != null) {

			sqlStr += " and returnParam_type = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_type() + "'";

		}
		if (veWebsocketServiceReturnParam.getReturnParam_name() != null) {

			sqlStr += " and returnParam_name = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_name() + "'";

		}
		if (veWebsocketServiceReturnParam.getReturnParam_description() != null) {

			sqlStr += "and returnParam_description = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_description() + "'";

		}

		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.query(sqlStr,
				new VeWebsocketServiceReturnParamRowMapper());
		logger.info(list);
		return list;
	}

	@Override
	public String delete(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam) {

		// preOperation(VeWebsocketServiceReturnParam);
		String sqlStr = "delete from iot_ve_websocket_service_returnParam where 1=1 ";
		if (veWebsocketServiceReturnParam.getReturnParam_id() != null) {
			sqlStr += " and returnParam_id = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_id() + "'";
		}
		if (veWebsocketServiceReturnParam.getService_id() != null) {
			sqlStr += " and service_id = " + "'"
					+ veWebsocketServiceReturnParam.getService_id() + "'";
		}

		if (veWebsocketServiceReturnParam.getReturnParam_type() != null) {

			sqlStr += " and returnParam_type = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_type() + "'";

		}
		if (veWebsocketServiceReturnParam.getReturnParam_name() != null) {

			sqlStr += " and returnParam_name = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_name() + "'";

		}
		if (veWebsocketServiceReturnParam.getReturnParam_description() != null) {

			sqlStr += "and returnParam_description = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_description() + "'";

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
	public String add(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam) {

		// preOperation(VeWebsocketServiceReturnParam);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_ve_websocket_service_returnParam(";
		if (veWebsocketServiceReturnParam.getReturnParam_id() != null) {

			sqlStr1 += "returnParam_id";
			sqlStr2 += "'" + veWebsocketServiceReturnParam.getReturnParam_id() + "'";
		}

		if (veWebsocketServiceReturnParam.getService_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "service_id";
				sqlStr2 += "'" + veWebsocketServiceReturnParam.getService_id() + "'";
			} else {
				sqlStr1 += ",service_id";
				sqlStr2 += ",'" + veWebsocketServiceReturnParam.getService_id() + "'";
			}

		}
		if (veWebsocketServiceReturnParam.getReturnParam_type() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "returnParam_type";
				sqlStr2 += "'" + veWebsocketServiceReturnParam.getReturnParam_type() + "'";
			} else {
				sqlStr1 += ",returnParam_type";
				sqlStr2 += ",'" + veWebsocketServiceReturnParam.getReturnParam_type() + "'";
			}

		}
		if (veWebsocketServiceReturnParam.getReturnParam_name() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "returnParam_name";
				sqlStr2 += "'" + veWebsocketServiceReturnParam.getReturnParam_name() + "'";
			} else {
				sqlStr1 += ",returnParam_name";
				sqlStr2 += ",'" + veWebsocketServiceReturnParam.getReturnParam_name() + "'";
			}

		}
		if (veWebsocketServiceReturnParam.getReturnParam_description() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "returnParam_description";
				sqlStr2 += "'" + veWebsocketServiceReturnParam.getReturnParam_description() + "'";
			} else {
				sqlStr1 += ",returnParam_description";
				sqlStr2 += ",'" + veWebsocketServiceReturnParam.getReturnParam_description() + "'";
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
	public String update(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam) {
		// preOperation(VeWebsocketServiceReturnParam);
		String sqlStr = "update iot_ve_websocket_service_returnParam set";
		if (veWebsocketServiceReturnParam.getService_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " service_id = " + "'"
						+ veWebsocketServiceReturnParam.getService_id() + "'";
			} else {
				sqlStr += " , service_id = " + "'"
						+ veWebsocketServiceReturnParam.getService_id() + "'";
			}
		}
		if (veWebsocketServiceReturnParam.getReturnParam_type() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " returnParam_type = " + "'"
						+ veWebsocketServiceReturnParam.getReturnParam_type() + "'";
			} else {
				sqlStr += " , returnParam_type = " + "'"
						+ veWebsocketServiceReturnParam.getReturnParam_type() + "'";
			}
		}
		if (veWebsocketServiceReturnParam.getReturnParam_name() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " returnParam_name = " + "'"
						+ veWebsocketServiceReturnParam.getReturnParam_name() + "'";
			} else {
				sqlStr += " , returnParam_name = " + "'"
						+ veWebsocketServiceReturnParam.getReturnParam_name() + "'";
			}
		}
		if (veWebsocketServiceReturnParam.getReturnParam_description() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " returnParam_description = " + "'"
						+ veWebsocketServiceReturnParam.getReturnParam_description() + "'";
			} else {
				sqlStr += " , returnParam_description = " + "'"
						+ veWebsocketServiceReturnParam.getReturnParam_description() + "'";
			}
		}

		sqlStr += " where returnParam_id = " + "'"
				+ veWebsocketServiceReturnParam.getReturnParam_id() + "'";

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
	public int queryCOUNTNUM(VeWebsocketServiceReturnParam veWebsocketServiceReturnParam) {
		// preOperation(VeWebsocketServiceReturnParam);
		String sqlStr = "select count(*) from iot_ve_websocket_service_returnParam where 1=1";
		if (veWebsocketServiceReturnParam.getReturnParam_id() != null) {
			sqlStr += " and returnParam_id = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_id() + "'";
		}
		if (veWebsocketServiceReturnParam.getService_id() != null) {
			sqlStr += " and service_id = " + "'"
					+ veWebsocketServiceReturnParam.getService_id() + "'";
		}

		if (veWebsocketServiceReturnParam.getReturnParam_type() != null) {

			sqlStr += " and returnParam_type = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_type() + "'";

		}
		if (veWebsocketServiceReturnParam.getReturnParam_name() != null) {

			sqlStr += " and returnParam_name = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_name() + "'";

		}
		if (veWebsocketServiceReturnParam.getReturnParam_description() != null) {

			sqlStr += " and returnParam_description = " + "'"
					+ veWebsocketServiceReturnParam.getReturnParam_description() + "'";

		}
		logger.info(sqlStr);
		int n = jdbcTemplate.queryForInt(sqlStr);
		return n;
	}

	class VeWebsocketServiceReturnParamRowMapper implements
			RowMapper<VeWebsocketServiceReturnParam> {
		@Override
		public VeWebsocketServiceReturnParam mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			VeWebsocketServiceReturnParam veWebsocketServiceReturnParam = new VeWebsocketServiceReturnParam();
			veWebsocketServiceReturnParam.setReturnParam_id(rs.getString("returnParam_id"));
			veWebsocketServiceReturnParam.setReturnParam_name(rs.getString("returnParam_name"));
			veWebsocketServiceReturnParam.setReturnParam_type(rs.getString("returnParam_type"));
			veWebsocketServiceReturnParam.setService_id(rs.getString("service_id"));
			veWebsocketServiceReturnParam.setReturnParam_description(rs.getString("returnParam_description"));
			return veWebsocketServiceReturnParam;
		}
	}

}
