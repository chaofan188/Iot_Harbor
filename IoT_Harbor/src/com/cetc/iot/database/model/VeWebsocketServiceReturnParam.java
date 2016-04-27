package com.cetc.iot.database.model;

public class VeWebsocketServiceReturnParam {
	private String returnParam_id;
	private String service_id;
	private String returnParam_type;
	private String returnParam_name;
	private String returnParam_description;
	
	
	
	public VeWebsocketServiceReturnParam(String returnParam_id,
			String service_id, String returnParam_type,
			String returnParam_name, String returnParam_description) {
		super();
		this.returnParam_id = returnParam_id;
		this.service_id = service_id;
		this.returnParam_type = returnParam_type;
		this.returnParam_name = returnParam_name;
		this.returnParam_description = returnParam_description;
	}
	
	public VeWebsocketServiceReturnParam() {
	}

	public String getReturnParam_id() {
		return returnParam_id;
	}
	public void setReturnParam_id(String returnParam_id) {
		this.returnParam_id = returnParam_id;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getReturnParam_type() {
		return returnParam_type;
	}
	public void setReturnParam_type(String returnParam_type) {
		this.returnParam_type = returnParam_type;
	}
	public String getReturnParam_name() {
		return returnParam_name;
	}
	public void setReturnParam_name(String returnParam_name) {
		this.returnParam_name = returnParam_name;
	}
	public String getReturnParam_description() {
		return returnParam_description;
	}
	public void setReturnParam_description(String returnParam_description) {
		this.returnParam_description = returnParam_description;
	}
	
}
