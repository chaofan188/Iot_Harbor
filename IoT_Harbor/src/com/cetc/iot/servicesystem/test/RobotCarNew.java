package com.cetc.iot.servicesystem.test;


import net.sf.json.JSONObject;

import com.cetc.iot.servicesystem.service.ServiceRequest;
import com.cetc.iot.servicesystem.service.ServiceResponse;
import com.cetc.iot.servicesystem.service.Topic;
import com.cetc.iot.servicesystem.service.VeObject;
import com.cetc.iot.servicesystem.service.impl.CallCenterSession;
import com.cetc.iot.servicesystem.util.DataLoopReceiverNew;


public class RobotCarNew extends VeObject {

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

		if(req.getParameter("veId")==null||req.getParameter("serviceName")==null||req.getParameter("param")==null){
			CallCenterSession.send(clientId, "你传的数据格式不对");
			return;
		}

		String veId = (String)req.getParameter("veId");
		JSONObject param = (JSONObject) req.getParameter("param");
		String peId = "12345678901234567890123456789013";

		System.out.println("*************************");

		/*
			JSONObject json = new JSONObject();
			if ("x".equalsIgnoreCase(req.getCtrl())){
				json.put("control", req.getParameter("values"));
				DataLoopReceiverNew.send(peId, "cam_interface_setX", json.toString());
			}
			else if ("y".equalsIgnoreCase(req.getCtrl())){
				json.put("control", req.getParameter("values"));
				DataLoopReceiverNew.send(peId, "cam_interface_setY", json.toString());
			}
			else {
				json.put(req.getCtrl(),req.getParameter("values"));
				DataLoopReceiverNew.send(peId, "car_interface", json.toString());
			}*/
		JSONObject json = new JSONObject();
		if("carControl".equalsIgnoreCase(req.getCtrl())){
			if(param.get("movementAmount")==null){
				param.accumulate("movementAmount", "0");
			}
			json.put(param.get("function"),param.get("movementAmount"));
			DataLoopReceiverNew.send(peId, "car_interface", json.toString());
		}else if("directionControl".equalsIgnoreCase(req.getCtrl())){
			if(param.get("movementAmount")==null){
				param.accumulate("movementAmount", "0");
			}
			json.put(param.get("direction"),param.get("movementAmount"));
			DataLoopReceiverNew.send(peId, "car_interface", json.toString());

		}else if("x".equalsIgnoreCase(req.getCtrl())){
			if(param.get("movementAmount")==null){
				param.accumulate("movementAmount", "0");
			}
			json.put("control", param.get("movementAmount"));
			DataLoopReceiverNew.send(peId, "cam_interface_setX", json.toString());
		}else if("y".equalsIgnoreCase(req.getCtrl())){
			if(param.get("movementAmount")==null){
				param.accumulate("movementAmount", "0");
			}
			json.put("control",param.get("movementAmount"));
			DataLoopReceiverNew.send(peId, "cam_interface_setY", json.toString());
		}

	}

	@Override
	public void receive(String a) {
		// TODO Auto-generated method stub
		
	}

}
