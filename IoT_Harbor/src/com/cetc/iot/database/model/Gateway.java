package com.cetc.iot.database.model;

public class Gateway {
	
	private String gatewayId;//连入网关ID
	
	private String gatewayName;//网关名称
	
	private String gatewayUri;//网关连接地址
	
	private String gatewayState;//网关状态
	
	private String gatewayKey;//网关密钥

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getGatewayUri() {
		return gatewayUri;
	}

	public void setGatewayUri(String gatewayUri) {
		this.gatewayUri = gatewayUri;
	}

	public String getGatewayState() {
		return gatewayState;
	}

	public void setGatewayState(String gatewayState) {
		this.gatewayState = gatewayState;
	}

	public String getGatewayKey() {
		return gatewayKey;
	}

	public void setGatewayKey(String gatewayKey) {
		this.gatewayKey = gatewayKey;
	}
	
	
}
