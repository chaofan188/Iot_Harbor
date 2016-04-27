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

import com.cetc.iot.database.dao.VeKeyConfigDao;
import com.cetc.iot.database.model.VeKeyConfig;
import com.cetc.iot.util.BaseUtil;

@Repository
public class VeKeyConfigDaoImpl implements VeKeyConfigDao {

	private static Logger logger = Logger.getLogger(VEDaoImpl.class.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// private String ve_id;
	// private String service_id;
	// private Date change_time;
	// private String base_key;
	// private String key_state;

	List<VeKeyConfig> list = new ArrayList<VeKeyConfig>();

	/*
	 * public void preOperation(VeKeyConfig veKeyConfig){ this.ve_id =
	 * veKeyConfig.getVe_id(); this.service_id = veKeyConfig.getService_id();
	 * this.change_time = veKeyConfig.getChange_time(); this.base_key =
	 * veKeyConfig.getBase_key(); this.key_state = veKeyConfig.getKey_state(); }
	 */

	@Override
	public String add(VeKeyConfig veKeyConfig) {
		// TODO Auto-generated method stub
		// preOperation(veKeyConfig);
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();

		sb1.append("insert into iot_ve_key_config( ");
		char lastChar = sb1.charAt(sb1.length() - 1);
		if (BaseUtil.isNotEmpty(veKeyConfig.getVe_id())) {
			sb1.append("ve_id");
			sb2.append("'").append(veKeyConfig.getVe_id()).append("'");
		}

		if (BaseUtil.isNotEmpty(veKeyConfig.getService_id())) {
			lastChar = sb1.charAt(sb1.length() - 1);
			if (lastChar == '(') {
				sb1.append("service_id");
				sb2.append("'").append(veKeyConfig.getService_id()).append("'");
			} else {
				sb1.append(",service_id");
				sb2.append(",'").append(veKeyConfig.getService_id())
						.append("'");
			}

		}
		if (veKeyConfig.getChange_time() != null) {
			lastChar = sb1.charAt(sb1.length() - 1);
			if (lastChar == '(') {
				sb1.append("change_time");
				sb2.append("'").append(veKeyConfig.getChange_time())
						.append("'");
			} else {
				sb1.append(",change_time");
				sb2.append(",'").append(veKeyConfig.getChange_time())
						.append("'");
			}

		}
		if (BaseUtil.isNotEmpty(veKeyConfig.getBase_key())) {
			lastChar = sb1.charAt(sb1.length() - 1);
			if (lastChar == '(') {
				sb1.append("base_key");
				sb2.append("'").append(veKeyConfig.getBase_key()).append("'");
			} else {
				sb1.append(",base_key");
				sb2.append(",'").append(veKeyConfig.getBase_key()).append("'");
			}

		}
		if (BaseUtil.isNotEmpty(veKeyConfig.getKey_state())) {
			lastChar = sb1.charAt(sb1.length() - 1);
			if (lastChar == '(') {
				sb1.append("key_state");
				sb2.append("'").append(veKeyConfig.getKey_state()).append("'");
			} else {
				sb1.append(",key_state");
				sb2.append(",'").append(veKeyConfig.getKey_state()).append("'");
			}

		}

		sb1.append(") values (");
		sb2.append(")");
		sb1.append(sb2);
		String sql = sb1.toString();
		logger.info(sql);
		try {
			int num = jdbcTemplate.update(sql);
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
	public String delete(VeKeyConfig veKeyConfig) {

		// preOperation(veKeyConfig);
		StringBuffer sb = new StringBuffer();

		if (BaseUtil.isNotEmpty(veKeyConfig.getVe_id())) {

			sb.append("delete from iot_ve_key_config where 1=1 ");
			sb.append(" and ve_id = ");
			sb.append("'").append(veKeyConfig.getVe_id()).append("'");
			// sb.append("and service_id = ");
			// sb.append("'").append(service_id).append("'");

			try {
				logger.info(sb.toString());
				jdbcTemplate.execute(sb.toString());
				return "success";
			} catch (DataAccessException e) {
				e.printStackTrace();
				return "fail";
			}
		} else {
			return "error";
		}

	}

	@Override
	public String update(VeKeyConfig veKeyConfig) {

		// preOperation(veKeyConfig);
		StringBuffer sb = new StringBuffer();
		sb.append("update iot_ve_key_config set ");
		String lastString = sb.toString().substring(sb.toString().length() - 3);
		if (BaseUtil.isNotEmpty(veKeyConfig.getBase_key())) {
			sb.append(" base_key = ");
			sb.append("'").append(veKeyConfig.getBase_key()).append("'");
		}
		if (veKeyConfig.getChange_time() != null) {
			lastString = sb.toString().substring(sb.toString().length() - 3);
			if ("set".equals(lastString)) {
				sb.append(" change_time = ");
				sb.append("'").append(veKeyConfig.getChange_time()).append("'");
			} else {
				sb.append(", change_time = ");
				sb.append("'").append(veKeyConfig.getChange_time()).append("'");
			}

		}
		if (BaseUtil.isNotEmpty(veKeyConfig.getKey_state())) {
			lastString = sb.toString().substring(sb.toString().length() - 3);
			if ("set".equals(lastString)) {
				sb.append(" key_state = ");
				sb.append("'").append(veKeyConfig.getKey_state()).append("'");
			} else {
				sb.append(", key_state = ");
				sb.append("'").append(veKeyConfig.getKey_state()).append("'");
			}
		}

		sb.append(" where ve_id = ").append("'").append(veKeyConfig.getVe_id())
				.append("'");
		sb.append(" and service_id = ").append("'")
				.append(veKeyConfig.getService_id()).append("'");
		logger.info(sb.toString());
		try {
			int num = jdbcTemplate.update(sb.toString());
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
	public List<VeKeyConfig> query(VeKeyConfig veKeyConfig, int page, int size) {

		// preOperation(veKeyConfig);
		StringBuffer sb = new StringBuffer();
		sb.append("select * from iot_ve_key_config where 1=1 ");

		if (BaseUtil.isNotEmpty(veKeyConfig.getVe_id())) {
			sb.append(" and ve_id = ");
			sb.append("'").append(veKeyConfig.getVe_id()).append("'");
		}
		if (BaseUtil.isNotEmpty(veKeyConfig.getService_id())) {
			sb.append(" and service_id = ");
			sb.append("'").append(veKeyConfig.getService_id()).append("'");
		}
		if (BaseUtil.isNotEmpty(veKeyConfig.getKey_state())) {
			sb.append(" and key_state = ");
			sb.append("'").append(veKeyConfig.getKey_state()).append("'");
		}

		sb.append(" limit ").append(page).append(", ").append(size);
		logger.info(sb.toString());
		list = jdbcTemplate.query(sb.toString(), new VeKeyConfigRowMapper());
		return list;
	}

	class VeKeyConfigRowMapper implements RowMapper<VeKeyConfig> {

		@Override
		public VeKeyConfig mapRow(ResultSet resultset, int i)
				throws SQLException {

			VeKeyConfig veKeyConfig = new VeKeyConfig();

			veKeyConfig.setVe_id(resultset.getString("ve_id"));
			veKeyConfig.setService_id(resultset.getString("service_id"));
			veKeyConfig.setChange_time(resultset.getDate("change_time"));
			veKeyConfig.setKey_state(resultset.getString("key_state"));
			veKeyConfig.setBase_key(resultset.getString("base_key"));

			return veKeyConfig;
		}
	}

}
