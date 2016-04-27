package com.cetc.iot.servicesystem.service;

/**
 * 和ServiceRequest类似，ServiceResponse对象也是由服务控制器构造和传入VE的service()方法。VE用它与客户端请求交流相应。
 * @author nci_my
 *
 */
public interface ServiceResponse {

	/**
	 * 设置消息体长度
	 * @param length
	 */
	public void setContentLength(int length);
	/**
	 * 设置消息类型
	 * @param type
	 */
	public void setContentType(String type);
	/**
	 * 设置消息对应的veid
	 * @param veid
	 */
	public void setVeid(String veid);
	/**
	 * 设置消息的参数
	 * @param name
	 * @param value
	 */
	public void setParameter(String name, String value);
	
	/**
	 * 设置相应结果的状态码
	 * @param sc
	 */
	public void setResult(int sc);
	/**
	 * 设置返回的消息内容
	 * @param sc
	 */
	public void setResponseMessage(String message);
	/**
	 * 获得返回的消息内容
	 * @return
	 */
	public String getResponseMessage();
	/**
	 * 获得返回的结果
	 * @return
	 */
	public String getResult();
}
