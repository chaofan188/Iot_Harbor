package com.cetc.iot.harbormanage.pojo;

public class ProvideManager {
	private String productid;
	private String devicename;
	private String status;
	private String lifecycle;
	private String visable;
	private String belong;
	private String applyuser;
	private String ve;
	public String getApplyuser() {
		return applyuser;
	}
	public String getBelong() {
		return belong;
	}
	public String getDevicename() {
		return devicename;
	}
	public String getLifecycle() {
		return lifecycle;
	}
	public String getProductid() {
		return productid;
	}
	public String getStatus() {
		return status;
	}
	public String getVe() {
		return ve;
	}
	public String getVisable() {
		return visable;
	}
	public void setApplyuser(String applyuser) {
		this.applyuser = applyuser;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public void setLifecycle(String lifecycle) {
		this.lifecycle = lifecycle;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setVe(String ve) {
		this.ve = ve;
	}
    public void setVisable(String visable) {
		this.visable = visable;
	}
}
