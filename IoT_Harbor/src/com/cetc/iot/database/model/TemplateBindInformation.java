package com.cetc.iot.database.model;

import java.util.ArrayList;
import java.util.List;

public class TemplateBindInformation {
	String	co_id;
	String  veTemplate_id;
	List<String>  peTemplate_id_List=new ArrayList<String>();

	public String getCo_id() {
		return co_id;
	}
	public void setCo_id(String co_id) {
		this.co_id = co_id;
	}
	public String getVeTemplate_id() {
		return veTemplate_id;
	}
	public void setVeTemplate_id(String veTemplate_id) {
		this.veTemplate_id = veTemplate_id;
	}
	public List<String> getPeTemplate_id_List() {
		return peTemplate_id_List;
	}
	public void setPeTemplate_id_List(List<String> peTemplate_id_List) {
		this.peTemplate_id_List = peTemplate_id_List;
	}
	
}
