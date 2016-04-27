package com.cetc.iot.database.model;

import java.util.Date;

public class Pe {
	
	private String pe_public;
    private String pe_description;

	private String peId;//peID(Ψһ��ʶ)

	private String peName;//pe���

	private String peOwner;//pe������

	private String peUser;//pe�����ʹ����
	private String pePictureUrl;//peͼƬ·��

	private String peKey;//pe��Կ
	private int peState;//pe��ǰ״̬
	
	private String peGeolocationId;//pe����λ��ID
	
	private int peLifecycle;//pe��������
	
	private Date peLastTime;//pe����¼ʱ��
	
	private int peTime;//pe��¼����
	
	private String templateId;//peģ��ID
	
	public String getPe_description() {
		return pe_description;
	}
	
	public String getPe_public() {
		return pe_public;
	}
	
	public String getPeGeolocationId() {
		return peGeolocationId;
	}
	
	public String getPeId() {
		return peId;
	}
	
	
	
	public String getPeKey() {
		return peKey;
	}

	public Date getPeLastTime() {
		return peLastTime;
	}

	public int getPeLifecycle() {
		return peLifecycle;
	}

	public String getPeName() {
		return peName;
	}

	public String getPeOwner() {
		return peOwner;
	}

	public String getPePictureUrl() {
		return pePictureUrl;
	}

	public int getPeState() {
		return peState;
	}

	public int getPeTime() {
		return peTime;
	}

	public String getPeUser() {
		return peUser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setPe_description(String pe_description) {
		this.pe_description = pe_description;
	}

	public void setPe_public(String pe_public) {
		this.pe_public = pe_public;
	}



	public void setPeGeolocationId(String peGeolocationId) {
		this.peGeolocationId = peGeolocationId;
	}

	public void setPeId(String peId) {
		this.peId = peId;
	}

	public void setPeKey(String peKey) {
		this.peKey = peKey;
	}

	public void setPeLastTime(Date peLastTime) {
		this.peLastTime = peLastTime;
	}

	public void setPeLifecycle(int peLifecycle) {
		this.peLifecycle = peLifecycle;
	}

	public void setPeName(String peName) {
		this.peName = peName;
	}

	public void setPeOwner(String peOwner) {
		this.peOwner = peOwner;
	}

	public void setPePictureUrl(String pePictureUrl) {
		this.pePictureUrl = pePictureUrl;
	}

	public void setPeState(int peState) {
		this.peState = peState;
	}

	public void setPeTime(int peTime) {
		this.peTime = peTime;
	}

	public void setPeUser(String peUser) {
		this.peUser = peUser;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
}
