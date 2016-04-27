package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.omg.CORBA.PRIVATE_MEMBER;

import net.sf.json.JSONObject;

import com.cetc.iot.harbormanage.service.PEDataService;
import com.cetc.iot.servicesystem.util.GetBean;

public class DatagramServerUtilTest {

	// @Autowired
	// private PEDataService peDataService;

	private PEDataService peDataService = (PEDataService) GetBean
			.getBean("peDataService");

	private static DatagramServerUtilTest temp = new DatagramServerUtilTest();

	private static int count = 0;

	private ExecutorService exec = Executors.newCachedThreadPool();

	public void read(ChannelHandlerContext ctx, String recv,
			InetSocketAddress address) {
		JSONObject json = JSONObject.fromObject(recv);
		// System.out.println(json.toString());
		// peDataService.addPEData(json.getString("Address"),
		// json.getString("IFID"), json.getJSONObject("Data").toString(), 1);

		exec.execute(new InsertData(json, count));
		// new Thread (temp.new InsertData(json,count)).start();
		count++;
		System.out.println("count: " + count);
	}

	public class InsertData implements Runnable {
		private JSONObject json;
		private int count;

		public InsertData(JSONObject json, int count) {
			this.json = json;
			this.count = count;
		}

		public void run() {
//			System.out.println("==============================");
			long start = System.currentTimeMillis();

			for (int i = 0; i < 200; i++) {
				peDataService.addPEData(json.getString("Address"), json
						.getString("IFID"), json.getJSONObject("Data")
						.toString(), 1);
				//System.out.println(count++);
			}
			long end = System.currentTimeMillis();
			System.out.println("last time is :" + (end - start));
		}
	}

}
