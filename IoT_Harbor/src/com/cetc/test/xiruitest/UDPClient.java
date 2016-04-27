package com.cetc.test.xiruitest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import net.sf.json.JSONObject;

public class UDPClient {
	private static String UDPServerIP = "192.168.12.151"; 
	private static int UDPServerPORT = 8801; 

	public static void main(String[] args) {
		UDPClient tempClient = new UDPClient();
		for (int i=1;i<=1;i++){
			new Thread(tempClient.new MutlThread()).start();
		}
	}
	
	public class MutlThread implements Runnable {
		public void run(){
			DatagramSocket ds = null; 
			DatagramPacket sendDp; 
			DatagramPacket receiveDp; 

			try {
				
				ds = new DatagramSocket();

				
				InetAddress address = InetAddress.getByName(UDPServerIP);
				byte[] b = new byte[1024];
				receiveDp = new DatagramPacket(b, b.length);


				JSONObject json = new JSONObject();
				json.put("Address", "test");
				json.put("IFID", "11234");
				JSONObject another = new JSONObject();
				another.put("name", "hello");
				json.put("Data", another);
				
				byte[] data = json.toString().getBytes();
				sendDp = new DatagramPacket(data, data.length, address,UDPServerPORT);
				long start = System.currentTimeMillis();
				for (int i=1;i<=50;i++){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e){
						e.printStackTrace();
					}
					ds.send(sendDp);
					System.out.println("send number: "+i);
				}
				long end = System.currentTimeMillis();
				System.out.println("last: "+(end-start));
	/*			ds.receive(receiveDp);
				byte[] response = receiveDp.getData();
				int len = receiveDp.getLength();
				String s = new String(response, 0, len);*/
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ds.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
