package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.ServiceDao;
import com.cetc.iot.database.model.VeService;


@Repository
public class ServiceDaoImpl implements ServiceDao{
	
	private static Logger logger = Logger.getLogger(ServiceDaoImpl.class.getName());
	
	@Autowired
	private JdbcTemplate jdbc_ve_db; //ע�����Դ
	
	/**
	 * ����Serviceʵ��
	 * @param service ����ӵ�Serviceʵ��
	 * @return boolean �������
	 */
	public boolean insert(VeService service){
		String sqlStr = "insert into iot_ve_service (service_ID, service_name, service_type, service_address_soap, service_address_rest, service_data_receive_class, service_ description, tpl_id)" +
				"values(?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = {service.getService_ID(), service.getService_name(), service.getService_type(), service.getService_address_soap(),
				service.getService_address_rest(), service.getService_data_receive_class(), service.getService_description(), service.getTpl_id()};
		logger.info(sqlStr);
		try{
			jdbc_ve_db.update(sqlStr, args);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteAll(){
		String sqlStr = "truncate table iot_ve_service";
		logger.info(sqlStr);
		try{
			jdbc_ve_db.execute(sqlStr);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteByTplId(int tpl_id){
		String sqlStr = "delete from iot_ve_service where tpl_id = ?";
		logger.info(sqlStr);
		try{
			jdbc_ve_db.update(sqlStr, tpl_id);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteByServiceId(String service_ID){
		String sqlStr = "delete from iot_ve_service where service_ID = ?";
		logger.info(sqlStr);
		try{
			jdbc_ve_db.update(sqlStr, service_ID);
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(VeService service){
		String sqlStr  = "update iot_ve_service set service_name = ?, service_type = ?, service_address_soap = ?, service_address_rest = ?," +
				"service_data_receive_class = ?, service_description = ?";
		Object[] args = {service.getService_name(), service.getService_type(), service.getService_address_soap(), service.getService_address_rest(),
				service.getService_data_receive_class(), service.getService_description()};
		logger.info(sqlStr);
		try{
			jdbc_ve_db.update(sqlStr, args);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Map<String, Object>> getAllServices(){
		List<Map<String, Object>> rdao = new ArrayList<Map<String, Object>>();
		String sqlStr = "select * from iot_ve_service";
		logger.info(sqlStr);
		try{
			rdao = jdbc_ve_db.queryForList(sqlStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rdao;
	}
	
	public List<Map<String, Object>> getServiceByTplId(int tpl_id){
		List<Map<String, Object>> rdao = new ArrayList<Map<String, Object>>();
		String sqlStr = "select * from iot_ve_service where tpl_id = ?";
		logger.info(sqlStr);
		try{
			rdao = jdbc_ve_db.queryForList(sqlStr, tpl_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rdao;
	}
	
	public Map<String, Object> getServiceByServiceId(String service_ID){
		Map<String, Object> rdao = new HashMap<String, Object>();
		String sqlStr = "select * from iot_ve_service where service_ID = ?";
		logger.info(sqlStr);
		try{
			rdao = jdbc_ve_db.queryForMap(sqlStr, service_ID);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rdao;
	}
}
