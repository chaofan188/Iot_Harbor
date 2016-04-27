package com.cetc.iot.database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.TemplateDao;
import com.cetc.iot.database.model.VETemplate;

@Repository
public class TemplateDaoImpl implements TemplateDao {
	private static Logger logger = Logger.getLogger(TemplateDaoImpl.class
			.getName());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// private String template_id;
	// private String tpl_id;
	// private String tpl_name;
	// private String tpl_state;
	// private String tpl_type_id;
	private Date tpl_enroll_time;
	// private String tpl_description;
	// private String tpl_owner;
	// private String tpl_developer;
	// private String template_openness;
	// private String tpl_icon;
	// private String tpl_display;
	// private String tpl_version;
	// private String tpl_classpath;
	// private String tpl_alias;
	Timestamp timeStamp = null;
	List<VETemplate> list = new ArrayList<VETemplate>();

	/*
	 * public void preOperation(VETemplate vetemplate) { this.template_id =
	 * vetemplate.getTemplate_id(); this.tpl_id = vetemplate.getTpl_id();
	 * this.tpl_name = vetemplate.getTpl_name(); this.tpl_state
	 * =vetemplate.getTpl_state(); this.tpl_type_id =
	 * vetemplate.getTpl_type_id(); //this.tpl_enroll_time = new
	 * java.sql.Date(new java.util.Date().getTime()); this.tpl_enroll_time =
	 * vetemplate.getTpl_enroll_time(); this.tpl_description =
	 * vetemplate.getTpl_description(); this.tpl_owner =
	 * vetemplate.getTpl_owner(); this.tpl_developer =
	 * vetemplate.getTpl_developer(); this.template_openness =
	 * vetemplate.getTemplate_openness(); this.tpl_icon =
	 * vetemplate.getTpl_icon(); this.tpl_display = vetemplate.getTpl_display();
	 * this.tpl_version = vetemplate.getTpl_version(); this.tpl_classpath =
	 * vetemplate.getTpl_classpath(); this.tpl_alias =
	 * vetemplate.getTpl_alias(); }
	 */

