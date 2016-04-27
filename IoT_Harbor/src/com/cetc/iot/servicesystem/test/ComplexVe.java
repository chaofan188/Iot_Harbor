package com.cetc.iot.servicesystem.test;


import net.sf.json.JSONObject;

import com.cetc.iot.servicesystem.service.ServiceRequest;
import com.cetc.iot.servicesystem.service.ServiceResponse;
import com.cetc.iot.servicesystem.service.Topic;
import com.cetc.iot.servicesystem.service.VeObject;
import com.cetc.iot.servicesystem.service.impl.CallCenterSession;
import com.cetc.iot.servicesystem.util.DataLoopReceiverNew;


public class ComplexVe extends VeObject {

	public void init(){
		addTopic(new Topic());
	}

	public void genEvent(){
		//TODO ѡ��Topic���γ��¼�����
		int arg = 0;
		getTopic(0).notifySubors(arg);
	}

	@Override
	public void service(ServiceRequest req, ServiceResponse resp) {

		String clientId = (String) req.getParameter("clientId");

		System.out.println("进入复合VE！"+req.getParameter("veId")+"++"+req.getCtrl()+"++"+req.getParameter("param"));

		if(req.getParameter("veId")==null||req.getCtrl()==null||req.getParameter("param")==null){
			CallCenterSession.send(clientId, "你传的数据格式不对");
			return;
		}

		String veId = (String)req.getParameter("veId");
		JSONObject param = (JSONObject) req.getParameter("param");

		String identifyIdForCar = "aaa";
		String identifyIdForSensor = "bbb";
		String peidForCar = "";
		String peidForSensor = "";
		if(param.containsKey(identifyIdForCar)){
			peidForCar = (String) param.get(identifyIdForCar);
		}
		if(param.containsKey(identifyIdForSensor)){
			peidForSensor = (String) param.get(identifyIdForSensor);
		}



		System.out.println("*************************");


		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		if("ComplexService".equalsIgnoreCase(req.getCtrl())){
			if(param.get("movementAmount")==null){
				param.accumulate("movementAmount", "0");
			}
			if(param.get("switcher")==null){
				param.accumulate("switcher", "on");
			}

			json1.put("control", param.get("direction"));
			json1.put("time",""+param.get("movementAmount"));
			String peIdsForCar[] = peidForCar.split(",");
			for(int i=0;i<peIdsForCar.length;i++){
				DataLoopReceiverNew.send(peIdsForCar[i], "car_control", json1.toString());
			}



			String peIdsForSensor[] = peidForSensor.split(",");
			for(int i=0;i<peIdsForSensor.length;i++){
				if("on".equals(param.get("temperatureswitcher").toString())){
					if(param.get("refreshTime")==null){
						param.accumulate("refreshTime", 5);
					}
					json2.put("control", "on");
					json2.put("time", Integer.parseInt(param.get("refreshTime").toString()));
					DataLoopReceiverNew.send(peIdsForSensor[i], "sensor-control", json2.toString());
					DataLoopReceiverNew.subscribe(veId,peIdsForSensor[i], "sensor-control", clientId);
					//DataLoopReceiverNew.receiveData(veId, peId, "sensor_control", this);
				}
				if("on".equals(param.get("temperatureswitcher").toString())){}
				//停止订阅
				if("off".equals(param.get("switcher").toString())){
					json2.put("control", "off");
					json2.put("time", 0);
					DataLoopReceiverNew.send(peIdsForSensor[i], "sensor-control", json2.toString());
					DataLoopReceiverNew.unsubscribe(clientId);
				}
			}



		}

	}

	@Override
	public void receive(String a) {
		// TODO Auto-generated method stub

	}

}
