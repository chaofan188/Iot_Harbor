package com.cetc.iot.harbormanage.pojo;

public class ParamPojo {

	String key;
	String type;
    String description;
	//String encode;
	String option;
	String max_val;
	String min_val;
	String pe_unit;//新添加 等会处理
	String pe_String;//新添加 等会处理
	String pe_Boolean;//新添加 等会处理
    public String getPe_unit() {
		return pe_unit;
	}
	public void setPe_unit(String pe_unit) {
		this.pe_unit = pe_unit;
	}
	public String getPe_String() {
		return pe_String;
	}
	public void setPe_String(String pe_String) {
		this.pe_String = pe_String;
	}
	public String getPe_Boolean() {
		return pe_Boolean;
	}
	public void setPe_Boolean(String pe_Boolean) {
		this.pe_Boolean = pe_Boolean;
	}

	
	public String getDescription() {
		return description;
	}
	/*public String getEncode() {
		return encode;
	}*/
	public String getKey() {
		return key;
	}
	public String getMax_val() {
		return max_val;
	}
	public String getMin_val() {
		return min_val;
	}
	public String getOption() {
		return option;
	}
	public String getType() {
		return type;
	}
	public void setDescription(String description) {
		this.description = description;
	}
/*	public void setEncode(String encode) {
		this.encode = encode;
	}*/
	public void setKey(String key) {
		this.key = key;
	}
	public void setMax_val(String max_val) {
		this.max_val = max_val;
	}
	public void setMin_val(String min_val) {
		this.min_val = min_val;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public void setType(String type) {
		this.type = type;
	}
}
