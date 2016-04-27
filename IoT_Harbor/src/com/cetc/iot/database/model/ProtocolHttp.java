package com.cetc.iot.database.model;

public class ProtocolHttp {
	private int id;
	private String url;
	private String rule;
	private int method;
	private int http_template_id;
	private String pe_id;
	private String interface_id;

	public String getInterface_id() {
		return interface_id;
	}

	public void setInterface_id(String interface_id) {
		this.interface_id = interface_id;
	}

	public String getPe_id() {
		return pe_id;
	}

	public void setPe_id(String pe_id) {
		this.pe_id = pe_id;
	}

	public int getHttp_template_id() {
		return http_template_id;
	}

	public void setHttp_template_id(int http_template_id) {
		this.http_template_id = http_template_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

}
