package com.cetc.iot.database.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class LoginLog implements Serializable {  //��¼��־ģ��
	private Integer loginLogID; //��־����id
	private Integer userID;     //����ԱID
	private String userName;    //����Ա����
	private String userIP;      //���һ�ε�¼IP
	private Date loginDate;     //���һ�ε�¼ʱ��
	
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
