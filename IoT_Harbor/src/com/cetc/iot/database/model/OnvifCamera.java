package com.cetc.iot.database.model;

public class OnvifCamera {

	String pe_id;
	String ipv4;
	String interface_id;
	String username;
	String password;

	public String getPe_id() {
		return pe_id;
	}

	public void setPe_id(String pe_id) {
		this.pe_id = pe_id;
	}

	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getInterface_id() {
		return interface_id;
	}

	public void setInterface_id(String interface_id) {
		this.interface_id = interface_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
