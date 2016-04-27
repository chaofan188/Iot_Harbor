package com.cetc.iot.database.model;

import java.util.Date;

public class PeTemplate {
	
	private String templateId;//pe模板ID
	
	private String templateName;//pe模板名称
	
	private String templateType;//pe模板类型
	
	private String templateModel;//pe厂家型号
	
	private Date templateTime;//pe模板插入时间
	
	private String templatePicUrl;//pe模板图片
	
	private String templateRemark;//pe模板描述

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTemplateModel() {
		return templateModel;
	}

	public void setTemplateModel(String templateModel) {
		this.templateModel = templateModel;
	}

	public Date getTemplateTime() {
		return templateTime;
	}

	public void setTemplateTime(Date templateTime) {
		this.templateTime = templateTime;
	}

	public String getTemplatePicUrl() {
		return templatePicUrl;
	}

	public void setTemplatePicUrl(String templatePicUrl) {
		this.templatePicUrl = templatePicUrl;
	}

	public String getTemplateRemark() {
		return templateRemark;
	}

	public void setTemplateRemark(String templateRemark) {
		this.templateRemark = templateRemark;
	}
	
	
}
