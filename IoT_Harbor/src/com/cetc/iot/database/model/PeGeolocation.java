package com.cetc.iot.database.model;

public class PeGeolocation {
	
	private String geolocationId;//����λ��ID
	
	private String peId;//pe��ID
	
	private int countryId;//����ID
	
	private int provinceId;//ʡID
	
	private int cityId;//����ID
	
	private String otherInfo;//��������λ����Ϣ
	
	private float altitude;//����
	
	private float longitude;//����
	
	private float latitude;//γ��

	public String getGeolocationId() {
		return geolocationId;
	}

	public void setGeolocationId(String geolocationId) {
		this.geolocationId = geolocationId;
	}

	public String getPeId() {
		return peId;
	}

	public void setPeId(String peId) {
		this.peId = peId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	
}
