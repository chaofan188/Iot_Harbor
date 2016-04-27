package com.cetc.iot.servicesystem.service.impl;


import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.servicesystem.service.ServiceContainerBase;
import com.cetc.iot.servicesystem.service.ServiceRequest;
import com.cetc.iot.servicesystem.service.ServiceResponse;
import com.cetc.iot.servicesystem.service.ServiceWrapper;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;
import com.cetc.iot.servicesystem.service.VeClassLoader;
import com.cetc.iot.servicesystem.service.VeObject;
import com.cetc.iot.servicesystem.service.VePeBindService;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.servicesystem.util.VeUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


/**
 * @author nci_my
 * VE消息处理模块
 */
@Service("serviceWrapper")
public class VeMessageWrapper extends ServiceContainerBase implements ServiceWrapper {
	private static Logger logger = Logger.getLogger(VeMessageWrapper.class.getName());
	@Autowired
	private VEWebsocketServiceService  vews; 
	
	@Autowired
	private VePeBindService vpbs;

	private ServiceRequest req;

	private ServiceResponse resp;

	public VeMessageWrapper(){

	}

	public void wrap(String message){
		JSONObject jsonMessage=JSONObject.fromObject(message);
		
		//获取相应关键参数
		String veId = jsonMessage.getString("veId");
		String serviceId = jsonMessage.getString("serviceId");
		JSONObject param = (JSONObject) jsonMessage.getJSONObject("param");
		//获取serviceName
		VeWebsocketService veWebsocketService = new VeWebsocketService();
		veWebsocketService.setService_id(serviceId);
		String ctrl = (String) vews.query(veWebsocketService, 0, 1).get(0).getService_name();
		
		VePeBind vePeBind = new VePeBind();
		vePeBind.setVe_id(veId);
		vePeBind.setService_id(serviceId);
		
		//获取VE、服务以及PE的绑定关系
		List<VePeBind> list1 = vpbs.query(vePeBind, 0, 200);
		
		logger.info("~~~~: "+list1.size());
		
		if(list1.size()>1){
			//多个绑定关系则遍历形成多值Map
			Multimap<String, String> multiMap = ArrayListMultimap.create();
			
			for(int i=0;i<list1.size();i++){
				multiMap.put(list1.get(i).getIdentify_id(), list1.get(i).getPe_id());
			}
			logger.info("=====: "+multiMap.toString());
			//遍历多值Map形成param
			Set<String> keys = multiMap.keySet();
			for(String key:keys)
			{
				StringBuffer peIds = new StringBuffer();
				Collection<String> values = multiMap.get(key);
			    for(String value:values)
			    {
			    		value = value + ",";
			    		peIds.append(value);
			    }
			    param.accumulate(key, peIds.substring(0, peIds.length()-1));
			}
			
		}else {//单一绑定直接形成参数组
			param.accumulate(list1.get(0).getIdentify_id(), list1.get(0).getPe_id());
		}
		
		//将新组成的参数组param写回message中
		jsonMessage.put("param", param);
		message = jsonMessage.toString();
		
		//获取VE对象的执行文件
		VeObject veObject = null;  
		String actualClass = VeUtil.getTplClassName(veId);
		VeClassLoader loader = super.getLoader();
		Class<?> classClass = null;  
		if (loader != null) {  
			logger.info("Using classLoader.loadClass! ");
			try {
				classClass = loader.loadClass(actualClass);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {  
			logger.info("Using forName! ");
			try {
				classClass = Class.forName(actualClass);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		try {
			veObject = (VeObject)classClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req = (ServiceRequest) GetBean.getBean("serviceRequest");
		resp = (ServiceResponse) GetBean.getBean("serviceResponse");
		req.setServiceMessage(message);
		req.setCtrl(ctrl);
		veObject.init(veId);
		veObject.service(req, resp);
		resp.setResult(0);
	}
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
