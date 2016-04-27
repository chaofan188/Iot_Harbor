package com.cetc.iot.database.model;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Multimap;

/**
 * �����VEģ���ģ���࣬����VETemplate��VeWebsocketService��VeWebsocketServiceParam���������ṩ�����VEģ��
 * @author ����ƽ̨
 * 
 */
public class CompleteTpl {
	private VETemplate veTpl;
	private Set<VeWebsocketService> services;
	private Set<VeWebsocketServiceParam> params;
	private Set<VeWebsocketServiceReturnParam> returnParam;
	private Set<TemplateBind> templateBinds;
	private Multimap<String, String> identifyIdServiceName;
	/**
	 * 读取ve模板描述文件的时候，存储pe模板下对应的interfaceId
	 * key:peTemplateId
	 * value:interfaceId1,interfaceId2...
	 */
	private Map<String, String> peTemplateInterface;  	
	
	public Set<VeWebsocketServiceReturnParam> getReturnParam() {
		return returnParam;
	}
	public void setReturnParam(Set<VeWebsocketServiceReturnParam> returnParam) {
		this.returnParam = returnParam;
	}
	public Multimap<String, String> getIdentifyIdServiceName() {
		return identifyIdServiceName;
	}
	public void setIdentifyIdServiceName(
			Multimap<String, String> identifyIdServiceName) {
		this.identifyIdServiceName = identifyIdServiceName;
	}
	public VETemplate getVeTpl() {
		return veTpl;
	}
	public void setVeTpl(VETemplate veTpl) {
		this.veTpl = veTpl;
	}
	public Set<VeWebsocketService> getServices() {
		return services;
	}
	public void setServices(Set<VeWebsocketService> services) {
		this.services = services;
	}
	public Set<VeWebsocketServiceParam> getParams() {
		return params;
	}
	public void setParams(Set<VeWebsocketServiceParam> params) {
		this.params = params;
	}
	public Set<TemplateBind> getTemplateBinds() {
		return templateBinds;
	}
	public void setTemplateBinds(Set<TemplateBind> templateBinds) {
		this.templateBinds = templateBinds;
	}
	public Map<String, String> getPeTemplateInterface() {
		return peTemplateInterface;
	}
	public void setPeTemplateInterface(Map<String, String> peTemplateInterface) {
		this.peTemplateInterface = peTemplateInterface;
	}
	
}
