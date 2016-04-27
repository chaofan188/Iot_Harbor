package com.cetc.iot.database.model;

public class VeWebsocketService {
	private String  service_id;
	private	String	template_id;
	private	String	service_name;
	private	String	service_description;
	
	
	public VeWebsocketService(String service_id, String template_id,
			String service_name, String service_description) {
		super();
		this.service_id = service_id;
		this.template_id = template_id;
		this.service_name = service_name;
		this.service_description = service_description;
		
	}
	public VeWebsocketService() {
		// TODO Auto-generated constructor stub
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getService_description() {
		return service_description;
	}
	public void setService_description(String service_description) {
		this.service_description = service_description;
	}
	
}
