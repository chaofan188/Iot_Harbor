package com.cetc.iot.database.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeDataDao;
import com.cetc.iot.database.model.PeData;

@Repository
public class PeDataDaoImpl implements PeDataDao {

	private static Logger logger = Logger.getLogger(PeDataDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// String dataId;// ���ID
	// Date dataTime;// ������ʱ��
	// String peId;// �����ݵ�pe��ID
	// String peInterfaceId;// �����ݵ�pe�Ľӿ�ID
	// String dataContent;// �������
	// int datail = -1;// �Ƿ��������
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(PeData peData) { this.dataId =
	 * peData.getDataId(); this.dataTime = peData.getDataTime(); this.peId =
	 * peData.getPeId(); this.peInterfaceId = peData.getPeInterfaceId();
	 * this.dataContent = peData.getDataContent(); this.datail =
	 * peData.getDatail(); }
	 */

	@Override
	public List<Map<String, Object>> query(PeData peData, int page, int size) {
		// preOperation(peData);
		String sqlStr = "select * from iot_pe_data where";
		if (peData.getDataId() != null) {
			sqlStr += " data_id = " + "'" + peData.getDataId() + "'";
		}
		if (peData.getDataTime() != null) {
			Timestamp timeStamp = new Timestamp(peData.getDataTime().getTime());
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " data_time = " + "'" + timeStamp + "'";
			} else {
				sqlStr += " and data_time = " + "'" + timeStamp + "'";
			}
		}
		if (peData.getPeId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_id = " + "'" + peData.getPeId() + "'";
			} else {
				sqlStr += " and pe_id = " + "'" + peData.getPeId() + "'";
			}
		}
		if (peData.getPeInterfaceId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_id = " + "'" + peData.getPeInterfaceId()
						+ "'";
			} else {
				sqlStr += " and interface_id = " + "'"
						+ peData.getPeInterfaceId() + "'";
			}
		}
		if (peData.getDataContent() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " data_content = " + "'" + peData.getDataContent()
						+ "'";
			} else {
				sqlStr += " and data_content = " + "'"
						+ peData.getDataContent() + "'";
			}
		}
		if (peData.getDatail() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " datail = " + "'" + peData.getDatail() + "'";
			} else {
				sqlStr += " and datail = " + "'" + peData.getDatail() + "'";
			}
		}
		sqlStr += " limit " + page + "," + size + "";
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String delete(PeData peData) {
		// preOperation(peData);
		String sqlStr = "delete from iot_pe_data where";
		if (peData.getPeId() != null) {
			sqlStr += " data_id = " + "'" + peData.getPeId() + "'";
		}
		if (peData.getDataTime() != null) {
			Timestamp timeStamp = new Timestamp(peData.getDataTime().getTime());
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " data_time = " + "'" + timeStamp + "'";
			} else {
				sqlStr += " and data_time = " + "'" + timeStamp + "'";
			}
		}
		if (peData.getPeId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_id = " + "'" + peData.getPeId() + "'";
			} else {
				sqlStr += " and pe_id = " + "'" + peData.getPeId() + "'";
			}
		}
		if (peData.getPeInterfaceId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_id = " + "'" + peData.getPeInterfaceId()
						+ "'";
			} else {
				sqlStr += " and interface_id = " + "'"
						+ peData.getPeInterfaceId() + "'";
			}
		}
		if (peData.getDataContent() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " data_content = " + "'" + peData.getDataContent()
						+ "'";
			} else {
				sqlStr += " and data_content = " + "'"
						+ peData.getDataContent() + "'";
			}
		}
		if (peData.getDatail() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " datail = " + "'" + peData.getDatail() + "'";
			} else {
				sqlStr += " and datail = " + "'" + peData.getDatail() + "'";
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
	public String add(PeData peData) {
		// preOperation(peData);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_pe_data(";
		if (peData.getDataId() != null) {

			sqlStr1 += "data_id";
			sqlStr2 += "'" + peData.getDataId() + "'";
		}

		if (peData.getDataTime() != null) {
			Timestamp timeStamp = new Timestamp(peData.getDataTime().getTime());
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "data_time";
				sqlStr2 += "'" + timeStamp + "'";
			} else {
				sqlStr1 += ",data_time";
				sqlStr2 += ",'" + timeStamp + "'";
			}

		}
		if (peData.getPeId() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_id";
				sqlStr2 += "'" + peData.getPeId() + "'";
			} else {
				sqlStr1 += ",pe_id";
				sqlStr2 += ",'" + peData.getPeId() + "'";
			}

		}
		if (peData.getPeInterfaceId() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_id";
				sqlStr2 += "'" + peData.getPeInterfaceId() + "'";
			} else {
				sqlStr1 += ",interface_id";
				sqlStr2 += ",'" + peData.getPeInterfaceId() + "'";
			}

		}
		if (peData.getDataContent() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "data_content";
				sqlStr2 += "'" + peData.getDataContent() + "'";
			} else {
				sqlStr1 += ",data_content";
				sqlStr2 += ",'" + peData.getDataContent() + "'";
			}

		}
		if (peData.getDatail() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "datail";
				sqlStr2 += "'" + peData.getDatail() + "'";
			} else {
				sqlStr1 += ",datail";
				sqlStr2 += ",'" + peData.getDatail() + "'";
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
	public String update(PeData peData) {
		// preOperation(peData);
		String sqlStr = "update iot_pe_data set";
		/*
		 * if(dataId!=null){ sqlStr += " data_id = "+"'"+dataId +"'"; }
		 */
		if (peData.getDataTime() != null) {
			Timestamp timeStamp = new Timestamp(peData.getDataTime().getTime());
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " data_time = " + "'" + timeStamp + "'";
			} else {
				sqlStr += " , data_time = " + "'" + timeStamp + "'";
			}
		}
		if (peData.getPeId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_id = " + "'" + peData.getPeId() + "'";
			} else {
				sqlStr += " , pe_id = " + "'" + peData.getPeId() + "'";
			}
		}
		if (peData.getPeInterfaceId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " interface_id = " + "'" + peData.getPeInterfaceId()
						+ "'";
			} else {
				sqlStr += " , interface_id = " + "'"
						+ peData.getPeInterfaceId() + "'";
			}
		}
		if (peData.getDataContent() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " data_content = " + "'" + peData.getDataContent()
						+ "'";
			} else {
				sqlStr += " , data_content = " + "'" + peData.getDataContent()
						+ "'";
			}
		}
		if (peData.getDatail() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " datail = " + "'" + peData.getDatail() + "'";
			} else {
				sqlStr += " , datail = " + "'" + peData.getDatail() + "'";
			}
		}
		sqlStr += " where data_id = " + "'" + peData.getDataId() + "'";
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
