package com.cetc.iot.harbormanage.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cetc.iot.accesssystem.downinterface.protocol.base.service.PeProtocolConfigService;
import com.cetc.iot.accesssystem.util.PEKey;
import com.cetc.iot.database.model.EzvizCamera;
import com.cetc.iot.database.model.OnvifCamera;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.database.model.PeInterface;
import com.cetc.iot.database.model.PeParam;
import com.cetc.iot.database.model.PeProtocolConfig;
import com.cetc.iot.database.model.PeTemplate;
import com.cetc.iot.database.model.PeTemplateParam;
import com.cetc.iot.database.model.PeTemplateProtocolHttp;
import com.cetc.iot.database.model.ProtocolHttp;
import com.cetc.iot.harbormanage.service.EzvizCameraService;
import com.cetc.iot.harbormanage.service.OnvifCameraService;
import com.cetc.iot.harbormanage.service.PEInterfaceKeyService;
import com.cetc.iot.harbormanage.service.PEInterfaceService;
import com.cetc.iot.harbormanage.service.PEParamService;
import com.cetc.iot.harbormanage.service.PEService;
import com.cetc.iot.harbormanage.service.PETemplateService;
import com.cetc.iot.harbormanage.service.PeTemplateParamService;
import com.cetc.iot.harbormanage.service.PeTemplateProtocolHttpSevice;
import com.cetc.iot.harbormanage.service.ProtocolHttpService;
import com.cetc.iot.servicesystem.service.impl.AtomicRegister;
import com.cetc.iot.util.ESPConfig;
import com.cetc.iot.util.PeUtil;
import com.cetc.iot.util.aes.AESCoder;
import com.cetc.iot.util.base64.Base64Coder;
import com.cetc.iot.util.peidutil.PEIDGeneration;

@Controller
public class PeController {
	
	private static Logger logger = Logger.getLogger(PeController.class.getName());
	
	
	@Autowired
	PEService peService;
	@Autowired
	private PEInterfaceKeyService peInterfaceKeyService;
	@Autowired
	private PETemplateService peTemplateService;
	@Autowired
	private OnvifCameraService camService;
	@Autowired
	private EzvizCameraService ezvizcamService;
	@Autowired
	private PEInterfaceService peInterfaceService;
	@Autowired
	private PeProtocolConfigService peProtocolConfigService;
	@Autowired
	private PEParamService peParamService;
	@Autowired
	private PeTemplateParamService peTemplateParamService;
	@Autowired
	private PeTemplateProtocolHttpSevice peTemplateProtocolHttpSevice;
	@Autowired
	private ProtocolHttpService protocolHttpService;

	private static final String NUM_AND_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@RequestMapping(value = "/mype.html", method = RequestMethod.GET)
	public ModelAndView myPe(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("mype");
		List<Map<String, Object>> result;

		result = peService.getPeByOwner(request.getParameter("ownername"),
				Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("size")));
		view.addObject("result", result);

