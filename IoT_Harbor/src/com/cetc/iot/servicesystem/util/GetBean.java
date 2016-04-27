package com.cetc.iot.servicesystem.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class GetBean {
	
	final static String  context[] = {"applicationContext.xml"};
	static ApplicationContext ctx=new ClassPathXmlApplicationContext(context);
	
	public static Object getBean(String beanId){
		
		return ctx.getBean(beanId);
	}
}
