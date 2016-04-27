package com.cetc.test;

import com.cetc.iot.accesssystem.upinterface.Session;
import com.cetc.test.TestSession.Reciver;
import com.cetc.test.TestSession.Sender;

public class TestMultiBlock {

	public static String result;
	public static Session session;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TestMultiBlock t = new TestMultiBlock();
		session = t.new Session();
		Blocker blocker = t.new Blocker();
		Blocker blocker2 = t.new Blocker();
		Blocker blocker3 = t.new Blocker();

		Unblocker unblocker = t.new Unblocker();
//		new Thread(blocker).start();
//		new Thread(blocker2).start();
//		new Thread(blocker3).start();
		blocker.start();
		blocker2.start();
		blocker3.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(unblocker).start();
	}
	

	public class Session{
		public void waitResponse() throws InterruptedException{
			
			synchronized (this) {
				this.wait();
				//check PE manager 中的session Manager中有没有对应的回应，如果有，则正常返回结果，如果没有，则TIMEOUT
				
			}			
		}
		
		public void wakeUpResponse(String result){
			synchronized (this) {
				this.notifyAll();
			}
		} 
	}
	
	public class Blocker extends Thread{
		public void run(){
			String result = null;
			System.out.println("Blocker going to Block!" + this.getName());

			try {
				session.waitResponse();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Blocker going on!");
		}
	}
	
	public class Unblocker extends Thread{
		public void run(){
			session.wakeUpResponse(result);
			System.out.println("I am unblocker, going to unblock!");
		}
	}
}
