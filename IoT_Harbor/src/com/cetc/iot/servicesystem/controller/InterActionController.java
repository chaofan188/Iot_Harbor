package com.cetc.iot.servicesystem.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VEAndTplInfo;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.database.model.VeKeyConfig;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.harbormanage.service.PEInterfaceKeyService;
import com.cetc.iot.harbormanage.service.PEService;
import com.cetc.iot.servicesystem.service.TemplateBindService;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.VETemplateService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;
import com.cetc.iot.servicesystem.service.VeKeyConfigService;
import com.cetc.iot.servicesystem.service.VePeBindService;
import com.cetc.iot.servicesystem.util.ConvertVeId;
import com.cetc.iot.servicesystem.util.Ve2Xml;
import com.cetc.iot.servicesystem.util.VeServiceKey;
import com.cetc.iot.util.BaseUtil;

/**
 * 应用接口
 * @author chengk
 *
 */

@Controller
public class InterActionController {
	
	private static Logger logger = Logger.getLogger(InterActionController.class.getName());

	@Autowired
	private PEInterfaceKeyService peInterfaceKeyService;
	@Autowired
	private VEService veService;
	@Autowired
	private PEService peservice;
	@Autowired
	private TemplateBindService templateBindService;
	@Autowired
	private VETemplateService vetemplateservice;
	@Autowired
	private VePeBindService vepebindservice;
	@Autowired
	private VeKeyConfigService veKeyConfigService;
	@Autowired
	private VEWebsocketServiceService veWebsocketServiceService;
	

