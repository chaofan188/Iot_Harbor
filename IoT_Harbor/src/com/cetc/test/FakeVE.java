package com.cetc.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

import com.cetc.iot.accesssystem.downinterface.socket.commmunication.Server;
import com.cetc.iot.accesssystem.upinterface.ControlChannel;
import com.cetc.iot.accesssystem.upinterface.DataChannel;
import com.cetc.iot.accesssystem.upinterface.PeController;
import com.cetc.iot.accesssystem.upinterface.impl.PeControllerImpl;

public class FakeVE implements Runnable {

	public static String peID = "11111";
	public static PeController controller = null;
	public static DataChannel dataChannel = null;
	public static ControlChannel controlChannel = null;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		controller = new PeControllerImpl(peID);
		dataChannel = controller.getDataChannel();
		controlChannel = controller.getControlChannel();
		FakeVE ve = new FakeVE();
		new Thread(ve).start();
		new Server().bind(8000);
	}

	public void testControl() {
		System.out.println("==========VE-PE控制接口测试=========================");
		JSONObject json = new JSONObject();
		json.put("temperature", "3.0");
		controlChannel.control(json.toString(), "12345");
		System.out.println("==========测试Done================");

	}

	public void testControlSync() {
		System.out.println("==========VE-PE同步控制接口测试=========================");
		StringBuffer result = new StringBuffer();
		JSONObject json = new JSONObject();
		json.put("humidity", "3.0");
		controlChannel.control(json.toString(), "12345", result);
		System.out.println(result.toString());
		System.out.println("==========测试Done================");

	}

	public void testData() {
		System.out.println("==========VE-PE数据接口测试=================");

		byte[] buf = new byte[1024];

		dataChannel.open();
		dataChannel.receive(buf);
		System.out.println(buf.toString());
		System.out.println("==========测试Done================");

	}

	@Override
	public void run() {
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			while (true) {
				String input = br.readLine();
				if ("1".equalsIgnoreCase(input)) {
					testControl();
				} else if ("2".equalsIgnoreCase(input)) {
					testControlSync();
				} else if ("3".equalsIgnoreCase(input)) {
					testData();
				} else if ("0".equalsIgnoreCase(input)) {
					break;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
