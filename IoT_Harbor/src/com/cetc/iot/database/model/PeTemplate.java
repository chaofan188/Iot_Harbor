package com.cetc.iot.database.model;

import java.util.Date;

public class PeTemplate {
	
	private String templateId;//peģ��ID
	
	private String templateName;//peģ������
	
	private String templateType;//peģ������
	
	private String templateModel;//pe�����ͺ�
	
	private Date templateTime;//peģ�����ʱ��
	
	private String templatePicUrl;//peģ��ͼƬ
	
	private String templateRemark;//peģ������

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
