package com.cetc.iot.accesssystem.downinterface.protocol.onvif;

import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.soap.SOAPException;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.me.javawsdiscovery.DeviceDiscovery;
import org.onvif.ver10.schema.Profile;

import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.communicate.PESession;
import com.cetc.iot.middle.PEOperationForAtomicVE;

import de.onvif.soap.OnvifDevice;
import de.onvif.soap.devices.PtzDevices;

public class OnvifCam {

	private static Logger logger = Logger.getLogger(OnvifCam.class.getName());
	
	
	static Map<String, OnvifCam> camList = new HashMap<String, OnvifCam>();
	final long STEP_TIME = 100;
	OnvifDevice device = null;
	PtzDevices ptzDevices = null;
	String profileToken = null;
	String peid;
	String interfaceID;
	private static ExecutorService exec = Executors.newCachedThreadPool();

	public OnvifCam(String ip, String userName, String passWord, String peid,
			String interfaceID) {
		// Get onvif camera instance

		try {
			device = new OnvifDevice(ip, userName, passWord);

		} catch (ConnectException e) {
			logger.error("Could not connect to NVT. ");

		} catch (SOAPException e) {
			e.printStackTrace();
		}
		List<Profile> profiles = device.getDevices().getProfiles();
		profileToken = profiles.get(0).getToken();

		ptzDevices = device.getPtz();
		this.peid = peid;
		this.interfaceID = interfaceID;
		// Add cam device to the list
		camList.put(peid + interfaceID, this);
	}

	public static OnvifCam getCam(String peid, String interfaceID) {

		return camList.get(peid + interfaceID);
	}

	public class OnvifCamThread implements Runnable {
		private String cmd;
		private PESession session;

		public OnvifCamThread(String cmd, PESession session) {
			// TODO Auto-generated constructor stub
			this.cmd = cmd;
			this.session = session;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				String result = action(cmd, session);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 此函数完成json命令到本地函数调用的转化适配
	public String action(String cmd, PESession session) throws Exception {
		// 根据data内容进行摄像头的操作
		JSONObject jsonData = JSONObject.fromObject(cmd);
		Set set = jsonData.keySet();
		if (set.contains("easy_control")) {
			PEData data = new PEData();
			data.peID = peid;
			data.interfaceID = interfaceID;

			String value = jsonData.getString("easy_control");
			if (value.equals("left") || value.equals("right")) {
				moveX(value);
				data.data = "'result':'ok'";
			} else if (value.equals("up") || value.equals("down")) {
				moveY(value);
				data.data = "'result':'ok'";
			} else if (value.equals("zoomIn") || value.equals("zoomOut")) {
				zoom(value);
				data.data = "'result':'ok'";
			} else if (value.equals("getRTSPAddr")) {
				String resultString = device.getMedia().getRTSPStreamUri(0);

				data.data = resultString;

			}
//add some judgement for session
			if (session!=null && (session.isActiveMQ())) {
				new UpperOperation().msgReceive(data, session);
			} else {
				exec.execute(new PEOperationForAtomicVE().new GetDataFromDevice(
						data.peID, data.interfaceID, data.data, session));
			}
		} else if (set.contains("control")) {
			String value = jsonData.getString("control");
			if (value.equals("absoulte_move")) {

			} else if (value.equals("continues_move")) {

			}
			;
			return "OK";
		}
		return "OK";
	}

	static public List<String> discovery() {

		final Collection<URL> urls = DeviceDiscovery.discoverWsDevicesAsUrls(
				"^http$", ".*onvif.*");
		List<String> results = new ArrayList<String>();
		for (URL url : urls) {
			if (!url.getHost().startsWith("[", 0)) {
				results.add(url.getHost());
			}
		}
		return results;
	}

	public void moveX(String direction) {
		if (direction.equals("left")) {
			stepMove(1, 0, 0);
		} else if (direction.equals("right")) {
			stepMove(-1, 0, 0);
		}
	}

	public void moveY(String direction) {
		if (direction.equals("up")) {
			stepMove(0, 1, 0);
		} else if (direction.equals("down")) {
			stepMove(0, -1, 0);
		}
	}

	public void zoom(String direction) {
		if (direction.equals("in")) {
			stepMove(0, 0, 1);
		} else if (direction.equals("out")) {
			stepMove(0, 0, -1);
		}
	}

	public void stepMove(float x, float y, float zoom) {
		ptzDevices.continuousMove(profileToken, x, y, zoom);
		try {
			Thread.sleep(STEP_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ptzDevices.continuousMove(profileToken, 0, 0, 0);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Cam Test begin!!");
		OnvifCam cam = new OnvifCam("192.168.12.237", "admin", "12345",
				"1234567890", "123");
		cam.action("{'easy_control':'left'}", null);
		System.out.println("OK");

	}
}
