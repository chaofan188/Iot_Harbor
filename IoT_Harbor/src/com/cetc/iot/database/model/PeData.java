package com.cetc.iot.database.model;

import java.util.Date;

public class PeData {
	
	private String dataId;//����ID
	
	private Date dataTime;//��������ʱ��
	
	private String peId;//�������ݵ�pe��ID
	
	private String peInterfaceId;//�������ݵ�pe�Ľӿ�ID
	
	private String dataContent;//��������
	
	private int datail;//�Ƿ��������

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
