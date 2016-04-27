package com.cetc.iot.database.model;

public class PeGeolocation {
	
	private String geolocationId;//地理位置ID
	
	private String peId;//pe的ID
	
	private int countryId;//国家ID
	
	private int provinceId;//省ID
	
	private int cityId;//城市ID
	
	private String otherInfo;//其他地理位置信息
	
	private float altitude;//海拔
	
	private float longitude;//经度
	
	private float latitude;//纬度

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
