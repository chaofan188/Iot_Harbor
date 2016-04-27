package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeParamDao;
import com.cetc.iot.database.model.PeParam;

@Repository
public class PeParamDaoImpl implements PeParamDao {

	private static Logger logger = Logger.getLogger(PeParamDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// int pe_param_id = -1;
	// String pe_id;
	// String key;
	// String content;
	// String template_param_id;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(PeParam param) { this.pe_param_id =
	 * param.getPe_param_id(); this.pe_id = param.getPe_id(); this.key =
	 * param.getKey(); this.content = param.getContent(); this.template_param_id
	 * = param.getTemplate_param_id(); }
	 */

	@Override
	public List<Map<String, Object>> query(PeParam param, int page, int size) {
		// preOperation(param);
		String sqlStr = "select * from iot_pe_param where";
		// if (param.getPe_param_id() != -1) {
		// sqlStr += " pe_param_id = " + "'" + param.getPe_param_id() + "'";
		// }
		if (param.getPe_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_id = " + "'" + param.getPe_id() + "'";
			} else {
				sqlStr += " and pe_id = " + "'" + param.getPe_id() + "'";
			}
		}
		if (param.getKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_param_key = " + "'" + param.getKey() + "'";
			} else {
				sqlStr += " and pe_param_key = " + "'" + param.getKey() + "'";
			}
		}
		if (param.getContent() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " content = " + "'" + param.getContent() + "'";
			} else {
				sqlStr += " and content = " + "'" + param.getContent() + "'";
			}
		}
		if (param.getTemplate_param_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_param_id = " + "'"
						+ param.getTemplate_param_id() + "'";
			} else {
				sqlStr += " and template_param_id = " + "'"
						+ param.getTemplate_param_id() + "'";
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
	public String add(PeParam param) {
		// preOperation(param);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_pe_param(";

		/*
		 * if(pe_param_id!=-1){
		 * 
		 * sqlStr1 +="pe_param_id"; sqlStr2 += "'"+pe_param_id+"'";
		 * 
		 * 
		 * }
		 */
		if (param.getPe_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_id";
				sqlStr2 += "'" + param.getPe_id() + "'";
			} else {
				sqlStr1 += ",pe_id";
				sqlStr2 += ",'" + param.getPe_id() + "'";
			}

		}
		if (param.getKey() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_param_key";
				sqlStr2 += "'" + param.getKey() + "'";
			} else {
				sqlStr1 += ",pe_param_key";
				sqlStr2 += ",'" + param.getKey() + "'";
			}

		}
		if (param.getTemplate_param_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_param_id";
				sqlStr2 += "'" + param.getTemplate_param_id() + "'";
			} else {
				sqlStr1 += ",template_param_id";
				sqlStr2 += ",'" + param.getTemplate_param_id() + "'";
			}

		}
		if (param.getContent() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "content";
				sqlStr2 += "'" + param.getContent() + "'";
			} else {
				sqlStr1 += ",content";
				sqlStr2 += ",'" + param.getContent() + "'";
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