	//���peid��ȡveģ���б�
	@RequestMapping(value = "/getVeTemplateByPETemplateId.html", method=RequestMethod.GET)
	@ResponseBody
	public String getVeTemplateByPETemplateId(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = request.getParameter("callbackparam");
		String result = "";

		List<Map<String,Object>> listmap = new ArrayList<Map<String,Object>>();
		String petemplateId = request.getParameter("peTemplateId");
		
		
		TemplateBind templateBind = new TemplateBind();
		templateBind.setPe_template_id(petemplateId);
		List<String> vetemplateidList = templateBindService.queryWithVETemplateIdDistinction(templateBind, 0, templateBindService.queryCOUNTNUM(templateBind));
		for(String temp:vetemplateidList){
			VETemplate vetemplate = new VETemplate();
			Map<String,Object> templateMap = new HashMap<String,Object>();
			vetemplate.setTemplate_id(temp);
			List<VETemplate> templateList= vetemplateservice.query(vetemplate, 0, 1);
			if(templateList.size()==0){
				return "false";
			}
			vetemplate = templateList.get(0);
			templateMap.put("template_id", vetemplate.getTemplate_id());
			templateMap.put("template_openness", vetemplate.getTemplate_openness());
			templateMap.put("tpl_alias", vetemplate.getTpl_alias());
			templateMap.put("tpl_classpath", vetemplate.getTpl_classpath());
			templateMap.put("tpl_description", vetemplate.getTpl_description());
			templateMap.put("tpl_developer", vetemplate.getTpl_developer());
			templateMap.put("tpl_display", vetemplate.getTpl_display());
			templateMap.put("tpl_enroll_time", new java.util.Date(vetemplate.getTpl_enroll_time().getTime()));
			templateMap.put("tpl_icon", vetemplate.getTpl_icon());
			templateMap.put("tpl_name", vetemplate.getTpl_name());
			templateMap.put("tpl_owner", vetemplate.getTpl_owner());
			templateMap.put("tpl_state", vetemplate.getTpl_state());
         	templateMap.put("tpl_type_id", vetemplate.getTpl_type_id());
			templateMap.put("tpl_version", vetemplate.getTpl_version());
			listmap.add(templateMap);
		}
		JSONArray json = JSONArray.fromObject(listmap);
		if(str==null){
			result = json.toString();
		}else{
			result =  str + "("+ json.toString() + ")";
		}
		return result;
	}
	//get ve information by the parameter  'userId'
	@RequestMapping(value = "/getVeByUser.html", method=RequestMethod.GET)
	@ResponseBody
	public String getVeByUser(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String type = "";
		String userId = request.getParameter("userId");
		String str = request.getParameter("callbackparam");
		String result = "";

		if(BaseUtil.isNotEmpty(userId)){

			List<Map<String,Object>> listmap = new ArrayList<Map<String,Object>>();

			List<VEAndTplInfo> list = new ArrayList<VEAndTplInfo>();
			list = veService.getVEAndTplInfoByCreatorID(userId);


			// 截取tpl_type_id获得该ve模板的type
			for(int i=0;i<list.size();i++){
				Map<String,Object> veMap = new HashMap<String,Object>();
				//type = json.getJSONObject(i).getString("tpl_type_id").substring(5, 8);
				type = list.get(i).getVetemplate().getTpl_type_id().substring(5, 8);
				//json.getJSONObject(i).remove("tpl_type_id");
				//json.getJSONObject(i).accumulate("type", type);
				list.get(i).getVetemplate().setTpl_type_id(type);
				//把ve信息转化成json
				veMap.put("ve_id", list.get(i).getVe().getVe_id());
				veMap.put("ve_name", list.get(i).getVe().getVe_name());
				veMap.put("ve_key", list.get(i).getVe().getVe_key());
				veMap.put("ve_state", list.get(i).getVe().getVe_state());
				veMap.put("ve_privacy", list.get(i).getVe().getVe_privacy());
				veMap.put("ve_init_time", list.get(i).getVe().getVe_init_time().toString());
				veMap.put("ve_description", list.get(i).getVe().getVe_description());
				veMap.put("ve_creator_id", list.get(i).getVe().getVe_creator_id());
				veMap.put("template_id", list.get(i).getVe().getTemplate_id());
				if(list.get(i).getVe().getGeo()!=null){
					veMap.put("altitude", list.get(i).getVe().getGeo().getAltitude());
					veMap.put("city_id", list.get(i).getVe().getGeo().getCity_id());
					veMap.put("country_id", list.get(i).getVe().getGeo().getCountry_id());
					veMap.put("geolocation_id", list.get(i).getVe().getGeo().getGeolocation_id());
					veMap.put("latitude", list.get(i).getVe().getGeo().getLatitude());
					veMap.put("longitude", list.get(i).getVe().getGeo().getLongitude());
					veMap.put("otherInfo", list.get(i).getVe().getGeo().getOtherInfo());
					veMap.put("province_id", list.get(i).getVe().getGeo().getProvince_id());
				}
				veMap.put("template_openness", list.get(i).getVetemplate().getTemplate_openness());
				veMap.put("tpl_classpath", list.get(i).getVetemplate().getTpl_classpath());
				veMap.put("tpl_description", list.get(i).getVetemplate().getTpl_description());
				veMap.put("tpl_developer", list.get(i).getVetemplate().getTpl_developer());
				veMap.put("tpl_display", list.get(i).getVetemplate().getTpl_display());
				veMap.put("tpl_enroll_time", list.get(i).getVetemplate().getTpl_enroll_time().toString());
				veMap.put("tpl_icon", list.get(i).getVetemplate().getTpl_icon());
				veMap.put("tpl_id", list.get(i).getVetemplate().getTpl_id());
				veMap.put("tpl_name", list.get(i).getVetemplate().getTpl_name());
				veMap.put("tpl_owner", list.get(i).getVetemplate().getTpl_owner());
				veMap.put("tpl_state", list.get(i).getVetemplate().getTpl_state());
				veMap.put("tpl_type_id", list.get(i).getVetemplate().getTpl_type_id());
				veMap.put("tpl_version", list.get(i).getVetemplate().getTpl_version());
				listmap.add(veMap);
			}
			JSONArray json = JSONArray.fromObject(listmap);
			if(str==null){
				result = json.toString() ;
			}else{
				result = str + "("+ json.toString() + ")";
			}

		}else{
			result = "userId is null";
		}

		return result;
	}


