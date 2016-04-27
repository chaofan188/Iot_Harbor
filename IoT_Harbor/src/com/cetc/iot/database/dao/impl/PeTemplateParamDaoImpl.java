package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeTemplateParamDao;
import com.cetc.iot.database.model.PeTemplateParam;

@Repository
public class PeTemplateParamDaoImpl implements PeTemplateParamDao {

	private static Logger logger = Logger
			.getLogger(PeTemplateParamDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// int template_param_id;
	// String template_id;
	// String key;
	// int type=-1;
	// String value_min;
	// String value_max;
	// String unit;
	// String option;
	// String description;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(PeTemplateParam param) { this.template_param_id
	 * = param.getParam_id(); this.template_id = param.getTemplate_id();
	 * this.key = param.getKey(); this.type = param.getType(); this.value_max =
	 * param.getValue_max(); this.value_min = param.getValue_min(); this.option
	 * = param.getOption(); this.description = param.getDescription(); }
	 */

	@Override
	public List<Map<String, Object>> query(PeTemplateParam param, int page,
			int size) {
		// preOperation(param);
		String sqlStr = "select * from iot_pe_template_param where";
		// if (param.getParam_id() > 0) {
		// sqlStr += " template_param_id = " + "'" + param.getParam_id() + "'";
		// }
		if (param.getTemplate_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_id = " + "'" + param.getTemplate_id()
						+ "'";
			} else {
				sqlStr += " and template_id = " + "'" + param.getTemplate_id()
						+ "'";
			}
		}
		if (param.getKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_template_key = " + "'" + param.getKey() + "'";
			} else {
				sqlStr += " and pe_template_key = " + "'" + param.getKey()
						+ "'";
			}
		}
		if (param.getType() > 0) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_template_type = " + param.getType();
			} else {
				sqlStr += " and pe_template_type = " + param.getType();
			}
		}
		if (param.getValue_min() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " value_min = " + "'" + param.getValue_min() + "'";
			} else {
				sqlStr += " and value_min = " + "'" + param.getValue_min()
						+ "'";
			}
		}
		if (param.getValue_max() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " value_max = " + "'" + param.getValue_max() + "'";
			} else {
				sqlStr += " and value_max = " + "'" + param.getValue_max()
						+ "'";
			}
		}
		if (param.getUnit() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " unit = " + "'" + param.getUnit() + "'";
			} else {
				sqlStr += " and unit = " + "'" + param.getUnit() + "'";
			}
		}
		if (param.getOption() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_template_option = " + "'" + param.getOption()
						+ "'";
			} else {
				sqlStr += " and pe_template_option = " + "'"
						+ param.getOption() + "'";
			}
		}
		if (param.getDescription() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " description = " + "'" + param.getDescription()
						+ "'";
			} else {
				sqlStr += " and description = " + "'" + param.getDescription()
						+ "'";
			}
		}

		if (page > 0 && size > 0) {
			sqlStr += " limit " + page + "," + size + "";
		}
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String add(PeTemplateParam param) {
		// preOperation(param);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_pe_template_param(";

		if (param.getTemplate_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_id";
				sqlStr2 += "'" + param.getTemplate_id() + "'";
			} else {
				sqlStr1 += ",template_id";
				sqlStr2 += ",'" + param.getTemplate_id() + "'";
			}

		}
		if (param.getKey() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_template_key";
				sqlStr2 += "'" + param.getKey() + "'";
			} else {
				sqlStr1 += ",pe_template_key";
				sqlStr2 += ",'" + param.getKey() + "'";
			}

		}
		if (param.getType() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_template_type";
				sqlStr2 += "" + param.getType();
			} else {
				sqlStr1 += ",pe_template_type";
				sqlStr2 += "," + param.getType();
			}

		}
		if (param.getValue_min() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "value_min";
				sqlStr2 += "'" + param.getValue_min() + "'";
			} else {
				sqlStr1 += ",value_min";
				sqlStr2 += ",'" + param.getValue_min() + "'";
			}

		}
		if (param.getValue_max() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "value_max";
				sqlStr2 += "'" + param.getValue_max() + "'";
			} else {
				sqlStr1 += ",value_max";
				sqlStr2 += ",'" + param.getValue_max() + "'";
			}

		}
		if (param.getUnit() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "unit";
				sqlStr2 += "'" + param.getUnit() + "'";
			} else {
				sqlStr1 += ",unit";
				sqlStr2 += ",'" + param.getUnit() + "'";
			}

		}
		if (param.getOption() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_template_option";
				sqlStr2 += "'" + param.getOption() + "'";
			} else {
				sqlStr1 += ",pe_template_option";
				sqlStr2 += ",'" + param.getOption() + "'";
			}

		}
		if (param.getDescription() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "description";
				sqlStr2 += "'" + param.getDescription() + "'";
			} else {
				sqlStr1 += ",description";
				sqlStr2 += ",'" + param.getDescription() + "'";
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
