package com.cetc.iot.database.model;

public class Country {
	
	private int countryId;//ID 唯一标识
	
	private String countryName;//国家名称

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
}
