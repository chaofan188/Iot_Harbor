package com.cetc.iot.database.model;

import java.util.Date;

public class VETemplate { // VEģ��

	private String template_id;
	private String tpl_id;
	private String tpl_name;
	private String tpl_state;
	private String tpl_type_id;
	private Date tpl_enroll_time;
	private String tpl_description;
	private String tpl_owner;
	private String tpl_developer;
	private String template_openness;
	private String tpl_icon;
	private String tpl_display;
	private String tpl_version;
	private String tpl_classpath;
	private String tpl_alias;
	

	public VETemplate() {

	}

	public VETemplate(String template_id, String tpl_id, String tpl_name, String tpl_state, String tpl_type_id,
			Date tpl_enroll_time, String tpl_description, String tpl_owner,String tpl_developer,String tpl_display,
			String template_openness,String tpl_icon,String tpl_version,String tpl_classpath,String tpl_alias) {
		super();
		this.tpl_id = tpl_id;
		this.tpl_name = tpl_name;
		this.tpl_state = tpl_state;
		this.tpl_type_id = tpl_type_id;
		this.tpl_enroll_time = tpl_enroll_time;
		this.tpl_description = tpl_description;
		this.tpl_owner = tpl_owner;
		this.tpl_icon = tpl_icon;
		this.template_openness = template_openness;
		this.tpl_display = tpl_display;
		this.template_id = template_id;
		this.tpl_developer = tpl_developer;
		this.tpl_version = tpl_version;
		this.tpl_classpath = tpl_classpath;
		this.tpl_alias = tpl_alias;
	}

	public String getTpl_display() {
		return tpl_display;
	}

	public void setTpl_display(String tpl_display) {
		this.tpl_display = tpl_display;
	}

	public String getTpl_name() {
		return tpl_name;
	}

	public String getTemplate_openness() {
		return template_openness;
	}

	public void setTemplate_openness(String template_openness) {
		this.template_openness = template_openness;
	}

	public void setTpl_name(String tpl_name) {
		this.tpl_name = tpl_name;
	}

	public String getTpl_state() {
		return tpl_state;
	}

	public void setTpl_state(String tpl_state) {
		this.tpl_state = tpl_state;
	}

	public String getTpl_type_id() {
		return tpl_type_id;
	}

	public void setTpl_type_id(String tpl_type_id) {
		this.tpl_type_id = tpl_type_id;
	}

	public Date getTpl_enroll_time() {
		return tpl_enroll_time;
	}

	public void setTpl_enroll_time(Date tpl_enroll_time) {
		this.tpl_enroll_time = tpl_enroll_time;
	}

	public String getTpl_description() {
		return tpl_description;
	}

	public void setTpl_description(String tpl_description) {
		this.tpl_description = tpl_description;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTpl_id() {
		return tpl_id;
	}

	public void setTpl_id(String tpl_id) {
		this.tpl_id = tpl_id;
	}

	public String getTpl_owner() {
		return tpl_owner;
	}

	public void setTpl_owner(String tpl_owner) {
		this.tpl_owner = tpl_owner;
	}

	public String getTpl_developer() {
		return tpl_developer;
	}

	public void setTpl_developer(String tpl_developer) {
		this.tpl_developer = tpl_developer;
	}

	public String getTpl_version() {
		return tpl_version;
	}

	public void setTpl_version(String tpl_version) {
		this.tpl_version = tpl_version;
	}

	public String getTpl_classpath() {
		return tpl_classpath;
	}

	public void setTpl_classpath(String tpl_classpath) {
		this.tpl_classpath = tpl_classpath;
	}

	public String getTpl_icon() {
		return tpl_icon;
	}

	public void setTpl_icon(String tpl_icon) {
		this.tpl_icon = tpl_icon;
	}

	public String getTpl_alias() {
		return tpl_alias;
	}

	public void setTpl_alias(String tpl_alias) {
		this.tpl_alias = tpl_alias;
	}

	
}
