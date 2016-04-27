package com.cetc.iot.servicesystem.pojo;


public class ResponsePojo {
	
	private String result;
	
	private String responseMessage;
	
	private String veId;
	
	private int contentLength;
	
	private String contentType;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getVeId() {
		return veId;
	}

	public void setVeId(String veId) {
		this.veId = veId;
	}

}
