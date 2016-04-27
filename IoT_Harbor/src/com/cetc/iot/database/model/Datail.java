package com.cetc.iot.database.model;

public class Datail {
	
	private int datailId;//数据详情ID
	
	private String dataId;//相关的数据ID
	
	private String datailKey;//详情的键
	
	private String datailValue;//详情的值	

	public int getDatailId() {
		return datailId;
	}

	public void setDatailId(int datailId) {
		this.datailId = datailId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDatailKey() {
		return datailKey;
	}

	public void setDatailKey(String datailKey) {
		this.datailKey = datailKey;
	}

	public String getDatailValue() {
		return datailValue;
	}

	public void setDatailValue(String datailValue) {
		this.datailValue = datailValue;
	}
	
	
}
