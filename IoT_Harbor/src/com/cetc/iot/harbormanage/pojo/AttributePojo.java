package com.cetc.iot.harbormanage.pojo;

public class AttributePojo {

	private String key;
	private String type;
	private String value_min;
	private String value_max;
	private String unit;
	private String option;
	private String description;
	public String getDescription() {
		return description;
	}
	public String getKey() {
		return key;
	}
	public String getOption() {
		return option;
	}
	public String getType() {
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
	public void setType(String type) {
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
