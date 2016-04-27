package com.cetc.iot.servicesystem.pojo;

import com.cetc.iot.servicesystem.service.SessionBean;

import net.sf.json.JSONObject;

public class RequestPojo {
	
	private int contentLength;
	
	private String contentType;
	
	private String remoteAddr;
	
	private String remoteHost;
	
	private String serviceMessage;
	
	private String veid;
	
	private String ctrl;
	
	private String parameterNames;
	
	private JSONObject arg;
	
	private SessionBean session;

	public String getCtrl() {
		return ctrl;
	}

	public void setCtrl(String ctrl) {
		this.ctrl = ctrl;
	}

	public JSONObject getArg() {
		return arg;
	}

	public void setArg(JSONObject arg) {
		this.arg = arg;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getServiceMessage() {
		return serviceMessage;
	}

	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}

	public String getVeid() {
		return veid;
	}

	public void setVeid(String veid) {
		this.veid = veid;
	}

	public String getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(String parameterNames) {
		this.parameterNames = parameterNames;
	}

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}
	
	
}
