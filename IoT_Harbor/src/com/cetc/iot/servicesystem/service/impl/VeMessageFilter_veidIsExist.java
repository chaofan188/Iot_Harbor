package com.cetc.iot.servicesystem.service.impl;


import net.sf.json.JSONObject;

import com.cetc.iot.servicesystem.service.ServiceContainerBase;
import com.cetc.iot.servicesystem.service.ServiceFilter;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.servicesystem.util.MyException;

/**
 * @author nci_my
 *
 */
public class VeMessageFilter_veidIsExist extends ServiceContainerBase implements ServiceFilter {

	public void filter(String message)throws MyException{
		//veid判空
		JSONObject jsonMessage=JSONObject.fromObject(message);
		String veid=(String) jsonMessage.get("veId");
		VEService vs = (VEService) GetBean.getBean("veService");
		if(vs.getVeByVeId(veid)==null){
			throw new MyException("veid does not exist");
		}
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
