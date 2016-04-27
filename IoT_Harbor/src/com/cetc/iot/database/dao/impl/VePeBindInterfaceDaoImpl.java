package com.cetc.iot.database.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.VePeBindInterfaceDao;
import com.cetc.iot.database.model.VePeBindInterface;

@Repository
public class VePeBindInterfaceDaoImpl implements VePeBindInterfaceDao {
	private static Logger logger = Logger
			.getLogger(VePeBindInterfaceDaoImpl.class.getName());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String sql;
	@Override
	public String add(VePeBindInterface bindInterface) {
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_vetemplate_petemplate_bind_interface(";
		if(bindInterface.getBindId() != null){
			sqlStr1 += "bind_id ";
			sqlStr2 += "'"+bindInterface.getBindId()+"'";
		}
		if(bindInterface.getInterfaceId()!=null){
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if(lastChar=='('){ 
				sqlStr1 += "interface_id";
				sqlStr2 += "'"+bindInterface.getInterfaceId()+"'";
			}else{
				sqlStr1 += ",interface_id";
				sqlStr2 += ",'"+bindInterface.getInterfaceId()+"'";
			}
		}
		sqlStr1 += ") values (";
		sqlStr2 += ")";
		sqlStr = sqlStr1 + sqlStr2;
		logger.info(sqlStr);
		try {
			jdbcTemplate.update(sqlStr);
		} catch (DataAccessException e) {
			return "fail";
		}
		return "success";
	}

	@Override
	public List<Map<String, Object>>  query(VePeBindInterface bindInterface) {
		String sqlStr1 = "";
		sqlStr1 = "select * from iot_vetemplate_petemplate_bind_interface where 1=1 ";
		if(bindInterface.getBindId() != null){
			sqlStr1 += " and bind_id ="+"'"+bindInterface.getBindId()+"'";
		}
		if(bindInterface.getInterfaceId()!=null){
			//String lastString = sqlStr1.substring(sqlStr1.length() - 5);
			
				sqlStr1 += " and interface_id = "+"'"+bindInterface.getInterfaceId()+"'";
		}
		logger.info(sqlStr1);
		List<Map<String, Object>> list  = jdbcTemplate.queryForList(sqlStr1);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryPeList(String veId, String serviceId,
			String peTemlapteId) {
		String sqlStr = "select * from iot_ve_pe_bind where 1=1";
		String subSqlStr = "select pe_id from iot_pe ";
		if(veId!=""){
			sqlStr += " and ve_id = "+"'"+veId+"'";
		}
		if(serviceId!=""){
			sqlStr += " and service_id =" + "'"+serviceId+"'";
		}
		if(peTemlapteId!=""){
			subSqlStr += "where template_id = " +"'"+peTemlapteId+"'";
			sqlStr += " and pe_id in ( "+subSqlStr +")";
		}
		logger.info(sqlStr);
		return jdbcTemplate.queryForList(sqlStr);
		
	}

}
