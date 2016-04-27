package com.cetc.test.xiruitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class TCPClientTest {
	private static final String IP = "192.168.12.77";
	private static final int PORT = 3456;
	private static final int MAX = 50;
	private static long start = 0;
	private static long end = 0;
	public static void main (String[] args) throws UnknownHostException, IOException,Exception{
		TCPClientTest temp = new TCPClientTest();
		for (int i=1;i<=MAX;i++){
			Socket socket = new Socket(IP,PORT);
			new Thread(temp.new ClientRead(socket)).start();
			new Thread(temp.new ClientWrite(socket)).start();
			System.out.println("Socket"+i+" Create! ");
			Thread.sleep(5);
		}
		System.out.println(""+MAX+"个连接完成！");
		
	}
	private class ClientRead implements Runnable{
		private Socket socket;
		public ClientRead(Socket socket){
			this.socket = socket;
		}
		public void run(){
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				char temp[] = new char[1024];
				while (true){
					int length = br.read(temp);
					end = System.currentTimeMillis();
					String recv = new String(temp,0,length);
					System.out.println("RECV: "+recv);
					System.out.println("LAST: "+(end-start));
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	private class ClientWrite implements Runnable {
		private Socket socket;
		public ClientWrite(Socket socket){
			this.socket = socket;
		}
		public void run(){
			try {
				String sendInfo = "{\"Address\":\"gateway\",\"Data\":{\"11111\":\"on\"}}\n";
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				char temp[] = sendInfo.toCharArray();
				for (int i=1;i<=1;i++){
					//Thread.sleep(500);
					pw.write(temp);
					pw.flush();
					start = System.currentTimeMillis();
					System.out.println("Socket: "+socket.hashCode()+" Message"+i+" Send! "+sendInfo);
				}
				System.out.println("Socket: "+socket.hashCode()+" Send Over! ");
				
				Thread.sleep(300000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

