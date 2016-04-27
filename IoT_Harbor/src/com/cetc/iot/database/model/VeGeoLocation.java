package com.cetc.iot.database.model;

import java.io.Serializable;

public class VeGeoLocation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1627134704360589538L;
	private String geolocation_id;
	private String ve_id;
	private int country_id;
	private int province_id;
	private int city_id;
	private String otherInfo;
	private float altitude;
	private float longitude;
	private float latitude;
	
	
	public VeGeoLocation(String ve_id, int country_id, int province_id,
			int city_id, String otherInfo, float altitude, float longitude,
			float latitude) {
		super();
		this.ve_id = ve_id;
		this.country_id = country_id;
		this.province_id = province_id;
		this.city_id = city_id;
		this.otherInfo = otherInfo;
		this.altitude = altitude;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public VeGeoLocation() {
		// TODO Auto-generated constructor stub
	}
	public String getGeolocation_id() {
		return geolocation_id;
	}
	public void setGeolocation_id(String geolocation_id) {
		this.geolocation_id = geolocation_id;
	}
	public String getVe_id() {
		return ve_id;
	}
	public void setVe_id(String ve_id) {
		this.ve_id = ve_id;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public int getProvince_id() {
		return province_id;
	}
	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
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
