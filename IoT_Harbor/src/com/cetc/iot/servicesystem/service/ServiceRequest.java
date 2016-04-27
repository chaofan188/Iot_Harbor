package com.cetc.iot.servicesystem.service;

import java.util.Enumeration;

/**
 * @author nci_my
 *VE服务请求消息的封装形式
 */
public interface ServiceRequest {
	/**
	 * 返回消息体的字节长度
	 * @return
	 */
	public int getContentLength();
	/**
	 * 返回消息类型
	 * @return
	 */
	public String getContentType();
	/**
	 * 返回boolean型，表示消息是否使用安全通道
	 * @return
	 */
	public boolean isSecure();
	/**
	 * 返回客户机地址
	 * @return
	 */
	public String getRemoteAddr();
	/**
	 * 返回客户机名称
	 * @return
	 */
	public String getRemoteHost();
	/**
	 * 返回完整的消息内容
	 * @return
	 */
	public String getServiceMessage();
	/**
	 * 返回消息对应的veid
	 * @return
	 */
	public String getVeid();
	/**
	 * 返回消息中包含的控制字
	 * @return
	 */
	public String getCtrl();
	/**
	 * 返回消息中包含的参数名列表
	 * @return
	 */
	public Enumeration<String> getParameterNames();
	/**
	 * 返回消息中某参数的取值
	 * @param name
	 * @return
	 */
	public Object getParameter(String name);
	
	/**
	 * 返回该请求的会话对象
	 * @return
	 */
	public SessionBean getSession();
	
	/**
	 * 设置消息内容
	 * @param message
	 */
	public void setServiceMessage(String message);
	/**
	 * 设置服务控制
	 * @param ctrl
	 */
	public void setCtrl(String ctrl);
	
}
