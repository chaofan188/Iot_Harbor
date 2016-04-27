package com.cetc.iot.database.model;

public class EzvizCamera {
	public String getPe_id() {
		return pe_id;
	}
	public void setPe_id(String pe_id) {
		this.pe_id = pe_id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getCameraname() {
		return cameraname;
	}
	public void setCameraname(String cameraname) {
		this.cameraname = cameraname;
	}
	public String getM3u8Url() {
		return m3u8Url;
	}
	public void setM3u8Url(String m3u8Url) {
		this.m3u8Url = m3u8Url;
	}
	public String getInterface_id() {
		return interface_id;
	}
	public void setInterface_id(String interface_id) {
		this.interface_id = interface_id;
	}
	private String pe_id;
	private String key;
	private String secret;
	private String cameraname;
	private String m3u8Url;
	private String interface_id;
	
}
