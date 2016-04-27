package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.ProvinceDao;
import com.cetc.iot.database.model.Province;

@Repository
public class ProvinceDaoImpl implements ProvinceDao {

	private static Logger logger = Logger.getLogger(ProvinceDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// int provinceId = -1;//ID Ψһ��ʶ
	// String provinceName;//ʡ���
	// int countryId = -1;//�������ID
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(Province province){ this.provinceId =
	 * province.getProvinceId(); this.provinceName = province.getProvinceName();
	 * this.countryId = province.getCountryId(); }
	 */
	@Override
	public List<Map<String, Object>> query(Province province, int page, int size) {
		// preOperation(province);
		String sqlStr = "select * from iot_province where";
		if (province.getProvinceId() != -1) {
			sqlStr += " province_id = " + "'" + province.getProvinceId() + "'";
		}
		if (province.getProvinceName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += "  province_name = " + "'"
						+ province.getProvinceName() + "'";
			} else {
				sqlStr += " and province_name = " + "'"
						+ province.getProvinceName() + "'";
			}
		}
		if (province.getCountryId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += "  country_id = " + "'" + province.getCountryId()
						+ "'";
			} else {
				sqlStr += " and country_id = " + "'" + province.getCountryId()
						+ "'";
			}
		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String delete(Province province) {
		// preOperation(province);
		String sqlStr = "delete from iot_province where";
		if (province.getProvinceId() != -1) {
			sqlStr += " province_id = " + "'" + province.getProvinceId() + "'";
		}
		if (province.getProvinceName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += "  province_name = " + "'"
						+ province.getProvinceName() + "'";
			} else {
				sqlStr += " and province_name = " + "'"
						+ province.getProvinceName() + "'";
			}
		}
		if (province.getCountryId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += "  country_id = " + "'" + province.getCountryId()
						+ "'";
			} else {
				sqlStr += " and country_id = " + "'" + province.getCountryId()
						+ "'";
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
	public String add(Province province) {
		// preOperation(province);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_province(";
		if (province.getProvinceId() != -1) {
			sqlStr1 += "province_id";
			sqlStr2 += "'" + province.getProvinceId() + "'";
		}

		if (province.getProvinceName() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "province_name";
				sqlStr2 += "'" + province.getProvinceName() + "'";
			} else {
				sqlStr1 += ",province_name";
				sqlStr2 += ",'" + province.getProvinceName() + "'";
			}
		}
		if (province.getCountryId() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "country_id";
				sqlStr2 += "'" + province.getCountryId() + "'";
			} else {
				sqlStr1 += ",country_id";
				sqlStr2 += ",'" + province.getCountryId() + "'";
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
	public String update(Province province) {
		// preOperation(province);
		String sqlStr = "update iot_province set";
		/*
		 * if (provinceId != -1) { sqlStr += " province_id = " + "'" +
		 * provinceId + "'"; }
		 */
		if (province.getProvinceName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " province_name = " + "'"
						+ province.getProvinceName() + "'";
			} else {
				sqlStr += " , province_name = " + "'"
						+ province.getProvinceName() + "'";
			}
		}
		if (province.getCountryId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " country_id = " + "'" + province.getCountryId()
						+ "'";
			} else {
				sqlStr += " , country_id = " + "'" + province.getCountryId()
						+ "'";
			}
		}
		sqlStr += " where province_id = " + "'" + province.getProvinceId()
				+ "'";
		logger.info(sqlStr);
		try {
			jdbcTemplate.update(sqlStr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return "fail";
		}
		System.out.println("11");
		return "success";
	}

}
