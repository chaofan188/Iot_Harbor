package com.cetc.iot.servicesystem.service;

/**
 * @author nci_my
 *封装器，根据消息格式形成相应的消息封装结构，供ve使用
 */
public interface ServiceWrapper{
	public void wrap(String message);
}
