package com.cetc.iot.database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.VeGeoLocationDao;
import com.cetc.iot.database.model.VeGeoLocation;

@Repository
public class VeGeoLocationDaoImpl implements VeGeoLocationDao {

	private static Logger logger = Logger.getLogger(VeGeoLocationDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// private String geolocation_id;
	// private String ve_id;
	// private int country_id;
	// private int province_id;
	// private int city_id;
	// private String otherInfo;
	// private float altitude;
	// private float longitude;
	// private float latitude;

	List<VeGeoLocation> list = new ArrayList<VeGeoLocation>();

	/*
	 * public void preOperation(VeGeoLocation veGeoLocation) {
	 * this.geolocation_id = veGeoLocation.getGeolocation_id(); this.ve_id =
	 * veGeoLocation.getVe_id(); this.country_id =
	 * veGeoLocation.getCountry_id(); this.province_id =
	 * veGeoLocation.getProvince_id(); this.city_id =
	 * veGeoLocation.getCity_id(); this.otherInfo =
	 * veGeoLocation.getOtherInfo(); this.altitude =
	 * veGeoLocation.getAltitude(); this.longitude =
	 * veGeoLocation.getLongitude(); this.latitude =
	 * veGeoLocation.getLatitude();
	 * 
	 * }
	 */

	@Override
	public List<VeGeoLocation> query(VeGeoLocation veGeoLocation, int page,
			int size) {
		// preOperation(veGeoLocation);
		String sqlStr = "select * from iot_ve_geolocation where 1=1";
		if (veGeoLocation.getGeolocation_id() != null) {
			sqlStr += " and geolocation_id = " + "'"
					+ veGeoLocation.getGeolocation_id() + "'";
		}
		if (veGeoLocation.getVe_id() != null) {
			sqlStr += " and ve_id = " + "'" + veGeoLocation.getVe_id() + "'";
		}
		if (veGeoLocation.getCountry_id() != -1) {
			sqlStr += " and country_id = " + "'"
					+ veGeoLocation.getCountry_id() + "'";
		}

		if (veGeoLocation.getProvince_id() != -1) {

			sqlStr += " and province_id = " + "'"
					+ veGeoLocation.getProvince_id() + "'";

		}
		if (veGeoLocation.getCity_id() != -1) {

			sqlStr += " and city_id = " + "'" + veGeoLocation.getCity_id()
					+ "'";

		}
		if (veGeoLocation.getOtherInfo() != null) {

			sqlStr += " and otherInfo = " + "'" + veGeoLocation.getOtherInfo()
					+ "'";

		}
		if (veGeoLocation.getAltitude() != -1) {

			sqlStr += " and altitude = " + "'" + veGeoLocation.getAltitude()
					+ "'";

		}
		if (veGeoLocation.getLatitude() != -1) {

			sqlStr += " and latitude = " + "'" + veGeoLocation.getLatitude()
					+ "'";

		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.query(sqlStr, new VeGeoLocationRowMapper());
		logger.info(list);
		return list;
	}

	@Override
	public String delete(VeGeoLocation veGeoLocation) {

		// preOperation(veGeoLocation);
		String sqlStr1 = "delete from iot_ve_geolocation where 1=1 ";
		String sqlStr2 = "select count(*) from iot_ve_geolocation where 1=1 ";
		String sqlStr = "";
		if (veGeoLocation.getGeolocation_id() != null) {
			sqlStr += " and geolocation_id = " + "'"
					+ veGeoLocation.getGeolocation_id() + "'";
		}
		if (veGeoLocation.getVe_id() != null) {
			sqlStr += " and ve_id = " + "'" + veGeoLocation.getVe_id() + "'";
		}
		// if (country_id != -1) {
		// sqlStr += " and country_id = " + "'" + country_id + "'";
		// }
		//
		// if (province_id != -1) {
		//
		// sqlStr += " and province_id = " + "'" + province_id + "'";
		//
		// }
		// if (city_id != -1) {
		//
		// sqlStr += " and city_id = " + "'" + city_id + "'";
		//
		// }
		// if (otherInfo != null) {
		//
		// sqlStr += " and otherInfo = " + "'" + otherInfo + "'";
		//
		// }
		// if (altitude != -1) {
		//
		// sqlStr += " and altitude = " + "'" + altitude + "'";
		//
		// }
		// if (latitude != -1) {
		//
		// sqlStr += " and latitude = " + "'" + latitude + "'";
		//
		// }
		//
		// System.out.println(sqlStr);
		try {
			logger.info(sqlStr2 + sqlStr);
			int i = jdbcTemplate.queryForInt(sqlStr2 + sqlStr);
			if (i != 0) {
				logger.info(sqlStr1 + sqlStr);
				jdbcTemplate.execute(sqlStr1 + sqlStr);
			} else {
				return "success";
			}
		} catch (DataAccessException e) {
			return "fail";
		}
		return "success";
	}

	@Override
	public String add(VeGeoLocation veGeoLocation) {

		// preOperation(veGeoLocation);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_ve_geolocation(";
		if (veGeoLocation.getGeolocation_id() != null) {

			sqlStr1 += "geolocation_id";
			sqlStr2 += "'" + veGeoLocation.getGeolocation_id() + "'";
		}

		if (veGeoLocation.getVe_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_id";
				sqlStr2 += "'" + veGeoLocation.getVe_id() + "'";
			} else {
				sqlStr1 += ",ve_id";
				sqlStr2 += ",'" + veGeoLocation.getVe_id() + "'";
			}

		}
		if (veGeoLocation.getCountry_id() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "country_id";
				sqlStr2 += "'" + veGeoLocation.getCountry_id() + "'";
			} else {
				sqlStr1 += ",country_id";
				sqlStr2 += ",'" + veGeoLocation.getCountry_id() + "'";
			}

		}
		if (veGeoLocation.getProvince_id() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "province_id";
				sqlStr2 += "'" + veGeoLocation.getProvince_id() + "'";
			} else {
				sqlStr1 += ",province_id";
				sqlStr2 += ",'" + veGeoLocation.getProvince_id() + "'";
			}

		}
		if (veGeoLocation.getCity_id() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "city_id";
				sqlStr2 += "'" + veGeoLocation.getCity_id() + "'";
			} else {
				sqlStr1 += ",city_id";
				sqlStr2 += ",'" + veGeoLocation.getCity_id() + "'";
			}

		}
		if (veGeoLocation.getOtherInfo() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "otherInfo";
				sqlStr2 += "'" + veGeoLocation.getOtherInfo() + "'";
			} else {
				sqlStr1 += ",otherInfo";
				sqlStr2 += ",'" + veGeoLocation.getOtherInfo() + "'";
			}

		}
		if (veGeoLocation.getAltitude() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "altitude";
				sqlStr2 += "'" + veGeoLocation.getAltitude() + "'";
			} else {
				sqlStr1 += ",altitude";
				sqlStr2 += ",'" + veGeoLocation.getAltitude() + "'";
			}

		}
		if (veGeoLocation.getLongitude() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "longitude";
				sqlStr2 += "'" + veGeoLocation.getLongitude() + "'";
			} else {
				sqlStr1 += ",longitude";
				sqlStr2 += ",'" + veGeoLocation.getLongitude() + "'";
			}

		}
		if (veGeoLocation.getLatitude() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "latitude";
				sqlStr2 += "'" + veGeoLocation.getLatitude() + "'";
			} else {
				sqlStr1 += ",latitude";
				sqlStr2 += ",'" + veGeoLocation.getLatitude() + "'";
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
	public String update(VeGeoLocation veGeoLocation) {
		// preOperation(veGeoLocation);
		String sqlStr = "update iot_ve_geolocation set";
		if (veGeoLocation.getVe_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_id = " + "'" + veGeoLocation.getVe_id() + "'";
			} else {
				sqlStr += " , ve_id = " + "'" + veGeoLocation.getVe_id() + "'";
			}
		}
		if (veGeoLocation.getCountry_id() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " country_id = " + "'"
						+ veGeoLocation.getCountry_id() + "'";
			} else {
				sqlStr += " , country_id = " + "'"
						+ veGeoLocation.getCountry_id() + "'";
			}
		}
		if (veGeoLocation.getProvince_id() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " province_id = " + "'"
						+ veGeoLocation.getProvince_id() + "'";
			} else {
				sqlStr += " , province_id = " + "'"
						+ veGeoLocation.getProvince_id() + "'";
			}
		}
		if (veGeoLocation.getCity_id() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " city_id = " + "'" + veGeoLocation.getCity_id()
						+ "'";
			} else {
				sqlStr += " , city_id = " + "'" + veGeoLocation.getCity_id()
						+ "'";
			}
		}
		if (veGeoLocation.getOtherInfo() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " otherInfo = " + "'" + veGeoLocation.getOtherInfo()
						+ "'";
			} else {
				sqlStr += " , otherInfo = " + "'"
						+ veGeoLocation.getOtherInfo() + "'";
			}
		}
		if (veGeoLocation.getAltitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " altitude = " + "'" + veGeoLocation.getAltitude()
						+ "'";
			} else {
				sqlStr += " , altitude = " + "'" + veGeoLocation.getAltitude()
						+ "'";
			}
		}

		if (veGeoLocation.getLongitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " longitude = " + "'" + veGeoLocation.getLongitude()
						+ "'";
			} else {
				sqlStr += " , longitude = " + "'"
						+ veGeoLocation.getLongitude() + "'";
			}
		}
		if (veGeoLocation.getLatitude() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " latitude = " + "'" + veGeoLocation.getLatitude()
						+ "'";
			} else {
				sqlStr += " , latitude = " + "'" + veGeoLocation.getLatitude()
						+ "'";
			}
		}
		sqlStr += " where geolocation_id = " + "'"
				+ veGeoLocation.getGeolocation_id() + "'";

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

	class VeGeoLocationRowMapper implements RowMapper<VeGeoLocation> {
		@Override
		public VeGeoLocation mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			VeGeoLocation veGeoLocation = new VeGeoLocation();
			veGeoLocation.setGeolocation_id(rs.getString("geolocation_id"));
			veGeoLocation.setAltitude(rs.getFloat("altitude"));
			veGeoLocation.setCity_id(rs.getInt("city_id"));
			veGeoLocation.setCountry_id(rs.getInt("country_id"));
			veGeoLocation.setLatitude(rs.getFloat("latitude"));
			veGeoLocation.setLongitude(rs.getFloat("longitude"));
			veGeoLocation.setOtherInfo(rs.getString("otherInfo"));
			veGeoLocation.setProvince_id(rs.getInt("province_id"));
			veGeoLocation.setVe_id(rs.getString("ve_id"));

			return veGeoLocation;
		}
	}
}
