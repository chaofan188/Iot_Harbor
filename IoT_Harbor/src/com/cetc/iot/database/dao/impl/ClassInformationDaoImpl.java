package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.ClassInformationDao;
import com.cetc.iot.database.model.ClassInformation;

@Repository
public class ClassInformationDaoImpl implements ClassInformationDao {

	private static Logger logger = Logger
			.getLogger(ClassInformationDaoImpl.class.getName());

	@Autowired
	JdbcTemplate jdbcTemplate;
	// String classId;// ���ID
	// String className;// ������
	// String classType;// ������ͣ�1������ 2������ 3��С�� 4:�ͺţ�

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(ClassInformation classInformation){ this.classId
	 * = classInformation.getClassId(); this.className =
	 * classInformation.getClassName(); this.classType =
	 * classInformation.getClassType(); }
	 */
	@Override
	public List<Map<String, Object>> query(ClassInformation classInformation,
			int page, int size) {
		// preOperation(classInformation);
		String sqlStr = "select * from iot_class_information where";
		if (classInformation.getClassId() != null) {
			sqlStr += " class_id = " + "'" + classInformation.getClassId()
					+ "'";
		}
		if (classInformation.getClassName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " class_name = " + "'"
						+ classInformation.getClassName() + "'";
			} else {
				sqlStr += " and class_name = " + "'"
						+ classInformation.getClassName() + "'";
			}
		}
		if (classInformation.getClassType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " class_type = " + "'"
						+ classInformation.getClassType() + "'";
			} else {
				sqlStr += " and class_type = " + "'"
						+ classInformation.getClassType() + "'";
			}
		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String delete(ClassInformation classInformation) {
		// preOperation(classInformation);
		String sqlStr = "delete from iot_class_information where";
		if (classInformation.getClassId() != null) {
			sqlStr += " class_id = " + "'" + classInformation.getClassId()
					+ "'";
		}
		if (classInformation.getClassName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " class_name = " + "'"
						+ classInformation.getClassName() + "' ";
			} else {
				sqlStr += " and class_name = " + "'"
						+ classInformation.getClassName() + "' ";
			}
		}
		if (classInformation.getClassType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " class_type = " + "'"
						+ classInformation.getClassType() + "'";
			} else {
				sqlStr += " and class_type = " + "'"
						+ classInformation.getClassType() + "'";
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
	public String add(ClassInformation classInformation) {
		// preOperation(classInformation);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		// preOperation(classInformation);
		sqlStr1 = "insert into iot_class_information(";
		if (classInformation.getClassId() != null) {

			sqlStr1 += "class_id";
			sqlStr2 += "'" + classInformation.getClassId() + "'";
		}

		if (classInformation.getClassName() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "class_name";
				sqlStr2 += "'" + classInformation.getClassName() + "'";
			} else {
				sqlStr1 += ",class_name";
				sqlStr2 += ",'" + classInformation.getClassName() + "'";
			}

		}
		if (classInformation.getClassType() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "class_type";
				sqlStr2 += "'" + classInformation.getClassType() + "'";
			} else {
				sqlStr1 += ",class_type";
				sqlStr2 += ",'" + classInformation.getClassType() + "'";
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
	public String update(ClassInformation classInformation) {
		// preOperation(classInformation);
		String sqlStr = "update iot_class_information set";
		/*
		 * if(classId!=null){ sqlStr += " class_id = "+"'"+classId +"'"; }
		 */
		if (classInformation.getClassName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " class_name = " + "'"
						+ classInformation.getClassName() + "'";
			} else {
				sqlStr += " , class_name = " + "'"
						+ classInformation.getClassName() + "'";
			}
		}
		if (classInformation.getClassType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " class_type = " + "'"
						+ classInformation.getClassType() + "'";
			} else {
				sqlStr += " , class_type = " + "'"
						+ classInformation.getClassType() + "'";
			}
		}

		sqlStr += " where class_id = " + "'" + classInformation.getClassId()
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

}
