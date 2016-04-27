package com.cetc.test;

import com.cetc.iot.accesssystem.upinterface.PEManager;
import com.cetc.iot.accesssystem.upinterface.Session;

public class TestSession {

	public Session session = null;
	public static  PEManager manager = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		manager = PEManager.getInstance();
		TestSession t = new TestSession();
		Sender sender = t.new Sender();

		Reciver reciver = t.new Reciver();
		sender.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reciver.start();
	}

	public class Sender extends Thread{
		public void run(){
			String result = null;
			
			session = new Session();
			manager.attachSession(session);
			System.out.println("I am Sender, going to be blocked");
			try {
				result = session.waitResponse();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Sender going on!");
			System.out.println(result);
		}
	}
	
	public class Reciver extends Thread{
		public void run(){
			System.out.println("I am reciver, going to unblock!");
			session.wakeUpResponse("Hello Sender");
		}
	}
}
