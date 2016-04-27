package com.cetc.iot.database.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeDao;
import com.cetc.iot.database.model.Pe;

@Repository
public class PeDaoImpl implements PeDao {
	
	private static Logger logger = Logger.getLogger(PeDaoImpl.class.getName());
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*String peId;// peID(Ψһ��ʶ)
	String peName;// pe���
	String peOwner;// pe������
	String peUser;// pe�����ʹ����
	String pePictureUrl;// peͼƬ·��
	String peKey;// pe��Կ
	int peState = -1;// pe��ǰ״̬
	String peGeolocationId;// pe����λ��ID
	int peLifecycle = -1;// pe��������
	Date peLastTime;// pe����¼ʱ��
	int peTime = -1;// pe��¼����
	String templateId;// peģ��ID
	String ispublic;
	String description;
	Timestamp timeStamp = null;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();*/

	/*public void preOperation(Pe pe) {
		this.peId = pe.getPeId();
		this.peName = pe.getPeName();
		this.peOwner = pe.getPeOwner();
		this.peUser = pe.getPeUser();
		this.pePictureUrl = pe.getPePictureUrl();
		this.peKey = pe.getPeKey();
		this.peState = pe.getPeState();
		this.peGeolocationId = pe.getPeGeolocationId();
		this.peLifecycle = pe.getPeLifecycle();
		this.peLastTime = pe.getPeLastTime();
		this.peTime = pe.getPeTime();
		this.templateId = pe.getTemplateId();
		this.ispublic = pe.getPe_public();
		this.description = pe.getPe_description();
		if (pe.getPeLastTime() != null) {
			this.timeStamp = new Timestamp(this.peLastTime.getTime());
		} else
			this.timeStamp = null;
	}*/

	@Override
	public List<Map<String, Object>> query(Pe pe,int page,int size) {
		/*preOperation(pe);*/
		String sqlStr = "select * from iot_pe where 1=1";
		if (pe.getPeId() != null) {
			sqlStr += " and pe_id = " + "'" + pe.getPeId() + "'";
		}
		if (pe.getPeName() != null) {
				sqlStr += " and pe_name = " + "'" + pe.getPeName() + "'";
			}
		
		if (pe.getPeOwner() != null) {
			
				sqlStr += " and pe_owner = " + "'" + pe.getPeOwner() + "'";
			
		}
		if (pe.getPe_public() != null) {
			
				sqlStr += " and pe_public = " + "'" + pe.getPe_public() + "'";
			
		}
		if (pe.getPe_description() != null) {
			
				sqlStr += " and pe_description = " + "'" + pe.getPe_public() + "'";
			
		}
		if (pe.getPeUser() != null) {
			
				sqlStr += " and pe_user = " + "'" + pe.getPeUser() + "'";
			
		}
		if (pe.getPePictureUrl() != null) {
			
				sqlStr += " and pe_picture_url = " + "'" + pe.getPePictureUrl() + "'";
			
		}
		if (pe.getPeKey() != null) {
			
				sqlStr += " and pe_key = " + "'" + pe.getPeKey() + "'";
			
		}
		if (pe.getPeState() != -1) {
			
				sqlStr += " and pe_state = " + "'" + pe.getPeState() + "'";
			
		}
		if (pe.getPeGeolocationId() != null) {
			
				sqlStr += " and pe_geolocation_id = " + "'" + pe.getPeGeolocationId()
						+ "'";
			
		}
		if (pe.getPeLifecycle() != -1) {
			
				sqlStr += " and pe_lifecycle = " + "'" + pe.getPeLifecycle() + "'";
			
		}
		
		Timestamp timeStamp;
		if (pe.getPeLastTime() != null) {
			timeStamp = new Timestamp(pe.getPeLastTime().getTime());
		} else {
			timeStamp = null;
		}
	
		if (timeStamp != null) {
			
				sqlStr += " and pe_lastTime = " + "'" + timeStamp + "'";
			
		}
		if (pe.getPeTime() != -1) {
			
				sqlStr += " and pe_time = " + "'" + pe.getPeTime() + "'";
			
		}
		if (pe.getTemplateId() != null) {
			
				sqlStr += " and template_id = " + "'" + pe.getTemplateId() + "'";
			
		}
		//sqlStr += " limit "+page+","+size+"";
		//logger.info(sqlStr);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlStr);
		//logger.info(list);
		return list;
	}

