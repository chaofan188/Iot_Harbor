package com.cetc.iot.servicesystem.authority.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: VeInterfaceRelation 
 * @Description: 主要描述了VE，PE，Service，interface之间的关系
 * @author ZhangYong
 * @date 2016年4月17日 上午10:47:59
 */
public class VeInterfaceRelation {
	private String veId;
	private String isAtomic;    		//标记是否为原子ve： 1为原子，0为复合
	
	/**
	 * key:serviceId
	 * value:map
	 * 			key:peId
	 * 			value:interfaceId1,interfaceId2,...	  
	 */
	Map<String, Map<String, String>> serviceIdsMap = new HashMap<String, Map<String, String>>();
	public String getVeId() {
		return veId;
	}
	public void setVeId(String veId) {
		this.veId = veId;
	}
	public String getIsAtomic() {
		return isAtomic;
	}
	public void setIsAtomic(String isAtomic) {
		this.isAtomic = isAtomic;
	}
	public Map<String, Map<String, String>> getServiceIdsMap() {
		return serviceIdsMap;
	}
	public void setServiceIdsMap(Map<String, Map<String, String>> serviceIdsMap) {
		this.serviceIdsMap = serviceIdsMap;
	}
	
	
}
