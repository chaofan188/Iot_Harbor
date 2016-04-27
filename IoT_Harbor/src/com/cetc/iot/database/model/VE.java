package com.cetc.iot.database.model;

import java.io.Serializable;
import java.util.Date;

public class VE implements Serializable{  //ʵ��VEģ��
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ve_id;
	private String ve_name;
	private String ve_key;
	private String ve_state;
	private String ve_privacy;
	private Date ve_init_time;
	private String ve_description;
	private String ve_creator_id;
	private String template_id;
	private String ve_atom;
	private VeGeoLocation geo;
	public VE(){
		
	}
	
	public VE(String ve_id, String ve_name, String ve_key, String ve_state,
			String ve_privacy,  Date ve_init_time,
			String ve_description, String ve_creator_id, 
			String template_id,String ve_atom,VeGeoLocation geo) {
		super();
		this.ve_id = ve_id;
		this.ve_name = ve_name;
		this.ve_key = ve_key;
		this.ve_state = ve_state;
		this.ve_privacy = ve_privacy;
		this.ve_init_time = ve_init_time;
		this.ve_description = ve_description;
		this.ve_creator_id = ve_creator_id;
		this.template_id = template_id;
		this.ve_atom = ve_atom;
		this.geo=geo;
	}

	
	public String getVe_id() {
		return ve_id;
	}
	public void setVe_id(String ve_id) {
		this.ve_id = ve_id;
	}
	public String getVe_name() {
		return ve_name;
	}
	public void setVe_name(String ve_name) {
		this.ve_name = ve_name;
	}
	public String getVe_key() {
		return ve_key;
	}
	public void setVe_key(String ve_key) {
		this.ve_key = ve_key;
	}
	public String getVe_state() {
		return ve_state;
	}
	public void setVe_state(String ve_state) {
		this.ve_state = ve_state;
	}
	public String getVe_privacy() {
		return ve_privacy;
	}
	public void setVe_privacy(String ve_privacy) {
		this.ve_privacy = ve_privacy;
	}
	public Date getVe_init_time() {
		return ve_init_time;
	}

	public void setVe_init_time(Date ve_init_time) {
		this.ve_init_time = ve_init_time;
	}

	public String getVe_description() {
		return ve_description;
	}
	public void setVe_description(String ve_description) {
		this.ve_description = ve_description;
	}
	public String getVe_creator_id() {
		return ve_creator_id;
	}
	public void setVe_creator_id(String ve_creator_id) {
		this.ve_creator_id = ve_creator_id;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	
	public String getVe_atom() {
		return ve_atom;
	}

	public void setVe_atom(String ve_atom) {
		this.ve_atom = ve_atom;
	}

	public VeGeoLocation getGeo() {
		return geo;
	}

	public void setGeo(VeGeoLocation geo) {
		this.geo = geo;
	}
	
}
