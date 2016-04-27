package com.cetc.iot.database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.TemplateBindDao;
import com.cetc.iot.database.model.TemplateBind;

@Repository
public class TemplateBindDaoImpl implements TemplateBindDao {

	private static Logger logger = Logger.getLogger(TemplateBindDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// private String template_bind_id;
	// private String identify_id;
	// private String ve_template_id;
	// private String pe_template_id;
	// private String service_id;
	// private int bind_max;
	// private int bind_min;
	// private String bind_type;

	Timestamp timeStamp = null;
	List<TemplateBind> list = new ArrayList<TemplateBind>();
	List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
	List<String> listString = new ArrayList<String>();

	/*
	 * public void preOperation(TemplateBind templateBind) {
	 * this.template_bind_id = templateBind.getTemplate_bind_id();
	 * this.identify_id = templateBind.getIdentify_id(); this.ve_template_id =
	 * templateBind.getVe_template_id(); this.pe_template_id
	 * =templateBind.getPe_template_id(); this.service_id =
	 * templateBind.getService_id(); this.bind_max =templateBind.getBind_max();
	 * this.bind_min =templateBind.getBind_min(); this.bind_type
	 * =templateBind.getBind_type(); }
	 */

	@Override
	public List<TemplateBind> query(TemplateBind templateBind, int page,
			int size) {
		// preOperation(templateBind);
		String sqlStr = "select * from iot_vetemplate_petemplate_bind where 1=1";
		if (templateBind.getTemplate_bind_id() != null) {
			sqlStr += " and template_bind_id = " + "'"
					+ templateBind.getTemplate_bind_id() + "'";
		}
		if (templateBind.getIdentify_id() != null) {
			sqlStr += " and identify_id = " + "'"
					+ templateBind.getIdentify_id() + "'";
		}

		if (templateBind.getVe_template_id() != null) {

			sqlStr += " and ve_template_id = " + "'"
					+ templateBind.getVe_template_id() + "'";

		}
		if (templateBind.getPe_template_id() != null) {

			sqlStr += " and pe_template_id = " + "'"
					+ templateBind.getPe_template_id() + "'";

		}
		if (templateBind.getService_id() != null) {

			sqlStr += " and service_id = " + "'" + templateBind.getService_id()
					+ "'";

		}
		if (templateBind.getBind_max() != 0) {

			sqlStr += " and bind_max = " + "'" + templateBind.getBind_max()
					+ "'";

		}
		if (templateBind.getBind_min() != 0) {

			sqlStr += " and bind_min = " + "'" + templateBind.getBind_min()
					+ "'";

		}
		if (templateBind.getBind_type() != null) {

			sqlStr += " and bind_type = " + "'" + templateBind.getBind_type()
					+ "'";

		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.query(sqlStr, new TemplateBindRowMapper());
		logger.info(list);
		return list;
	}

	@Override
	public String delete(TemplateBind templateBind) {

		// preOperation(templateBind);
		String sqlStr = "delete from iot_vetemplate_petemplate_bind where 1=1 ";
		if (templateBind.getTemplate_bind_id() != null) {
			sqlStr += " and template_bind_id = " + "'"
					+ templateBind.getTemplate_bind_id() + "'";
		}
		if (templateBind.getIdentify_id() != null) {
			sqlStr += " and identify_id = " + "'"
					+ templateBind.getIdentify_id() + "'";
		}

		if (templateBind.getVe_template_id() != null) {

			sqlStr += " and ve_template_id = " + "'"
					+ templateBind.getVe_template_id() + "'";

		}
		if (templateBind.getPe_template_id() != null) {

			sqlStr += " and pe_template_id = " + "'"
					+ templateBind.getPe_template_id() + "'";

		}
		if (templateBind.getService_id() != null) {

			sqlStr += " and service_id = " + "'" + templateBind.getService_id()
					+ "'";

		}
		if (templateBind.getBind_max() != -1) {

			sqlStr += " and bind_max = " + "'" + templateBind.getBind_max()
					+ "'";

		}
		if (templateBind.getBind_min() != -1) {

			sqlStr += " and bind_min = " + "'" + templateBind.getBind_min()
					+ "'";

		}
		if (templateBind.getBind_type() != null) {

			sqlStr += " and bind_type = " + "'" + templateBind.getBind_type()
					+ "'";

		}
		logger.info(sqlStr);
		try {
			jdbcTemplate.execute(sqlStr);
		} catch (DataAccessException e) {
			logger.error(sqlStr, e);
			return "fail";
		}
		return "success";
	}

	@Override
	public String add(TemplateBind templateBind) {

		// preOperation(templateBind);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_vetemplate_petemplate_bind(";
		if (templateBind.getTemplate_bind_id() != null) {

			sqlStr1 += "template_bind_id";
			sqlStr2 += "'" + templateBind.getTemplate_bind_id() + "'";
		}

		if (templateBind.getIdentify_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "identify_id";
				sqlStr2 += "'" + templateBind.getIdentify_id() + "'";
			} else {
				sqlStr1 += ",identify_id";
				sqlStr2 += ",'" + templateBind.getIdentify_id() + "'";
			}

		}
		if (templateBind.getVe_template_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_template_id";
				sqlStr2 += "'" + templateBind.getVe_template_id() + "'";
			} else {
				sqlStr1 += ",ve_template_id";
				sqlStr2 += ",'" + templateBind.getVe_template_id() + "'";
			}

		}
		if (templateBind.getPe_template_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_template_id";
				sqlStr2 += "'" + templateBind.getPe_template_id() + "'";
			} else {
				sqlStr1 += ",pe_template_id";
				sqlStr2 += ",'" + templateBind.getPe_template_id() + "'";
			}

		}
		if (templateBind.getService_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "service_id";
				sqlStr2 += "'" + templateBind.getService_id() + "'";
			} else {
				sqlStr1 += ",service_id";
				sqlStr2 += ",'" + templateBind.getService_id() + "'";
			}

		}
		if (templateBind.getBind_max() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "bind_max";
				sqlStr2 += "'" + templateBind.getBind_max() + "'";
			} else {
				sqlStr1 += ",bind_max";
				sqlStr2 += ",'" + templateBind.getBind_max() + "'";
			}

		}
		if (templateBind.getBind_min() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "bind_min";
				sqlStr2 += "'" + templateBind.getBind_min() + "'";
			} else {
				sqlStr1 += ",bind_min";
				sqlStr2 += ",'" + templateBind.getBind_min() + "'";
			}

		}
		if (templateBind.getBind_type() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "bind_type";
				sqlStr2 += "'" + templateBind.getBind_type() + "'";
			} else {
				sqlStr1 += ",bind_type";
				sqlStr2 += ",'" + templateBind.getBind_type() + "'";
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
	public String update(TemplateBind templateBind) {
		// preOperation(templateBind);
		String sqlStr = "update iot_vetemplate_petemplate_bind set";
		if (templateBind.getIdentify_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " identify_id = " + "'"
						+ templateBind.getIdentify_id() + "'";
			} else {
				sqlStr += " , identify_id = " + "'"
						+ templateBind.getIdentify_id() + "'";
			}
		}
		if (templateBind.getVe_template_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_template_id = " + "'"
						+ templateBind.getVe_template_id() + "'";
			} else {
				sqlStr += " , ve_template_id = " + "'"
						+ templateBind.getVe_template_id() + "'";
			}
		}
		if (templateBind.getPe_template_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_template_id = " + "'"
						+ templateBind.getPe_template_id() + "'";
			} else {
				sqlStr += " , pe_template_id = " + "'"
						+ templateBind.getPe_template_id() + "'";
			}
		}
		if (templateBind.getService_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " service_id = " + "'" + templateBind.getService_id()
						+ "'";
			} else {
				sqlStr += " , service_id = " + "'"
						+ templateBind.getService_id() + "'";
			}
		}
		if (templateBind.getBind_max() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " bind_max = " + "'" + templateBind.getBind_max()
						+ "'";
			} else {
				sqlStr += " , bind_max = " + "'" + templateBind.getBind_max()
						+ "'";
			}
		}
		if (templateBind.getBind_min() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " bind_min = " + "'" + templateBind.getBind_min()
						+ "'";
			} else {
				sqlStr += " , bind_min = " + "'" + templateBind.getBind_min()
						+ "'";
			}
		}
		if (templateBind.getBind_type() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " bind_type = " + "'" + templateBind.getBind_type()
						+ "'";
			} else {
				sqlStr += " , bind_type = " + "'" + templateBind.getBind_type()
						+ "'";
			}
		}
		sqlStr += " where template_bind_id = " + "'"
				+ templateBind.getTemplate_bind_id() + "'";
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
	public List<String> queryWithVETemplateIdDistinction(
			TemplateBind templateBind, int page, int size) {
		// preOperation(templateBind);
		String sqlStr = "select distinct ve_template_id from iot_vetemplate_petemplate_bind where 1=1";
		if (templateBind.getTemplate_bind_id() != null) {
			sqlStr += " and template_bind_id = " + "'"
					+ templateBind.getTemplate_bind_id() + "'";
		}
		if (templateBind.getIdentify_id() != null) {
			sqlStr += " and identify_id = " + "'"
					+ templateBind.getIdentify_id() + "'";
		}
		if (templateBind.getPe_template_id() != null) {

			sqlStr += " and pe_template_id = " + "'"
					+ templateBind.getPe_template_id() + "'";

		}
		if (templateBind.getService_id() != null) {

			sqlStr += " and service_id = " + "'" + templateBind.getService_id()
					+ "'";

		}
		if (templateBind.getBind_type() != null) {

			sqlStr += " and bind_type = " + "'" + templateBind.getBind_type()
					+ "'";

		}
		sqlStr += " limit " + page + "," + size + " ";
		logger.info(sqlStr);
		listMap = jdbcTemplate.queryForList(sqlStr);
		logger.info(listMap);
		/*
		 * for(int i=0;i<listMap.size();i++){
		 * listString.add(listMap.get(i).toString()); }
		 */
		if (listString.size() != 0) {
			listString.clear();
		}
		for (Map<String, Object> temp : listMap) {
			listString.add(temp.get("ve_template_id").toString());
		}
		return listString;
	}

	class TemplateBindRowMapper implements RowMapper<TemplateBind> {
		@Override
		public TemplateBind mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			TemplateBind templateBind = new TemplateBind();
			templateBind.setBind_max(rs.getInt("bind_max"));
			templateBind.setBind_min(rs.getInt("bind_min"));
			templateBind.setBind_type(rs.getString("bind_type"));
			templateBind.setIdentify_id(rs.getString("identify_id"));
			templateBind.setPe_template_id(rs.getString("pe_template_id"));
			templateBind.setTemplate_bind_id(rs.getString("template_bind_id"));
			templateBind.setVe_template_id(rs.getString("ve_template_id"));
			templateBind.setService_id(rs.getString("service_id"));

			return templateBind;
		}
	}

	@Override
	public int queryCOUNTNUM(TemplateBind templateBind) {
		// preOperation(templateBind);
		String sqlStr = "select count(*) from iot_vetemplate_petemplate_bind where 1=1";
		if (templateBind.getTemplate_bind_id() != null) {
			sqlStr += " and template_bind_id = " + "'"
					+ templateBind.getTemplate_bind_id() + "'";
		}
		if (templateBind.getIdentify_id() != null) {
			sqlStr += " and identify_id = " + "'"
					+ templateBind.getIdentify_id() + "'";
		}

		if (templateBind.getVe_template_id() != null) {

			sqlStr += " and ve_template_id = " + "'"
					+ templateBind.getVe_template_id() + "'";

		}
		if (templateBind.getPe_template_id() != null) {

			sqlStr += " and pe_template_id = " + "'"
					+ templateBind.getPe_template_id() + "'";

		}
		if (templateBind.getService_id() != null) {

			sqlStr += " and service_id = " + "'" + templateBind.getService_id()
					+ "'";

		}
		if (templateBind.getBind_type() != null) {

			sqlStr += " and bind_type = " + "'" + templateBind.getBind_type()
					+ "'";

		}
		logger.info(sqlStr);
		int i = jdbcTemplate.queryForInt(sqlStr);
		return i;
	}

}
