package com.cetc.iot.database.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeTemplateDao;
import com.cetc.iot.database.model.PeTemplate;

@Repository
public class PeTemplateDaoImpl implements PeTemplateDao {

	private static Logger logger = Logger.getLogger(PeTemplateDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// String templateId;//peģ��ID
	// String templateName;//peģ�����
	// String templateType;//peģ������
	// String templateModel;//pe�����ͺ�
	// Date templateTime;//peģ�����ʱ��
	// String templatePicUrl;//peģ��ͼƬ
	// String templateRemark;//peģ������
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(PeTemplate peTemplate){ this.templateId =
	 * peTemplate.getTemplateId(); this.templateName =
	 * peTemplate.getTemplateName(); this.templateType =
	 * peTemplate.getTemplateType(); this.templateModel =
	 * peTemplate.getTemplateModel(); this.templateTime =
	 * peTemplate.getTemplateTime(); this.templatePicUrl =
	 * peTemplate.getTemplatePicUrl(); this.templateRemark =
	 * peTemplate.getTemplateRemark(); }
	 */
	@Override
	public List<Map<String, Object>> query(PeTemplate peTemplate, int page,
			int size) {
		// preOperation(peTemplate);
		String sqlStr = "select * from iot_pe_template where";
		if (peTemplate.getTemplateId() != null) {
			sqlStr += " template_id = " + "'" + peTemplate.getTemplateId()
					+ "'";
		}
		if (peTemplate.getTemplateTime() != null) {
			Timestamp timeStamp = new Timestamp(peTemplate.getTemplateTime()
					.getTime());
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_time = " + "'" + timeStamp + "'";
			} else {
				sqlStr += " and template_time = " + "'" + timeStamp + "'";
			}
		}
		if (peTemplate.getTemplateName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_name = " + "'"
						+ peTemplate.getTemplateName() + "'";
			} else {
				sqlStr += " and template_name = " + "'"
						+ peTemplate.getTemplateName() + "'";
			}
		}
		if (peTemplate.getTemplateType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_type = " + "'"
						+ peTemplate.getTemplateType() + "'";
			} else {
				sqlStr += " and template_type = " + "'"
						+ peTemplate.getTemplateType() + "'";
			}
		}
		if (peTemplate.getTemplateModel() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_model = " + "'"
						+ peTemplate.getTemplateModel() + "'";
			} else {
				sqlStr += " and template_model = " + "'"
						+ peTemplate.getTemplateModel() + "'";
			}
		}
		if (peTemplate.getTemplatePicUrl() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_pic_url = " + "'"
						+ peTemplate.getTemplatePicUrl() + "'";
			} else {
				sqlStr += " and template_pic_url = " + "'"
						+ peTemplate.getTemplatePicUrl() + "'";
			}
		}
		if (peTemplate.getTemplateRemark() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_remark = " + "'"
						+ peTemplate.getTemplateRemark() + "'";
			} else {
				sqlStr += " and template_remark = " + "'"
						+ peTemplate.getTemplateRemark() + "'";
			}
		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String delete(PeTemplate peTemplate) {
		// preOperation(peTemplate);
		String sqlStr = "delete from iot_pe_template where";
		if (peTemplate.getTemplateId() != null) {
			sqlStr += " template_id = " + "'" + peTemplate.getTemplateId()
					+ "'";
		}
		if (peTemplate.getTemplateTime() != null) {
			Timestamp timeStamp = new Timestamp(peTemplate.getTemplateTime()
					.getTime());
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_time = " + "'" + timeStamp + "'";
			} else {
				sqlStr += " and template_time = " + "'" + timeStamp + "'";
			}
		}
		if (peTemplate.getTemplateName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_name = " + "'"
						+ peTemplate.getTemplateName() + "'";
			} else {
				sqlStr += " and template_name = " + "'"
						+ peTemplate.getTemplateName() + "'";
			}
		}
		if (peTemplate.getTemplateType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_type = " + "'"
						+ peTemplate.getTemplateType() + "'";
			} else {
				sqlStr += " and template_type = " + "'"
						+ peTemplate.getTemplateType() + "'";
			}
		}
		if (peTemplate.getTemplateModel() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_model = " + "'"
						+ peTemplate.getTemplateModel() + "'";
			} else {
				sqlStr += " and template_model = " + "'"
						+ peTemplate.getTemplateModel() + "'";
			}
		}
		if (peTemplate.getTemplatePicUrl() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_pic_url = " + "'"
						+ peTemplate.getTemplatePicUrl() + "'";
			} else {
				sqlStr += " and template_pic_url = " + "'"
						+ peTemplate.getTemplatePicUrl() + "'";
			}
		}
		if (peTemplate.getTemplateRemark() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_remark = " + "'"
						+ peTemplate.getTemplateRemark() + "'";
			} else {
				sqlStr += " and template_remark = " + "'"
						+ peTemplate.getTemplateRemark() + "'";
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
	public String add(PeTemplate peTemplate) {

		// preOperation(peTemplate);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_pe_template(";
		if (peTemplate.getTemplateId() != null) {

			sqlStr1 += "template_id";
			sqlStr2 += "'" + peTemplate.getTemplateId() + "'";
		}

		if (peTemplate.getTemplateTime() != null) {
			Timestamp timeStamp = new Timestamp(peTemplate.getTemplateTime()
					.getTime());
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_time";
				sqlStr2 += "'" + timeStamp + "'";
			} else {
				sqlStr1 += ",template_time";
				sqlStr2 += ",'" + timeStamp + "'";
			}

		}
		if (peTemplate.getTemplateName() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_name";
				sqlStr2 += "'" + peTemplate.getTemplateName() + "'";
			} else {
				sqlStr1 += ",template_name";
				sqlStr2 += ",'" + peTemplate.getTemplateName() + "'";
			}

		}
		if (peTemplate.getTemplateType() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_type";
				sqlStr2 += "'" + peTemplate.getTemplateType() + "'";
			} else {
				sqlStr1 += ",template_type";
				sqlStr2 += ",'" + peTemplate.getTemplateType() + "'";
			}

		}
		if (peTemplate.getTemplateModel() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_model";
				sqlStr2 += "'" + peTemplate.getTemplateModel() + "'";
			} else {
				sqlStr1 += ",template_model";
				sqlStr2 += ",'" + peTemplate.getTemplateModel() + "'";
			}

		}
		if (peTemplate.getTemplatePicUrl() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_pic_url";
				sqlStr2 += "'" + peTemplate.getTemplatePicUrl() + "'";
			} else {
				sqlStr1 += ",template_pic_url";
				sqlStr2 += ",'" + peTemplate.getTemplatePicUrl() + "'";
			}

		}
		if (peTemplate.getTemplateRemark() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_remark";
				sqlStr2 += "'" + peTemplate.getTemplateRemark() + "'";
			} else {
				sqlStr1 += ",template_remark";
				sqlStr2 += ",'" + peTemplate.getTemplateRemark() + "'";
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
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	@Override
	public String update(PeTemplate peTemplate) {

		// preOperation(peTemplate);
		String sqlStr = "update iot_pe_template set";
		/*
		 * if(templateId!=null){ sqlStr += " template_id"+"'"+templateId +"'"; }
		 */
		if (peTemplate.getTemplateTime() != null) {
			Timestamp timeStamp = new Timestamp(peTemplate.getTemplateTime()
					.getTime());
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_time = " + "'" + timeStamp + "'";
			} else {
				sqlStr += " , template_time = " + "'" + timeStamp + "'";
			}
		}
		if (peTemplate.getTemplateName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_name = " + "'"
						+ peTemplate.getTemplateName() + "'";
			} else {
				sqlStr += " , template_name = " + "'"
						+ peTemplate.getTemplateName() + "'";
			}
		}
		if (peTemplate.getTemplateType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_type = " + "'"
						+ peTemplate.getTemplateType() + "'";
			} else {
				sqlStr += " , template_type = " + "'"
						+ peTemplate.getTemplateType() + "'";
			}
		}
		if (peTemplate.getTemplateModel() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_model = " + "'"
						+ peTemplate.getTemplateModel() + "'";
			} else {
				sqlStr += " , template_model = " + "'"
						+ peTemplate.getTemplateModel() + "'";
			}
		}
		if (peTemplate.getTemplatePicUrl() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_pic_url = " + "'"
						+ peTemplate.getTemplatePicUrl() + "'";
			} else {
				sqlStr += " , template_pic_url = " + "'"
						+ peTemplate.getTemplatePicUrl() + "'";
			}
		}
		if (peTemplate.getTemplateRemark() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_remark = " + "'"
						+ peTemplate.getTemplateRemark() + "'";
			} else {
				sqlStr += " , template_remark = " + "'"
						+ peTemplate.getTemplateRemark() + "'";
			}
		}
		sqlStr += " where template_id = " + "'" + peTemplate.getTemplateId()
				+ "'";

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
	public List<Map<String, Object>> queryAll() {
		// TODO Auto-generated method stub
		String sqlStr = "select * from iot_pe_template";
		System.out.println(sqlStr);
		List<Map<String, Object>> resultList = jdbcTemplate
				.queryForList(sqlStr);
		logger.info(resultList);
		return resultList;
	}

}
