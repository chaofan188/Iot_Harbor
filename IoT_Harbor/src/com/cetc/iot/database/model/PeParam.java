package com.cetc.iot.database.model;

public class PeParam {
	private int pe_param_id;
	private String pe_id;
	private String key;
	private String content;
	private String template_param_id;
	public int getPe_param_id() {
		return pe_param_id;
	}
	public void setPe_param_id(int pe_param_id) {
		this.pe_param_id = pe_param_id;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTemplate_param_id() {
		return template_param_id;
	}
	public void setTemplate_param_id(String template_param_id) {
		this.template_param_id = template_param_id;
	}
}
