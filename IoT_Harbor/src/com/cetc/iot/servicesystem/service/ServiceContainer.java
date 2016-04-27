package com.cetc.iot.servicesystem.service;

/**
 * @author nci_my
 *
 */
public interface ServiceContainer{
	
	public VeLogger getLogger();
	public VeManager getManager();
	public VeClassLoader getLoader();
	public void setLogger();
	public void setManager();
	public void setLoader();
}
