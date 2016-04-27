package com.cetc.iot.database.model;

public class VeService {
	private int id;
	private String service_ID;
	private String service_name;
	private String service_type;
	private String service_address_soap;
	private String service_address_rest;
	private String service_data_receive_class;
	private String service_description;
	private int tpl_id;
	
	public VeService(){
		
	}
	
	public VeService(int id, String service_ID, String service_name,
			String service_type, String service_address_soap,
			String service_address_rest, String service_data_receive_class,
			String service_description, int tpl_id) {
		super();
		this.id = id;
		this.service_ID = service_ID;
		this.service_name = service_name;
		this.service_type = service_type;
		this.service_address_soap = service_address_soap;
		this.service_address_rest = service_address_rest;
		this.service_data_receive_class = service_data_receive_class;
		this.service_description = service_description;
		this.tpl_id = tpl_id;
	}
	
	public VeService(String service_ID, String service_name,
			String service_type, String service_address_soap,
			String service_address_rest, String service_data_receive_class,
			String service_description, int tpl_id) {
		super();
		this.service_ID = service_ID;
		this.service_name = service_name;
		this.service_type = service_type;
		this.service_address_soap = service_address_soap;
		this.service_address_rest = service_address_rest;
		this.service_data_receive_class = service_data_receive_class;
		this.service_description = service_description;
		this.tpl_id = tpl_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getService_ID() {
		return service_ID;
	}

	public void setService_ID(String service_ID) {
		this.service_ID = service_ID;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getService_address_soap() {
		return service_address_soap;
	}

	public void setService_address_soap(String service_address_soap) {
		this.service_address_soap = service_address_soap;
	}

	public String getService_address_rest() {
		return service_address_rest;
	}

	public void setService_address_rest(String service_address_rest) {
		this.service_address_rest = service_address_rest;
	}
	
	public String getService_data_receive_class() {
		return service_data_receive_class;
	}

	public void setService_data_receive_class(String service_data_receive_class) {
		this.service_data_receive_class = service_data_receive_class;
	}

	public String getService_description() {
		return service_description;
	}

	public void setService_description(String service_description) {
		this.service_description = service_description;
	}

	public int getTpl_id() {
		return tpl_id;
	}

	public void setTpl_id(int tpl_id) {
		this.tpl_id = tpl_id;
	}
}
