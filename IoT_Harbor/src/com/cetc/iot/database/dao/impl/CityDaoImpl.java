package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.CityDao;
import com.cetc.iot.database.model.City;

@Repository
public class CityDaoImpl implements CityDao {

	private static Logger logger = Logger
			.getLogger(CityDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// int cityId = -1;// ID Ψһ��ʶ
	// String cityName;// �������
	// int provinceId = -1;// ����ʡID
	String sql;
	String state = "success";

	/*
	 * public void preOperation(City city) { this.cityId = city.getCityId();
	 * this.cityName = city.getCityName(); this.provinceId =
	 * city.getProvinceId(); }
	 */

	@Override
	public List<Map<String, Object>> query(City city, int page, int size) {
		// preOperation(city);
		String sqlStr = "select * from iot_city where";
		if (city.getCityId() != -1) {
			sqlStr += " city_id = " + "'" + city.getCityId() + "'";
		}
		if (city.getCityName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " city_name = " + "'" + city.getCityName() + "'";
			} else {
				sqlStr += " and city_name = " + "'" + city.getCityName() + "'";
			}
		}
		if (city.getProvinceId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += "  province_id = " + "'" + city.getProvinceId() + "'";
			} else {
				sqlStr += " and province_id = " + "'" + city.getProvinceId()
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
	public String delete(City city) {
		// preOperation(city);
		String sqlStr = "delete from iot_city where";
		if (city.getCityId() != -1) {
			sqlStr += " city_id = " + "'" + city.getCityId() + "'";
		}
		if (city.getCityName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " city_name = " + "'" + city.getCityName() + "'";
			} else {
				sqlStr += " and city_name = " + "'" + city.getCityName() + "'";
			}
		}
		if (city.getProvinceId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " province_id = " + "'" + city.getProvinceId() + "'";
			} else {
				sqlStr += " and province_id = " + "'" + city.getProvinceId()
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
	public String add(City city) {
		// preOperation(city);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		// preOperation(city);
		sqlStr1 = "insert into iot_city(";
		if (city.getCityId() != -1) {
			sqlStr1 += "city_id";
			sqlStr2 += "'" + city.getCityId() + "'";
		}

		if (city.getCityName() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "city_name";
				sqlStr2 += "'" + city.getCityName() + "'";
			} else {
				sqlStr1 += ",city_name";
				sqlStr2 += ",'" + city.getCityName() + "'";
			}
		}
		if (city.getProvinceId() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "province_id";
				sqlStr2 += "'" + city.getProvinceId() + "'";
			} else {
				sqlStr1 += ",province_id";
				sqlStr2 += ",'" + city.getProvinceId() + "'";
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
	public String update(City city) {
		// preOperation(city);
		String sqlStr = "update iot_city set";
		/*
		 * if (cityId != -1) { sqlStr += " city_id = " + "'" + cityId + "'"; }
		 */
		if (city.getCityName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " city_name = " + "'" + city.getCityName() + "'";
			} else {
				sqlStr += " , city_name = " + "'" + city.getCityName() + "'";
			}
		}
		if (city.getProvinceId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " province_id = " + "'" + city.getProvinceId() + "'";
			} else {
				sqlStr += " , province_id = " + "'" + city.getProvinceId()
						+ "'";
			}
		}
		sqlStr += " where city_id = " + "'" + city.getCityId() + "'";
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
