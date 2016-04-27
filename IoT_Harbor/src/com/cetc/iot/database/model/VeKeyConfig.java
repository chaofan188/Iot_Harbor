package com.cetc.iot.database.model;

import java.util.Date;

public class VeKeyConfig {

	private String ve_id;
	private	String service_id;
	private	Date change_time;
	private	String base_key;
	private String key_state;
	public String getVe_id() {
		return ve_id;
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
	public Date getChange_time() {
		return change_time;
	}
	public void setChange_time(Date change_time) {
		this.change_time = change_time;
	}
	public String getBase_key() {
		return base_key;
	}
	public void setBase_key(String base_key) {
		this.base_key = base_key;
	}
	public String getKey_state() {
		return key_state;
	}
	public void setKey_state(String key_state) {
		this.key_state = key_state;
	}
	
}
