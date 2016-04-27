package com.cetc.iot.database.model;

public class PeInterface {
	
	private String interfaceId;//�ӿ�ID
	
	private String interfaceName;//�ӿ����
	
	private String interfaceType;//�ӿ�����
	
	private int interfaceResponse = -1;//�ӿ���Ӧ����
	
	private int interfaceFashion = -1;//�ӿڷ�ʽ
	
	private int interfaceChannel = -1;//�ӿ�ͨ��
	
	private String templateId;//���peģ��ID
	
	private String interfaceRemark;//�ӿڱ�ע
	
	private String interfaceContent;//ʵ�ʽӿڣ�����ֵ�ԣ����棩
	
	private String parameterType;//�ӿڲ������ͣ�����ԣ�������
	
	private String result;//���ؽ���ʽ
	
	private String resultType;//���������ͣ������˳���ԣ�������

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public int getInterfaceResponse() {
		return interfaceResponse;
	}

	public void setInterfaceResponse(int interfaceResponse) {
		this.interfaceResponse = interfaceResponse;
	}

	public int getInterfaceFashion() {
		return interfaceFashion;
	}

	public void setInterfaceFashion(int interfaceFashion) {
		this.interfaceFashion = interfaceFashion;
	}

	public int getInterfaceChannel() {
		return interfaceChannel;
	}

	public void setInterfaceChannel(int interfaceChannel) {
		this.interfaceChannel = interfaceChannel;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getInterfaceRemark() {
		return interfaceRemark;
	}

	public void setInterfaceRemark(String interfaceRemark) {
		this.interfaceRemark = interfaceRemark;
	}

	public String getInterfaceContent() {
		return interfaceContent;
	}

	public void setInterfaceContent(String interfaceContent) {
		this.interfaceContent = interfaceContent;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
	
}
