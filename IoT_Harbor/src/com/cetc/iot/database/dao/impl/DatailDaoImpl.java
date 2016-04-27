package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.DatailDao;
import com.cetc.iot.database.model.Datail;

@Repository
public class DatailDaoImpl implements DatailDao {

	private static Logger logger = Logger.getLogger(DatailDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// int datailId;//�������ID
	// String dataId;//��ص����ID
	// String datailKey;//����ļ�
	// String datailValue;//�����ֵ
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(Datail datail) { this.datailId =
	 * datail.getDatailId(); this.dataId = datail.getDataId(); this.datailKey =
	 * datail.getDatailKey(); this.datailValue = datail.getDatailValue(); }
	 */

	@Override
	public List<Map<String, Object>> query(Datail datail, int page, int size) {

		// preOperation(datail);
		String sqlStr = "select * from iot_data_detail where";
		if (datail.getDatailId() != -1) {
			sqlStr += " detail_id = " + "'" + datail.getDatailId() + "'";
		}
		if (datail.getDataId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " data_id = " + "'" + datail.getDataId() + "'";
			} else {
				sqlStr += " and data_id = " + "'" + datail.getDataId() + "'";
			}
		}
		if (datail.getDatailKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " detail_key = " + "'" + datail.getDatailKey() + "'";
			} else {
				sqlStr += " and detail_key = " + "'" + datail.getDatailKey()
						+ "'";
			}
		}
		if (datail.getDatailKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " detail_value = " + "'" + datail.getDatailValue()
						+ "'";
			} else {
				sqlStr += " and detail_value = " + "'"
						+ datail.getDatailValue() + "'";
			}
		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String delete(Datail datail) {
		// preOperation(datail);
		String sqlStr = "delete from iot_data_detail where";
		if (datail.getDatailId() != -1) {
			sqlStr += " detail_id = " + "'" + datail.getDatailId() + "'";
		}
		if (datail.getDataId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " data_id = " + "'" + datail.getDataId() + "' ";
			} else {
				sqlStr += " and data_id = " + "'" + datail.getDataId() + "' ";
			}
		}
		if (datail.getDatailKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " datail_key = " + "'" + datail.getDatailKey() + "' ";
			} else {
				sqlStr += " and datail_key = " + "'" + datail.getDatailKey()
						+ "' ";
			}
		}

		if (datail.getDatailValue() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " datail_value = " + "'" + datail.getDatailValue()
						+ "' ";
			} else {
				sqlStr += " and detail_value = " + "'"
						+ datail.getDatailValue() + "' ";
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
	public String add(Datail datail) {
		// preOperation(datail);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_data_detail(";
		/*
		 * if(datailId!=null){
		 * 
		 * sqlStr1 += "datail_id"; sqlStr2 += "'"+datailId+"'"; }
		 */

		/*
		 * if(dataId!=null){ char lastChar = sqlStr1.charAt(sqlStr1.length()-1);
		 * if(lastChar=='('){ sqlStr1 += "data_id"; sqlStr2 += "'"+dataId+"'"; }
		 * else{ sqlStr1 +=",data_id"; sqlStr2 += ",'"+dataId+"'"; }
		 * 
		 * }
		 */
		if (datail.getDataId() != null) {

			sqlStr1 += "data_id";
			sqlStr2 += "'" + datail.getDataId() + "'";

		}
		if (datail.getDatailKey() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "detail_key";
				sqlStr2 += "'" + datail.getDatailKey() + "'";
			} else {
				sqlStr1 += ",detail_key";
				sqlStr2 += ",'" + datail.getDatailKey() + "'";
			}

		}
		if (datail.getDatailValue() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "detail_value";
				sqlStr2 += "'" + datail.getDatailValue() + "'";
			} else {
				sqlStr1 += ",detail_value";
				sqlStr2 += ",'" + datail.getDatailValue() + "'";
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
	public String update(Datail datail) {
		// preOperation(datail);
		String sqlStr = "update iot_data_detail set";
		/*
		 * if(datailId!=null){ sqlStr += " datail_id = "+"'"+datailId +"'"; }
		 */
		if (datail.getDataId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " data_id = " + "'" + datail.getDataId() + "'";
			} else {
				sqlStr += " , data_id = " + "'" + datail.getDataId() + "'";
			}
		}
		if (datail.getDatailKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " detail_key = " + "'" + datail.getDatailKey() + "'";
			} else {
				sqlStr += " , detail_key = " + "'" + datail.getDatailKey()
						+ "'";
			}
		}
		if (datail.getDatailKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " detail_value = " + "'" + datail.getDatailValue()
						+ "'";
			} else {
				sqlStr += " , detail_value = " + "'" + datail.getDatailValue()
						+ "'";
			}
		}

		sqlStr += " where detail_id = " + "'" + datail.getDatailId() + "'";
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
