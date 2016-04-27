package com.cetc.iot.harbormanage.pojo;

public class InterfacePojo {

	/*
	 * String is_control;//鏂版坊鍔�绛変細澶勭悊 String is_transparent;//鏂版坊鍔�绛変細澶勭悊
	 * String need_response;//鏂版坊鍔�绛変細澶勭悊 String is_dir_down;//鏂版坊鍔�绛変細澶勭悊
	 */String interface_description;

	
    
	String interfaceID;
	String interface_direction;
	ParamPojo[] param;
	ParamPojo[] return_param;
	String interface_protocol;


	String protocol_rule;
    
	
    public String getInterface_description() {
		return interface_description;
	}

	public String getInterface_direction() {
		return interface_direction;
	}
	
	public String getInterface_protocol() {
		return interface_protocol;
	}

	public String getInterfaceID() {
		return interfaceID;
	}

	/*
	 * public String getIs_control() { return is_control; } public String
	 * getIs_dir_down() { return is_dir_down; } public String
	 * getIs_transparent() { return is_transparent; } public String
	 * getNeed_response() { return need_response; }
	 */
	public ParamPojo[] getParam() {
		return param;
	}

	public String getProtocol_rule() {
		return protocol_rule;
	}

	public ParamPojo[] getReturn_param() {
		return return_param;
	}

	public void setInterface_description(String interface_description) {
		this.interface_description = interface_description;
	}

	public void setInterface_direction(String interface_direction) {
		this.interface_direction = interface_direction;
	}

	public void setInterface_protocol(String interface_protocol) {
		// TODO Auto-generated method stub
		this.interface_protocol = interface_protocol;

	}

	public void setInterfaceID(String interfaceID) {
		this.interfaceID = interfaceID;
	}

	/*
	 * public void setIs_control(String is_control) { this.is_control =
	 * is_control; } public void setIs_dir_down(String is_dir_down) {
	 * this.is_dir_down = is_dir_down; } public void setIs_transparent(String
	 * is_transparent) { this.is_transparent = is_transparent; } public void
	 * setNeed_response(String need_response) { this.need_response =
	 * need_response; }
	 */
	public void setParam(ParamPojo[] param) {
		this.param = param;
	}
	public void setProtocol_rule(String protocol_rule) {
		this.protocol_rule = protocol_rule;
	}

	public void setReturn_param(ParamPojo[] return_param) {
		this.return_param = return_param;
	}
}
