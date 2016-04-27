package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeTemplateProtocolHttpDao;
import com.cetc.iot.database.model.PeTemplateProtocolHttp;

@Repository
public class PeTemplateProtocolHttpDaoImpl implements PeTemplateProtocolHttpDao {

	private static Logger logger = Logger
			.getLogger(PeTemplateProtocolHttpDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// int id = -1;
	// String rule;
	// String interface_id;
	// String template_id;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(PeTemplateProtocolHttp peTemplateProtocolHttp) {
	 * this.id = peTemplateProtocolHttp.getId(); this.rule =
	 * peTemplateProtocolHttp.getRule(); this.interface_id =
	 * peTemplateProtocolHttp.getInterface_id(); this.template_id =
	 * peTemplateProtocolHttp.getTemplate_id(); }
	 */

	@Override
	public List<Map<String, Object>> query(
			PeTemplateProtocolHttp peTemplateProtocolHttp, int page, int size) {
		// preOperation(peTemplateProtocolHttp);
		String sqlStr = "select * from iot_pe_template_protocol_http where";
		if (peTemplateProtocolHttp.getId() != -1) {
			sqlStr += " id = " + peTemplateProtocolHttp.getId();
		}
		if (peTemplateProtocolHttp.getRule() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " rule = " + "'" + peTemplateProtocolHttp.getRule()
						+ "'";
			} else {
				sqlStr += " and rule = " + "'"
						+ peTemplateProtocolHttp.getRule() + "'";
			}
		}
		if (peTemplateProtocolHttp.getInterface_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_id = " + "'"
						+ peTemplateProtocolHttp.getInterface_id() + "'";
			} else {
				sqlStr += " and interface_id = " + "'"
						+ peTemplateProtocolHttp.getInterface_id() + "'";
			}
		}
		if (peTemplateProtocolHttp.getTemplate_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_id = " + "'"
						+ peTemplateProtocolHttp.getTemplate_id() + "'";
			} else {
				sqlStr += " and template_id = " + "'"
						+ peTemplateProtocolHttp.getTemplate_id() + "'";
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
	public String add(PeTemplateProtocolHttp peTemplateProtocolHttp) {
		// preOperation(peTemplateProtocolHttp);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_pe_template_protocol_http(";

		if (peTemplateProtocolHttp.getRule() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "rule";
				sqlStr2 += "'" + peTemplateProtocolHttp.getRule() + "'";
			} else {
				sqlStr1 += ",rule";
				sqlStr2 += ",'" + peTemplateProtocolHttp.getRule() + "'";
			}

		}
		if (peTemplateProtocolHttp.getInterface_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_id";
				sqlStr2 += "'" + peTemplateProtocolHttp.getInterface_id() + "'";
			} else {
				sqlStr1 += ",interface_id";
				sqlStr2 += ",'" + peTemplateProtocolHttp.getInterface_id()
						+ "'";
			}

		}
		if (peTemplateProtocolHttp.getTemplate_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_id";
				sqlStr2 += ",'" + peTemplateProtocolHttp.getTemplate_id() + "'";
			} else {
				sqlStr1 += ",template_id";
				sqlStr2 += ",'" + peTemplateProtocolHttp.getTemplate_id() + "'";
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
