package com.cetc.test.xiruitest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.cetc.iot.accesssystem.downinterface.socket.commmunication.DatagramServer;
import com.cetc.iot.accesssystem.downinterface.socket.commmunication.Server;
import com.cetc.test.FakeVE;

public class AllStart {
	public static AllStart start = new AllStart();
	public static FakeVE ve = new FakeVE();
	
	public static void start() {
		// ��ʼ����ve
//		FakeVE.controller = new PeControllerImpl(FakeVE.peID);
//		FakeVE.dataChannel = FakeVE.controller.getDataChannel();
//		FakeVE.controlChannel = FakeVE.controller.getControlChannel();
		//FakeVE ve = new FakeVE();

		// ��������������
		new Thread(start.new KeyBoardInput(ve)).start();

		// ����TCPServer
		new Thread(start.new TCPServerStart(8800)).start();
		// ����UDPServer
		new Thread(start.new UDPServerStart(8801)).start();
	}
	
	public static void main (String[] args) throws Exception{
		AllStart.start();
		
	}
	
	private class TCPServerStart implements Runnable{
		private int port = 8800;
		private TCPServerStart (int port){
			this.port = port;
		}
		public void run(){
			try {
				new Server().bind(port);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class UDPServerStart implements Runnable {
		private int port = 8801;
		private UDPServerStart (int port){
			this.port = port;
		}
		public void run() {
			try {
				new DatagramServer().run(port);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	private class ControlSync implements Runnable{
		private FakeVE ve;
		private ControlSync (FakeVE ve){
			this.ve = ve;
		}
		public void run(){
			ve.testControlSync();
		}
	}
	
	public static void controlSyncTest(){
		new Thread(start.new ControlSync(ve)).start();
	}
	
	private class KeyBoardInput implements Runnable{
		private FakeVE ve;
		private KeyBoardInput (FakeVE ve){
			this.ve = ve;
		}
		public void run(){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				while (true){
					String input = br.readLine();
					if (input.startsWith("1")){
						ve.testControl();
					}
					else if (input.startsWith("2")){
						new Thread(start.new ControlSync(ve)).start();
					}
					else if (input.startsWith("3")){
						new Thread(start.new Data(ve)).start();
					}
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private class Data implements Runnable{
		private FakeVE ve;
		private Data(FakeVE ve){
			this.ve = ve;
		}
		public void run(){
			ve.testData();
		}
	}
}
