package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.CountryDao;
import com.cetc.iot.database.model.Country;

@Repository
public class CountryDaoImpl implements CountryDao {

	private static Logger logger = Logger.getLogger(CountryDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// int countryId = -1;// ID Ψһ��ʶ
	// String countryName;// ������
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(Country country) { this.countryId =
	 * country.getCountryId(); this.countryName = country.getCountryName(); }
	 */

	@Override
	public List<Map<String, Object>> query(Country country, int page, int size) {

		// preOperation(country);
		String sqlStr = "select * from iot_country where";
		if (country.getCountryId() != -1) {
			sqlStr += " country_id = " + "'" + country.getCountryId() + "'";
		}
		if (country.getCountryName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " country_name = " + "'" + country.getCountryName()
						+ "'";
			} else {
				sqlStr += " and country_name = " + "'"
						+ country.getCountryName() + "'";
			}
		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;

	}

	@Override
	public String delete(Country country) {
		// TODO Auto-generated method stub
		// preOperation(country);
		String sqlStr = "delete from iot_country where";
		if (country.getCountryId() != -1) {
			sqlStr += " country_id = " + "'" + country.getCountryId() + "'";
		}
		if (country.getCountryName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " country_name = " + "'" + country.getCountryName()
						+ "' ";
			} else {
				sqlStr += " and country_name = " + "'"
						+ country.getCountryName() + "' ";
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
	public String add(Country country) {
		// TODO Auto-generated method stub
		// preOperation(countrty);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_country(";
		if (country.getCountryId() != -1) {

			sqlStr1 += "country_id";
			sqlStr2 += "'" + country.getCountryId() + "'";
		}

		if (country.getCountryName() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "country_name";
				sqlStr2 += "'" + country.getCountryName() + "'";
			} else {
				sqlStr1 += ",country_name";
				sqlStr2 += ",'" + country.getCountryName() + "'";
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
	public String update(Country country) {
		// TODO Auto-generated method stub
		// preOperation(country);
		String sqlStr = "update iot_country set";
		/*
		 * if(countryId!=-1){ sqlStr += " country_id = "+"'"+countryId +"'"; }
		 */
		if (country.getCountryName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " country_name = " + "'" + country.getCountryName()
						+ "'";
			} else {
				sqlStr += " , country_name = " + "'" + country.getCountryName()
						+ "'";
			}
		}

		sqlStr += " where country_id = " + "'" + country.getCountryId() + "'";
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
