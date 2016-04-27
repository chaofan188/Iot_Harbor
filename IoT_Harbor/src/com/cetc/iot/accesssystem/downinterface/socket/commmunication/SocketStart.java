package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cetc.iot.accesssystem.downinterface.socket.service.SocketPeService;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.util.ESPConfig;



/**
 * TCP and UDP start class
 * @author xzc
 * Create Time: 2014-10-22
 * Author: xzc
 * Details: Add start() function to start TCP server at port 8800 and UDP server at port 8801
 * 			Add start(TCPPort,UDPPort) function to start TCP and UDP server, allows to set TCPPort and UDPPort
 * 			Add close function to interrupt the TCP server thread and UDP server thread
 * Update Time: 2014-12-09
 * Author: xzc
 * Details: Add monitor TCP server start thread
 *          update start function adding monitor TCP server start and end function
 *
 */
public class SocketStart {
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
	
	private SocketPeService socketPeService = (SocketPeService)GetBean.getBean("socketPeService");
	
	private static SocketStart socketStart = new SocketStart();
	private static Thread tcpThread;
	private static Thread udpThread;
	private static Thread monitorTCPThread;
	/**
	 * Start TCP server at port 8800 and UDP server at port 8801, monitor TCP server at 8810
	 */
	public static void start(){
		socketStart.closePe();
		tcpThread = new Thread(socketStart.new TCPStart());
		tcpThread.start();
		udpThread = new Thread(socketStart.new UDPStart());
		udpThread.start();
		monitorTCPThread = new Thread(socketStart.new MonitorTCPStart());
		monitorTCPThread.start();
		new Thread(socketStart.new CheckThreadRun()).start();
	}
	
	/**
	 * Start TCP server and UDP server and monitor TCP server, allows to set TCPPort, UDPPort, monitor TCP Port
	 * @param tcpPort TCPPort to start server
	 * @param udpPort UDPPort to start server
	 * @param monitorTCPPort monitor TCP port to start server
	 */
	public static void start(int tcpPort,int udpPort,int monitorTCPPort){
		socketStart.closePe();
		tcpThread = new Thread(socketStart.new TCPStart(tcpPort));
		tcpThread.start();
		udpThread = new Thread(socketStart.new UDPStart(udpPort));
		udpThread.start();
		monitorTCPThread = new Thread(socketStart.new MonitorTCPStart(monitorTCPPort));
		monitorTCPThread.start();
		new Thread(socketStart.new CheckThreadRun()).start();
	}
	/**
	 * Interrupt the TCP and UDP server
	 */
	public static void close(){
		//tcpThread.interrupt();
		//udpThread.interrupt();
		socketStart.closePe();
	}
	
	private void closePe(){
		List<Pe> list = socketPeService.getAllPe();
		socketPeService.closeAll(list);
	}
	
	private class CheckThreadRun implements Runnable{
		public void run(){
			while (true){
				try {
					Thread.sleep(10000);
					System.out.println("~~~~~~~~~~~~~~~~~~~~Still Alive~~~~~~"+format.format(new Date()));
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * TCP Server Start Thread
	 * @author xzc
	 * 
	 */
	private class TCPStart implements Runnable{
		private int port = Integer.parseInt(ESPConfig.getValue("HarborTCPPort"));
		/**
		 * Allows to set TCPPort
		 * @param port TCPPort
		 */
		public TCPStart(int port){
			this.port = port;
		}
		/**
		 * At default port from esp.properties
		 */
		public TCPStart(){
			
		}
		public void run(){
			try {
				new Server().bind(port);
			} catch (InterruptedException e){
				System.out.println("TCP Socket Server Interrupted! ");
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * UDP Server Start Thread
	 * @author xzc
	 *
	 */
	private class UDPStart implements Runnable{
		private int port = Integer.parseInt(ESPConfig.getValue("HarborUDPPort"));
		/**
		 * Allows to set UDPPort
		 * @param port UDPPort
		 */
		public UDPStart(int port){
			this.port = port;
		}
		/**
		 * At default port from esp.properties
		 */
		public UDPStart(){
			
		}
		public void run(){
			try {
				new DatagramServer().run(port);
			}catch (InterruptedException e){
				System.out.println("UDP Socket Server Interrupted! ");
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Monitor TCP Server Start Thread
	 * @author xzc
	 *
	 */
	private class MonitorTCPStart implements Runnable{
		private int port = Integer.parseInt(ESPConfig.getValue("HarborMonitorTCPPort"));
		/**
		 * Allows to set Monitor TCP Port
		 * @param port
		 */
		public MonitorTCPStart(int port){
			this.port = port;
		}
		/**
		 * At default port from esp.properties
		 */
		public MonitorTCPStart(){
			
		}
		public void run(){
			try {
				new MonitorSocket().bind(port);
			} catch (InterruptedException e){
				System.out.println("Monitor Socket Server Interrupted! ");
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
