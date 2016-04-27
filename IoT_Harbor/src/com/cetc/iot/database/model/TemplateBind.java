package com.cetc.iot.database.model;

public class TemplateBind {
	private String template_bind_id;
	private String identify_id;
	private String ve_template_id;
	private String pe_template_id;
	private String service_id;
	private int 	 bind_max;
	private int 	 bind_min;
	private String   bind_type;
	//private List<String>  peTemplate_id_List=new ArrayList<String>();


	public String getTemplate_bind_id() {
		return template_bind_id;
	}
	public void setTemplate_bind_id(String template_bind_id) {
		this.template_bind_id = template_bind_id;
	}
	public String getIdentify_id() {
		return identify_id;
	}
	public void setIdentify_id(String identify_id) {
		this.identify_id = identify_id;
	}
	public String getVe_template_id() {
		return ve_template_id;
	}
	public void setVe_template_id(String ve_template_id) {
		this.ve_template_id = ve_template_id;
	}
	public int getBind_max() {
		return bind_max;
	}
	public void setBind_max(int bind_max) {
		this.bind_max = bind_max;
	}
	public int getBind_min() {
		return bind_min;
	}
	public void setBind_min(int bind_min) {
		this.bind_min = bind_min;
	}
	public String getBind_type() {
		return bind_type;
	}
	public void setBind_type(String bind_type) {
		this.bind_type = bind_type;
	}
	public String getPe_template_id() {
		return pe_template_id;
	}
	public void setPe_template_id(String pe_template_id) {
		this.pe_template_id = pe_template_id;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	/*public List<String> getPeTemplate_id_List() {
		return peTemplate_id_List;
	}
	public void setPeTemplate_id_List(List<String> peTemplate_id_List) {
		this.peTemplate_id_List = peTemplate_id_List;
	}
	 */


}
