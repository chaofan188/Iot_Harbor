package com.cetc.iot.database.model;

public class Province {

	private int provinceId;//ID Ψһ��ʶ
	
	private String provinceName;//ʡ����
	
	private int countryId;//��������ID

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	
}
