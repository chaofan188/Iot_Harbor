package com.cetc.iot.database.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UserAdmin implements Serializable { //����Աģ��
	private int user_ID;         //����ԱID
	private String user_name;    //����Ա����
	private String user_pass;    //����Ա����
	private Date user_lastvisit; //���һ�ε�¼ʱ��
	private String user_lastip;  //���һ�ε�¼IP
	
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getUser_lastip() {
		return user_lastip;
	}
	public void setUser_lastip(String user_lastip) {
		this.user_lastip = user_lastip;
	}
	public Date getUser_lastvisit() {
		return user_lastvisit;
	}
	public void setUser_lastvisit(Date user_lastvisit) {
		this.user_lastvisit = user_lastvisit;
	}
}
