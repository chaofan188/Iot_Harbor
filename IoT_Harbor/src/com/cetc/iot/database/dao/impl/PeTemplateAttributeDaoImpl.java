package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeTemplateAttributeDao;
import com.cetc.iot.database.model.PeTemplateAttribute;

@Repository
public class PeTemplateAttributeDaoImpl implements PeTemplateAttributeDao {

	private static Logger logger = Logger
			.getLogger(PeTemplateAttributeDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// String attributeId;//����ID
	// String attributeKey;//���Եļ�
	// String attributeValue;//���Ե�ֵ
	// String attributeUnit;//����ֵ��λ
	// String templateId;//����peģ��ID
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(PeTemplateAttribute peTemplateAttribute){
	 * this.attributeId = peTemplateAttribute.getAttributeId();
	 * this.attributeKey = peTemplateAttribute.getAttributeKey();
	 * this.attributeValue = peTemplateAttribute.getAttributeValue();
	 * this.attributeUnit = peTemplateAttribute.getAttributeUnit();
	 * this.templateId = peTemplateAttribute.getTemplateId(); }
	 */
	@Override
	public List<Map<String, Object>> query(
			PeTemplateAttribute peTemplateAttribute, int page, int size) {
		// preOperation(peTemplateAttribute);
		String sqlStr = "select * from iot_template_attribute where";
		if (peTemplateAttribute.getAttributeId() != null) {
			sqlStr += " attribute_id = " + "'"
					+ peTemplateAttribute.getAttributeId() + "'";
		}
		if (peTemplateAttribute.getAttributeKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " attribute_key = " + "'"
						+ peTemplateAttribute.getAttributeKey() + "'";
			} else {
				sqlStr += " and attribute_key = " + "'"
						+ peTemplateAttribute.getAttributeKey() + "'";
			}
		}
		if (peTemplateAttribute.getAttributeValue() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " attribute_value = " + "'"
						+ peTemplateAttribute.getAttributeValue() + "'";
			} else {
				sqlStr += " and attribute_value = " + "'"
						+ peTemplateAttribute.getAttributeValue() + "'";
			}
		}
		if (peTemplateAttribute.getAttributeUnit() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " attribute_unit = " + "'"
						+ peTemplateAttribute.getAttributeUnit() + "'";
			} else {
				sqlStr += " and attribute_unit = " + "'"
						+ peTemplateAttribute.getAttributeUnit() + "'";
			}
		}
		if (peTemplateAttribute.getTemplateId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_id = " + "'"
						+ peTemplateAttribute.getTemplateId() + "'";
			} else {
				sqlStr += " and template_id = " + "'"
						+ peTemplateAttribute.getTemplateId() + "'";
			}
		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String delete(PeTemplateAttribute peTemplateAttribute) {
		// preOperation(peTemplateAttribute);
		String sqlStr = "delete from iot_template_attribute where";
		if (peTemplateAttribute.getAttributeId() != null) {
			sqlStr += " attribute_id = " + "'"
					+ peTemplateAttribute.getAttributeId() + "'";
		}
		if (peTemplateAttribute.getAttributeId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " attribute_key = " + "'"
						+ peTemplateAttribute.getAttributeId() + "'";
			} else {
				sqlStr += " and attribute_key = " + "'"
						+ peTemplateAttribute.getAttributeId() + "'";
			}
		}
		if (peTemplateAttribute.getAttributeValue() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " attribute_value = " + "'"
						+ peTemplateAttribute.getAttributeValue() + "'";
			} else {
				sqlStr += " and attribute_value = " + "'"
						+ peTemplateAttribute.getAttributeValue() + "'";
			}
		}
		if (peTemplateAttribute.getAttributeUnit() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " attribute_unit = " + "'"
						+ peTemplateAttribute.getAttributeUnit() + "'";
			} else {
				sqlStr += " and attribute_unit = " + "'"
						+ peTemplateAttribute.getAttributeUnit() + "'";
			}
		}
		if (peTemplateAttribute.getTemplateId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_id = " + "'"
						+ peTemplateAttribute.getTemplateId() + "'";
			} else {
				sqlStr += " and template_id = " + "'"
						+ peTemplateAttribute.getTemplateId() + "'";
			}
		}
		logger.info(sqlStr);
		try {
			jdbcTemplate.execute(sqlStr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return "fail";
		}
		return "success";

	}

	@Override
	public String add(PeTemplateAttribute peTemplateAttribute) {

		// preOperation(peTemplateAttribute);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_template_attribute(";
		if (peTemplateAttribute.getAttributeId() != null) {

			sqlStr1 += "attribute_id";
			sqlStr2 += "'" + peTemplateAttribute.getAttributeId() + "'";
		}

		if (peTemplateAttribute.getAttributeKey() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "attribute_key";
				sqlStr2 += "'" + peTemplateAttribute.getAttributeKey() + "'";
			} else {
				sqlStr1 += ",attribute_key";
				sqlStr2 += ",'" + peTemplateAttribute.getAttributeKey() + "'";
			}

		}
		if (peTemplateAttribute.getAttributeValue() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "attribute_value";
				sqlStr2 += "'" + peTemplateAttribute.getAttributeValue() + "'";
			} else {
				sqlStr1 += ",attribute_value";
				sqlStr2 += ",'" + peTemplateAttribute.getAttributeValue() + "'";
			}

		}
		if (peTemplateAttribute.getAttributeUnit() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "attribute_unit";
				sqlStr2 += "'" + peTemplateAttribute.getAttributeUnit() + "'";
			} else {
				sqlStr1 += ",attribute_unit";
				sqlStr2 += ",'" + peTemplateAttribute.getAttributeUnit() + "'";
			}

		}
		if (peTemplateAttribute.getTemplateId() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_id";
				sqlStr2 += "'" + peTemplateAttribute.getTemplateId() + "'";
			} else {
				sqlStr1 += ",template_id";
				sqlStr2 += ",'" + peTemplateAttribute.getTemplateId() + "'";
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

	@Override
	public String update(PeTemplateAttribute peTemplateAttribute) {

		// preOperation(peTemplateAttribute);
		String sqlStr = "update iot_template_attribute set";
		/*
		 * if(attributeId!=null){ sqlStr += " attribute_id = "+"'"+attributeId
		 * +"'"; }
		 */
		if (peTemplateAttribute.getAttributeKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " attribute_key = " + "'"
						+ peTemplateAttribute.getAttributeKey() + "'";
			} else {
				sqlStr += " , attribute_key = " + "'"
						+ peTemplateAttribute.getAttributeKey() + "'";
			}
		}
		if (peTemplateAttribute.getAttributeValue() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " attribute_value = " + "'"
						+ peTemplateAttribute.getAttributeValue() + "'";
			} else {
				sqlStr += " , attribute_value = " + "'"
						+ peTemplateAttribute.getAttributeValue() + "'";
			}
		}
		if (peTemplateAttribute.getAttributeUnit() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " attribute_unit = " + "'"
						+ peTemplateAttribute.getAttributeUnit() + "'";
			} else {
				sqlStr += " , attribute_unit = " + "'"
						+ peTemplateAttribute.getAttributeUnit() + "'";
			}
		}
		if (peTemplateAttribute.getTemplateId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_id = " + "'"
						+ peTemplateAttribute.getTemplateId() + "'";
			} else {
				sqlStr += " , template_id = " + "'"
						+ peTemplateAttribute.getTemplateId() + "'";
			}
		}
		sqlStr += " where attribute_id = " + "'"
				+ peTemplateAttribute.getTemplateId() + "'";
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
