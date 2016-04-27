package com.cetc.iot.accesssystem.downinterface.protocol.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.communicate.PESession;
import com.cetc.iot.middle.PEOperationForAtomicVE;

public class HttpAdaptor {
	
	private static Logger logger = Logger.getLogger(HttpAdaptor.class.getName());
	
	static Map<String, HttpAdaptor> httpList = new HashMap<String, HttpAdaptor>();

	String peID;
	String url;
	String rule;
	int method;
	String interfaceID;
	Map<String, String> paramList;
	private static ExecutorService exec = Executors.newCachedThreadPool();

	public static HttpAdaptor getAdaptor(String peID, String interfaceID) {
		// TODO Auto-generated method stub
		return httpList.get(peID + interfaceID);
	}

	public class HttpThread implements Runnable {
		private String cmd;
		private PESession session;

		public HttpThread(String cmd, PESession session) {
			this.cmd = cmd;
			this.session = session;
		}

		@Override
		public void run() {
			// TODO: param of PE will stored in database

			StringBuilder queryString = new StringBuilder();
			String resultString = null;
			Map<String, String> params = new HashMap<String, String>();

			// parse cmd
			JSONObject jsonObject = JSONObject.fromObject(cmd);
			Set keySet = jsonObject.keySet();

			//queryString.append("?");
			for (Object key : keySet) {
				Object value = jsonObject.get(key);
				if (method == HttpToolkit.METHOD_GET) {
					queryString.append(key + "=" + value + "&");
				} else if (method == HttpToolkit.METHOD_POST) { // post
					params.put(key.toString(), value.toString());
				}
			}
			// parse rule TODO:Maybe construct a rule parser?
			Set paramKeySet = paramList.keySet();
			
			for (Object key : paramKeySet) {
				if(rule.contains("$" + key)){
					queryString.append(key + "=" + paramList.get(key) + "&");
				}
			}

			// do get or post
			logger.info("HTTP Sending... URL is: "+url+"   Method is: "+method+"  Query is: "+queryString+"  Map is: "+params);
			if (method == HttpToolkit.METHOD_GET) {
				resultString = HttpToolkit.doGet(url, queryString.toString(),
						"utf-8", false);

			} else if (method == HttpToolkit.METHOD_POST) {
				resultString = HttpToolkit.doPost(url, params, "utf-8", false);
			}

			PEData data = new PEData();
			data.peID = peID;
			data.interfaceID = interfaceID;
			data.data = resultString;

			if (session.isActiveMQ()) {
				try {
					new UpperOperation().msgReceive(data, session);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				exec.execute(new PEOperationForAtomicVE().new GetDataFromDevice(
						data.peID, data.interfaceID, data.data, session));
			}
			return;
		}

	}
}
