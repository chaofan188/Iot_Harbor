package com.cetc.iot.database.model;

public class ClassInformation {
	
	private String classId;//类别ID
	
	private String className;//类别名称
	
	private String classType;//类别类型（1：大类 2：中类 3：小类 4:型号）

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	
}
