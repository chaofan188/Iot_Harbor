package com.cetc.iot.servicesystem.service;

import com.cetc.iot.servicesystem.util.MyException;

/**
 * @author nci_my
 *过滤器，对待处理的消息进行同意过滤
 */
public interface ServiceFilter{
	public void filter(String message) throws MyException;
}
