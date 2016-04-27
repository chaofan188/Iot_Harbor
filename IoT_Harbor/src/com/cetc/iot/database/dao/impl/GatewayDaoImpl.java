package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.GatewayDao;
import com.cetc.iot.database.model.Gateway;

@Repository
public class GatewayDaoImpl implements GatewayDao {

	private static Logger logger = Logger.getLogger(GatewayDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// String gatewayId;// �������ID
	// String gatewayName;// ������
	// String gatewayUri;// ������ӵ�ַ
	// String gatewayState;// ���״̬
	// String gatewayKey;// �����Կ
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(Gateway gateWay) { this.gatewayId =
	 * gateWay.getGatewayId(); this.gatewayKey = gateWay.getGatewayKey();
	 * this.gatewayName = gateWay.getGatewayName(); this.gatewayState =
	 * gateWay.getGatewayState(); this.gatewayUri = gateWay.getGatewayUri(); }
	 */
	@Override
	public List<Map<String, Object>> query(Gateway gateWay, int page, int size) {
		// preOperation(gateWay);
		String sqlStr = "select * from iot_gateway where";
		if (gateWay.getGatewayId() != null) {
			sqlStr += " gateway_id = " + "'" + gateWay.getGatewayId() + "'";
		}
		if (gateWay.getGatewayName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " gateway_name = " + "'" + gateWay.getGatewayName()
						+ "'";
			} else {
				sqlStr += " and gateway_name = " + "'"
						+ gateWay.getGatewayName() + "'";
			}
		}
		if (gateWay.getGatewayUri() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " gateway_uri = " + "'" + gateWay.getGatewayUri()
						+ "'";
			} else {
				sqlStr += " and gateway_uri = " + "'" + gateWay.getGatewayUri()
						+ "'";
			}
		}
		if (gateWay.getGatewayState() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " gateway_state = " + "'" + gateWay.getGatewayState()
						+ "'";
			} else {
				sqlStr += " and gateway_state = " + "'"
						+ gateWay.getGatewayState() + "'";
			}
		}
		if (gateWay.getGatewayKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " gateway_key = " + "'" + gateWay.getGatewayKey()
						+ "'";
			} else {
				sqlStr += " and gateway_key = " + "'" + gateWay.getGatewayKey()
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
	public String delete(Gateway gateWay) {
		// preOperation(gateWay);
		String sqlStr = "delete from iot_gateway where";
		if (gateWay.getGatewayId() != null) {
			sqlStr += " gateway_id = " + "'" + gateWay.getGatewayId() + "'";
		}
		if (gateWay.getGatewayName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " gateway_name = " + "'" + gateWay.getGatewayName()
						+ "' ";
			} else {
				sqlStr += " and gateway_name = " + "'"
						+ gateWay.getGatewayName() + "' ";
			}
		}
		if (gateWay.getGatewayUri() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " gateway_uri = " + "'" + gateWay.getGatewayUri()
						+ "' ";
			} else {
				sqlStr += " and gateway_uri = " + "'" + gateWay.getGatewayUri()
						+ "' ";
			}
		}

		if (gateWay.getGatewayState() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " gateway_state = " + "'" + gateWay.getGatewayState()
						+ "' ";
			} else {
				sqlStr += " and gateway_state = " + "'"
						+ gateWay.getGatewayState() + "' ";
			}
		}

		if (gateWay.getGatewayKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " gateway_key = " + "'" + gateWay.getGatewayKey()
						+ "' ";
			} else {
				sqlStr += " and gateway_key = " + "'" + gateWay.getGatewayKey()
						+ "' ";
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
	public String add(Gateway gateWay) {
		// preOperation(gateWay);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_gateway(";
		if (gateWay.getGatewayId() != null) {

			sqlStr1 += "gateway_id";
			sqlStr2 += "'" + gateWay.getGatewayId() + "'";
		}

		if (gateWay.getGatewayName() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "gateway_name";
				sqlStr2 += "'" + gateWay.getGatewayName() + "'";
			} else {
				sqlStr1 += ",gateway_name";
				sqlStr2 += ",'" + gateWay.getGatewayName() + "'";
			}

		}
		if (gateWay.getGatewayUri() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "gateway_uri";
				sqlStr2 += "'" + gateWay.getGatewayUri() + "'";
			} else {
				sqlStr1 += ",gateway_uri";
				sqlStr2 += ",'" + gateWay.getGatewayUri() + "'";
			}

		}
		if (gateWay.getGatewayState() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "gateway_state";
				sqlStr2 += "'" + gateWay.getGatewayState() + "'";
			} else {
				sqlStr1 += ",gateway_state";
				sqlStr2 += ",'" + gateWay.getGatewayState() + "'";
			}

		}
		if (gateWay.getGatewayKey() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "gateway_key";
				sqlStr2 += "'" + gateWay.getGatewayKey() + "'";
			} else {
				sqlStr1 += ",gateway_key";
				sqlStr2 += ",'" + gateWay.getGatewayKey() + "'";
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
	public String update(Gateway gateWay) {
		// preOperation(gateWay);
		String sqlStr = "update iot_gateway set";
		/*
		 * if(gatewayId!=null){ sqlStr += " gateway_id = "+"'"+gatewayId +"'"; }
		 */
		if (gateWay.getGatewayName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " gateway_name = " + "'" + gateWay.getGatewayName()
						+ "'";
			} else {
				sqlStr += " , gateway_name = " + "'" + gateWay.getGatewayName()
						+ "'";
			}
		}
		if (gateWay.getGatewayUri() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " gateway_uri = " + "'" + gateWay.getGatewayUri()
						+ "'";
			} else {
				sqlStr += " , gateway_uri = " + "'" + gateWay.getGatewayUri()
						+ "'";
			}
		}
		if (gateWay.getGatewayState() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " gateway_state = " + "'" + gateWay.getGatewayState()
						+ "'";
			} else {
				sqlStr += " , gateway_state = " + "'"
						+ gateWay.getGatewayState() + "'";
			}
		}
		if (gateWay.getGatewayKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " gateway_key = " + "'" + gateWay.getGatewayKey()
						+ "'";
			} else {
				sqlStr += " , gateway_key = " + "'" + gateWay.getGatewayKey()
						+ "'";
			}
		}

		sqlStr += " where gateway_id = " + "'" + gateWay.getGatewayId() + "'";
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
