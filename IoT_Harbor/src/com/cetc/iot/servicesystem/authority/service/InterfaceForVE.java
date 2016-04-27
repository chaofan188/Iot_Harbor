package com.cetc.iot.servicesystem.authority.service;

import com.cetc.iot.servicesystem.authority.model.VeInterfaceRelation;

/**
 * @ClassName: InterfaceForVE 
 * @Description: 该接口主要用于VE和所使用PE模板中interface接口的关系获取
 * @author ZhangYong
 * @date 2016年4月17日 上午10:41:13
 */
public interface InterfaceForVE {
	
	/**
	 * 
	 * @Title: getVeInterfaceRelationByVeID 
	 * @Description: 通过veID，查找VE所对应的VeInterfaceRelation对象
	 * @param @param veID
	 * @param @return 
	 * @return VeInterfaceRelation  ,如果返回null，说明传入得veId不存在
	 * @throws
	 */
	public VeInterfaceRelation getVeInterfaceRelationByVeID(String veId);
}
