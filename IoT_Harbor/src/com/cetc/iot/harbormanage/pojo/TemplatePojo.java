package com.cetc.iot.harbormanage.pojo;

public class TemplatePojo {

	String template_name;
	String ID;
	String producer;
	String type;
	String model;
	public String getID() {
		return ID;
	}
	public String getModel() {
		return model;
	}
	public String getProducer() {
		return producer;
	}
	public String getTemplate_name() {
		return template_name;
	}
	public String getType() {
		return type;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	public void setType(String type) {
		this.type = type;
	}
}
