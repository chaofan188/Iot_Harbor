package com.cetc.iot.accesssystem.downinterface.protocol.ezviz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.communicate.PESession;
import com.cetc.iot.database.model.EzvizCamera;
import com.cetc.iot.harbormanage.service.EzvizCameraService;
import com.cetc.iot.middle.PEOperationForAtomicVE;
import com.cetc.iot.middle.PEOperationForAtomicVE.GetDataFromDevice;
import com.cetc.iot.servicesystem.util.GetBean;

public class EzvizCam {
	
	
	static Map<String, EzvizCam> camList = new HashMap<String, EzvizCam>();
	final long STEP_TIME = 100;
	/*OnvifDevice device = null;
	PtzDevices ptzDevices = null;
	String profileToken = null;*/
	String peid;
	String interfaceID;
	String key;
	String cameraname;
	String secret;
	private static ExecutorService exec = Executors.newCachedThreadPool();

	
	EzvizCam(String peid, String interfaceID){
		//Get onvif camera instance
		
		this.peid = peid;
		this.interfaceID = interfaceID;
		//Add cam device to the list
		camList.put(peid + interfaceID, this);
	}
	
	public static EzvizCam getCam(String peid, String interfaceID){
		
		return camList.get(peid + interfaceID);
	}
	
	public class EzvizCamThread implements Runnable{
		private String cmd;
		private PESession session;
		
		public EzvizCamThread(String cmd, PESession session) {
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
	
	//此函数完成json命令到本地函数调用的转化适配
	public String action(String cmd, PESession session) throws Exception{
		//根据data内容进行摄像头的操作
		PEData data = new PEData();
		data.peID = peid;
		data.interfaceID = interfaceID;
		String result="";
		JSONObject jsonData = JSONObject.fromObject(cmd);
		Set set = jsonData.keySet();
		if(set.contains("ezviz_control")){
			String value = jsonData.getString("ezviz_control");
			if(value.equals("getM3u8Url")){
				result = getM3u8Url(this.peid,this.interfaceID);
				data.data = result;
			} 
			
			
			if (session.isActiveMQ()) {
				new UpperOperation().msgReceive(data, session);
			} else {
				exec.execute(new PEOperationForAtomicVE().new GetDataFromDevice(
						data.peID, data.interfaceID, data.data, session));
			}
		} 
		return  "OK";
	}
	
	public static String getM3u8Url(String peid,String interfaceid){
	    EzvizCameraService ezvizCameraService = (EzvizCameraService)GetBean.getBean("ezvizCameraService");
        EzvizCamera ezvizCamera = new EzvizCamera();
		ezvizCamera.setPe_id(peid);
		ezvizCamera.setInterface_id(interfaceid);
		List<Map<String, Object>> list = ezvizCameraService.query(ezvizCamera, -1, -1);
		String result = (String)list.get(0).get("m3u8Url");
		return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Cam Test begin!!");
		EzvizCam cam = new EzvizCam("56eacb76e47fa3f350fb283685ed81f9", "465465");
		String result = cam.action("{'ezviz_control':'getM3u8Url'}", null);
		System.out.println(result);
		
	}
}
