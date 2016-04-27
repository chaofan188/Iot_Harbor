package com.cetc.iot.servicesystem.test;

import net.sf.json.JSONObject;

import com.cetc.iot.servicesystem.service.ServiceRequest;
import com.cetc.iot.servicesystem.service.ServiceResponse;
import com.cetc.iot.servicesystem.service.VeObject;
import com.cetc.iot.servicesystem.util.DataLoopReceiverNew;

public class ComplexVeTest extends VeObject{
	private String clientId;
	private String veId;
	private String peIdViberator;
	private String peIdTemperature;
	private String control;
	private String temTime;
	private int  data2; 
	@Override
	public void service(ServiceRequest arg0, ServiceResponse arg1) {
		
		//ȡͨ�õĲ���
		clientId = arg0.getParameter("clientId").toString();
		veId = arg0.getParameter("veId").toString();
		String serviceName = arg0.getCtrl();
		JSONObject param = (JSONObject) arg0.getParameter("param");
		
		peIdViberator = (String)param.get("viberator");
		peIdTemperature = (String)param.get("temperature");
		
		control = param.getString("Temperatureswitcher"); 
		temTime = param.getString("temperaturetime");
		
		//ƥ��һ������
		if("ComplexService".equals(serviceName)){
			
			//ȡ��ҵ�����������д�߼�,����param����identifyId��peId�ļ�ֵ��ϵ��
			//���Ը�ݿ������Լ������identifyId��ȡ��Ӧ��peId�����в���
			
			JSONObject json = new JSONObject();
			json.put("switch", param.get("viberswitcher"));
			json.put("time", Integer.parseInt((String)param.get("vibertime")));
			
			//��PE���Ϳ���ָ��
			DataLoopReceiverNew.send(peIdViberator, "sensor-control",json.toString() );
			//��PE���Ͷ���ָ��
			DataLoopReceiverNew.receiveData(veId, peIdViberator, "sensor-get", this);

			
		}
		
	}

	@Override
	public void receive(String json) {
		JSONObject json1 = JSONObject.fromObject(json);
		String peID = (String)json1.get("peID");
		String iFID = (String)json1.get("IFID");
		String data = (String)json1.get("Data");
		
		
		if (peID.equals(peIdViberator)&&iFID.equals("sensor-get")){
		JSONObject json2 = JSONObject.fromObject(data); 
		String zvalue = (String)json2.get("zvalue");

		int zvalueInt = Integer.parseInt(zvalue);
		if(zvalueInt >100 ){
			
			JSONObject json3 = new JSONObject();
			json3.put("control", control);
			json3.put("time", Integer.parseInt(temTime));
			//��PE���Ϳ���ָ��
			DataLoopReceiverNew.send(peIdTemperature, "sensor-control", json3.toString());
			//��PE���Ͷ���ָ��
			DataLoopReceiverNew.subscribe(veId, peIdTemperature, "sensor-get", clientId);	
			JSONObject json4 = new JSONObject();
			json4.put("switch", "off");
			json4.put("time", 0);
			DataLoopReceiverNew.send(peIdViberator, "sensor-control", json4.toString());
			}
		}
	}

}
