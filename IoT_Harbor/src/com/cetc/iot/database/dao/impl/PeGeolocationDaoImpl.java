package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeGeolocationDao;
import com.cetc.iot.database.model.PeGeolocation;

@Repository
public class PeGeolocationDaoImpl implements PeGeolocationDao {

	private static Logger logger = Logger.getLogger(PeGeolocationDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// String geolocationId;//����λ��ID
	// String peId;//pe��ID
	// int countryId = -1;//���ID
	// int provinceId = -1;//ʡID
	// int cityId = -1;//����ID
	// String otherInfo;//�������λ����Ϣ
	// float altitude = -1;//����
	// float longitude = -1;//����
	// float latitude = -1;//γ��

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(PeGeolocation peGeolocation){ this.geolocationId
	 * = peGeolocation.getGeolocationId(); this.peId = peGeolocation.getPeId();
	 * this.countryId = peGeolocation.getCountryId(); this.provinceId =
	 * peGeolocation.getProvinceId(); this.cityId = peGeolocation.getCityId();
	 * this.otherInfo = peGeolocation.getOtherInfo(); this.altitude =
	 * peGeolocation.getAltitude(); this.longitude =
	 * peGeolocation.getLongitude(); this.latitude =
	 * peGeolocation.getAltitude(); }
	 */
	@Override
	public List<Map<String, Object>> query(PeGeolocation peGeolocation,
			int page, int size) {
		// preOperation(peGeolocation);
		String sqlStr = "select * from iot_pe_geolocation where";
		if (peGeolocation.getGeolocationId() != null) {
			sqlStr += " geolocation_id = " + "'"
					+ peGeolocation.getGeolocationId() + "'";
		}
		if (peGeolocation.getPeId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_id = " + "'" + peGeolocation.getPeId() + "'";
			} else {
				sqlStr += " and pe_id = " + "'" + peGeolocation.getPeId() + "'";
			}
		}
		if (peGeolocation.getCountryId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " country_id = " + "'" + peGeolocation.getCountryId()
						+ "'";
			} else {
				sqlStr += " and country_id = " + "'"
						+ peGeolocation.getCountryId() + "'";
			}
		}
		if (peGeolocation.getProvinceId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " province_id = " + "'"
						+ peGeolocation.getProvinceId() + "'";
			} else {
				sqlStr += " and province_id = " + "'"
						+ peGeolocation.getProvinceId() + "'";
			}
		}
		if (peGeolocation.getCityId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " city_id = " + "'" + peGeolocation.getCityId() + "'";
			} else {
				sqlStr += " and city_id = " + "'" + peGeolocation.getCityId()
						+ "'";
			}
		}
		if (peGeolocation.getOtherInfo() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " otherInfo = " + "'" + peGeolocation.getOtherInfo()
						+ "'";
			} else {
				sqlStr += " and otherInfo = " + "'"
						+ peGeolocation.getOtherInfo() + "'";
			}
		}
		if (peGeolocation.getAltitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " altitude = " + "'" + peGeolocation.getAltitude()
						+ "'";
			} else {
				sqlStr += " and altitude = " + "'"
						+ peGeolocation.getAltitude() + "'";
			}
		}
		if (peGeolocation.getLongitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " longitude = " + "'" + peGeolocation.getLongitude()
						+ "'";
			} else {
				sqlStr += " and longitude = " + "'"
						+ peGeolocation.getLongitude() + "'";
			}
		}
		if (peGeolocation.getLatitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " latitude = " + "'" + peGeolocation.getLatitude()
						+ "'";
			} else {
				sqlStr += " and latitude = " + "'"
						+ peGeolocation.getLatitude() + "'";
			}
		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String delete(PeGeolocation peGeolocation) {

		// preOperation(peGeolocation);
		String sqlStr = "delete from iot_pe_geolocation where";
		if (peGeolocation.getGeolocationId() != null) {
			sqlStr += " geolocation_id = " + "'"
					+ peGeolocation.getGeolocationId() + "'";
		}
		if (peGeolocation.getPeId() != null) {

			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_id = " + "'" + peGeolocation.getPeId() + "'";
			} else {
				sqlStr += " and pe_id = " + "'" + peGeolocation.getPeId() + "'";
			}
		}
		if (peGeolocation.getPeId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_id = " + "'" + peGeolocation.getPeId() + "'";
			} else {
				sqlStr += " and pe_id = " + "'" + peGeolocation.getPeId() + "'";
			}
		}
		if (peGeolocation.getCountryId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " country_id = " + "'" + peGeolocation.getCountryId()
						+ "'";
			} else {
				sqlStr += " and country_id = " + "'"
						+ peGeolocation.getCountryId() + "'";
			}
		}
		if (peGeolocation.getProvinceId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " province_id = " + "'"
						+ peGeolocation.getProvinceId() + "'";
			} else {
				sqlStr += " and province_id = " + "'"
						+ peGeolocation.getProvinceId() + "'";
			}
		}
		if (peGeolocation.getCityId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " city_id = " + "'" + peGeolocation.getCityId() + "'";
			} else {
				sqlStr += " and city_id = " + "'" + peGeolocation.getCityId()
						+ "'";
			}
		}
		if (peGeolocation.getOtherInfo() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " otherInfo = " + "'" + peGeolocation.getOtherInfo()
						+ "'";
			} else {
				sqlStr += " and otherInfo = " + "'"
						+ peGeolocation.getOtherInfo() + "'";
			}
		}
		if (peGeolocation.getAltitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " altitude = " + "'" + peGeolocation.getAltitude()
						+ "'";
			} else {
				sqlStr += " and altitude = " + "'"
						+ peGeolocation.getAltitude() + "'";
			}
		}
		if (peGeolocation.getLongitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " longitude = " + "'" + peGeolocation.getLongitude()
						+ "'";
			} else {
				sqlStr += " and longitude = " + "'"
						+ peGeolocation.getLongitude() + "'";
			}
		}
		if (peGeolocation.getLatitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " latitude = " + "'" + peGeolocation.getLatitude()
						+ "'";
			} else {
				sqlStr += " and latitude = " + "'"
						+ peGeolocation.getLatitude() + "'";
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
	public String add(PeGeolocation peGeolocation) {

		// preOperation(peGeolocation);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_pe_geolocation(";
		if (peGeolocation.getGeolocationId() != null) {

			sqlStr1 += "geolocation_id";
			sqlStr2 += "'" + peGeolocation.getGeolocationId() + "'";
		}

		if (peGeolocation.getPeId() != null) {

			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_id";
				sqlStr2 += "'" + peGeolocation.getPeId() + "'";
			} else {
				sqlStr1 += ",pe_id";
				sqlStr2 += ",'" + peGeolocation.getPeId() + "'";
			}

		}
		if (peGeolocation.getCountryId() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "country_id";
				sqlStr2 += "'" + peGeolocation.getCountryId() + "'";
			} else {
				sqlStr1 += ",country_id";
				sqlStr2 += ",'" + peGeolocation.getCountryId() + "'";
			}

		}
		if (peGeolocation.getProvinceId() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "province_id";
				sqlStr2 += "'" + peGeolocation.getProvinceId() + "'";
			} else {
				sqlStr1 += ",province_id";
				sqlStr2 += ",'" + peGeolocation.getProvinceId() + "'";
			}

		}
		if (peGeolocation.getCityId() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "city_id";
				sqlStr2 += "'" + peGeolocation.getCityId() + "'";
			} else {
				sqlStr1 += ",city_id";
				sqlStr2 += ",'" + peGeolocation.getCityId() + "'";
			}

		}
		if (peGeolocation.getOtherInfo() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "otherInfo";
				sqlStr2 += "'" + peGeolocation.getOtherInfo() + "'";
			} else {
				sqlStr1 += ",otherInfo";
				sqlStr2 += ",'" + peGeolocation.getOtherInfo() + "'";
			}

		}
		if (peGeolocation.getAltitude() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "altitude";
				sqlStr2 += "'" + peGeolocation.getAltitude() + "'";
			} else {
				sqlStr1 += ",altitude";
				sqlStr2 += ",'" + peGeolocation.getAltitude() + "'";
			}

		}
		if (peGeolocation.getLongitude() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "longitude";
				sqlStr2 += "'" + peGeolocation.getLongitude() + "'";
			} else {
				sqlStr1 += ",longitude";
				sqlStr2 += ",'" + peGeolocation.getLongitude() + "'";
			}

		}
		if (peGeolocation.getLatitude() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "latitude";
				sqlStr2 += "'" + peGeolocation.getLatitude() + "'";
			} else {
				sqlStr1 += ",latitude";
				sqlStr2 += ",'" + peGeolocation.getLatitude() + "'";
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
	public String update(PeGeolocation peGeolocation) {

		// preOperation(peGeolocation);
		String sqlStr = "update iot_pe_geolocation set";
		/*
		 * if(geolocationId!=null){ sqlStr +=
		 * " geolocation_id = "+"'"+geolocationId +"'"; }
		 */
		if (peGeolocation.getPeId() != null) {

			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_id = " + "'" + peGeolocation.getPeId() + "'";
			} else {
				sqlStr += " , pe_id = " + "'" + peGeolocation.getPeId() + "'";
			}
		}
		if (peGeolocation.getPeId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_id = " + "'" + peGeolocation.getPeId() + "'";
			} else {
				sqlStr += " , pe_id = " + "'" + peGeolocation.getPeId() + "'";
			}
		}
		if (peGeolocation.getCountryId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " country_id = " + "'" + peGeolocation.getCountryId()
						+ "'";
			} else {
				sqlStr += " , country_id = " + "'"
						+ peGeolocation.getCountryId() + "'";
			}
		}
		if (peGeolocation.getProvinceId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " province_id = " + "'"
						+ peGeolocation.getProvinceId() + "'";
			} else {
				sqlStr += " , province_id = " + "'"
						+ peGeolocation.getProvinceId() + "'";
			}
		}
		if (peGeolocation.getCityId() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " city_id = " + "'" + peGeolocation.getCityId() + "'";
			} else {
				sqlStr += " , city_id = " + "'" + peGeolocation.getCityId()
						+ "'";
			}
		}
		if (peGeolocation.getOtherInfo() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " otherInfo = " + "'" + peGeolocation.getOtherInfo()
						+ "'";
			} else {
				sqlStr += " , otherInfo = " + "'"
						+ peGeolocation.getOtherInfo() + "'";
			}
		}
		if (peGeolocation.getAltitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " altitude = " + "'" + peGeolocation.getAltitude()
						+ "'";
			} else {
				sqlStr += " , altitude = " + "'" + peGeolocation.getAltitude()
						+ "'";
			}
		}
		if (peGeolocation.getLongitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " longitude = " + "'" + peGeolocation.getLongitude()
						+ "'";
			} else {
				sqlStr += " , longitude = " + "'"
						+ peGeolocation.getLongitude() + "'";
			}
		}
		if (peGeolocation.getLatitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " latitude = " + "'" + peGeolocation.getLatitude()
						+ "'";
			} else {
				sqlStr += " , latitude = " + "'" + peGeolocation.getLatitude()
						+ "'";
			}
		}
		sqlStr += " where geolocation_id = " + "'"
				+ peGeolocation.getLatitude() + "'";
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
