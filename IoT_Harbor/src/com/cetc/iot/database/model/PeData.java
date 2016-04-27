package com.cetc.iot.database.model;

import java.util.Date;

public class PeData {
	
	private String dataId;//数据ID
	
	private Date dataTime;//数据生成时间
	
	private String peId;//生成数据的pe的ID
	
	private String peInterfaceId;//生成数据的pe的接口ID
	
	private String dataContent;//数据内容
	
	private int datail;//是否存在详情

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public String getPeId() {
		return peId;
	}

	public void setPeId(String peId) {
		this.peId = peId;
	}

	public String getPeInterfaceId() {
		return peInterfaceId;
	}

	public void setPeInterfaceId(String peInterfaceId) {
		this.peInterfaceId = peInterfaceId;
	}

	public String getDataContent() {
		return dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	public int getDatail() {
		return datail;
	}

	public void setDatail(int datail) {
		this.datail = datail;
	}
	
	
}
