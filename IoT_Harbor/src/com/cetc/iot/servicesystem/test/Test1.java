package com.cetc.iot.servicesystem.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cetc.iot.database.dao.VEDao;
import com.cetc.iot.database.dao.VeGeoLocationDao;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VEAndTplInfo;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.database.model.VeGeoLocation;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.VETemplateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class Test1 {

	@Autowired
	private VEService veService;
	@Autowired
	private VETemplateService vet;
	@Autowired
	private VEDao vedao;
	@Autowired
	private VeGeoLocationDao veGeoLocationDao;
	
	@Test
	public void Test1(){
		
		/*List<Map<String, Object>> list= veService.getVEAndTplInfoByCreatorID("11");
		System.out.println(list.toString());
		Map<String, Object> peMap = new HashMap<String, Object> ();
		peMap.put("template_id", 1);
		String a= Ve2Xml.ve2Xml(veService.getVEByID("iot_bj_cetc15:lamp*"),peMap);
		System.out.println(a);
		
		List<Map<String, Object>> list= vet.getTplList(null, null, null, null, null, null, null, null, 2, 10);
		System.out.println(list.toString());*/
		//Map<String,String> a =new HashMap<String,String>();
		//System.out.println("111111111111");
		/*VETemplate v = new VETemplate();
		int a = vet.query(v, 0, 100).size();
		System.out.println("a:"+a);
		*/
		VE  ve =new VE();
		ve.setVe_id("1");
		List<VE> velist=veService.query(ve, 0, 1);
		System.out.println("velist:"+velist.size());
		System.out.println(velist.get(0));
		
		/*VE  ve =new VE();
		VeGeoLocation veGeolocation = new VeGeoLocation();
		veGeolocation.setAltitude(0);
		ve.setTemplate_id("11");
		ve.setVe_creator_id("22");
		ve.setVe_description("22");
		ve.setVe_key("22");
		ve.setVe_name("22");
		ve.setVe_privacy("22");
	    veService.add(ve, veGeolocation);*/
		
		/*VE  ve =new VE();
		//VeGeoLocation veGeolocation = new VeGeoLocation();
		//veGeolocation.setVe_id("123214");
		ve.setTemplate_id("1");
		ve.setVe_creator_id("2");
		ve.setVe_description("2");
		ve.setVe_key("2");
		ve.setVe_name("2");
		ve.setVe_privacy("2");
		//System.out.println("123:"+veGeoLocationDao.delete(veGeolocation));
	    veService.delete(ve);*/
		
		/*VE  ve =new VE();
		VeGeoLocation veGeolocation = new VeGeoLocation();
		ve.setVe_id("102150123183244192168121531001");
		ve.setTemplate_id("1");
		ve.setVe_creator_id("2");
		ve.setVe_description("2");
		ve.setVe_key("3");
		ve.setVe_name("3");
		ve.setVe_privacy("2");
	    veService.update(ve, null);*/
		
	}
	@Test
	public void Test2(){
		VE  ve =new VE();
		VeGeoLocation veGeolocation = new VeGeoLocation();
		veGeolocation.setAltitude(1);
		veGeolocation.setCity_id(1);
		veGeolocation.setCountry_id(1);
		//veGeolocation.setGeolocation_id("1111");
		veGeolocation.setLatitude(1);
		veGeolocation.setLongitude(1);
		veGeolocation.setOtherInfo("温度传感器的位置");
		veGeolocation.setProvince_id(1);
		veGeolocation.setVe_id("123311123124454");
		ve.setTemplate_id("000014");
		ve.setVe_creator_id("18");
		ve.setVe_description("调试区温度传感器");
		ve.setVe_key("11");
		ve.setVe_name("temperatureSensor11111111222222");
		ve.setVe_privacy("002");
		ve.setGeo(veGeolocation);
		//ve.setVe_id("234");
		ve.setVe_state("001");
	    veService.add(ve, veGeolocation);
		
	}

	@Test
	public void Test4(){
		List<VEAndTplInfo> list = veService.getVEAndTplInfoByCreatorID("18");
		JSONArray json = JSONArray.fromObject(list);
		System.out.println("json:::"+json.toString());
		
	}
	@Test
	public void Test5(){
		List<VEAndTplInfo> list = veService.getVEAndTplInfoByCreatorID("18");
		JSONArray json = JSONArray.fromObject(list);
		System.out.println("json:::"+json.toString());
		
	}
	
}
