package com.cetc.iot.servicesystem.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.cetc.iot.database.dao.ConDB;
import com.cetc.iot.servicesystem.service.ServiceRequest;
import com.cetc.iot.servicesystem.service.ServiceResponse;
import com.cetc.iot.servicesystem.service.Topic;
import com.cetc.iot.servicesystem.service.VeObject;
import com.cetc.iot.servicesystem.service.impl.CallCenterSession;
import com.cetc.iot.servicesystem.util.DataLoopReceiverNew;


public class temperatureSensorTest extends VeObject {
	private String data;
	//topic订阅VE服务
	public void init(){
		addTopic(new Topic());
	}

	public void genEvent(){
		int arg = 0;
		getTopic(0).notifySubors(arg);
	}
	String veId;
	String serviceName;
	JSONObject param;
	String peId;
	@Override
	public void service(ServiceRequest req, ServiceResponse resp) {

		String clientId = req.getParameter("clientId").toString();
		if(req.getParameter("veId")==null||req.getParameter("param")==null){
			CallCenterSession.send(clientId, "你传的数据格式不对");
			return;
		}

		 String identifyIdForSensor="111";
		 veId = req.getParameter("veId").toString();
		 serviceName = req.getCtrl();
		 param = (JSONObject) req.getParameter("param");
		 peId = (String) param.get(identifyIdForSensor);
		
		

		JSONObject json = new JSONObject();
		if("getValueControl".equalsIgnoreCase(serviceName)){
			//开始订阅
			if(param.get("switcher")==null){
				param.accumulate("switcher", "on");
			}
			if("on".equals(param.get("switcher").toString())){
				if(param.get("refreshTime")==null){
					param.accumulate("refreshTime", 5);
				}
				json.put("control", "on");
				json.put("time", Integer.parseInt(param.get("refreshTime").toString()));
				
				DataLoopReceiverNew.send(peId, "sensor-control", json.toString());
				//DataLoopReceiverNew.subscribe(veId,peId, "sensor_control", clientId);
				DataLoopReceiverNew.receiveData(veId, peId, "sensor-get", this);
			    //这里直接调用名叫data的全局变量，这个data就是传感器上报的数据

		   			
			
			}
			//停止订阅
			if("off".equals(param.get("switcher").toString())){
				json.put("off", 0);
				DataLoopReceiverNew.send(peId, "sensor_control", json.toString());
				DataLoopReceiverNew.unsubscribe(clientId);
			}

		}else if("eventSubscribe".equalsIgnoreCase((String)req.getParameter("serviceName"))){
			if(param.get("refreshTime")==null){
				param.accumulate("refreshTime", 5);
			}
			json.put("on", Integer.parseInt(param.get("refreshTime").toString()));
			DataLoopReceiverNew.send(peId, "sensor_control", json.toString());
			DataLoopReceiverNew.eventSubscribe(veId,peId, "sensor_control", clientId,param);

		}

	}

	@Override
	public void receive(String data) {
		JSONObject json1 = JSONObject.fromObject(data);
		String peID = (String)json1.get("peID");
		String iFID = (String)json1.get("IFID");
		String dataValue = (String)json1.get("Data");
		
		
		if (peID.equals(peId)&&iFID.equals("sensor-get")){
			
           //以下是连接数据库过程，insert()
	    	ConDB db = new ConDB();
	    	//url是你的数据库的地址
			String url="jdbc:mysql://192.168.12.11:3306/iot_tss_db";
			//username是数据库的登录用户名
			String username="root";
			//password是登录数据库的密码
			String password="111111";
			Map<String,Object> map = new HashMap<String,Object>();
			//map用来存储你要插进数据库的数值，key数据库表的字段，value是该表该字段该记录的值，需要给哪个字段赋值就放进map里。tips:表里非空字段必须赋值。
			map.put("veid", peID+Math.random());
			map.put("value", dataValue);
		
			db.insert(url,username,password,"iot_db",map);
			//下面是查询数据库的的实例
			/*ResultSet rs = db.query(url, username, password, tableName);
			while(rs.next()){
				String veid = rs.getString("veid");
				System.out.println("veid:"+veid);
				String value = rs.getString("value");
				System.out.println("value:"+value);
			}*/
		}
	}

}
