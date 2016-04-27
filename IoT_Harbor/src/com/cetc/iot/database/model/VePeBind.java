package com.cetc.iot.database.model;

public class VePeBind {
	String bind_id;
	String identify_id;
	String pe_id;
	String ve_id;
	String service_id;
	String state;
	String bind_type;
	
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}
	public String getBind_type() {
		return bind_type;
	}
	public void setBind_type(String bind_type) {
		this.bind_type = bind_type;
	}
	public String getIdentify_id() {
		return identify_id;
	}
	public void setIdentify_id(String identify_id) {
		this.identify_id = identify_id;
	}
	public String getPe_id() {
		return pe_id;
	}
	public String getState() {
		return state;
	}
	public String getVe_id() {
		return ve_id;
	}
	public void setPe_id(String pe_id) {
		this.pe_id = pe_id;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setVe_id(String ve_id) {
		this.ve_id = ve_id;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	
}
