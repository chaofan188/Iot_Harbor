package com.cetc.iot.database.model;

public class PeTemplateParam {
	private int template_param_id;
	private String template_id;
	private String key;
	private int type;
	private String description;
	private String value_min;
	private String value_max;
	private String unit;
	private String option;
	public String getDescription() {
		return description;
	}
	public String getKey() {
		return key;
	}
	public String getOption() {
		return option;
	}
	public int getParam_id() {
		return template_param_id;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public int getType() {
		return type;
	}
	public String getUnit() {
		return unit;
	}
	public String getValue_max() {
		return value_max;
	}
	public String getValue_min() {
		return value_min;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public void setParam_id(int param_id) {
		this.template_param_id = param_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setValue_max(String value_max) {
		this.value_max = value_max;
	}
	public void setValue_min(String value_min) {
		this.value_min = value_min;
	}
	
	
}
