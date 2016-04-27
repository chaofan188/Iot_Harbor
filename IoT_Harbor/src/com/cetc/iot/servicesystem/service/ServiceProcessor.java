package com.cetc.iot.servicesystem.service;

import com.cetc.iot.servicesystem.util.MyException;

/**
 * @author nci_my
 *消息处理器，提供过滤器和封装器，对访问消息进行处理，形成ve的请求封装形式
 */
public interface ServiceProcessor {
	public void process(String message) throws MyException;
}
