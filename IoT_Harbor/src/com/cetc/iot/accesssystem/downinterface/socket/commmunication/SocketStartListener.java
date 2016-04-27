package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SocketStartListener implements ServletContextListener{
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//SocketStart.close();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		SocketStart.start();
	}

}
