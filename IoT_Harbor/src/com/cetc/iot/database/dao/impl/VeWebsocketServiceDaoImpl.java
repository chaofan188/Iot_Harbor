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

import com.cetc.iot.database.dao.VeWebsocketServiceDao;
import com.cetc.iot.database.model.VeWebsocketService;

@Repository
public class VeWebsocketServiceDaoImpl implements VeWebsocketServiceDao {

	private static Logger logger = Logger
			.getLogger(VeWebsocketServiceDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// private String service_id;
	// private String template_id;
	// private String service_name;
	// private String service_description;

	Timestamp timeStamp = null;
	List<VeWebsocketService> list = new ArrayList<VeWebsocketService>();

	/*
	 * public void preOperation(VeWebsocketService veWebsocketService) {
	 * this.service_id = veWebsocketService.getService_id(); this.template_id =
	 * veWebsocketService.getTemplate_id(); this.service_name =
	 * veWebsocketService.getService_name(); this.service_description
	 * =veWebsocketService.getService_description();
	 * 
	 * }
	 */

	@Override
	public List<VeWebsocketService> query(
			VeWebsocketService veWebsocketService, int page, int size) {
		// preOperation(veWebsocketService);
		String sqlStr = "select * from iot_ve_websocket_service where 1=1";
		if (veWebsocketService.getService_id() != null) {
			sqlStr += " and service_id = " + "'"
					+ veWebsocketService.getService_id() + "'";
		}
		if (veWebsocketService.getTemplate_id() != null) {
			sqlStr += " and template_id = " + "'"
					+ veWebsocketService.getTemplate_id() + "'";
		}

		if (veWebsocketService.getService_name() != null) {

			sqlStr += " and service_name = " + "'"
					+ veWebsocketService.getService_name() + "'";

		}
		if (veWebsocketService.getService_description() != null) {

			sqlStr += " and service_description = " + "'"
					+ veWebsocketService.getService_description() + "'";

		}

		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.query(sqlStr, new VeWebsocketServiceRowMapper());
		logger.info(list);
		return list;
	}

	@Override
	public String delete(VeWebsocketService veWebsocketService) {

		// preOperation(veWebsocketService);
		String sqlStr = "delete from iot_ve_websocket_service where 1=1 ";
		if (veWebsocketService.getService_id() != null) {
			sqlStr += " and service_id = " + "'"
					+ veWebsocketService.getService_id() + "'";
		}
		if (veWebsocketService.getTemplate_id() != null) {
			sqlStr += " and template_id = " + "'"
					+ veWebsocketService.getTemplate_id() + "'";
		}

		if (veWebsocketService.getService_name() != null) {

			sqlStr += " and service_name = " + "'"
					+ veWebsocketService.getService_name() + "'";

		}
		if (veWebsocketService.getService_description() != null) {

			sqlStr += " and service_description = " + "'"
					+ veWebsocketService.getService_description() + "'";

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
	public String add(VeWebsocketService veWebsocketService) {

		// preOperation(veWebsocketService);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_ve_websocket_service(";
		if (veWebsocketService.getService_id() != null) {

			sqlStr1 += "service_id";
			sqlStr2 += "'" + veWebsocketService.getService_id() + "'";
		}

		if (veWebsocketService.getTemplate_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_id";
				sqlStr2 += "'" + veWebsocketService.getTemplate_id() + "'";
			} else {
				sqlStr1 += ",template_id";
				sqlStr2 += ",'" + veWebsocketService.getTemplate_id() + "'";
			}

		}
		if (veWebsocketService.getService_name() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "service_name";
				sqlStr2 += "'" + veWebsocketService.getService_name() + "'";
			} else {
				sqlStr1 += ",service_name";
				sqlStr2 += ",'" + veWebsocketService.getService_name() + "'";
			}

		}
		if (veWebsocketService.getService_description() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "service_description";
				sqlStr2 += "'" + veWebsocketService.getService_description()
						+ "'";
			} else {
				sqlStr1 += ",service_description";
				sqlStr2 += ",'" + veWebsocketService.getService_description()
						+ "'";
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
	public String update(VeWebsocketService veWebsocketService) {
		// preOperation(veWebsocketService);
		String sqlStr = "update iot_ve_websocket_service set";
		if (veWebsocketService.getTemplate_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_id = " + "'"
						+ veWebsocketService.getTemplate_id() + "'";
			} else {
				sqlStr += " , template_id = " + "'"
						+ veWebsocketService.getTemplate_id() + "'";
			}
		}
		if (veWebsocketService.getService_name() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " service_name = " + "'"
						+ veWebsocketService.getService_name() + "'";
			} else {
				sqlStr += " , service_name = " + "'"
						+ veWebsocketService.getService_name() + "'";
			}
		}
		if (veWebsocketService.getService_description() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " service_description = " + "'"
						+ veWebsocketService.getService_description() + "'";
			} else {
				sqlStr += " , service_description = " + "'"
						+ veWebsocketService.getService_description() + "'";
			}
		}

		sqlStr += " where service_id = " + "'"
				+ veWebsocketService.getService_id() + "'";

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
	public int queryCOUNTNUM(VeWebsocketService veWebsocketService) {
		// preOperation(veWebsocketService);
		String sqlStr = "select count(*) from iot_ve_websocket_service where 1=1";
		if (veWebsocketService.getService_id() != null) {
			sqlStr += " and service_id = " + "'"
					+ veWebsocketService.getService_id() + "'";
		}
		if (veWebsocketService.getTemplate_id() != null) {
			sqlStr += " and template_id = " + "'"
					+ veWebsocketService.getTemplate_id() + "'";
		}

		if (veWebsocketService.getService_name() != null) {

			sqlStr += " and service_name = " + "'"
					+ veWebsocketService.getService_name() + "'";

		}
		if (veWebsocketService.getService_description() != null) {

			sqlStr += " and service_description = " + "'"
					+ veWebsocketService.getService_description() + "'";

		}
		logger.info(sqlStr);
		int n = jdbcTemplate.queryForInt(sqlStr);
		return n;
	}

	class VeWebsocketServiceRowMapper implements RowMapper<VeWebsocketService> {
		@Override
		public VeWebsocketService mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			VeWebsocketService veWebsocketService = new VeWebsocketService();
			veWebsocketService.setService_id(rs.getString("service_id"));
			veWebsocketService.setService_name(rs.getString("service_name"));
			veWebsocketService.setTemplate_id(rs.getString("template_id"));
			veWebsocketService.setService_description(rs
					.getString("service_description"));
			return veWebsocketService;
		}
	}

}
