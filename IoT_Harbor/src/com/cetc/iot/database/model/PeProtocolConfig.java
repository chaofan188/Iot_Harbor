package com.cetc.iot.database.model;

public class PeProtocolConfig {
	private String peId;
	private String basePassword;
	private String upPassword;
	private String downPassword;
	private int loginTimes;
	public String getPeId() {
		return peId;
	}
	public void setPeId(String peId) {
		this.peId = peId;
	}
	public String getBasePassword() {
		return basePassword;
	}
	public void setBasePassword(String basePassword) {
		this.basePassword = basePassword;
	}
	public String getUpPassword() {
		return upPassword;
	}
	public void setUpPassword(String upPassword) {
		this.upPassword = upPassword;
	}
	public String getDownPassword() {
		return downPassword;
	}
	public void setDownPassword(String downPassword) {
		this.downPassword = downPassword;
	}
	public int getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}
	
}
