package com.cetc.iot.database.model;

public class PeTemplateAttribute {
	
	private String attributeId;//属性ID
	
	private String attributeKey;//属性的键
	
	private String attributeValue;//属性的值
	
	private String attributeUnit;//属性值单位
	
	private String templateId;//所属pe模板ID

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeKey() {
		return attributeKey;
	}

	public void setAttributeKey(String attributeKey) {
		this.attributeKey = attributeKey;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getAttributeUnit() {
		return attributeUnit;
	}

	public void setAttributeUnit(String attributeUnit) {
		this.attributeUnit = attributeUnit;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
	
}
