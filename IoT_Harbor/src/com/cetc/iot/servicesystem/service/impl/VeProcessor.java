package com.cetc.iot.servicesystem.service.impl;

import java.util.Map;

import net.sf.json.JSONObject;

import com.cetc.iot.servicesystem.service.ServiceContainerBase;
import com.cetc.iot.servicesystem.service.ServiceFilter;
import com.cetc.iot.servicesystem.service.ServiceProcessor;
import com.cetc.iot.servicesystem.service.ServiceWrapper;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.servicesystem.util.MyException;


public class VeProcessor extends ServiceContainerBase implements ServiceProcessor {
	private ServiceFilter serviceFilter;
	private ServiceWrapper serviceWrapper;
	private Map<String,ServiceFilter> ServiceFilterMap;
	
	public VeProcessor(){}
	public VeProcessor(ServiceFilter serviceFilter,ServiceWrapper serviceWrapper){
		/*serviceFilter = new VeMessageFilter();
		serviceWrapper = new VeMessageWrapper();*/
		this.serviceFilter=serviceFilter;
		this.serviceWrapper=serviceWrapper;
	}
	
	public void setServiceFilterMap(Map<String,ServiceFilter> ServiceFilterMap ){
		this.ServiceFilterMap = ServiceFilterMap;
	}
	
	public void process(String message) throws MyException{
		
		//校验MAP若存在，依次执行进行校验
		if(ServiceFilterMap!=null){
		for(int i = 0; i <ServiceFilterMap.size(); i++){
			ServiceFilter serviceFilter =ServiceFilterMap.get(String.valueOf(i));
			System.out.println("I="+i);
			serviceFilter.filter(message);
		   }
		}
		JSONObject jsonMessage=JSONObject.fromObject(message);
		if("1".equals(jsonMessage.get("is_atom"))){
			//原子VE执行
		    AtomVeExecute ve = new AtomVeExecute(jsonMessage);
		    ve.doService();
		} else{
			//this.serviceFilter.filter();
			//复合VE执行
			serviceWrapper = (ServiceWrapper) GetBean.getBean("serviceWrapper");
			this.serviceWrapper.wrap(message);
		}

	}
	/**
	 * @return the serviceFilter
	 */
	public ServiceFilter getServiceFilter() {
		return serviceFilter;
	}

	/**
	 * @param serviceFilter the serviceFilter to set
	 */
	public void setServiceFilter(ServiceFilter serviceFilter) {
		this.serviceFilter = serviceFilter;
	}

	/**
	 * @return the serviceWrapper
	 */
	public ServiceWrapper getServiceWrapper() {
		return serviceWrapper;
	}

	/**
	 * @param serviceWrapper the serviceWrapper to set
	 */
	public void setServiceWrapper(ServiceWrapper serviceWrapper) {
		this.serviceWrapper = serviceWrapper;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
