package com.cetc.iot.database.model;

import java.util.ArrayList;
import java.util.List;

public class VePeBindInformation {
	private String	bind_id;
	private String  identify_id;
	private String  ve_id;
	private String  state;
	private String  bind_type;
	private List<String>  pe_id_List=new ArrayList<String>();
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}
	public String getIdentify_id() {
		return identify_id;
	}
	public void setIdentify_id(String identify_id) {
		this.identify_id = identify_id;
	}
	public String getVe_id() {
		return ve_id;
	}
	public void setVe_id(String ve_id) {
		this.ve_id = ve_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBind_type() {
		return bind_type;
	}
	public void setBind_type(String bind_type) {
		this.bind_type = bind_type;
	}
	public List<String> getPe_id_List() {
		return pe_id_List;
	}
	public void setPe_id_List(List<String> pe_id_List) {
		this.pe_id_List = pe_id_List;
	}
	
	
	
	
}