		return view;
	}

	@RequestMapping(value = "/mype.html", method = RequestMethod.POST)
	public ModelAndView myPefilter(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("mype");
		List<Map<String, Object>> result;

		result = peService.getPeFilter(request.getParameter("templateid"),
				request.getParameter("pestatus"),
				request.getParameter("pelifecycle"),
				Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("size")));
		view.addObject("result", result);

		return view;
	}

	@RequestMapping(value = "/mypetemplate.html", method = RequestMethod.POST)
	public String getTemplate(HttpServletRequest request) {
		List<Map<String, Object>> result;

		result = peService.getTemplate(request.getParameter("templatetype"),
				Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("size")));

		return PeUtil.listToJson(result);
	}

	@RequestMapping(value = "/mype/viewpe.html", method = RequestMethod.GET)
	public ModelAndView peBaseInfo(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("viewmype");

		List<Map<String, Object>> pe;
		List<Map<String, Object>> data;
		List<Map<String, Object>> auth;

		String peId = request.getParameter("peid");

		pe = peService.getPeById(peId);
		data = peService.getDataById(peId);
		auth = peService.getAuthById(peId);

		view.addObject("pe", pe);
		view.addObject("data", data);
		view.addObject("auth", auth);

		return view;
	}

	@RequestMapping(value = "/otherpe.html", method = RequestMethod.GET)
	public ModelAndView peBaseInfoOther(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("viewmype");

		List<Map<String, Object>> pe;

		pe = peService.getPeById(request.getParameter("peid"));

		view.addObject("pe", pe);

		return view;
	}

	
	/**
	 * this method is used for register pe into harbor
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	@RequestMapping(value = "/registerPE.html", method = RequestMethod.GET)
	@ResponseBody
	public String registerPE(HttpServletRequest request,
			HttpServletResponse response) {
		
		System.out.println("\n===============================================register PE here!=======================================");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// check whether deviceID is null!
		String deviceID = request.getParameter("device_id");
		if (deviceID == null || "".equals(deviceID)) {
			System.out.println("PEID is NULL!!");
			Map<String, Object> peIDNullMap = new HashMap<String, Object>();
			peIDNullMap.put("result", "error: id null");
			return JSONObject.fromObject(peIDNullMap).toString();
		}
		// check whether deviceID exists!
		String peID = PEIDGeneration.generate(deviceID,
				ESPConfig.getValue("harborName"));
		List<Map<String, Object>> queryPEIDList = peService.queryByPEID(peID);
		if (queryPEIDList.size() > 0) {
			System.out.println("PEID exists!!");
			Map<String, Object> peIDExistsMap = new HashMap<String, Object>();
			peIDExistsMap.put("result", "error: id exists");
			return JSONObject.fromObject(peIDExistsMap).toString();
		}
		// check whether templateID is null
		String templateID = request.getParameter("template_id");
		if (templateID == null) {
			System.out.println("TemplateID null!!");
			Map<String, Object> templateIDNullMap = new HashMap<String, Object>();
			templateIDNullMap.put("result", "error: template null");
			return JSONObject.fromObject(templateIDNullMap).toString();
		}
		// check whether templateID exits
		PeTemplate template = new PeTemplate();
		template.setTemplateId(templateID);
		List<Map<String, Object>> queryTemplateIDList = peTemplateService
				.query(template, 0, 10);
		if (queryTemplateIDList.size() <= 0) {
			System.out.println("TemplateID not exists!!");
			Map<String, Object> templateIDNotExistsMap = new HashMap<String, Object>();
			templateIDNotExistsMap.put("result", "error: template not exists");
			return JSONObject.fromObject(templateIDNotExistsMap).toString();
		}
		// check whether deviceName is null
		String deviceName = request.getParameter("device_name");
		System.out.println("!!!!!!!!!");
		System.out.println(deviceName);
		System.out.println("!!!!!!!!!");
		if (deviceName == null) {
			System.out.println("DeviceName null!!");
			Map<String, Object> deviceNameNullMap = new HashMap<String, Object>();
			deviceNameNullMap.put("result", "error: device name null");
			return JSONObject.fromObject(deviceNameNullMap).toString();
		}
		// check whether belongTo is null
		String belongTo = request.getParameter("belongto");
		if (belongTo == null) {
			System.out.println("BelongTo null!!");
			Map<String, Object> belongToNullMap = new HashMap<String, Object>();
			belongToNullMap.put("result", "error: belongto null");
			return JSONObject.fromObject(belongToNullMap).toString();
		}
		String deviceDescription = request.getParameter("device_description");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(deviceDescription);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
		// check whether public is null
		String isPublic = request.getParameter("public");
		if (isPublic == null) {
			System.out.println("Public null!!");
			Map<String, Object> publicNullMap = new HashMap<String, Object>();
			publicNullMap.put("result", "error: public null");
			return JSONObject.fromObject(publicNullMap).toString();
		}

		String onvifCount = request.getParameter("onvif_count");

		if (onvifCount != null) {
			int count = Integer.decode(onvifCount);
			for (int i = 0; i < count; i++) {
				String onvif_id = request.getParameter("onvif_"
						+ String.valueOf(i + 1));
				String ip = request.getParameter("onvif_ip_" + onvif_id);
				String username = request.getParameter("onvif_username_"
						+ onvif_id);
				String password = request.getParameter("onvif_password_"
						+ onvif_id);
				OnvifCamera cam = new OnvifCamera();
				cam.setPe_id(peID);
				cam.setInterface_id(onvif_id);
				cam.setUsername(username);
				cam.setIpv4(ip);
				cam.setPassword(password);
				camService.add(cam);
			}
		}

		// add pe
		Pe pe = new Pe();
		pe.setPeId(peID);
		pe.setPe_public(isPublic);
		pe.setPeKey(deviceID);
		pe.setPeLastTime(new Date());
		pe.setPeLifecycle(0);
		pe.setPeName(deviceName);
		pe.setPeOwner(belongTo);
		pe.setPeState(0);
		pe.setPeTime(0);
		pe.setPeUser("");
		pe.setPe_description(deviceDescription);
		pe.setTemplateId(templateID);
		pe.setPeGeolocationId("");
		String registerResult = peService.add(pe);
		// check whether register success
		if ("fail".equalsIgnoreCase(registerResult)) {
			System.out.println("DataBase fail!!");
			Map<String, Object> registerFailMap = new HashMap<String, Object>();
			registerFailMap.put("result", "error: database fail");
			return JSONObject.fromObject(registerFailMap).toString();
		}
		// Generate PE Key by template information
		PeInterface param = new PeInterface();
		param.setTemplateId(templateID);
		param.setInterfaceChannel(-1);
		param.setInterfaceFashion(-1);
		param.setInterfaceResponse(-1);
		List<Map<String, Object>> peInterfaces = peInterfaceService.query(
				param, 0, 100);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("peid", peID);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		System.out.println("size is " + peInterfaces.size());
		for (int i = 0; i < peInterfaces.size(); i++) {
			Map<String, String> map2 = new HashMap<String, String>();
			String interfaceID = peInterfaces.get(i).get("interface_id")
					.toString();
			map2.put("interface_id", interfaceID);
			String key = PEKey.genKey();
			map2.put("key", key);
			list.add(map2);
			peInterfaceKeyService.addKey(peID, interfaceID, key);
		}
		map.put("interfaceKey", list);
		// generate device key
		String baseKey = Base64Coder.encode(AESCoder.initKey());
		String upKey = generateNumOrCharString(4);
		String downKey = generateNumOrCharString(4);
		PeProtocolConfig peProtocolConfig = new PeProtocolConfig();
		peProtocolConfig.setPeId(peID);
		peProtocolConfig.setBasePassword(baseKey);
		peProtocolConfig.setUpPassword(upKey);
		peProtocolConfig.setDownPassword(downKey);
		peProtocolConfig.setLoginTimes(0);
		peProtocolConfigService.add(peProtocolConfig);
		map.put("baseKey", baseKey);
		map.put("upKey", upKey);
		map.put("downKey", downKey);
		map.put("deviceID", deviceID);
		String jsonString = JSONObject.fromObject(map).toString();
		System.out.println(jsonString);
		JSONObject finalReturnJson = new JSONObject();
		finalReturnJson.put("pe", jsonString);
		// call atomicRegister
		JSONObject atomicRegisterJsonObject = new JSONObject();
		atomicRegisterJsonObject.put("veName",
				request.getParameter("device_name"));
		// atomicRegisterJsonObject.put("vePassword",
		// request.getParameter("vePassword"));
		atomicRegisterJsonObject.put("privacy", request.getParameter("public"));
		atomicRegisterJsonObject.put("templateId",
				request.getParameter("template_id"));
		atomicRegisterJsonObject.put("description",
				request.getParameter("device_description"));
		atomicRegisterJsonObject.put("peId", peID);
		atomicRegisterJsonObject.put("longitude",
				request.getParameter("longitude"));
		atomicRegisterJsonObject.put("latitude",
				request.getParameter("latitude"));
		atomicRegisterJsonObject.put("otherInfo",
				request.getParameter("otherInfo"));
		String atomicRegisterResult = AtomicRegister
				.registerAtomicVe(atomicRegisterJsonObject.toString());
		finalReturnJson.put("ve", atomicRegisterResult);
		System.out.println(finalReturnJson.toString());
		return finalReturnJson.toString();

	}
	*/

	/**
	 * this method is used to create random char or number
	 * 
	 * @param length
	 *            length to create
	 * @return random string based on char or number
	 */
	private static String generateNumOrCharString(int length) {
		String result = "";
		for (int i = 1; i <= length; i++) {
			char temp = NUM_AND_CHAR
					.charAt((int) (Math.random() * (NUM_AND_CHAR.length() - 1)));
			result = result + String.valueOf(temp);
		}
		return result;
	}

	@RequestMapping(value = "/registerPE.html" ,method = RequestMethod.POST)
	@ResponseBody
	public String registerPEPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		StringBuilder sb = new StringBuilder();
		//BufferedReader reader = request.getReader();
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		logger.info(request.getContentLength());
		try {
		    String line;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line).append('\n');
		    }
		} finally {
		    reader.close();
		}
		logger.info("sb: "+sb.toString());
		JSONObject json = (JSONObject)JSONSerializer.toJSON(sb.toString());
		logger.info(json.toString());
		String deviceID = json.getString("device_id");
		
		boolean use_veid = false;
		if(json.containsKey("use_veid")){
			 if(json.get("use_veid").equals("on"))
				 use_veid = true;
		}
		
		if (deviceID == null || "".equals(deviceID)) {
			logger.info("PEID Null! ");
			Map<String, Object> peIDNullMap = new HashMap<String, Object>();
			peIDNullMap.put("result", "error: id null");
			return JSONObject.fromObject(peIDNullMap).toString();
		}
		// check whether deviceID exists!
		String peID = PEIDGeneration.generate(deviceID,
				ESPConfig.getValue("harborName"));
		List<Map<String, Object>> queryPEIDList = peService.queryByPEID(peID);
		if (queryPEIDList.size() > 0) {
			logger.info("PEID exists! ");
			Map<String, Object> peIDExistsMap = new HashMap<String, Object>();
			peIDExistsMap.put("result", "error: id exists");
			return JSONObject.fromObject(peIDExistsMap).toString();
		}
		
		String templateID = json.getString("template_id");
		if (templateID == null) {
			logger.info("TemplateID null! ");
			Map<String, Object> templateIDNullMap = new HashMap<String, Object>();
			templateIDNullMap.put("result", "error: template null");
			return JSONObject.fromObject(templateIDNullMap).toString();
		}
		// check whether templateID exits
		PeTemplate template = new PeTemplate();
		template.setTemplateId(templateID);
		List<Map<String, Object>> queryTemplateIDList = peTemplateService
				.query(template, 0, 10);
		if (queryTemplateIDList.size() <= 0) {
			logger.info("TemplateID not exists! ");
			Map<String, Object> templateIDNotExistsMap = new HashMap<String, Object>();
			templateIDNotExistsMap.put("result", "error: template not exists");
			return JSONObject.fromObject(templateIDNotExistsMap).toString();
		}
		// check whether deviceName is null
		String deviceName = json.getString("device_name");
		logger.info("deviceName: "+deviceName);
		if (deviceName == null) {
			logger.info("DeviceName Null! ");
			Map<String, Object> deviceNameNullMap = new HashMap<String, Object>();
			deviceNameNullMap.put("result", "error: device name null");
			return JSONObject.fromObject(deviceNameNullMap).toString();
		}
		// check whether belongTo is null
		String belongTo = json.getString("belongto");
		if (belongTo == null) {
			logger.info("Belongto Null! ");
			Map<String, Object> belongToNullMap = new HashMap<String, Object>();
			belongToNullMap.put("result", "error: belongto null");
			return JSONObject.fromObject(belongToNullMap).toString();
		}
		String deviceDescription = json.getString("device_description");
		logger.info("deviceDescription: "+deviceDescription);
		// check whether public is null
		String isPublic = json.getString("public");
		if (isPublic == null) {
			logger.info("Public null! ");
			Map<String, Object> publicNullMap = new HashMap<String, Object>();
			publicNullMap.put("result", "error: public null");
			return JSONObject.fromObject(publicNullMap).toString();
		}

		//存储Onvif协议信息到数据库
		JSONArray onvifArray = (JSONArray)json.get("onvif");
		
		int onvifCount = 0;
		if(onvifArray != null){
			onvifCount = onvifArray.size();
		}

		if (onvifCount > 0) {
			for (int i = 0; i < onvifCount; i++) {
				JSONObject onvifObj = (JSONObject) onvifArray.get(i);
				System.out.println(onvifObj);
				String onvif_id = onvifObj.getString("interface_id");
				String ip = onvifObj.getString("onvif_ip");
				String username = onvifObj.getString("username");
				String password = onvifObj.getString("password");
				OnvifCamera cam = new OnvifCamera();
				cam.setPe_id(peID);
				cam.setInterface_id(onvif_id);
				cam.setUsername(username);
				cam.setIpv4(ip);
				cam.setPassword(password);
				camService.add(cam);
			}
		}

		//存储ezviz接口信息到数据库
		JSONArray ezvizArray = (JSONArray)json.get("ezviz");
		int ezvizCount = 0;
		if(ezvizArray != null){
			ezvizCount = ezvizArray.size();
		}
	
		if (ezvizCount > 0) {
			for (int i = 0; i < ezvizCount; i++) {
				JSONObject ezvizObj = (JSONObject) ezvizArray.get(i);
				System.out.println(ezvizObj);
				String ezviz_id = ezvizObj.getString("interface_id");
				String key = ezvizObj.getString("ezviz_key");
				String secret = ezvizObj.getString("ezviz_secret");
				String cameraName = ezvizObj.getString("ezviz_cameraname");
				EzvizCamera ezvizcam = new EzvizCamera();
			    M3u8UrlController mu = new M3u8UrlController();
			    String result = mu.getM3u8Url(cameraName, key, secret);
			    int begin = result.indexOf("m3u8Url");
			    int end = result.indexOf(".m3u8");
			    String m3u8Url = "";
			    if(begin>0&&end>0){
				   begin +=10;
				   end +=5;
				   m3u8Url = result.substring(begin, end);
			    }
			   
			    if (m3u8Url!="") {
			    	ezvizcam.setCameraname(cameraName);
			    	ezvizcam.setInterface_id(ezviz_id);
			    	ezvizcam.setKey(key);
			    	ezvizcam.setM3u8Url(m3u8Url);
			    	ezvizcam.setPe_id(peID);
			    	ezvizcam.setSecret(secret);
			    	ezvizcamService.add(ezvizcam);
			      
			    }
			}
		}
		
		//存储HTTP属性到数据库
		JSONArray httpArray = (JSONArray)json.get("http");
		int httpCount = 0;
		if(httpArray != null){
			httpCount = httpArray.size();
		}
		if(httpCount > 0){
			for(int i=0; i<httpCount; i++){
				JSONObject httpObj = (JSONObject) httpArray.get(i);
				String url = httpObj.getString("url");
				String interface_id = httpObj.getString("interface_id");
				if(interface_id == null){
					Map<String, Object> templateIDNotExistsMap = new HashMap<String, Object>();
					templateIDNotExistsMap.put("result", "error: interface id not exists");
					return JSONObject.fromObject(templateIDNotExistsMap).toString();
				}
				ProtocolHttp http = new ProtocolHttp();
				PeTemplateProtocolHttp httpTemplate = new PeTemplateProtocolHttp();
				httpTemplate.setInterface_id(interface_id);
				httpTemplate.setTemplate_id(templateID);
				httpTemplate.setId(-1);
				List<Map<String, Object>> httpTemplateResult = peTemplateProtocolHttpSevice.query(httpTemplate, 0, 1);
				if(httpTemplateResult.size() != 1){
					logger.error("Error occur when reading http template interface information! ");
					break;
				}
				String rule = (String) httpTemplateResult.get(0).get("rule");
				Long httpTemplateId = (Long)httpTemplateResult.get(0).get("id");
				http.setHttp_template_id(httpTemplateId.intValue());
				http.setMethod(1);
				http.setPe_id(peID);
				http.setRule(rule);
				http.setUrl(url);
				http.setInterface_id(interface_id);
				protocolHttpService.add(http);
			}
		}

		//存储PE属性到数据库
		JSONArray attributeArray = (JSONArray)json.get("attribute");
		int attributeCount = 0;
		if(attributeArray != null){
			attributeCount = attributeArray.size();
		}
		
		if(attributeCount > 0){
			for(int i=0; i<attributeCount; i++){
				JSONObject attributeObj = (JSONObject) attributeArray.get(i);
				String key = attributeObj.getString("key");
				String value = attributeObj.getString("value");
				String template_param_id = null;
				try {
					if(attributeObj.containsKey("id"))
						template_param_id = attributeObj.getString("id");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("Contain no id area! ");
				}
				
				if(template_param_id == null){ //If register contain no "ID" of attribute,then find it from database.
					PeTemplateParam templateParam = new PeTemplateParam();
					templateParam.setTemplate_id(templateID);
					templateParam.setKey(key);
					templateParam.setParam_id(-1);
					List<Map<String, Object>> result = peTemplateParamService.queryAll(templateParam);
					if(result.size() != 1){
						logger.info("Model Attribute Key Error! May be cause by duplication of keys. ");
						continue;
					}
					Long param_id_long = (Long) result.get(0).get("template_param_id");
					template_param_id = Integer.toString(param_id_long.intValue());
				}
				
				PeParam param = new PeParam();
				param.setKey(key);
				param.setContent(value);
				param.setPe_id(peID);
				param.setTemplate_param_id(template_param_id);
				peParamService.add(param);
			}
		}
		
		Pe pe = new Pe();
		pe.setPeId(peID);
		pe.setPe_public(isPublic);
		pe.setPeKey(deviceID);
		pe.setPeLastTime(new Date());
		pe.setPeLifecycle(0);
		pe.setPeName(deviceName);
		pe.setPeOwner(belongTo);
		pe.setPeState(0);
		pe.setPeTime(0);
		pe.setPeUser("");
		pe.setPe_description(deviceDescription);
		pe.setTemplateId(templateID);
		pe.setPeGeolocationId("");
		String registerResult = peService.add(pe);
		// check whether register success
		if ("fail".equalsIgnoreCase(registerResult)) {
			logger.info("Database fail! ");
			Map<String, Object> registerFailMap = new HashMap<String, Object>();
			registerFailMap.put("result", "error: database fail");
			return JSONObject.fromObject(registerFailMap).toString();
		}
		// Generate PE Key by template information
		PeInterface param = new PeInterface();
		param.setTemplateId(templateID);
		param.setInterfaceChannel(-1);
		param.setInterfaceFashion(-1);
		param.setInterfaceResponse(-1);
		List<Map<String, Object>> peInterfaces = peInterfaceService.query(
				param, 0, 100);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("peid", peID);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		logger.info("size is: "+peInterfaces.size());
		for (int i = 0; i < peInterfaces.size(); i++) {
			Map<String, String> map2 = new HashMap<String, String>();
			String interfaceID = peInterfaces.get(i).get("interface_id")
					.toString();
			map2.put("interface_id", interfaceID);
			String key = PEKey.genKey();
			map2.put("key", key);
			list.add(map2);
			peInterfaceKeyService.addKey(peID, interfaceID, key);
		}
		map.put("interfaceKey", list);
		// generate device key
		String baseKey = Base64Coder.encode(AESCoder.initKey());
		String upKey = generateNumOrCharString(4);
		String downKey = generateNumOrCharString(4);
		PeProtocolConfig peProtocolConfig = new PeProtocolConfig();
		peProtocolConfig.setPeId(peID);
		peProtocolConfig.setBasePassword(baseKey);
		peProtocolConfig.setUpPassword(upKey);
		peProtocolConfig.setDownPassword(downKey);
		peProtocolConfig.setLoginTimes(0);
		peProtocolConfigService.add(peProtocolConfig);
		map.put("baseKey", baseKey);
		map.put("upKey", upKey);
		map.put("downKey", downKey);
		map.put("deviceID",deviceID);
		String jsonString = JSONObject.fromObject(map).toString();
		logger.info(jsonString.toString());
		JSONObject finalReturnJson = new JSONObject();
		finalReturnJson.put("result", "success");
		finalReturnJson.put("pe", jsonString);
		// call atomicRegister
		JSONObject atomicRegisterJsonObject = new JSONObject();
		atomicRegisterJsonObject.put("veName",
				json.getString("device_name"));
		// atomicRegisterJsonObject.put("vePassword",
		// request.getParameter("vePassword"));
		atomicRegisterJsonObject.put("privacy", json.getString("public"));
		atomicRegisterJsonObject.put("templateId",
				json.getString("template_id"));
		atomicRegisterJsonObject.put("description",
				json.getString("device_description"));
		atomicRegisterJsonObject.put("peId", peID);
		atomicRegisterJsonObject.put("longitude",
				json.getString("longitude"));
		atomicRegisterJsonObject.put("latitude",
				json.getString("latitude"));
		atomicRegisterJsonObject.put("otherInfo",
				json.getString("otherInfo"));
		atomicRegisterJsonObject.put("deviceID", deviceID);
		atomicRegisterJsonObject.put("directVEID", use_veid); // 是否使用deviceID作为VEID
		String atomicRegisterResult = AtomicRegister
				.registerAtomicVe(atomicRegisterJsonObject.toString());
		finalReturnJson.put("ve", atomicRegisterResult);
		logger.info("final Return Json: "+finalReturnJson.toString());
		return finalReturnJson.toString();
		
	}
}
