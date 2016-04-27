package com.cetc.iot.servicesystem.service.impl;

import org.springframework.stereotype.Service;

import com.cetc.iot.servicesystem.pojo.ResponsePojo;
import com.cetc.iot.servicesystem.service.ServiceResponse;

@Service("serviceResponse")
public class ServiceResponseWrappe implements ServiceResponse {

	private ResponsePojo resp = new ResponsePojo();
	
	@Override
	public void setContentLength(int length) {
		// TODO Auto-generated method stub
		resp.setContentLength(length);
	}

	@Override
	public void setContentType(String type) {
		// TODO Auto-generated method stub
		resp.setContentType(type);
	}

	@Override
	public void setVeid(String veid) {
		// TODO Auto-generated method stub
		resp.setVeId(veid);
	}

	@Override
	public void setParameter(String name, String value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setResponseMessage(String message) {
		
		resp.setResponseMessage(message);
	}
	
	@Override
	public String getResponseMessage(){
		return resp.getResponseMessage();
	}

	@Override
	public void setResult(int sc) {
		if(sc==0){
			resp.setResult("success");
		}else{
			resp.setResult("fail");
		}
	}

	@Override
	public String getResult() {
		
		return resp.getResult();
	}

}
