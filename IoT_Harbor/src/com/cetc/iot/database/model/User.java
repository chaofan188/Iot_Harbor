package com.cetc.iot.database.model;

public class User {
	private String userName;
	private String userPassword;
	private String userRealName;
	private String email;
	private String tel;
	private String address;
	private String state;
	private String registerTime;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getRegisterTime(){
		return registerTime;
	}
	public void setRegisterTime(String registerTime){
		this.registerTime = registerTime;
	}

}
