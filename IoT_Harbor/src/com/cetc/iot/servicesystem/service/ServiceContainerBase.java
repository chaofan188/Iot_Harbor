package com.cetc.iot.servicesystem.service;

/**
 * @author nci_my
 *
 */
public abstract class ServiceContainerBase implements ServiceContainer {
	private VeLogger logger = null;
	private VeManager manager = null;
	private VeClassLoader loader = null;

	public abstract String getInfo();
	@Override
	public VeLogger getLogger() {
		// TODO Auto-generated method stub
		return this.logger;
	}
	@Override
	public VeManager getManager() {
		// TODO Auto-generated method stub
		return this.manager;
	}
	@Override
	public VeClassLoader getLoader() {
		// TODO Auto-generated method stub
		return this.loader;
	}
	@Override
	public void setLogger() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setManager() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLoader() {
		// TODO Auto-generated method stub
		
	}


}
