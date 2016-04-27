package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.ProtocolHttpDao;
import com.cetc.iot.database.model.ProtocolHttp;

@Repository
public class ProtocolHttpDaoImpl implements ProtocolHttpDao {

	private static Logger logger = Logger.getLogger(ProtocolHttpDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// int id = -1;
	// String url;
	// String rule;
	// int method = -1;
	// String pe_id;
	// int http_template_id;
	// String interface_id;

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(ProtocolHttp http) { this.id = http.getId();
	 * this.url = http.getUrl(); this.rule = http.getRule(); this.method =
	 * http.getMethod(); this.pe_id = http.getPe_id(); this.http_template_id =
	 * http.getHttp_template_id(); this.interface_id = http.getInterface_id(); }
	 */

	@Override
	public List<Map<String, Object>> query(ProtocolHttp http, int page, int size) {
		// preOperation(http);
		String sqlStr = "select * from iot_protocol_http where";
		// if (http.getId() != -1) {
		// sqlStr += " id = " + "'" + http.getId() + "'";
		// }
		if (http.getUrl() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " url = " + "'" + http.getUrl() + "'";
			} else {
				sqlStr += " and url = " + "'" + http.getUrl() + "'";
			}
		}
		if (http.getMethod() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " method = " + "'" + http.getMethod() + "'";
			} else {
				sqlStr += " and method = " + "'" + http.getMethod() + "'";
			}
		}
		if (http.getRule() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " rule = " + "'" + http.getRule() + "'";
			} else {
				sqlStr += " and rule = " + "'" + http.getRule() + "'";
			}
		}
		if (http.getPe_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_id = " + "'" + http.getPe_id() + "'";
			} else {
				sqlStr += " and pe_id = " + "'" + http.getPe_id() + "'";
			}
		}
		if (http.getHttp_template_id() > -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " http_template_id = " + "'"
						+ http.getHttp_template_id() + "'";
			} else {
				sqlStr += " and http_template_id = " + "'"
						+ http.getHttp_template_id() + "'";
			}
		}

		if (http.getInterface_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_id = " + "'" + http.getInterface_id()
						+ "'";
			} else {
				sqlStr += " and interface_id = " + "'" + http.getInterface_id()
						+ "'";
			}
		}
		if (page > 0 && size > 0)
			sqlStr += " limit " + page + "," + size + "";

		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String add(ProtocolHttp http) {
		// preOperation(http);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_protocol_http(";

		/*
		 * if(pe_param_id!=-1){
		 * 
		 * sqlStr1 +="pe_param_id"; sqlStr2 += "'"+pe_param_id+"'";
		 * 
		 * 
		 * }
		 */
		if (http.getUrl() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "url";
				sqlStr2 += "'" + http.getUrl() + "'";
			} else {
				sqlStr1 += ",url";
				sqlStr2 += ",'" + http.getUrl() + "'";
			}

		}
		if (http.getRule() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "rule";
				sqlStr2 += "'" + http.getRule() + "'";
			} else {
				sqlStr1 += ",rule";
				sqlStr2 += ",'" + http.getRule() + "'";
			}

		}
		if (http.getMethod() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "method";
				sqlStr2 += "'" + http.getMethod() + "'";
			} else {
				sqlStr1 += ",method";
				sqlStr2 += ",'" + http.getMethod() + "'";
			}

		}
		if (http.getHttp_template_id() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "http_template_id";
				sqlStr2 += "," + http.getHttp_template_id() + "'";
			} else {
				sqlStr1 += ",http_template_id";
				sqlStr2 += ",'" + http.getHttp_template_id() + "'";
			}

		}
		if (http.getPe_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_id";
				sqlStr2 += "'" + http.getPe_id() + "'";
			} else {
				sqlStr1 += ",pe_id";
				sqlStr2 += ",'" + http.getPe_id() + "'";
			}

		}

		if (http.getInterface_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_id";
				sqlStr2 += "'" + http.getInterface_id() + "'";
			} else {
				sqlStr1 += ",interface_id";
				sqlStr2 += ",'" + http.getInterface_id() + "'";
			}

		}

		sqlStr1 += ") values (";
		sqlStr2 += ")";
		sqlStr = sqlStr1 + sqlStr2;
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
