package com.cetc.iot.database.model;

/**
 * @ClassName: VePeBindInterface 
 * @Description: 和iot_vetemplate_petemplate_bind_interface表对应的pojo
 * @author ZhangYong
 * @date 2016年4月20日 下午4:51:23
 */
public class VePeBindInterface {
	//veTemplate,peTemplate绑定关系的id
	private String bindId;
	
	//ve绑定的pe对应的interfaceId
	private String interfaceId;

	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}
	
}
