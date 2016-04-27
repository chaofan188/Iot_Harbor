package com.cetc.iot.harbormanage.pojo;
public class MyDevice {

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	/*public String getEnrolltime() {
		return enrolltime;
	}

	public void setEnrolltime(String enrolltime) {
		this.enrolltime = enrolltime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}*/

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(String lifecycle) {
		this.lifecycle = lifecycle;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getVisable() {
		return visable;
	}

	public void setVisable(String visable) {
		this.visable = visable;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	String operate;
	String devicename;
	String ID;
	/*String enrolltime;
	String updatetime;*/
	String status;
	String lifecycle;
	String belongto;
	String visable;

}
