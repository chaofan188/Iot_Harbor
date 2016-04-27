package com.cetc.iot.harbormanage.pojo;

public class PEModelPojo {

	public String getModel_id() {
		return model_id;
	}
	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	public TypePojo getType() {
		return type;
	}
	public void setType(TypePojo type) {
		this.type = type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public InterfacePojo[] getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(InterfacePojo[] interfaces) {
		this.interfaces = interfaces;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public AttributePojo[] getAttribute() {
		return attributes;
	}
	public void setAttribute(AttributePojo[] attributes) {
		this.attributes = attributes;
	}
	String model_id;
	TypePojo type;
	String model;
	String description;
	String pic_url;
	InterfacePojo[] interfaces;
    String name;//新添加 等会处理

	AttributePojo[] attributes;

}
