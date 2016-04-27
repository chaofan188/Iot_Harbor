package com.cetc.iot.servicesystem.service.impl;

import java.util.Enumeration;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.cetc.iot.servicesystem.pojo.RequestPojo;
import com.cetc.iot.servicesystem.service.ServiceRequest;
import com.cetc.iot.servicesystem.service.SessionBean;

/**
 * 服务平台收到VE的请求消息，形成ServiceRequest对象，传入VE的service()方法。它包装请求消息。
 * @author nci_my
 *
 */
@Service("serviceRequest")
public class ServiceRequestWrapper implements ServiceRequest{
	private RequestPojo req = new RequestPojo();
//	private ServiceRequest serviceRequest;
	
//	public ServiceRequestWrapper(ServiceRequest serviceRequest){
//		this.serviceRequest = serviceRequest;
//	}
//	/**
//	 * @return the serviceRequest
//	 */
//	public ServiceRequest getServiceRequest() {
//		return serviceRequest;
//	}
//	/**
//	 * @param serviceRequest the serviceRequest to set
//	 */
//	public void setServiceRequest(ServiceRequest serviceRequest) {
//		this.serviceRequest = serviceRequest;
//	}
	/**
	 * 返回消息体的字节长度
	 * @return
	 */
	public int getContentLength(){
		return req.getContentLength();
	}
	/**
	 * 返回消息类型
	 * @return
	 */
	public String getContentType(){
		return req.getContentType();
	}
	/**
	 * 返回boolean型，表示消息是否使用安全通道
	 * @return
	 */
	public boolean isSecure(){
		return false;
	}
	/**
	 * 返回客户机地址
	 * @return
	 */
	public String getRemoteAddr(){
		return req.getRemoteAddr();
	}
	/**
	 * 返回客户机名称
	 * @return
	 */
	public String getRemoteHost(){
		return req.getRemoteHost();
	}
	/**
	 * 返回完整的消息内容
	 * @return
	 */
	public String getServiceMessage(){
		return req.getServiceMessage();
	}
	/**
	 * 返回消息对应的veid
	 * @return
	 */
	public String getVeid(){
		return req.getVeid();
	}
	/**
	 * 返回消息中包含的控制字
	 * @return
	 */
	public String getCtrl(){
		return req.getCtrl();
	}
	/**
	 * 返回消息中包含的参数名列表
	 * @return
	 */
	public Enumeration<String> getParameterNames(){

		return null;
	}
	/**
	 * 返回消息中某参数的取值
	 * @param name
	 * @return
	 */
	public Object getParameter(String name){
		JSONObject json = JSONObject.fromObject(req.getServiceMessage());
		return json.get(name);
	}
	
	/**
	 * 返回该请求的会话对象
	 * @return
	 */
	public SessionBean getSession(){
		return req.getSession();
	}
	/**
	 * 设置消息内容
	 * @param message
	 */
	public void setServiceMessage(String message){
		req.setServiceMessage(message);
	}
	@Override
	public void setCtrl(String ctrl) {
		req.setCtrl(ctrl);
		
	}
}