	@Override
	public String delete(Pe pe) {

//		preOperation(pe);
		String sqlStr = "delete from iot_pe where";
		if (pe.getPeId() != null) {
			sqlStr += " pe_id = " + "'" + pe.getPeId() + "'";
		}
		if (pe.getPeName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_name = " + "'" + pe.getPeName() + "' ";
			} else {
				sqlStr += " and pe_name = " + "'" + pe.getPeName() + "' ";
			}
		}
		if (pe.getPe_public() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_public = " + "'" + pe.getPe_public() + "'";
			} else {
				sqlStr += " and pe_public = " + "'" + pe.getPe_public() + "'";
			}
		}
		if (pe.getPe_public() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_description = " + "'" + pe.getPe_public() + "'";
			} else {
				sqlStr += " and pe_description = " + "'" + pe.getPe_public() + "'";
			}
		}
		if (pe.getPeOwner() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_owner = " + "'" + pe.getPeOwner() + "' ";
			} else {
				sqlStr += " and pe_owner = " + "'" + pe.getPeOwner() + "' ";
			}
		}

		if (pe.getPeUser() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_user = " + "'" + pe.getPeUser() + "' ";
			} else {
				sqlStr += " and pe_user = " + "'" + pe.getPeUser() + "' ";
			}
		}

		if (pe.getPePictureUrl() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_picture_url = " + "'" + pe.getPePictureUrl() + "' ";
			} else {
				sqlStr += " and pe_picture_url = " + "'" + pe.getPePictureUrl() + "' ";
			}
		}
		if (pe.getPeKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_key = " + "'" + pe.getPeKey() + "'";
			} else {
				sqlStr += " and pe_key = " + "'" + pe.getPeKey() + "'";
			}
		}
		if (pe.getPeState() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_state = " + "'" + pe.getPeState() + "'";
			} else {
				sqlStr += " and pe_state = " + "'" + pe.getPeState() + "'";
			}
		}
		if (pe.getPeGeolocationId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_geolocation_id = " + "'" + pe.getPeGeolocationId() + "'";
			} else {
				sqlStr += " and pe_geolocation_id = " + "'" + pe.getPeGeolocationId()
						+ "'";
			}
		}
		if (pe.getPeLifecycle() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_lifecycle = " + "'" + pe.getPeLifecycle() + "'";
			} else {
				sqlStr += " and pe_lifecycle = " + "'" + pe.getPeLifecycle() + "'";
			}
		}
		Timestamp timeStamp;
		if (pe.getPeLastTime() != null) {
			timeStamp = new Timestamp(pe.getPeLastTime().getTime());
		} else {
			timeStamp = null;
		}
		if (timeStamp != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_lastTime = " + "'" + timeStamp + "'";
			} else {
				sqlStr += " and pe_lastTime = " + "'" + timeStamp + "'";
			}
		}
		if (pe.getPeTime() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " pe_time = " + "'" + pe.getPeTime() + "'";
			} else {
				sqlStr += " and pe_time = " + "'" + pe.getPeTime() + "'";
			}
		}
		if (pe.getTemplateId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_id = " + "'" + pe.getTemplateId() + "'";
			} else {
				sqlStr += " and template_id = " + "'" + pe.getTemplateId() + "'";
			}
		}
		//logger.info(sqlStr);
		try {
			jdbcTemplate.execute(sqlStr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return "fail";
		}
		return "success";
	}

	@Override
	public String add(Pe pe) {

//		preOperation(pe);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_pe(";
		if (pe.getPeId() != null) {

			sqlStr1 += "pe_id";
			sqlStr2 += "'" + pe.getPeId() + "'";
		}

		if (pe.getPeName() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_name";
				sqlStr2 += "'" + pe.getPeName() + "'";
			} else {
				sqlStr1 += ",pe_name";
				sqlStr2 += ",'" + pe.getPeName() + "'";
			}

		}
		if (pe.getPe_public() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_public";
				sqlStr2 += "'" + pe.getPe_public() + "'";
			} else {
				sqlStr1 += ",pe_public";
				sqlStr2 += ",'" + pe.getPe_public() + "'";
			}

		}
		if (pe.getPe_public() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_description";
				sqlStr2 += "'" + pe.getPe_public() + "'";
			} else {
				sqlStr1 += ",pe_description";
				sqlStr2 += ",'" + pe.getPe_public() + "'";
			}

		}
		if (pe.getPeOwner() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_owner";
				sqlStr2 += "'" + pe.getPeOwner() + "'";
			} else {
				sqlStr1 += ",pe_owner";
				sqlStr2 += ",'" + pe.getPeOwner() + "'";
			}

		}
		if (pe.getPeUser() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_user";
				sqlStr2 += "'" + pe.getPeUser() + "'";
			} else {
				sqlStr1 += ",pe_user";
				sqlStr2 += ",'" + pe.getPeUser() + "'";
			}

		}
		if (pe.getPePictureUrl() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_picture_url";
				sqlStr2 += "'" + pe.getPePictureUrl() + "'";
			} else {
				sqlStr1 += ",pe_picture_url";
				sqlStr2 += ",'" + pe.getPePictureUrl() + "'";
			}

		}
		if (pe.getPeKey() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_key";
				sqlStr2 += "'" + pe.getPeKey() + "'";
			} else {
				sqlStr1 += ",pe_key";
				sqlStr2 += ",'" + pe.getPeKey() + "'";
			}

		}
		if (pe.getPeState() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_state";
				sqlStr2 += "'" + pe.getPeState() + "'";
			} else {
				sqlStr1 += ",pe_state";
				sqlStr2 += ",'" + pe.getPeState() + "'";
			}

		}
		if (pe.getPeGeolocationId() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_geolocation_id";
				sqlStr2 += "'" + pe.getPeGeolocationId() + "'";
			} else {
				sqlStr1 += ",pe_geolocation_id";
				sqlStr2 += ",'" + pe.getPeGeolocationId() + "'";
			}

		}
		if (pe.getPeLifecycle() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_lifecycle";
				sqlStr2 += "'" + pe.getPeLifecycle() + "'";
			} else {
				sqlStr1 += ",pe_lifecycle";
				sqlStr2 += ",'" + pe.getPeLifecycle() + "'";
			}

		}
		
		Timestamp timeStamp;
		if (pe.getPeLastTime() != null) {
			timeStamp = new Timestamp(pe.getPeLastTime().getTime());
		} else {
			timeStamp = null;
		}
		
		if (pe.getPeLastTime() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_lastTime";
				sqlStr2 += "'" + timeStamp + "'";
			} else {
				sqlStr1 += ",pe_lastTime";
				sqlStr2 += ",'" + timeStamp + "'";
			}

		}
		if (pe.getPeTime() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "pe_time";
				sqlStr2 += "'" + pe.getPeTime() + "'";
			} else {
				sqlStr1 += ",pe_time";
				sqlStr2 += ",'" + pe.getPeTime() + "'";
			}

		}
		if (pe.getTemplateId() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_id";
				sqlStr2 += "'" + pe.getTemplateId() + "'";
			} else {
				sqlStr1 += ",template_id";
				sqlStr2 += ",'" + pe.getTemplateId() + "'";
			}

		}
		sqlStr1 += ") values (";
		sqlStr2 += ")";
		sqlStr = sqlStr1 + sqlStr2;
		//logger.info(sqlStr);
		try {
			jdbcTemplate.update(sqlStr);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return "fail";
		}
		return "success";

	}

	@Override
	public String update(Pe pe) {
//		preOperation(pe);
		String sqlStr = "update iot_pe set";
		/*if (pe.getPeId() != null) {
		sqlStr += " pe_id = " + "'" + pe.getPeId() + "'";
	}*/
		if (pe.getPeName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_name = " + "'" + pe.getPeName() + "'";
			} else {
				sqlStr += " , pe_name = " + "'" + pe.getPeName() + "'";
			}
		}
		if (pe.getPeOwner() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_owner = " + "'" + pe.getPeOwner() + "'";
			} else {
				sqlStr += " , pe_owner = " + "'" + pe.getPeOwner() + "'";
			}
		}
		if (pe.getPe_public() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_public = " + "'" + pe.getPe_public() + "'";
			} else {
				sqlStr += " , pe_public = " + "'" + pe.getPe_public() + "'";
			}
		}
		if (pe.getPe_public() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_description = " + "'" + pe.getPe_public() + "'";
			} else {
				sqlStr += " , pe_description = " + "'" + pe.getPe_public() + "'";
			}
		}
		if (pe.getPeUser() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_user = " + "'" + pe.getPeUser() + "'";
			} else {
				sqlStr += " , pe_user = " + "'" + pe.getPeUser() + "'";
			}
		}
		if (pe.getPePictureUrl() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_picture_url = " + "'" + pe.getPePictureUrl() + "'";
			} else {
				sqlStr += " , pe_picture_url = " + "'" + pe.getPePictureUrl() + "'";
			}
		}

		if (pe.getPeKey() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_key = " + "'" + pe.getPeKey() + "'";
			} else {
				sqlStr += " , pe_key = " + "'" + pe.getPeKey() + "'";
			}
		}
		if (pe.getPeState() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_state = " + "'" + pe.getPeState() + "'";
			} else {
				sqlStr += " , pe_state = " + "'" + pe.getPeState() + "'";
			}
		}
		if (pe.getPeGeolocationId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_geolocation_id = " + "'" + pe.getPeGeolocationId() + "'";
			} else {
				sqlStr += " , pe_geolocation_id = " + "'" + pe.getPeGeolocationId() + "'";
			}
		}
		if (pe.getPeLifecycle() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_lifecycle = " + "'" + pe.getPeLifecycle() + "'";
			} else {
				sqlStr += " , pe_lifecycle = " + "'" + pe.getPeLifecycle() + "'";
			}
		}
		
		Timestamp timeStamp;
		if (pe.getPeLastTime() != null) {
			timeStamp = new Timestamp(pe.getPeLastTime().getTime());
		} else {
			timeStamp = null;
		}
		if (pe.getPeLastTime() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += "  pe_lastTime= " + "'" + timeStamp + "'";
			} else {
				sqlStr += " , pe_lastTime = " + "'" + timeStamp + "'";
			}
		}
		if (pe.getPeTime() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " pe_time = " + "'" + pe.getPeTime() + "'";
			} else {
				sqlStr += " , pe_time = " + "'" + pe.getPeTime() + "'";
			}
		}
		if (pe.getTemplateId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_id = " + "'" + pe.getTemplateId() + "'";
			} else {
				sqlStr += " , template_id = " + "'" + pe.getTemplateId() + "'";
			}
		}
		sqlStr += " where pe_id = " + "'" + pe.getPeId() + "'";
		//logger.info(sqlStr);
		try {
			jdbcTemplate.update(sqlStr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return "fail";
		}
		return "success";

	}

}
