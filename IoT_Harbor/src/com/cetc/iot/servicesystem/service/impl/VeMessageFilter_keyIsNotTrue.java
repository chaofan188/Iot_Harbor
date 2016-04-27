package com.cetc.iot.servicesystem.service.impl;

import net.sf.json.JSONObject;

import com.cetc.iot.database.model.VeKeyConfig;
import com.cetc.iot.servicesystem.service.ServiceContainerBase;
import com.cetc.iot.servicesystem.service.ServiceFilter;
import com.cetc.iot.servicesystem.service.VeKeyConfigService;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.servicesystem.util.MyException;

public class VeMessageFilter_keyIsNotTrue extends ServiceContainerBase implements ServiceFilter {

	public void filter(String message)throws MyException{
		//校验KEY是否正确
		JSONObject jsonMessage=JSONObject.fromObject(message);
		String veid=(String) jsonMessage.get("veId");
		String veServiceId = (String) jsonMessage.get("serviceId");
		String key=(String) jsonMessage.get("key");
		VeKeyConfigService vkcs = (VeKeyConfigService) GetBean.getBean("veKeyConfigService");
		VeKeyConfig veKeyConfig = new VeKeyConfig();
		veKeyConfig.setVe_id(veid);
		veKeyConfig.setService_id(veServiceId);
		String keyState = vkcs.query(veKeyConfig, 0, 1).get(0).getKey_state();
		String BaseKey = vkcs.query(veKeyConfig, 0, 1).get(0).getBase_key();
		if("0".equals(keyState)&&!key.equals(BaseKey)){
			throw new MyException("key is not match to your ve");
		}
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
