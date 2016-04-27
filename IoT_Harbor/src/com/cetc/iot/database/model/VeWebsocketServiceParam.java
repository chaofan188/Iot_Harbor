package com.cetc.iot.database.model;

public class VeWebsocketServiceParam {
	private String param_id;
	private String service_id;
	private String param_type;
	private String param_name;
	private String param_value;
	
	
	
	public VeWebsocketServiceParam(String param_id, String service_id,
			String param_type, String param_name,String param_value) {
		super();
		this.service_id = service_id;
		this.param_type = param_type;
		this.param_name = param_name;
		this.param_id = param_id;
		this.param_value = param_value;
	}
	public VeWebsocketServiceParam() {
		// TODO Auto-generated constructor stub
	}
	
	public String getParam_id() {
		return param_id;
	}
	public void setParam_id(String param_id) {
		this.param_id = param_id;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getParam_type() {
		return param_type;
	}
	public void setParam_type(String param_type) {
		this.param_type = param_type;
	}
	public String getParam_name() {
		return param_name;
	}
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	public String getParam_value() {
		return param_value;
	}
	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}
	
	
}