	@Override
	public List<VETemplate> query(VETemplate vetemplate, int page, int size) {
		// preOperation(vetemplate);
		String sqlStr = "select * from iot_ve_template where 1=1";
		if (vetemplate.getTemplate_id() != null) {
			sqlStr += " and template_id = " + "'" + vetemplate.getTemplate_id()
					+ "'";
		}
		if (vetemplate.getTpl_id() != null) {
			sqlStr += " and tpl_id = " + "'" + vetemplate.getTpl_id() + "'";
		}

		if (vetemplate.getTpl_name() != null) {

			sqlStr += " and tpl_name = " + "'" + vetemplate.getTpl_name() + "'";

		}
		if (vetemplate.getTpl_state() != null) {

			sqlStr += " and tpl_state = " + "'" + vetemplate.getTpl_state()
					+ "'";

		}
		if (vetemplate.getTpl_type_id() != null) {

			sqlStr += " and tpl_type_id = " + "'" + vetemplate.getTpl_type_id()
					+ "'";

		}
		if (tpl_enroll_time != null) {

			sqlStr += " and tpl_enroll_time = " + "'" + tpl_enroll_time + "'";

		}
		if (vetemplate.getTpl_description() != null) {

			sqlStr += " and tpl_description like " + "'%"
					+ vetemplate.getTpl_description() + "%'";

		}
		if (vetemplate.getTpl_owner() != null) {

			sqlStr += " and tpl_owner = " + "'" + vetemplate.getTpl_owner()
					+ "'";

		}
		if (vetemplate.getTpl_developer() != null) {

			sqlStr += " and tpl_developer = " + "'"
					+ vetemplate.getTpl_developer() + "'";
		}
		if (vetemplate.getTemplate_openness() != null) {

			sqlStr += " and template_openness = " + "'"
					+ vetemplate.getTemplate_openness() + "'";
		}
		if (vetemplate.getTpl_icon() != null) {

			sqlStr += " and tpl_icon = " + "'" + vetemplate.getTpl_icon() + "'";
		}
		if (vetemplate.getTpl_display() != null) {

			sqlStr += " and tpl_display = " + "'" + vetemplate.getTpl_display()
					+ "'";
		}
		if (vetemplate.getTpl_version() != null) {

			sqlStr += " and tpl_version = " + "'" + vetemplate.getTpl_version()
					+ "'";
		}
		if (vetemplate.getTpl_classpath() != null) {

			sqlStr += " and tpl_classpath = " + "'"
					+ vetemplate.getTpl_classpath() + "'";
		}
		if (vetemplate.getTpl_alias() != null) {

			sqlStr += " and tpl_alias = " + "'" + vetemplate.getTpl_alias()
					+ "'";
		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.query(sqlStr, new VETemplateRowMapper());
		logger.info(list);
		return list;
	}

	@Override
	public String delete(VETemplate vetemplate) {

		// preOperation(vetemplate);
		String sqlStr = "delete from iot_ve_template where 1=1 ";
		if (vetemplate.getTemplate_id() != null) {
			sqlStr += " and template_id = " + "'" + vetemplate.getTemplate_id()
					+ "'";
		}
		if (vetemplate.getTpl_id() != null) {
			sqlStr += " and tpl_id = " + "'" + vetemplate.getTpl_id() + "'";
		}

		if (vetemplate.getTpl_name() != null) {

			sqlStr += " and tpl_name = " + "'" + vetemplate.getTpl_name() + "'";

		}
		if (vetemplate.getTpl_state() != null) {

			sqlStr += " and tpl_state = " + "'" + vetemplate.getTpl_state()
					+ "'";

		}
		if (vetemplate.getTpl_type_id() != null) {

			sqlStr += " and tpl_type_id = " + "'" + vetemplate.getTpl_type_id()
					+ "'";

		}
		if (tpl_enroll_time != null) {

			sqlStr += " and tpl_enroll_time = " + "'" + tpl_enroll_time + "'";

		}
		if (vetemplate.getTpl_description() != null) {

			sqlStr += " and tpl_description = " + "'"
					+ vetemplate.getTpl_description() + "'";

		}
		if (vetemplate.getTpl_owner() != null) {

			sqlStr += " and tpl_owner = " + "'" + vetemplate.getTpl_owner()
					+ "'";

		}
		if (vetemplate.getTemplate_id() != null) {

			sqlStr += " and template_id = " + "'" + vetemplate.getTemplate_id()
					+ "'";
		}
		if (vetemplate.getTpl_developer() != null) {

			sqlStr += " and tpl_developer = " + "'"
					+ vetemplate.getTpl_developer() + "'";
		}
		if (vetemplate.getTemplate_openness() != null) {

			sqlStr += " and template_openness = " + "'"
					+ vetemplate.getTemplate_openness() + "'";
		}
		if (vetemplate.getTpl_icon() != null) {

			sqlStr += " and tpl_icon = " + "'" + vetemplate.getTpl_icon() + "'";
		}
		if (vetemplate.getTpl_display() != null) {

			sqlStr += " and tpl_display = " + "'" + vetemplate.getTpl_display()
					+ "'";
		}
		if (vetemplate.getTpl_version() != null) {

			sqlStr += " and tpl_version = " + "'" + vetemplate.getTpl_version()
					+ "'";
		}
		if (vetemplate.getTpl_classpath() != null) {

			sqlStr += " and tpl_classpath = " + "'"
					+ vetemplate.getTpl_classpath() + "'";
		}
		if (vetemplate.getTpl_alias() != null) {

			sqlStr += " and tpl_alias = " + "'" + vetemplate.getTpl_alias()
					+ "'";
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
	public String add(VETemplate vetemplate) {

		// preOperation(vetemplate);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_ve_template(";
		if (vetemplate.getTemplate_id() != null) {

			sqlStr1 += "template_id";
			sqlStr2 += "'" + vetemplate.getTemplate_id() + "'";
		}

		if (vetemplate.getTpl_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_id";
				sqlStr2 += "'" + vetemplate.getTpl_id() + "'";
			} else {
				sqlStr1 += ",tpl_id";
				sqlStr2 += ",'" + vetemplate.getTpl_id() + "'";
			}

		}
		if (vetemplate.getTpl_name() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_name";
				sqlStr2 += "'" + vetemplate.getTpl_name() + "'";
			} else {
				sqlStr1 += ",tpl_name";
				sqlStr2 += ",'" + vetemplate.getTpl_name() + "'";
			}

		}
		if (vetemplate.getTpl_state() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_state";
				sqlStr2 += "'" + vetemplate.getTpl_state() + "'";
			} else {
				sqlStr1 += ",tpl_state";
				sqlStr2 += ",'" + vetemplate.getTpl_state() + "'";
			}

		}
		if (vetemplate.getTpl_type_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_type_id";
				sqlStr2 += "'" + vetemplate.getTpl_type_id() + "'";
			} else {
				sqlStr1 += ",tpl_type_id";
				sqlStr2 += ",'" + vetemplate.getTpl_type_id() + "'";
			}

		}
		if (tpl_enroll_time != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_enroll_time";
				sqlStr2 += "'" + tpl_enroll_time + "'";
			} else {
				sqlStr1 += ",tpl_enroll_time";
				sqlStr2 += ",'" + tpl_enroll_time + "'";
			}

		}
		if (vetemplate.getTpl_description() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_description";
				sqlStr2 += "'" + vetemplate.getTpl_description() + "'";
			} else {
				sqlStr1 += ",tpl_description";
				sqlStr2 += ",'" + vetemplate.getTpl_description() + "'";
			}

		}
		if (vetemplate.getTpl_owner() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_owner";
				sqlStr2 += "'" + vetemplate.getTpl_owner() + "'";
			} else {
				sqlStr1 += ",tpl_owner";
				sqlStr2 += ",'" + vetemplate.getTpl_owner() + "'";
			}

		}
		if (vetemplate.getTpl_developer() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_developer";
				sqlStr2 += "'" + vetemplate.getTpl_developer() + "'";
			} else {
				sqlStr1 += ",tpl_developer";
				sqlStr2 += ",'" + vetemplate.getTpl_developer() + "'";
			}

		}
		if (vetemplate.getTemplate_openness() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_openness";
				sqlStr2 += "'" + vetemplate.getTemplate_openness() + "'";
			} else {
				sqlStr1 += ",template_openness";
				sqlStr2 += ",'" + vetemplate.getTemplate_openness() + "'";
			}

		}
		if (vetemplate.getTpl_icon() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_icon";
				sqlStr2 += "'" + vetemplate.getTpl_icon() + "'";
			} else {
				sqlStr1 += ",tpl_icon";
				sqlStr2 += ",'" + vetemplate.getTpl_icon() + "'";
			}

		}
		if (vetemplate.getTpl_display() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_display";
				sqlStr2 += "'" + vetemplate.getTpl_display() + "'";
			} else {
				sqlStr1 += ",tpl_display";
				sqlStr2 += ",'" + vetemplate.getTpl_display() + "'";
			}

		}
		if (vetemplate.getTpl_version() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_version";
				sqlStr2 += "'" + vetemplate.getTpl_version() + "'";
			} else {
				sqlStr1 += ",tpl_version";
				sqlStr2 += ",'" + vetemplate.getTpl_version() + "'";
			}

		}
		if (vetemplate.getTpl_classpath() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_classpath";
				sqlStr2 += "'" + vetemplate.getTpl_classpath() + "'";
			} else {
				sqlStr1 += ",tpl_classpath";
				sqlStr2 += ",'" + vetemplate.getTpl_classpath() + "'";
			}

		}
		if (vetemplate.getTpl_alias() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "tpl_alias";
				sqlStr2 += "'" + vetemplate.getTpl_alias() + "'";
			} else {
				sqlStr1 += ",tpl_alias";
				sqlStr2 += ",'" + vetemplate.getTpl_alias() + "'";
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
			logger.error(sqlStr, e);
			return "fail";
		}

	}

	@Override
	public String update(VETemplate vetemplate) {
		// preOperation(vetemplate);
		String sqlStr = "update iot_ve_template set";
		if (vetemplate.getTpl_developer() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_developer = " + "'"
						+ vetemplate.getTpl_developer() + "'";
			} else {
				sqlStr += " , tpl_developer = " + "'"
						+ vetemplate.getTpl_developer() + "'";
			}
		}
		if (vetemplate.getTpl_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_id = " + "'" + vetemplate.getTpl_id() + "'";
			} else {
				sqlStr += " , tpl_id = " + "'" + vetemplate.getTpl_id() + "'";
			}
		}
		if (vetemplate.getTpl_name() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_name = " + "'" + vetemplate.getTpl_name() + "'";
			} else {
				sqlStr += " , tpl_name = " + "'" + vetemplate.getTpl_name()
						+ "'";
			}
		}
		if (vetemplate.getTpl_state() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_state = " + "'" + vetemplate.getTpl_state()
						+ "'";
			} else {
				sqlStr += " , tpl_state = " + "'" + vetemplate.getTpl_state()
						+ "'";
			}
		}
		if (vetemplate.getTpl_type_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_type_id = " + "'" + vetemplate.getTpl_type_id()
						+ "'";
			} else {
				sqlStr += " , tpl_type_id = " + "'"
						+ vetemplate.getTpl_type_id() + "'";
			}
		}
		if (tpl_enroll_time != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_enroll_time = " + "'" + tpl_enroll_time + "'";
			} else {
				sqlStr += " , tpl_enroll_time = " + "'" + tpl_enroll_time + "'";
			}
		}

		if (vetemplate.getTpl_description() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_description = " + "'"
						+ vetemplate.getTpl_description() + "'";
			} else {
				sqlStr += " , tpl_description = " + "'"
						+ vetemplate.getTpl_description() + "'";
			}
		}
		if (vetemplate.getTpl_owner() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_owner = " + "'" + vetemplate.getTpl_owner()
						+ "'";
			} else {
				sqlStr += " , tpl_owner = " + "'" + vetemplate.getTpl_owner()
						+ "'";
			}
		}
		if (vetemplate.getTemplate_openness() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_openness = " + "'"
						+ vetemplate.getTemplate_openness() + "'";
			} else {
				sqlStr += " , template_openness = " + "'"
						+ vetemplate.getTemplate_openness() + "'";
			}
		}
		if (vetemplate.getTpl_icon() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_icon = " + "'" + vetemplate.getTpl_icon() + "'";
			} else {
				sqlStr += " , tpl_icon = " + "'" + vetemplate.getTpl_icon()
						+ "'";
			}
		}
		if (vetemplate.getTpl_display() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_display = " + "'" + vetemplate.getTpl_display()
						+ "'";
			} else {
				sqlStr += " , tpl_display = " + "'"
						+ vetemplate.getTpl_display() + "'";
			}
		}
		if (vetemplate.getTpl_version() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_version = " + "'" + vetemplate.getTpl_version()
						+ "'";
			} else {
				sqlStr += " , tpl_version = " + "'"
						+ vetemplate.getTpl_version() + "'";
			}
		}
		if (vetemplate.getTpl_classpath() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_classpath = " + "'"
						+ vetemplate.getTpl_classpath() + "'";
			} else {
				sqlStr += " , tpl_classpath = " + "'"
						+ vetemplate.getTpl_classpath() + "'";
			}
		}
		if (vetemplate.getTpl_alias() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " tpl_alias = " + "'" + vetemplate.getTpl_alias()
						+ "'";
			} else {
				sqlStr += " , tpl_alias = " + "'" + vetemplate.getTpl_alias()
						+ "'";
			}
		}
		sqlStr += " where template_id = " + "'" + vetemplate.getTemplate_id()
				+ "'";

		logger.info(sqlStr);
		try {
			int num = jdbcTemplate.update(sqlStr);
			if (num != 0) {
				return "success";
			} else {
				return "fail";
			}
		} catch (DataAccessException e) {
			logger.error(sqlStr, e);
			return "fail";
		}

	}

	class VETemplateRowMapper implements RowMapper<VETemplate> {
		@Override
		public VETemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
			VETemplate vetemplate = new VETemplate();
			vetemplate.setTemplate_id(rs.getString("template_id"));
			vetemplate.setTemplate_openness(rs.getString("template_openness"));
			vetemplate.setTpl_classpath(rs.getString("tpl_classpath"));
			vetemplate.setTpl_description(rs.getString("tpl_description"));
			vetemplate.setTpl_developer(rs.getString("tpl_owner"));
			vetemplate.setTpl_display(rs.getString("tpl_display"));
			vetemplate.setTpl_enroll_time(rs.getDate("tpl_enroll_time"));
			vetemplate.setTpl_icon(rs.getString("tpl_icon"));
			vetemplate.setTpl_id(rs.getString("tpl_id"));
			vetemplate.setTpl_name(rs.getString("tpl_name"));
			vetemplate.setTpl_owner(rs.getString("tpl_owner"));
			vetemplate.setTpl_state(rs.getString("tpl_state"));
			vetemplate.setTpl_type_id(rs.getString("tpl_type_id"));
			vetemplate.setTpl_version(rs.getString("tpl_version"));
			vetemplate.setTpl_version(rs.getString("tpl_alias"));

			return vetemplate;
		}
	}
}
