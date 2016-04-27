package com.cetc.iot.servicesystem.service;

public class SessionBean {
	private String id;
	private String veId;
	private String peId;
	
	public SessionBean() {
	}
	
	public SessionBean(String id, String veId, String peId) {
		this.id = id;
		this.veId = veId;
		this.peId = peId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVeId() {
		return veId;
	}
	public void setVeId(String veId) {
		this.veId = veId;
	}
	public String getPeId() {
		return peId;
	}
	public void setPeId(String peId) {
		this.peId = peId;
	}
	
	
}