	//获取ve的 psdl
	@RequestMapping(value = "/getPsdl.html", method=RequestMethod.GET)
	@ResponseBody
	public String getPsdl(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String veId = request.getParameter("veId");
		veId = ConvertVeId.simplifyVeId(veId);
		String result = "";

		if(BaseUtil.isNotEmpty(veId)){

			result = Ve2Xml.ve2Xml(veService.getVeByVeId(veId),2);

			System.out.println(result);			
		}else{
			result = "veId is null";
		}

		return result;
	}

	//移动版注册VE
	@RequestMapping(value = "/registerVEWithMobile.html", method=RequestMethod.GET)
	@ResponseBody
	public String registerVEWithMobile(HttpServletRequest request){
		
		String result = "";
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String veInfo = request.getParameter("veInfo");//identity_id的list
		JSONArray jsonarray = JSONArray.fromObject(veInfo);
		/*int countSum=0;//完全匹配的数量
		int realSum=0;//实际匹配的数量
		List<VePeBind> bindList = new ArrayList<VePeBind>();
		for(int i=0; i<jsonarray.size(); i++){
			JSONObject object = jsonarray.getJSONObject(i);
			String identify_id = object.getString("identify_id");
			String peInfo = object.getString("pe_list");
			System.out.println("identify_id为"+identify_id+"的peInfo为："+peInfo);
			if(peInfo==null || "".equals(peInfo)){
				//需要判断该identity_id的必要性，若为非必要则continue;必要则return false
			}else{
				JSONArray peList = JSONArray.fromObject(peInfo);
				countSum += peList.size();
				for(int j=0;j<peList.size();j++){
					JSONObject pe = peList.getJSONObject(j);
					String peid = "";
					String peInterfaceId = "";
					String peInterfaceIdKey = "";
					if(pe.containsKey("pe_id")&&pe.containsKey("pe_id")&&pe.containsKey("pe_id")){
						peid = pe.getString("pe_id");
						peInterfaceId = pe.getString("if_id");
						peInterfaceIdKey = pe.getString("if_id_key");
					}else if(pe.containsKey("peId")&&pe.containsKey("peInterfaceId")&&pe.containsKey("peInterfaceIdKey")){
						peid = pe.getString("peId");
						peInterfaceId = pe.getString("peInterfaceId");
						peInterfaceIdKey = pe.getString("peInterfaceIdKey");
					}else{
						System.out.println("identify_id为"+identify_id+"的peInfo信息的key形式不符合标准");
						return "false";
					}
					int r = peInterfaceKeyService.queryForIsExsit(peid, peInterfaceId, peInterfaceIdKey);
					if(r==1)
					realSum ++;
					boolean isExist = false;
					for(int t=0; t<bindList.size(); t++){
						if(peid.equals(bindList.get(t).getPe_id())){
							isExist = true;
							break;
						}
					}
					if(!isExist){
						VePeBind bind = new VePeBind();
						bind.setIdentify_id(identify_id);
						bind.setPe_id(peid);
						bind.setBind_type("0");//必要
						bind.setState("0");//待审核
						bindList.add(bind);
					}					
				}
			}			
		}
		if(countSum==realSum){*/
			//初始化VE
			//VeGeoLocation veGeoLocation = new VeGeoLocation();
			VE ve = new VE();
			String veName = request.getParameter("veName");
			String veKey = request.getParameter("vePassword");
			String privacy = request.getParameter("privacy");
			String templateId = request.getParameter("templateId");
			String description = request.getParameter("description");
			//String country =request.getParameter("country");
			//String province =request.getParameter("province");
			//String city =request.getParameter("city");
			/*String country="1";
			String province="1";
			String city="1";
			String longitude =request.getParameter("longitude");
			String latitude =request.getParameter("latitude");
			String altitude ="0";
			String otherInfo =request.getParameter("otherInfo");*/
			ve.setTemplate_id(templateId);
			ve.setVe_creator_id("18");
			ve.setVe_description(description);
			ve.setVe_key(veKey);
			ve.setVe_name(veName);
			ve.setVe_privacy(privacy);
			ve.setVe_state("001");
			ve.setVe_atom("0");
			/*veGeoLocation.setAltitude(Float.parseFloat(altitude));
			veGeoLocation.setCity_id(Integer.parseInt(city));
			veGeoLocation.setCountry_id(Integer.parseInt(country));
			veGeoLocation.setProvince_id(Integer.parseInt(province));
			veGeoLocation.setLatitude(Float.parseFloat(latitude));
			veGeoLocation.setLongitude(Float.parseFloat(longitude));
			veGeoLocation.setOtherInfo(otherInfo);*/
			//ve.setGeo(veGeoLocation);
			//插入iot_ve表以及iot_ve_geolocation表
			if("success".equals(veService.add(ve, null))){
				String veId = veService.query(ve, 0, 100).get(0).getVe_id();
				//插入iot_ve_pe_bind表
				VePeBind bind = new VePeBind();
				for(int i=0;i<jsonarray.size();i++){
					JSONObject object = jsonarray.getJSONObject(i);
					bind.setVe_id(veId);
					bind.setIdentify_id(object.getString("identify_id"));
					logger.info("****: "+object.getString("identify_id"));
					bind.setPe_id(object.getString("pe_id"));
					bind.setService_id(object.getString("service_id"));
					bind.setBind_type("0");//必要
					bind.setState("0");//待审核
					logger.info("bind.getService_id(): "+bind.getService_id());
					vepebindservice.add(bind);
				}
				//插入iot_ve_key_config表
				VeWebsocketService veWebsocketService = new VeWebsocketService();				
				veWebsocketService.setTemplate_id(templateId);
				List<VeWebsocketService> serviceList = veWebsocketServiceService.query(veWebsocketService, 0, 1000);
				VeKeyConfig veKeyConfig1 = new VeKeyConfig();
				for(int j = 0;j<serviceList.size();j++){
					veKeyConfig1.setVe_id(veId);
					veKeyConfig1.setService_id(serviceList.get(j).getService_id());
					veKeyConfig1.setBase_key(VeServiceKey.getKey());
					veKeyConfigService.add(veKeyConfig1);
				}
				result = Ve2Xml.ve2Xml(veService.getVeByVeId(veId),1);
				logger.info("VE 注册成功的返回值result为: "+result);
				return result;
			}else{
				return "false";
			}
		
	}
	
