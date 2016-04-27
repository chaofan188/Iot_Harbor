package com.cetc.iot.database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.VEDao;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VeGeoLocation;

@Repository
public class VEDaoImpl implements VEDao {
	private static Logger logger = Logger.getLogger(VEDaoImpl.class.getName());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// private String ve_id;
	// private String ve_name;
	// private String ve_key;
	// private String ve_state;
	// private String ve_privacy;
	// private Date ve_init_time;
	// private String ve_description;
	// private String ve_creator_id;
	// private String template_id;
	// private String ve_atom;
	Timestamp timeStamp = null;
	List<VE> list = new ArrayList<VE>();

	/*
	 * public void preOperation(VE ve) { this.ve_id = ve.getVe_id();
	 * this.ve_name = ve.getVe_name(); this.ve_key = ve.getVe_key();
	 * this.ve_state = ve.getVe_state(); this.ve_privacy = ve.getVe_privacy();
	 * this.ve_init_time = ve.getVe_init_time(); this.ve_description =
	 * ve.getVe_description(); this.ve_creator_id = ve.getVe_creator_id();
	 * this.template_id = ve.getTemplate_id(); this.ve_atom = ve.getVe_atom(); }
	 */

	@Override
	public List<VE> query(VE ve, int page, int size) {
		// preOperation(ve);
		String sqlStr = "select A.*,B.geolocation_id,B.country_id,B.province_id,B.city_id,B.altitude,B.latitude,B.longitude,B.otherInfo from iot_ve as A left join iot_ve_geolocation as B on A.ve_id = B.ve_id where 1=1";
		if (ve.getVe_id() != null) {
			sqlStr += " and A.ve_id = " + "'" + ve.getVe_id() + "'";
		}
		if (ve.getVe_name() != null) {
			// sqlStr += " and A.ve_name = " + "'" + ve_name + "'";
			sqlStr += " and A.ve_name like " + "'%" + ve.getVe_name() + "%'";
		}

		if (ve.getVe_key() != null) {

			sqlStr += " and A.ve_key = " + "'" + ve.getVe_key() + "'";

		}
		if (ve.getVe_state() != null) {

			sqlStr += " and A.ve_state = " + "'" + ve.getVe_state() + "'";

		}
		if (ve.getVe_privacy() != null) {

			sqlStr += " and A.ve_privacy = " + "'" + ve.getVe_privacy() + "'";

		}
		if (ve.getVe_init_time() != null) {

			sqlStr += " and A.ve_init_time = " + "'" + ve.getVe_init_time()
					+ "'";

		}
		if (ve.getVe_description() != null) {

			sqlStr += " and A.ve_description like " + "'%"
					+ ve.getVe_description() + "%'";
		}
		if (ve.getVe_creator_id() != null) {

			sqlStr += " and A.ve_creator_id = " + "'" + ve.getVe_creator_id()
					+ "'";

		}
		if (ve.getTemplate_id() != null) {

			sqlStr += " and A.template_id = " + "'" + ve.getTemplate_id() + "'";
		}
		if (ve.getVe_atom() != null) {
			sqlStr += " and A.ve_atom = " + "'" + ve.getVe_atom() + "'";
		}

		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.query(sqlStr, new VERowMapper());
		logger.info(list);
		return list;
	}

