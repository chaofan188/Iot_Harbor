package com.cetc.iot.servicesystem.service.impl;

import net.sf.json.JSONObject;

import com.cetc.iot.database.model.VE;
import com.cetc.iot.servicesystem.service.ServiceFilter;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.servicesystem.util.MyException;

public class VeMessageFilter_checkVeState implements ServiceFilter {

	@Override
	public void filter(String message) throws MyException {

		//check ve can be used or not
		JSONObject jsonMessage=JSONObject.fromObject(message);
		String veid=(String) jsonMessage.get("veId");
		VEService vs = (VEService) GetBean.getBean("veService");
		VE ve = vs.getVeByVeId(veid);
		String state = ve.getVe_state();
		if(!state.equals("001")){
			throw new MyException("Your ve can not be used for its state is not accessible");
		}
	
	}

}
