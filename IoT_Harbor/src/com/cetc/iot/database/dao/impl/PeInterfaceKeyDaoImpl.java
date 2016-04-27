package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeInterfaceKeyDao;
import com.cetc.iot.database.model.PeInterfaceKey;
@Repository
public class PeInterfaceKeyDaoImpl implements PeInterfaceKeyDao {
	
	private static Logger logger = Logger.getLogger(PeInterfaceKeyDaoImpl.class.getName());
	

	@Autowired
	private JdbcTemplate jdbc_user_db;
	
	private static PeInterfaceKey mapToObject(Map<String,Object> map){
		PeInterfaceKey peInterfaceKey = new PeInterfaceKey();
		peInterfaceKey.setPeID((String)map.get("pe_id"));
		peInterfaceKey.setInterfaceID((String)map.get("if_id"));
		peInterfaceKey.setInterfaceIDKey((String)map.get("if_id_key"));
		return peInterfaceKey;
	}
	
	@Override
	public PeInterfaceKey query(String peID, String interfaceID) {
		// TODO Auto-generated method stub
		if (peID == null || "".equalsIgnoreCase(peID)){
			logger.error("ERROR: QUERY: peID null! ");
			return null;
		}
		if (interfaceID == null || "".equalsIgnoreCase(interfaceID)){
			logger.error("ERROR: QUERY: interfaceID null! ");
		}
		String sql = "select * from iot_pe_interface_key where pe_id = '"+peID+"' and if_id = '"+interfaceID+"'";
		logger.info(sql);
		Map<String,Object> map = jdbc_user_db.queryForMap(sql);
		logger.info(map);
		if (map.isEmpty()){
			logger.error("ERROR: QUERY: not found! ");
			return null;
		}
		return mapToObject(map);
	}

	@Override
	public String add(PeInterfaceKey peInterfaceKey) {
		// TODO Auto-generated method stub
		if (peInterfaceKey == null || peInterfaceKey.getPeID() == null ||
				peInterfaceKey.getInterfaceID() == null || peInterfaceKey.getInterfaceIDKey() == null ||
				"".equalsIgnoreCase(peInterfaceKey.getPeID()) || "".equalsIgnoreCase(peInterfaceKey.getInterfaceID()) ||
						"".equalsIgnoreCase(peInterfaceKey.getInterfaceIDKey())){
			logger.error("ERROR: ADD: some params are null! ");
			return "fail";
		}
		String sql = "insert into iot_pe_interface_key (pe_id, if_id, if_id_key) values ('"+peInterfaceKey.getPeID()+"','"
				+peInterfaceKey.getInterfaceID()+"','"+peInterfaceKey.getInterfaceIDKey()+"')";
		logger.info(sql);
		try {
			int result = jdbc_user_db.update(sql);
			return (result == 1 || result == 0) ? "success" : "fail" ;
		} catch (DataAccessException e){
			return "fail";
		}
	}

	@Override
	public String update(PeInterfaceKey peInterfaceKey) {
		// TODO Auto-generated method stub
		if (peInterfaceKey == null || peInterfaceKey.getPeID() == null ||
				peInterfaceKey.getInterfaceID() == null || peInterfaceKey.getInterfaceIDKey() == null ||
				"".equalsIgnoreCase(peInterfaceKey.getPeID()) || "".equalsIgnoreCase(peInterfaceKey.getInterfaceID()) ||
						"".equalsIgnoreCase(peInterfaceKey.getInterfaceIDKey())){
			logger.error("ERROR: UPDATE: some params are null! ");
			return "fail";
		}
		String sql = "update iot_pe_interface_key set if_id_key = '"+peInterfaceKey.getInterfaceIDKey()+"' where pe_id = '"
				+peInterfaceKey.getPeID()+"' and if_id = '"+peInterfaceKey.getInterfaceID()+"'";
		logger.info(sql);
		try {
			int result = jdbc_user_db.update(sql);
			return (result == 1 || result == 0) ? "success" : "fail";
		} catch (DataAccessException e){
			return "fail";
		}
	}

	@Override
	public String delete(PeInterfaceKey peInterfaceKey) {
		// TODO Auto-generated method stub
		if (peInterfaceKey == null || peInterfaceKey.getPeID() == null || peInterfaceKey.getInterfaceID() == null ||
				"".equalsIgnoreCase(peInterfaceKey.getPeID()) || "".equalsIgnoreCase(peInterfaceKey.getInterfaceID())){
			logger.error("ERROR: DELETE: some params are null! ");
			return "fail";
		}
		String sql = "delete from iot_pe_interface_key where pe_id = '"+peInterfaceKey.getPeID()+"' and if_id = '"
				+peInterfaceKey.getInterfaceID()+"'";
		logger.info(sql);
		try {
			jdbc_user_db.execute(sql);
		} catch (DataAccessException e){
			return "fail";
		}
		return "success";
	}

	@Override
	public String delete(String peID) {
		// TODO Auto-generated method stub
		if (peID == null || "".equalsIgnoreCase(peID)){
			logger.error("ERROR: DELETE: peID null! ");
			return "fail";
		}
		String sql = "delete from iot_pe_interface_key where pe_id = '"+peID+"'";
		logger.info(sql);
		try {
			jdbc_user_db.execute(sql);
		} catch (DataAccessException e){
			return "fail";
		}
		return "success";
	}

	@Override
	public int queryForIsExsit(String peID, String interfaceID, String key) {
		if(peID == null || "".equalsIgnoreCase(peID)||interfaceID == null || "".equalsIgnoreCase(interfaceID)||key == null || "".equalsIgnoreCase(key)){
			logger.error("ERROR: QUERY: some params are null or empty! ");
			return 0;
		}
		String sql = "select count(*) from iot_pe_interface_key where pe_id = '"+peID+"' and if_id = '"+interfaceID+"' and if_id_key = '"+key+"'";
		logger.info(sql);
		int i = jdbc_user_db.queryForInt(sql);
		return i;
	}

	@Override
	public List<Map<String, Object>> query(String peID) {
		// TODO Auto-generated method stub
		if (peID == null || "".equalsIgnoreCase(peID)){
			logger.error("ERROR: QUERY: peID null! ");
			return null;
		}
		String sql = "select * from iot_pe_interface_key where pe_id = '"+peID+"'";
		logger.info(sql);
		List<Map<String,Object>> list = jdbc_user_db.queryForList(sql);
		logger.info(list);
		if (list.isEmpty()){
			logger.error("ERROR: QUERY: not found! ");
			return null;
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> query(List<String> peIDList) {
		// TODO Auto-generated method stub
		if (peIDList == null){
			logger.error("ERROR: List null! ");
			return new ArrayList<Map<String,Object>>();
		}
		if (peIDList.size() == 0){
			logger.error("ERROR: List empty! ");
			return new ArrayList<Map<String,Object>>();
		}
		String sql = "select * from iot_pe_interface_key where pe_id in ('";
		Iterator<String> iterator = peIDList.iterator();
		boolean isFirst = true;
		while (iterator.hasNext()){
			String tempString = iterator.next();
			if (isFirst){
				sql = sql + tempString +"'";
				isFirst = false;
			}
			else {
				sql = sql + " , '"+tempString+"'";
			}
		}
		sql = sql + ")";
		logger.info(sql);
		List<Map<String,Object>> list = jdbc_user_db.queryForList(sql);
		logger.info(list);
		System.out.println(list);
		if (list.isEmpty()){
			logger.error("ERROR: QUERY: not found! ");
		}
		return list;
	}

}
