package com.cetc.iot.servicesystem.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.cetc.iot.database.model.CompleteTpl;
import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.database.model.VeGeoLocation;
import com.cetc.iot.database.model.VeKeyConfig;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.database.model.VeWebsocketServiceParam;
import com.cetc.iot.database.model.VeWebsocketServiceReturnParam;
import com.cetc.iot.servicesystem.service.TemplateBindService;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.VETemplateService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;
import com.cetc.iot.servicesystem.service.VeKeyConfigService;
import com.cetc.iot.servicesystem.service.VePeBindService;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.servicesystem.util.Ve2Xml;
import com.cetc.iot.servicesystem.util.VeServiceKey;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * register atomic_ve_template
 * register atomic_ve
 * @author website
 *
 */
public class AtomicRegister {
	
	private static Logger logger = Logger.getLogger(AtomicRegister.class.getName());
	
	/**
	 * 1.添加iot_ve
	 * 2.添加iot_ve_geolocation
	 * 3.添加iot_ve_pe_bind
	 * 4.添加iot_ve_key_config
	 * @param veInfo
	 * @return
	 */
	public static String registerAtomicVe(String veInfo){
		
		JSONObject vejson = JSONObject.fromObject(veInfo);
		//实例化ve		
		VE ve = new VE();
		String veName = vejson.getString("veName");//ve名称
		//String veKey = vejson.getString("vePassword");//vekey
		String privacy = vejson.getString("privacy");//ve公开性
		String templateId = vejson.getString("templateId");//pe模板id
		boolean directVEID = (Boolean) vejson.get("directVEID");
		String veid =null;
		if(directVEID==true){
			 veid = vejson.getString("deviceID");
		}else if(directVEID==false){
			//do nothing 
		}
		//String veid = vejson.getString("deviceID");//原子VE的veid是以deviceID命名的
		String description = "";//ve描述
		if(vejson.containsKey("description")){
			description =  vejson.getString("description");
		}
		String peId = vejson.getString("peId");//pe的信息
		/*String country =vejson.getString("country");
		String province =vejson.getString("province");
		String city =vejson.getString("city");*/
		String country="1";
		String province="1";
		String city="1";
		String longitude ="";		
		String latitude ="";
		String altitude ="0";
		String otherInfo ="";
		if(vejson.containsKey("otherInfo")){
			otherInfo =vejson.getString("otherInfo");
		}
		//根据petemplateID和原子标识identify_id到iot_vetemplate_petemplate_bind表中查询vetemplateId
		TemplateBindService templatebindservice = (TemplateBindService) GetBean.getBean("templateBindService");
		TemplateBind templatebind = new TemplateBind();
		templatebind.setPe_template_id(templateId);
		templatebind.setIdentify_id("000");
		List<TemplateBind> list = templatebindservice.query(templatebind, 0, 10);
		if(list.size()==0){
			return "petemplate has not binded any vetemplate";
		}
		String vetemplateId = list.get(0).getVe_template_id();
		if(vetemplateId==null || "".equals(vetemplateId)){
			return "false";
		}
		ve.setVe_id(veid);
		ve.setTemplate_id(vetemplateId);
		ve.setVe_creator_id("18");//ve的创建者，目前写死
		ve.setVe_description(description);
		ve.setVe_key("111");//vekey,目前没有用，先写死
		ve.setVe_name(veName);
		ve.setVe_privacy(privacy);
		ve.setVe_state("001");//ve的状态信息，感觉没啥用
		ve.setVe_atom("1");//原子ve
		//ve的地理位置信息
		VeGeoLocation veGeoLocation = new VeGeoLocation();
		veGeoLocation.setAltitude(Float.parseFloat(altitude));
		veGeoLocation.setCity_id(Integer.parseInt(city));
		veGeoLocation.setCountry_id(Integer.parseInt(country));
		veGeoLocation.setProvince_id(Integer.parseInt(province));
		if(vejson.containsKey("longitude")){
			longitude =vejson.getString("longitude");
			veGeoLocation.setLongitude(Float.parseFloat(longitude));
		}
		if(vejson.containsKey("latitude")){
			latitude =vejson.getString("latitude");
			veGeoLocation.setLatitude(Float.parseFloat(latitude));
		}
		veGeoLocation.setOtherInfo(otherInfo);
		ve.setGeo(veGeoLocation);//ve初始化已经完成
		VEService veService = (VEService)GetBean.getBean("veService");		
		//插入记录到iot_ve和iot_ve_geolocation表中
		if("success".equals(veService.add(ve, veGeoLocation))){
			//获取刚插入的veId
			String veId = veService.query(ve, 0, 100).get(0).getVe_id();
			//实例化VePeBind
			VePeBind vpb = new VePeBind();
			vpb.setVe_id(veId);
			vpb.setIdentify_id("000");//原子ve的标识
			vpb.setPe_id(peId);
			vpb.setState("1");//审核状态：1表示通过		
			vpb.setBind_type("0");//绑定类型：0为必要型
			//执行插入操作
			VePeBindService vepebindservice = (VePeBindService) GetBean.getBean("bindService");
			
			//实例化VeWebsocketService，用于查询该ve模板下的服务
			VeWebsocketService veWebsocketService = new VeWebsocketService();
			veWebsocketService.setTemplate_id(vetemplateId);
			VEWebsocketServiceService veWebsocketServiceService = (VEWebsocketServiceService) GetBean.getBean("veWebsocketServiceService");
			VeKeyConfigService veKeyConfigService = (VeKeyConfigService) GetBean.getBean("veKeyConfigService");
			//查询到的服务列表
			List<VeWebsocketService> serviceList = veWebsocketServiceService.query(veWebsocketService, 0, 1000);
			for(int j = 0;j<serviceList.size();j++){
				vpb.setService_id(serviceList.get(j).getService_id());
				vepebindservice.add(vpb);
				//将每个服务的key添加到iot_ve_key_config表中
				VeKeyConfig veKeyConfig1 = new VeKeyConfig();
				veKeyConfig1.setVe_id(veId);
				veKeyConfig1.setService_id(serviceList.get(j).getService_id());
				veKeyConfig1.setBase_key(VeServiceKey.getKey());
				veKeyConfigService.add(veKeyConfig1);
				logger.info("veID: "+veId+"..........ServiceID: "+serviceList.get(j).getService_id());
			}
			//返回给应用的xml描述
			String result = Ve2Xml.ve2Xml(veService.getVeByVeId(veId),1);
			logger.info("result: "+result);
			return result;
		}else{
			return "false";
		}
	} 
	public static boolean registerAtomicVeTemplate(String veTemplateInfo){
		JSONObject veTemplateJson = JSONObject.fromObject(veTemplateInfo);
		String tplAlias = "";
		String tplOpenness ="1";
		String tpl_classpath = "" ;
		
		Map<String,Object> methodParam = new HashMap<String,Object>();
		Multimap<String, String> IdentifyIdServiceName=ArrayListMultimap.create();
		methodParam.put("tplAlias", tplAlias);
		methodParam.put("tplOpenness",tplOpenness );
		methodParam.put("tpl_classpath",tpl_classpath);
		
		
		
		CompleteTpl cTpl = new CompleteTpl();
		VETemplate tpl = new VETemplate();
		Set<TemplateBind> templateBindSet = new HashSet<TemplateBind>();
		Set<VeWebsocketService> serviceSet = new HashSet<VeWebsocketService>();
		Set<VeWebsocketServiceParam> paramSet = new HashSet<VeWebsocketServiceParam>();
		Set<VeWebsocketServiceReturnParam> returnParamSet = new HashSet<VeWebsocketServiceReturnParam>();
		
		tpl.setTpl_id(veTemplateJson.getString("model_id"));
		tpl.setTemplate_openness("1");
		tpl.setTpl_alias("mine");
		tpl.setTpl_classpath("");
		tpl.setTpl_description(veTemplateJson.getString("description"));
		tpl.setTpl_developer("ve");
		tpl.setTpl_display("");
		tpl.setTpl_name(veTemplateJson.getString("name"));
		tpl.setTpl_owner("ve");
		tpl.setTpl_state("001");
		tpl.setTpl_type_id("000000");
		tpl.setTpl_version("1.0");
		
		JSONArray interfaceArray =  veTemplateJson.getJSONArray("interfaces");
		if(interfaceArray.size()==0){
			return false;
		}
		for(int i=0;i<interfaceArray.size();i++){
			JSONObject tmp = interfaceArray.getJSONObject(i);
			VeWebsocketService veWebsocketService = new VeWebsocketService();
			//判断是否是上行接口
			//service_name是interfaceID和上下行标识组成
			if("up".equals(tmp.getString("interface_direction"))){
				//上行接口，就向参数中添加订阅通道开关参数
				veWebsocketService.setService_name(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
				IdentifyIdServiceName.put("000", tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
				VeWebsocketServiceParam veWebsocketServiceParam = new VeWebsocketServiceParam();
				veWebsocketServiceParam.setService_id(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
				veWebsocketServiceParam.setParam_name("subscribe");
				veWebsocketServiceParam.setParam_type("String");
				veWebsocketServiceParam.setParam_value("on;off");
				paramSet.add(veWebsocketServiceParam);
				VeWebsocketServiceReturnParam veWebsocketServiceReturnParam = new VeWebsocketServiceReturnParam();
				veWebsocketServiceReturnParam.setService_id(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
				veWebsocketServiceReturnParam.setReturnParam_name("justFULLFILLDEMAND");
				veWebsocketServiceReturnParam.setReturnParam_type("String");
				veWebsocketServiceReturnParam.setReturnParam_description("NULL");
				returnParamSet.add(veWebsocketServiceReturnParam);
			} else if("down".equals(tmp.getString("interface_direction"))) {
				//下行接口，就按照PE模板继续填写
				veWebsocketService.setService_name(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
				IdentifyIdServiceName.put("000", tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
				logger.info(IdentifyIdServiceName.get("000").toString());
				JSONArray paramArray =  tmp.getJSONArray("param");
				JSONArray returnParamArray =  tmp.getJSONArray("return_param");
				if(paramArray.size()==0){
					return false;
				}
				for(int j=0;j<paramArray.size();j++){
					JSONObject temp = paramArray.getJSONObject(j);
					VeWebsocketServiceParam veWebsocketServiceParam = new VeWebsocketServiceParam();
					veWebsocketServiceParam.setService_id(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
					veWebsocketServiceParam.setParam_name(temp.getString("key"));
					veWebsocketServiceParam.setParam_type(temp.getString("type"));
					veWebsocketServiceParam.setParam_value(temp.getString("option"));
					paramSet.add(veWebsocketServiceParam);
				}
				if(!returnParamArray.isEmpty()||returnParamArray.size()!=0){
					for(int j=0;j<returnParamArray.size();j++){
						JSONObject temp = returnParamArray.getJSONObject(j);
						VeWebsocketServiceReturnParam veWebsocketServiceReturnParam = new VeWebsocketServiceReturnParam();
						veWebsocketServiceReturnParam.setService_id(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
						veWebsocketServiceReturnParam.setReturnParam_name(temp.getString("key"));
						veWebsocketServiceReturnParam.setReturnParam_type(temp.getString("type"));
						veWebsocketServiceReturnParam.setReturnParam_description(temp.getString("description"));
						returnParamSet.add(veWebsocketServiceReturnParam);
					}
				}
			} else if("bidirection".equals(tmp.getString("interface_direction"))) {
				//请求响应接口
				veWebsocketService.setService_name(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
				IdentifyIdServiceName.put("000", tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
				System.out.println(IdentifyIdServiceName.get("000").toString());
				JSONArray paramArray =  tmp.getJSONArray("param");
				JSONArray returnParamArray =  tmp.getJSONArray("return_param");
				if(paramArray.size()==0){
					return false;
				}
				for(int j=0;j<paramArray.size();j++){
					JSONObject temp = paramArray.getJSONObject(j);
					VeWebsocketServiceParam veWebsocketServiceParam = new VeWebsocketServiceParam();
					veWebsocketServiceParam.setService_id(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
					veWebsocketServiceParam.setParam_name(temp.getString("key"));
					veWebsocketServiceParam.setParam_type(temp.getString("type"));
					veWebsocketServiceParam.setParam_value(temp.getString("option"));
					paramSet.add(veWebsocketServiceParam);
				}
				if(!returnParamArray.isEmpty()||returnParamArray.size()!=0){
					for(int j=0;j<returnParamArray.size();j++){
						JSONObject temp = returnParamArray.getJSONObject(j);
						VeWebsocketServiceReturnParam veWebsocketServiceReturnParam = new VeWebsocketServiceReturnParam();
						veWebsocketServiceReturnParam.setService_id(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
						veWebsocketServiceReturnParam.setReturnParam_name(temp.getString("key"));
						veWebsocketServiceReturnParam.setReturnParam_type(temp.getString("type"));
						veWebsocketServiceReturnParam.setReturnParam_description(temp.getString("description"));
						returnParamSet.add(veWebsocketServiceReturnParam);
					}
				}
			}
			
			//增加pe属性查询服务和具体属性查询服务
			if(tmp.containsKey("description")){
				veWebsocketService.setService_description(tmp.getString("description"));
			}
			serviceSet.add(veWebsocketService);
			//templateBind
			TemplateBind templateBind = new TemplateBind();
			templateBind.setPe_template_id(veTemplateJson.getString("model_id"));
			templateBind.setBind_type("0");
			templateBind.setIdentify_id("000");
			templateBind.setBind_max(1);
			templateBind.setBind_min(1);
			templateBind.setService_id(tmp.getString("interfaceID")+"_"+tmp.getString("interface_direction"));
			templateBind.setTemplate_bind_id(""+i);
			templateBindSet.add(templateBind);
		}
		/////////为原子ve模板添加查询pe所有属性和某一具体属性的接口
		VeWebsocketService veWebsocketServiceAllProp = new VeWebsocketService();
		VeWebsocketService veWebsocketServiceSpeProp = new VeWebsocketService();
		veWebsocketServiceAllProp.setService_name("getPeAllParameters");
		veWebsocketServiceSpeProp.setService_name("getPeSpecificParameter");
		veWebsocketServiceAllProp.setService_description("获取pe所有属性名称");
		veWebsocketServiceSpeProp.setService_description("获取pe一个具体属性值");
		serviceSet.add(veWebsocketServiceAllProp);
		serviceSet.add(veWebsocketServiceSpeProp);
		//为getPeSpecificProperty添加获取哪个参数的形参
		VeWebsocketServiceParam veWebsocketServiceParam = new VeWebsocketServiceParam();
		veWebsocketServiceParam.setService_id("getPeSpecificParameter");
		veWebsocketServiceParam.setParam_name("PeParameterName");
		veWebsocketServiceParam.setParam_type("String");
		veWebsocketServiceParam.setParam_value("");
		paramSet.add(veWebsocketServiceParam);
		///////////////////////////
		cTpl.setVeTpl(tpl);
		cTpl.setParams(paramSet);
		cTpl.setServices(serviceSet);
		cTpl.setTemplateBinds(templateBindSet);
		cTpl.setReturnParam(returnParamSet);
		logger.info("IdentifyIdServiceName: "+IdentifyIdServiceName.toString());
		cTpl.setIdentifyIdServiceName(IdentifyIdServiceName);
		VETemplateService vts = (VETemplateService) GetBean.getBean("veTplService");
		Boolean result = vts.insertCompleteTplForAtomVE(cTpl, methodParam);
		return result;
	}
}
