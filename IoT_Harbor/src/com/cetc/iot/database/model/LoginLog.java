package com.cetc.iot.database.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class LoginLog implements Serializable {  //登录日志模型
	private Integer loginLogID; //日志主键id
	private Integer userID;     //管理员ID
	private String userName;    //管理员名称
	private String userIP;      //最后一次登录IP
	private Date loginDate;     //最后一次登录时间
	
	public Integer getLoginLogID() {
		return loginLogID;
	}
	public void setLoginLogID(Integer loginLogID) {
		this.loginLogID = loginLogID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