	@Override
	// 不给ve赋值就是删除所有ve
	public String delete(VE ve) {

		// preOperation(ve);
		String sqlStr = "delete from iot_ve where 1=1 ";
		if (ve.getVe_id() != null) {
			sqlStr += " and ve_id = " + "'" + ve.getVe_id() + "'";
		}
		if (ve.getVe_name() != null) {
			sqlStr += " and ve_name = " + "'" + ve.getVe_name() + "'";
		}

		if (ve.getVe_key() != null) {

			sqlStr += " and ve_key = " + "'" + ve.getVe_key() + "'";

		}
		if (ve.getVe_state() != null) {

			sqlStr += " and ve_state = " + "'" + ve.getVe_state() + "'";

		}
		if (ve.getVe_privacy() != null) {

			sqlStr += " and ve_privacy = " + "'" + ve.getVe_privacy() + "'";

		}
		if (ve.getVe_init_time() != null) {

			sqlStr += " and ve_init_time = " + "'" + ve.getVe_init_time() + "'";

		}
		if (ve.getVe_description() != null) {

			sqlStr += " and ve_description = " + "'" + ve.getVe_description()
					+ "'";

		}
		if (ve.getVe_creator_id() != null) {

			sqlStr += " and ve_creator_id = " + "'" + ve.getVe_creator_id()
					+ "'";

		}
		if (ve.getTemplate_id() != null) {

			sqlStr += " and template_id = " + "'" + ve.getTemplate_id() + "'";
		}
		if (ve.getVe_atom() != null) {
			sqlStr += "and ve_atom = " + "'" + ve.getVe_atom() + "'";
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
	public String add(VE ve) {

		// preOperation(ve);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_ve(";
		if (ve.getVe_id() != null) {

			sqlStr1 += "ve_id";
			sqlStr2 += "'" + ve.getVe_id() + "'";
		}

		if (ve.getVe_name() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_name";
				sqlStr2 += "'" + ve.getVe_name() + "'";
			} else {
				sqlStr1 += ",ve_name";
				sqlStr2 += ",'" + ve.getVe_name() + "'";
			}

		}
		if (ve.getVe_key() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_key";
				sqlStr2 += "'" + ve.getVe_key() + "'";
			} else {
				sqlStr1 += ",ve_key";
				sqlStr2 += ",'" + ve.getVe_key() + "'";
			}

		}
		if (ve.getVe_state() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_state";
				sqlStr2 += "'" + ve.getVe_state() + "'";
			} else {
				sqlStr1 += ",ve_state";
				sqlStr2 += ",'" + ve.getVe_state() + "'";
			}

		}
		if (ve.getVe_privacy() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_privacy";
				sqlStr2 += "'" + ve.getVe_privacy() + "'";
			} else {
				sqlStr1 += ",ve_privacy";
				sqlStr2 += ",'" + ve.getVe_privacy() + "'";
			}

		}
		if (ve.getVe_init_time() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_init_time";
				sqlStr2 += "'" + ve.getVe_init_time() + "'";
			} else {
				sqlStr1 += ",ve_init_time";
				sqlStr2 += ",'" + ve.getVe_init_time() + "'";
			}

		}
		if (ve.getVe_description() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_description";
				sqlStr2 += "'" + ve.getVe_description() + "'";
			} else {
				sqlStr1 += ",ve_description";
				sqlStr2 += ",'" + ve.getVe_description() + "'";
			}

		}
		if (ve.getVe_creator_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_creator_id";
				sqlStr2 += "'" + ve.getVe_creator_id() + "'";
			} else {
				sqlStr1 += ",ve_creator_id";
				sqlStr2 += ",'" + ve.getVe_creator_id() + "'";
			}

		}
		if (ve.getTemplate_id() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_id";
				sqlStr2 += "'" + ve.getTemplate_id() + "'";
			} else {
				sqlStr1 += ",template_id";
				sqlStr2 += ",'" + ve.getTemplate_id() + "'";
			}

		}
		if (ve.getVe_atom() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "ve_atom";
				sqlStr2 += "'" + ve.getVe_atom() + "'";
			} else {
				sqlStr1 += ",ve_atom";
				sqlStr2 += ",'" + ve.getVe_atom() + "'";
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
	public String update(VE ve) {
		// preOperation(ve);
		String sqlStr = "update iot_ve set";
		if (ve.getVe_name() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_name = " + "'" + ve.getVe_name() + "'";
			} else {
				sqlStr += " , ve_name = " + "'" + ve.getVe_name() + "'";
			}
		}
		if (ve.getVe_key() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_key = " + "'" + ve.getVe_key() + "'";
			} else {
				sqlStr += " , ve_key = " + "'" + ve.getVe_key() + "'";
			}
		}
		if (ve.getVe_state() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_state = " + "'" + ve.getVe_state() + "'";
			} else {
				sqlStr += " , ve_state = " + "'" + ve.getVe_state() + "'";
			}
		}
		if (ve.getVe_privacy() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_privacy = " + "'" + ve.getVe_privacy() + "'";
			} else {
				sqlStr += " , ve_privacy = " + "'" + ve.getVe_privacy() + "'";
			}
		}
		if (ve.getVe_init_time() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_init_time = " + "'" + ve.getVe_init_time() + "'";
			} else {
				sqlStr += " , ve_init_time = " + "'" + ve.getVe_init_time()
						+ "'";
			}
		}
		if (ve.getVe_description() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_description = " + "'" + ve.getVe_description()
						+ "'";
			} else {
				sqlStr += " , ve_description = " + "'" + ve.getVe_description()
						+ "'";
			}
		}

		if (ve.getVe_creator_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_creator_id = " + "'" + ve.getVe_creator_id()
						+ "'";
			} else {
				sqlStr += " , ve_creator_id = " + "'" + ve.getVe_creator_id()
						+ "'";
			}
		}
		if (ve.getTemplate_id() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_id = " + "'" + ve.getTemplate_id() + "'";
			} else {
				sqlStr += " , template_id = " + "'" + ve.getTemplate_id() + "'";
			}
		}
		if (ve.getVe_atom() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " ve_atom = " + "'" + ve.getVe_atom() + "'";
			} else {
				sqlStr += " , ve_atom = " + "'" + ve.getVe_atom() + "'";
			}
		}
		sqlStr += " where ve_id = " + "'" + ve.getVe_id() + "'";

		System.out.println(sqlStr);
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
	public int queryCOUNTNUM(VE ve) {
		// preOperation(ve);
		String sqlStr = "select count(*) from iot_ve where 1=1";
		if (ve.getVe_id() != null) {
			sqlStr += " and ve_id = " + "'" + ve.getVe_id() + "'";
		}
		if (ve.getVe_name() != null) {
			sqlStr += " and ve_name = " + "'" + ve.getVe_name() + "'";
		}

		if (ve.getVe_key() != null) {

			sqlStr += " and ve_key = " + "'" + ve.getVe_key() + "'";

		}
		if (ve.getVe_state() != null) {

			sqlStr += " and ve_state = " + "'" + ve.getVe_state() + "'";

		}
		if (ve.getVe_privacy() != null) {

			sqlStr += " and ve_privacy = " + "'" + ve.getVe_privacy() + "'";

		}
		if (ve.getVe_init_time() != null) {

			sqlStr += " and ve_init_time = " + "'" + ve.getVe_init_time() + "'";

		}
		if (ve.getVe_description() != null) {

			sqlStr += " and ve_description = " + "'" + ve.getVe_description()
					+ "'";

		}
		if (ve.getVe_creator_id() != null) {

			sqlStr += " and ve_creator_id = " + "'" + ve.getVe_creator_id()
					+ "'";

		}
		if (ve.getTemplate_id() != null) {

			sqlStr += " and template_id = " + "'" + ve.getTemplate_id() + "'";
		}
		if (ve.getVe_atom() != null) {

			sqlStr += " and ve_atom = " + "'" + ve.getVe_atom() + "'";
		}

		System.out.println(sqlStr);
		int n = jdbcTemplate.queryForInt(sqlStr);
		return n;
	}

	@Override
	public List<VE> queryVEALLINFO(VE ve, int page, int size) {
		// preOperation(ve);
		String sqlStr = "select A.*,B.geolocation_id,B.country_id,B.province_id,B.city_id,B.altitude,B.latitude,B.longitude,B.otherInfo from iot_ve as A left join iot_ve_geolocation as B on A.ve_id = B.ve_id  where 1=1";
		if (ve.getVe_id() != null) {
			sqlStr += " and ve_id = " + "'" + ve.getVe_id() + "'";
		}
		if (ve.getVe_name() != null) {
			sqlStr += " and ve_name = " + "'" + ve.getVe_name() + "'";
		}

		if (ve.getVe_key() != null) {

			sqlStr += " and ve_key = " + "'" + ve.getVe_key() + "'";

		}
		if (ve.getVe_state() != null) {

			sqlStr += " and ve_state = " + "'" + ve.getVe_state() + "'";

		}
		if (ve.getVe_privacy() != null) {

			sqlStr += " and ve_privacy = " + "'" + ve.getVe_privacy() + "'";

		}
		if (ve.getVe_init_time() != null) {

			sqlStr += " and ve_init_time = " + "'" + ve.getVe_init_time() + "'";

		}
		if (ve.getVe_description() != null) {

			sqlStr += " and ve_description = " + "'" + ve.getVe_description()
					+ "'";

		}
		if (ve.getVe_creator_id() != null) {

			sqlStr += " and ve_creator_id = " + "'" + ve.getVe_creator_id()
					+ "'";

		}
		if (ve.getTemplate_id() != null) {

			sqlStr += " and template_id = " + "'" + ve.getTemplate_id() + "'";
		}
		if (ve.getVe_atom() != null) {

			sqlStr += " and ve_atom = " + "'" + ve.getVe_atom() + "'";
		}
		sqlStr += " limit " + page + "," + size + "";
		System.out.println(sqlStr);
		list = jdbcTemplate.query(sqlStr, new VERowMapper());
		System.out.println(list);
		return list;
	}

	class VERowMapper implements RowMapper<VE> {
		@Override
		public VE mapRow(ResultSet rs, int rowNum) throws SQLException {
			VE ve = new VE();
			VeGeoLocation veGeoLocation = new VeGeoLocation();
			veGeoLocation.setAltitude(rs.getFloat("altitude"));
			veGeoLocation.setLatitude(rs.getFloat("latitude"));
			veGeoLocation.setLongitude(rs.getFloat("longitude"));
			veGeoLocation.setGeolocation_id(rs.getString("geolocation_id"));
			veGeoLocation.setCountry_id(rs.getInt("country_id"));
			veGeoLocation.setProvince_id(rs.getInt("province_id"));
			veGeoLocation.setCity_id(rs.getInt("city_id"));
			veGeoLocation.setOtherInfo(rs.getString("otherInfo"));
			ve.setGeo(veGeoLocation);
			ve.setTemplate_id(rs.getString("template_id"));
			ve.setVe_creator_id(rs.getString("ve_creator_id"));
			ve.setVe_description(rs.getString("ve_description"));
			ve.setVe_id(rs.getString("ve_id"));
			ve.setVe_init_time(new java.util.Date(rs.getTimestamp(
					"ve_init_time").getTime()));
			ve.setVe_key(rs.getString("ve_key"));
			ve.setVe_name(rs.getString("ve_name"));
			ve.setVe_privacy(rs.getString("ve_privacy"));
			ve.setVe_state(rs.getString("ve_state"));
			ve.setVe_atom(rs.getString("ve_atom"));
			return ve;
		}
	}

	@Override
	public List<Map<String, Object>> queryALLVeForMap() {
		String sqlStr = "select A.*,B.geolocation_id,B.country_id,B.province_id,B.city_id,B.altitude,B.latitude,B.longitude,B.otherInfo from iot_ve as A left outer join iot_ve_geolocation as B on A.ve_id = B.ve_id";
		System.out.println(sqlStr);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlStr);
		return list;
	}

}
