package com.cetc.iot.database.dao.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeProtocolConfigDao;
import com.cetc.iot.database.model.PeProtocolConfig;
@Repository
public class PeProtocolConfigDaoImpl implements PeProtocolConfigDao {
	
	private static Logger logger = Logger.getLogger(PeProtocolConfigDaoImpl.class.getName());

	
	@Autowired
	private JdbcTemplate jdbc_user_db;
	
	
	private static PeProtocolConfig mapToObject(Map<String,Object> resultMap){
		PeProtocolConfig peProtocolConfig = new PeProtocolConfig();
		peProtocolConfig.setPeId((String)resultMap.get("pe_id"));
		peProtocolConfig.setBasePassword((String)resultMap.get("basePassword"));
		peProtocolConfig.setUpPassword((String)resultMap.get("upPassword"));
		peProtocolConfig.setDownPassword((String)resultMap.get("downPassword"));
		peProtocolConfig.setLoginTimes((Integer)resultMap.get("loginTimes"));
		return peProtocolConfig;
	}
	
	@Override
	public PeProtocolConfig query(String peID) {
		// TODO Auto-generated method stub
		if (peID == null || ("").equalsIgnoreCase(peID)){
			logger.error("ERROR: QUERY: peID null! ");
			return null;
		}
		String sql = "select * from iot_pe_protocol_config where pe_id = '"+peID+"'";
		//logger.info(sql);
		try {
			Map<String, Object> resultMap = jdbc_user_db.queryForMap(sql);
			//logger.info(resultMap);
			return mapToObject(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public String add(PeProtocolConfig peProtocolConfig) {
		// TODO Auto-generated method stub
		if (peProtocolConfig == null || peProtocolConfig.getPeId() == null ||peProtocolConfig.getBasePassword() == null||
				peProtocolConfig.getUpPassword() == null|| peProtocolConfig.getDownPassword()==null||
				("").equalsIgnoreCase(peProtocolConfig.getPeId())||("").equalsIgnoreCase(peProtocolConfig.getBasePassword())||
				("").equalsIgnoreCase(peProtocolConfig.getUpPassword())||("").equalsIgnoreCase(peProtocolConfig.getDownPassword())){
			logger.error("ERROR: ADD: soma params are null! ");
			return "fail";
		}
		else {
			String sql = "insert into iot_pe_protocol_config (pe_id, basePassword , upPassword, downPassword, loginTimes) values ('"
					+peProtocolConfig.getPeId()+"', '"+peProtocolConfig.getBasePassword()+"', '"+peProtocolConfig.getUpPassword()+"', '"
					+peProtocolConfig.getDownPassword()+"', "+peProtocolConfig.getLoginTimes()+")";
			//logger.info(sql);
			try {
				int sqlResult = jdbc_user_db.update(sql);
				return (sqlResult == 1 || sqlResult == 0) ? "success" : "fail" ;
			} catch (DataAccessException e){
				return "fail";
			}
		}
	}

	@Override
	public String update(PeProtocolConfig peProtocolConfig) {
		// TODO Auto-generated method stub
		if (peProtocolConfig == null || peProtocolConfig.getPeId() == null ||peProtocolConfig.getBasePassword() == null||
				peProtocolConfig.getUpPassword() == null|| peProtocolConfig.getDownPassword()==null||
				("").equalsIgnoreCase(peProtocolConfig.getPeId())||("").equalsIgnoreCase(peProtocolConfig.getBasePassword())||
				("").equalsIgnoreCase(peProtocolConfig.getUpPassword())||("").equalsIgnoreCase(peProtocolConfig.getDownPassword())){
			logger.error("ERROR: UPDATE: some params are null! ");
			return "fail";
		}
		else {
			String sql = "update iot_pe_protocol_config set basePassword = '"+peProtocolConfig.getBasePassword()+"', upPassword = '"
					+peProtocolConfig.getUpPassword()+"', downPassword = '"+peProtocolConfig.getDownPassword()+"', loginTimes = "
					+peProtocolConfig.getLoginTimes()+" where pe_id = '"+peProtocolConfig.getPeId()+"'";
			//logger.info(sql);
			try {
				int sqlResult = jdbc_user_db.update(sql);
				return (sqlResult == 1 || sqlResult == 0) ? "success" : "fail" ;
			} catch (DataAccessException e){
				return "fail";
			}
		}
	}

	@Override
	public String delete(PeProtocolConfig peProtocolConfig) {
		// TODO Auto-generated method stub
		if (peProtocolConfig == null || peProtocolConfig.getPeId() == null || ("").equalsIgnoreCase(peProtocolConfig.getPeId())){
			logger.error("ERROR: DELETE: some params are null! ");
			return "fail";
		}
		else {
			String sql = "delete from iot_pe_protocol_config where pe_id = '"+peProtocolConfig.getPeId()+"'";
			//logger.info(sql);
			try {
				jdbc_user_db.execute(sql);
			} catch (DataAccessException e) {
				return "fail";
			}
			return "success";
		}
	}

	@Override
	public String delete(String peID) {
		// TODO Auto-generated method stub
		if (peID == null || "".equalsIgnoreCase(peID)){
			logger.error("ERROR: DELETE: peID null! ");
			return "fail";
		}
		String sql = "delete from iot_pe_protocol_config where pe_id = '"+peID+"'";
		//logger.info(sql);
		try {
			jdbc_user_db.execute(sql);
		} catch (DataAccessException e){
			return "fail";
		}
		return "success";
	}
	

}
