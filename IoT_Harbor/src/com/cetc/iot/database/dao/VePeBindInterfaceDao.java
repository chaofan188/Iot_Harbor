package com.cetc.iot.database.dao;

import java.util.List;
import java.util.Map;

import com.cetc.iot.database.model.VePeBindInterface;

/**
 * @ClassName: VePeBindInterfaceDao 
 * @Description: ve绑定pe，所对应的interface的操作方法
 * @author ZhangYong
 * @date 2016年4月20日 下午4:55:11
 */
public interface VePeBindInterfaceDao {
	
	/**
	 * @Title: add 
	 * @Description: 向表iot_vetemplate_petemplate_bind_interface表添加一条记录
	 * @param @param bindInterface 
	 * @return String  
	 * @throws
	 */
	public String add(VePeBindInterface bindInterface);
	
	/**
	 * @Title: query 
	 * @Description: 根据bindInterface查询记录
	 * @param @param bindInterface
	 * @param @return 
	 * @return List<Map<String, Object>>   
	 * @throws
	 */
	public List<Map<String, Object>> query(VePeBindInterface bindInterface);
	
	/**
	 * @Title: queryPeList 
	 * @Description: 根据veId,serviceId,petemplateId查询pe，涉及到表iot_ve_pe_bind和iot_pe的子查询
	 * @param @param veId
	 * @param @param serviceId
	 * @param @param peTemlapteId
	 * @param @return 
	 * @return List<Map<String,Object>>  
	 * @throws
	 */
	public List<Map<String, Object>> queryPeList(String veId,String serviceId,String peTemlapteId);
	
}
