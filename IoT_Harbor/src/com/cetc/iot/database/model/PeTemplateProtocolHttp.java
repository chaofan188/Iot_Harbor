package com.cetc.iot.database.model;

public class PeTemplateProtocolHttp {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getInterface_id() {
		return interface_id;
	}
	public void setInterface_id(String interface_id) {
		this.interface_id = interface_id;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	int id;
	String rule;
	String interface_id;
	String template_id;
}