	/**
	 * VE当前状态查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getVeState.html", method=RequestMethod.GET)
	@ResponseBody
	public String getVeState(HttpServletRequest request){
		
		String veId = request.getParameter("veId");
		veId = ConvertVeId.simplifyVeId(veId);
		String result = "";
		List<Map<String, Object>> peList = new ArrayList<Map<String, Object>> ();		
		
		if(BaseUtil.isNotEmpty(veId)){

			VE ve = veService.getVeByVeId(veId);
			if(ve==null){
				return "there does not exist such ve which match the veid";
			}
			String vestate = ve.getVe_state();
			if("001".equals(vestate)){
				VePeBind vePeBind = new VePeBind();
				vePeBind.setVe_id(veId);
				List<VePeBind> list = vepebindservice.query(vePeBind, 0, 1000);
				if(list.size()==0){
					return "the ve has not binded any pe";
				}
				for(int i = 0;i<list.size();i++){
					peList = peservice.getPeById(list.get(i).getPe_id());
					if(peList.size()==0){
						return "pe table does not exist pe which peid is"+list.get(i).getPe_id();
					}
					if(Integer.parseInt(peList.get(0).get("pe_state").toString())==0){
						result = "006";//006不可用
						break;
					}
				}
				if(result!="006"){
					result = vestate;
				}
			}else{
				result = "006";
			}
		}else{
			result = "veid is null";
		}
		return result;
	}
}
