package com.cetc.iot.harbormanage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cetc.iot.accesssystem.downinterface.protocol.base.service.PeProtocolConfigService;
import com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.database.model.PeGeolocation;
import com.cetc.iot.database.model.PeInterface;
import com.cetc.iot.database.model.PeTemplate;
import com.cetc.iot.database.model.PeTemplateParam;
import com.cetc.iot.database.model.PeTemplateProtocolHttp;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.harbormanage.pojo.AttributePojo;
import com.cetc.iot.harbormanage.pojo.ClassPojo;
import com.cetc.iot.harbormanage.pojo.DeviceAuthorStatusPojo;
import com.cetc.iot.harbormanage.pojo.HistoryDataPojo;
import com.cetc.iot.harbormanage.pojo.InterfacePojo;
import com.cetc.iot.harbormanage.pojo.MyDevice;
import com.cetc.iot.harbormanage.pojo.PEModelPojo;
import com.cetc.iot.harbormanage.pojo.ParamPojo;
import com.cetc.iot.harbormanage.pojo.ProvideManager;
import com.cetc.iot.harbormanage.pojo.TemplatePojo;
import com.cetc.iot.harbormanage.service.PEGeolocationService;
import com.cetc.iot.harbormanage.service.PEInterfaceKeyService;
import com.cetc.iot.harbormanage.service.PEInterfaceService;
import com.cetc.iot.harbormanage.service.PEService;
import com.cetc.iot.harbormanage.service.PETemplateService;
import com.cetc.iot.harbormanage.service.PeTemplateParamService;
import com.cetc.iot.harbormanage.service.PeTemplateProtocolHttpSevice;
import com.cetc.iot.harbormanage.service.VEPEBindService;
import com.cetc.iot.servicesystem.service.impl.AtomicRegister;
import com.cetc.iot.servicesystem.util.GetBean;
/*import com.sun.org.apache.bcel.internal.generic.NEW;*/

@Controller
public class ImportTemplateContoller {
	
	private static Logger logger = Logger.getLogger(ImportTemplateContoller.class.getName());
		
	@Autowired
	private PEService peService;
	@Autowired
	private PETemplateService peTemplateService;
	@Autowired
	private PEInterfaceService peInterfaceService;
	@Autowired
	private PEGeolocationService peGeolocationService;
	@Autowired
	private VEPEBindService vePeService;
	@Autowired
	private PEInterfaceKeyService peInterfaceKeyService;
	@Autowired
	private PeProtocolConfigService peProtocolConfigService;
	@Autowired
	private PeTemplateProtocolHttpSevice peTemplateProtocolHttpSevice;
	@Autowired
	private PeTemplateParamService peTemplateParamService;
	/*
	 * 上传模板
	 */
	/*@RequestMapping(value = "/addfile.html", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("uploadfile");
		PeTemplate peTemplate = new PeTemplate();

		InterfacePojo[] peInterfaceArray;
		int peInterfaceArrayLength;
		byte[] b;
		int len = 0;
		int temp = 0;
		String path = request.getSession().getServletContext().getRealPath("/");
		File f = null;

		String content = "";
		String interfaceID;
		ParamPojo[] paramPojoArray;
		int paramPojoArrayLength;

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		MultipartHttpServletRequest myfile = (MultipartHttpServletRequest) request;
		MultipartFile file = myfile.getFile("inputmodel");
		String name = file.getOriginalFilename();
		b = file.getBytes();
		
		 * String s2 = new String(b); System.out.println(s2);
		 
		String s1 = new String(b, "utf-8");
		String s1 = new String(b);
		System.out.println(s1);
		f = new File(path + File.separator + name);
		UUID uuid = UUID.randomUUID();
		JSONObject j = JSONObject.fromObject(s1);
		PEModelPojo pemodel = (PEModelPojo) JSONObject.toBean(j,
				PEModelPojo.class);
		peInterfaceArray = pemodel.getInterfaces();
		peInterfaceArrayLength = peInterfaceArray.length;
		while (peInterfaceArrayLength > 0) {
			PeInterface peInterface = new PeInterface();
			interfaceID = peInterfaceArray[peInterfaceArrayLength - 1]
					.getInterfaceID();
			paramPojoArray = peInterfaceArray[peInterfaceArrayLength - 1]
					.getParam();
			paramPojoArrayLength = paramPojoArray.length;
			while (paramPojoArrayLength > 0) {

				ParamPojo paramPojo;
				paramPojo = paramPojoArray[paramPojoArrayLength - 1];
				content += "param:" + "key:" + paramPojo.getKey() + "|"
						+ "type:" + paramPojo.getType() + "|" + "option:"
						+ paramPojo.getOption() + "|" + "description:"
						+ paramPojo.getDescription() + "|" + "encode:"
						+ paramPojo.getEncode() + "|" + "max_val:"
						+ paramPojo.getMax_val() + "|" + "min_val:"
						+ paramPojo.getMin_val() + ";";
				paramPojoArrayLength--;
			}
			peInterface.setTemplateId(pemodel.getModel_id());
			peInterface.setInterfaceRemark("");
			peInterface.setInterfaceChannel(-1);
			peInterface.setInterfaceContent(content);
			peInterface.setInterfaceFashion(-1);
			peInterface.setInterfaceName("");
			peInterface.setInterfaceResponse(-1);
			peInterface.setInterfaceType("-1");
			peInterface.setResult("");
			peInterface.setResultType("");
			peInterface.setInterfaceId(UUID.randomUUID() + "_" + interfaceID);
			peInterfaceArrayLength--;
			peInterfaceService.add(peInterface);
			content = "";
		}

		peTemplate.setTemplateId(pemodel.getModel_id());
		peTemplate.setTemplateModel(pemodel.getModel());
		peTemplate.setTemplateName(pemodel.getModel_name());
		peTemplate.setTemplatePicUrl(pemodel.getPic_url());
		peTemplate.setTemplateRemark(pemodel.getDescription());
		peTemplate.setTemplateTime(new Date());
		peTemplate.setTemplateType(" ");
		peTemplateService.add(peTemplate);

		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		response.setStatus(302);
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.1.1-SelectTemplate.jsp");
		return "a";
	}*/

	/*
	 * @RequestMapping(value = "/addfile.html", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String uploadFile(HttpServletRequest request,
	 * HttpServletResponse response) throws IOException {
	 * System.out.println("uploadfile"); byte[] b; int len = 0; int temp = 0;
	 * String path = request.getSession().getServletContext().getRealPath("/");
	 * File f = null; response.setContentType("text/html");
	 * MultipartHttpServletRequest myfile = (MultipartHttpServletRequest)
	 * request; MultipartFile file = myfile.getFile("inputmodel"); String name =
	 * file.getOriginalFilename(); f = new File(path + File.separator + name); b
	 * = file.getBytes(); OutputStream out = new FileOutputStream(f);
	 * out.write(b); out.flush(); out.close(); String basePath =
	 * request.getScheme() + "://" + request.getServerName() + ":" +
	 * request.getServerPort() + path + "/"; response.setStatus(302);
	 * response.addHeader("location",
	 * "/IoT_Harbor/jsp/Module1/1.1.1-SelectTemplate.jsp"); return "a"; }
	 */
	@RequestMapping(value = "/addfile.html", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("uploadfile");
		PeTemplate peTemplate = new PeTemplate();
		InterfacePojo[] peInterfaceArray;
		AttributePojo[] attributeArray;
		int peInterfaceArrayLength;
		int attributeArrayLength;
		byte[] b;
		int len = 0;
		int temp = 0;
		String path = request.getSession().getServletContext().getRealPath("/");
		File f = null;

		String content = "";
		String interfaceID;
		ParamPojo[] paramPojoArray;
		int paramPojoArrayLength;

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		MultipartHttpServletRequest myfile = (MultipartHttpServletRequest) request;
		MultipartFile file = myfile.getFile("inputmodel");
		String name = file.getOriginalFilename();
		b = file.getBytes();
		/*
		 * String s2 = new String(b); System.out.println(s2);
		 */
		/* String s1 = new String(b, "utf-8"); */
		String s1 = new String(b);
		logger.info(s1);

		f = new File(path + File.separator + name);
		UUID uuid = UUID.randomUUID();
		JSONObject j = JSONObject.fromObject(s1);
		PEModelPojo pemodel = (PEModelPojo) JSONObject.toBean(j,
				PEModelPojo.class);
		peInterfaceArray = pemodel.getInterfaces();
		peInterfaceArrayLength = peInterfaceArray.length;

		PEInterfaceService peInterfaceService1 = (PEInterfaceService) GetBean
				.getBean("peInterfaceService");
		PETemplateService peTemplateService1 = (PETemplateService) GetBean
				.getBean("peTemplateService");
		PeTemplateProtocolHttpSevice peTemplateProtocolHttpSevice1 = (PeTemplateProtocolHttpSevice) GetBean
				.getBean("peTemplateProtocolHttpSevice");
		PeTemplateParamService peTemplateParamService1 = (PeTemplateParamService) GetBean
				.getBean("peTemplateParamService");

		while (peInterfaceArrayLength > 0) {
			PeInterface peInterface = new PeInterface();
			interfaceID = peInterfaceArray[peInterfaceArrayLength - 1]
					.getInterfaceID();
			String protocolType;
			String templateId;
			String protocolRule = null;

			protocolType = pemodel.getInterfaces()[peInterfaceArrayLength - 1]
					.getInterface_protocol();
			protocolRule = pemodel.getInterfaces()[peInterfaceArrayLength - 1]
					.getProtocol_rule();
			templateId = pemodel.getModel_id();

			peInterface.setTemplateId(pemodel.getModel_id());
			peInterface
					.setInterfaceRemark(pemodel.getInterfaces()[peInterfaceArrayLength - 1]
							.getInterface_description());
			peInterface.setInterfaceChannel(-1);
			peInterface.setInterfaceContent(content);
			peInterface.setInterfaceFashion(-1);
			peInterface.setInterfaceName("");
			peInterface.setInterfaceResponse(-1);
			peInterface.setInterfaceType(protocolType);
			peInterface.setResult("");
			peInterface.setResultType("");
			peInterface.setInterfaceId(interfaceID);
			peInterfaceArrayLength--;
			peInterfaceService1.add(peInterface);
			content = "";

			if (protocolType.equalsIgnoreCase("http") && protocolRule != null) {
				PeTemplateProtocolHttp peTemplateProtocolHttp = new PeTemplateProtocolHttp();
				peTemplateProtocolHttp.setTemplate_id(templateId);
				peTemplateProtocolHttp.setRule(protocolRule);
				peTemplateProtocolHttp.setInterface_id(interfaceID);
				peTemplateProtocolHttpSevice1.add(peTemplateProtocolHttp);

			}
		}

		// 获取attributes
		attributeArray = pemodel.getAttribute();
		attributeArrayLength = attributeArray.length;
		while (attributeArrayLength > 0) {
			PeTemplateParam peTemplateParam = new PeTemplateParam();
			AttributePojo attributePojo = new AttributePojo();
			attributePojo = pemodel.getAttribute()[attributeArrayLength - 1];
			String type = attributePojo.getType();
			int type_int = -1;
			if (type.endsWith("String"))
				type_int = 0;
			else if (type.endsWith("Int"))
				type_int = 1;
			else if (type.endsWith("enum"))
				type_int = 2;
			else if (type.endsWith("Float"))
				type_int = 3;
			else if (type.endsWith("Boolean"))
				type_int = 4;
			peTemplateParam.setDescription(attributePojo.getDescription());
			peTemplateParam.setKey(attributePojo.getKey());
			peTemplateParam.setOption(attributePojo.getOption());
			peTemplateParam.setTemplate_id(pemodel.getModel_id());
			peTemplateParam.setType(type_int);
			peTemplateParam.setUnit(attributePojo.getUnit());
			peTemplateParam.setValue_max(attributePojo.getValue_max());
			peTemplateParam.setValue_min(attributePojo.getValue_min());
			peTemplateParamService1.add(peTemplateParam);
			attributeArrayLength--;
		}
		peTemplate.setTemplateId(pemodel.getModel_id());
		peTemplate.setTemplateModel(pemodel.getModel());
		peTemplate.setTemplateName(pemodel.getName());
		peTemplate.setTemplatePicUrl(pemodel.getPic_url());
		peTemplate.setTemplateRemark(pemodel.getDescription());
		peTemplate.setTemplateTime(new Date());
		peTemplate.setTemplateType(pemodel.getType().getBigtype());

		peTemplateService1.add(peTemplate);

		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		response.setStatus(302);
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.1.1-SelectTemplate.jsp");

		// call AtomicRegister.registerAtomicVeTemplate function to register
		// ve-model
		boolean veModelRegisterResult = AtomicRegister
				.registerAtomicVeTemplate(s1);
		logger.info("VE_Model_Register_Result: " + veModelRegisterResult);
		return "a";
	}
	
	
	@RequestMapping(value = "/fileuploadtest.html", method = RequestMethod.POST)
	@ResponseBody
	public String recoveryPemodel(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("recoveryPemodel");
	
		byte[] b;
		int len = 0;
		int temp = 0;
		String path = request.getSession().getServletContext().getRealPath("/");
		File f = null;

		String content = "";
		String interfaceID;
		ParamPojo[] paramPojoArray;
		int paramPojoArrayLength;

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		MultipartHttpServletRequest myfile = (MultipartHttpServletRequest) request;
		MultipartFile file = myfile.getFile("inputmodel");
		String name = file.getOriginalFilename();
		b = file.getBytes();

		String pemodel = new String(b);
		System.out.println(pemodel);
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		map.put("pemodel", pemodel); 
		/*return new ModelAndView("/IoT_Harbor/jsp/json1","data",map);*/
		response.setStatus(302);
		response.addHeader("location","/IoT_Harbor/jsp/Module1/PemodelRevise.jsp?pemodel="+pemodel);
		return "Module1/json1.jsp";
	/*	response.sendRedirect("json1.jsp?pemodel="+pemodel);
		return "/IoT_Harbor/jsp/Module1/json1.jsp?pemodel="+pemodel;*/
	
	}
	
	
	/*
	 * 上传图片
	 */
	@RequestMapping(value = "/addpicture.html", method = RequestMethod.POST)
	@ResponseBody
	public String uploadPicture(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("uploadpicture");
		byte[] b;
		int len = 0;
		int temp = 0;
		String path = request.getSession().getServletContext().getRealPath("/");
		logger.info(path);
		File f = null;

		response.setContentType("text/html");

		MultipartHttpServletRequest myfile = (MultipartHttpServletRequest) request;
		MultipartFile file = myfile.getFile("inputPicture");
		String name = file.getOriginalFilename();
		f = new File(path + File.separator + name);
		b = file.getBytes();
		OutputStream out = new FileOutputStream(f);
		out.write(b);
		out.flush();
		out.close();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + "/IoT_Harbor" + "/" + name;
		logger.info(basePath);
		response.setStatus(302);
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.1.2-InputBasicInformationUploadImage.jsp?file="
						+ basePath);
		return basePath;
	}

	/*
	 * 查找大類
	 */
	@RequestMapping(value = "/type1.html", method = RequestMethod.GET)
	@ResponseBody
	public String queryType1(HttpServletRequest request) {

		JSONArray json = null;
		List list = new ArrayList();
		ClassPojo classPojo = new ClassPojo();
		classPojo.setClassId("11");
		classPojo.setClassName("a");
		ClassPojo classPojo1 = new ClassPojo();
		classPojo1.setClassId("12");
		classPojo1.setClassName("aaa");
		list.add(classPojo);
		list.add(classPojo1);

		json = JSONArray.fromObject(list);
		logger.info(json.toString());
		return json.toString();
	}

	/*
	 * 查詢中類
	 */
	@RequestMapping(value = "/type2.html", method = RequestMethod.GET)
	@ResponseBody
	public String queryType2(HttpServletRequest request) {

		JSONArray json = null;
		List list = new ArrayList();
		ClassPojo classPojo = new ClassPojo();
		classPojo.setClassId("21");
		classPojo.setClassName("bb");
		ClassPojo classPojo1 = new ClassPojo();
		classPojo1.setClassId("22");
		classPojo1.setClassName("bbb");
		list.add(classPojo);
		list.add(classPojo1);

		json = JSONArray.fromObject(list);
		logger.info(json.toString());
		return json.toString();

	}

	/*
	 * 查詢小類
	 */
	@RequestMapping(value = "/type3.html", method = RequestMethod.GET)
	@ResponseBody
	public String queryType3(HttpServletRequest request) {

		JSONArray json = null;
		List list = new ArrayList();
		ClassPojo classPojo = new ClassPojo();
		classPojo.setClassId("31");
		classPojo.setClassName("cc");
		ClassPojo classPojo1 = new ClassPojo();
		classPojo1.setClassId("31");
		classPojo1.setClassName("ccc");
		list.add(classPojo);
		list.add(classPojo1);

		json = JSONArray.fromObject(list);
		logger.info(json.toString());
		return json.toString();
	}

	/*
	 * 导入模板页面里的查询功能，输入大中小类和设备名称进行查询
	 */
	@RequestMapping(value = "/query.html", method = RequestMethod.POST)
	@ResponseBody
	public String queryDevice(HttpServletRequest request) {
		List<Map<String, Object>> template = null;
		String TypeLevel1 = null;
		String TypeLevel2 = null;
		String TypeLevel3 = null;
		String device_name = null;
		JSONArray json = null;
		List list = new ArrayList();

		PeTemplate peTemplate = new PeTemplate();
		TypeLevel1 = request.getParameter("TypeLevel1");
		TypeLevel2 = request.getParameter("TypeLevel2");
		TypeLevel3 = request.getParameter("TypeLevel3");
		device_name = request.getParameter("device_name");
		if (TypeLevel1 == null && TypeLevel2 == null && TypeLevel3 == null
				&& device_name == null) {
			json = JSONArray.fromObject(list);

		} else {
			// 通过接收到的参数查询数据 待完成
			peTemplate.setTemplateName(device_name);
			template = peTemplateService.query(peTemplate, 0, 1);
			Iterator iterator = template.iterator();
			int i = template.size();
			while (i > 0) {

				TemplatePojo templatePojo = new TemplatePojo();
				templatePojo.setTemplate_name(device_name);
				templatePojo.setProducer("CETC-15"); //TODO：在数据库的模板中加入生产商属性
				templatePojo.setTemplate_name((String) template.get(i - 1).get(
						"template_name"));
				templatePojo.setType((String) template.get(i - 1).get(
						"template_type"));
				templatePojo.setModel((String) template.get(i - 1).get(
						"template_model"));
				i--;
				list.add(templatePojo);

			}
			json = JSONArray.fromObject(list);
		}
		logger.info(json.toString());
		return json.toString();

	}

	/*
	 * 对应于填写设备基本信息的页面
	 */
	@RequestMapping(value = "/information.html", method = RequestMethod.GET)
	@ResponseBody
	public String writeInformation(HttpServletRequest request,
			HttpServletResponse response) {
		UUID uuid = null;
		Pe pe = new Pe();
		PeGeolocation peGeolocation = new PeGeolocation();
		logger.info("information");
		// ModelAndView view = new ModelAndView("mype");
		String template_id = null;
		String device_picture = null;
		String device_name = null;
		String device_identifier = null;
		String belongto = null;
		String Country = null;
		String Prov = null;
		String City = null;
		String OtherAddress = null;
		String pe_description = null;
		String userName = null;
		/*
		 * String IsShouq = null; String IsVisible = null;
		 */
		String longitude = null;
		String latitude = null;
		String altitude = null;
		String ispublic = null;
		String device_description = null;
		String peid = null;
		String check = "1";
		String panduan = "0";
		peid = request.getParameter("peid");
		template_id = request.getParameter("ID");
		logger.info(template_id);
		device_name = request.getParameter("device_name");
	 /*   System.out.println("device_name:"+device_name);*/
		device_identifier = request.getParameter("device_identifier");
		belongto = request.getParameter("belongto");
		Country = request.getParameter("Country");
		Prov = request.getParameter("Prov");
		City = request.getParameter("City");
		OtherAddress = request.getParameter("OtherAddress");
		pe_description = request.getParameter("device_description");
		userName = request.getParameter("username");
		System.out.println("userName:"+userName);
		ispublic = request.getParameter("public");
		longitude = request.getParameter("longitude");
		latitude = request.getParameter("latitude");
		altitude = request.getParameter("altitude");
		device_description = request.getParameter("device_description");
		device_picture = request.getParameter("device_picture");
		String s = UUID.randomUUID().toString();
		String geoId = UUID.randomUUID().toString();
		if (peid.equals("null")) {
			panduan = "1";
		} else {
			panduan = "2";

		}
		if (panduan.equals("1")) {
			peid = device_identifier;
			pe.setPeId(peid);
			check = "1";
		}
		if (panduan.equals("2")) {
			pe.setPeId(peid);
			check = "2";
		}
		pe.setPe_public(ispublic);
		pe.setPeKey("");
		pe.setPeLastTime(new Date());
		pe.setPeLifecycle(0);
		pe.setPeName(device_name);
		pe.setPeOwner(belongto);
		pe.setPePictureUrl(device_picture);
		pe.setPeState(0);
		pe.setPeTime(-1);
		pe.setPeUser("yp");
		pe.setPe_description(pe_description);
		pe.setTemplateId(template_id);
		pe.setPeGeolocationId(geoId);
		peGeolocation.setGeolocationId(geoId);
		peGeolocation.setCityId(1);
		peGeolocation.setCountryId(1);
		peGeolocation.setProvinceId(1);
		peGeolocation.setAltitude(Float.parseFloat(altitude));
		peGeolocation.setLatitude(Float.parseFloat(latitude));
		peGeolocation.setLongitude(Float.parseFloat(longitude));
		peGeolocation.setPeId(peid);
		peGeolocation.setOtherInfo(OtherAddress);
		if (check.equals("1")) {
			peGeolocationService.add(peGeolocation);
			peService.add(pe);
		} else {
			pe.setTemplateId(null);
			peGeolocationService.update(peGeolocation);
			peService.update(pe);
		}
		logger.info("peID: "+peid);
		response.setStatus(302);
		response.setContentType("text/html");
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.1.3-ActiveDevice.jsp?peid=" + peid);
		return "a";

	}

	/*
	 * 对应于激活页面的激活事件按钮
	 */
	@RequestMapping(value = "/active.html", method = RequestMethod.GET)
	@ResponseBody
	public String activeDevice(HttpServletRequest request,
			HttpServletResponse response) {
		Pe pe = new Pe();

		String peid = (String) request.getParameter("peid");
		pe.setPeId(peid);
		pe.setPeLifecycle(-1);
		pe.setPeState(1);
		pe.setPeTime(-1);
		peService.update(pe);
		logger.info(peid);
		response.setStatus(302);
		response.setContentType("text/html");
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.1.3-ActiveDevice.jsp?peid=" + peid);
		return "/Module1/1.1.3-ActiveDevice";

	}
	
	@RequestMapping(value = "/activePe.html", method = RequestMethod.GET)
	@ResponseBody
	public String activePE(HttpServletRequest request,
			HttpServletResponse response) {
		Pe pe = new Pe();

		System.out.println("Test Active PE");
		String peid = (String) request.getParameter("peid");
		pe.setPeId(peid);
		pe.setPeLifecycle(-1);
		pe.setPeState(1);
		pe.setPeTime(-1);
		peService.update(pe);
		logger.info(peid);
		
		return "OK";

	}

	/**
	 * this method is used to get available pe_model information
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getPEModel.html" , method = RequestMethod.GET)
	@ResponseBody
	public String getPEModel(HttpServletRequest request,HttpServletResponse response){
		List<Map<String,Object>> list = peTemplateService.queryAll();
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
	
	/**
	 * this method is used to get pe key information by peid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getPEKeyByPEID.html" , method = RequestMethod.GET)
	@ResponseBody
	public String getPEKeyByPEID(HttpServletRequest request, HttpServletResponse response){
		String peID = request.getParameter("peid");
		List<Map<String,Object>> list = peInterfaceKeyService.queryByPEID(peID);
		logger.info(list);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
	
	/**
	 * this method is used to get available pe list by pe_model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getPEByPEModelID.html" , method = RequestMethod.GET)
	@ResponseBody
	public String getPEByPEModelID(HttpServletRequest request,HttpServletResponse response){
		String templateID = request.getParameter("template_id");
		Pe pe = new Pe();
		pe.setTemplateId(templateID);
		pe.setPeState(-1);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);
		List<Map<String,Object>> list = peService.query(pe, 0, 100);
		logger.info(list);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();		
	}
	
	
	
	/*
	 * 对应于激活页面的取消激活事件按钮
	 */
	@RequestMapping(value = "/unactive.html", method = RequestMethod.GET)
	@ResponseBody
	public String unactiveDevice(HttpServletRequest request,
			HttpServletResponse response) {
		Pe pe = new Pe();

		String peid = (String) request.getParameter("peid");
		pe.setPeId(peid);
		pe.setPeLifecycle(1);
		peService.update(pe);
		logger.info(peid);
		response.setStatus(302);
		response.setContentType("text/html");
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.1.3-ActiveDevice.jsp?peid=" + peid);
		return "/Module1/1.1.3-ActiveDevice";

	}

	/*
	 * 对应于我的设备页面的查询事件
	 */
	/*
	 * 对应于激活页面的激活事件按钮
	 * 
	 * @RequestMapping(value = "/active.html", method = RequestMethod.GET) //
	 * @ResponseBody public String activeDevice(HttpServletRequest request) { Pe
	 * pe = new Pe();
	 * 
	 * String peid = (String) request.getParameter("peid"); pe.setPeId(peid);
	 * pe.setPeLifecycle(1); peService.update(pe); System.out.println(peid);
	 * return "/Module1/1.1.3-ActiveDevice";
	 * 
	 * }
	 * 
	 * 对应于激活页面的取消激活事件按钮
	 * 
	 * @RequestMapping(value = "/unactive.html", method = RequestMethod.GET) //
	 * @ResponseBody public String unactiveDevice(HttpServletRequest request) {
	 * Pe pe = new Pe();
	 * 
	 * String peid = (String) request.getParameter("peid"); pe.setPeId(peid);
	 * pe.setPeLifecycle(1); peService.update(pe); System.out.println(peid);
	 * return "/Module1/1.1.3-ActiveDevice";
	 * 
	 * }
	 * 
	 * 对应于我的设备页面的查询事件
	 */
	@RequestMapping(value = "/mydevice.html", method = RequestMethod.POST)
	@ResponseBody
	public String myDevice(HttpServletRequest request) {
		String deviceName = null;
		String devicelifecycle = null;
		/* String lifeCycle = null; */
		String devicestate = null;
		String TypeLevel1 = null;
		String TypeLevel2 = null;
		String TypeLevel3 = null;
		JSONArray json = new JSONArray();
		Pe pe = new Pe();
		List<MyDevice> list = new ArrayList();
		List<Map<String, Object>> pelist = null;
		TypeLevel1 = request.getParameter("TypeLevel1");
		TypeLevel2 = request.getParameter("TypeLevel2");
		TypeLevel3 = request.getParameter("TypeLevel3");
		deviceName = request.getParameter("deviceName");
		devicelifecycle = request.getParameter("devicelifecycle");
		devicestate = request.getParameter("devicestate");

		if (deviceName == null && devicelifecycle == null
				&& devicestate == null && TypeLevel1 == null
				&& TypeLevel2 == null && TypeLevel3 == null) {
			json = JSONArray.fromObject(list);
		} else {

			if (devicestate != null && devicestate != "") {
				pe.setPeState(Integer.parseInt(devicestate));
			} else {
				pe.setPeState(-1);
			}
			if (deviceName != null && deviceName != "") {
				pe.setPeName(deviceName);
			}

			if (devicelifecycle != null && devicelifecycle != "") {
				pe.setPeLifecycle(Integer.parseInt(devicelifecycle));
			} else {
				pe.setPeLifecycle(-1);
			}
			pe.setPeTime(-1);
			pelist = peService.query(pe, 0, 100);
			int i = pelist.size();
			while (i > 0) {
				MyDevice myDevice = new MyDevice();
				myDevice.setDevicename((String) pelist.get(i - 1)
						.get("pe_name"));

				myDevice.setID((String) pelist.get(i - 1).get("pe_id"));
				if (((String) pelist.get(i - 1).get("pe_lifecycle").toString())
						.equals("0")) {
					myDevice.setLifecycle("正常");
				} else if (((String) pelist.get(i - 1).get("pe_lifecycle")
						.toString()).equals("1")) {
					myDevice.setLifecycle("待激活");
				} else if (((String) pelist.get(i - 1).get("pe_lifecycle")
						.toString()).equals("2")) {
					myDevice.setLifecycle("冻结");
				}
				if (((String) pelist.get(i - 1).get("pe_state").toString())
						.equals("0")) {
					myDevice.setStatus("在线");
				} else if (((String) pelist.get(i - 1).get("pe_state")
						.toString()).equals("1")) {
					myDevice.setStatus("故障");
				} else if (((String) pelist.get(i - 1).get("pe_state")
						.toString()).equals("2")) {
					myDevice.setStatus("离线");
				}
				myDevice.setVisable("是");
				myDevice.setBelongto(pelist.get(i - 1).get("pe_owner")
						.toString());
				i--;
				list.add(myDevice);
			}
			json = JSONArray.fromObject(list);
			logger.info(json.toString());
		}
		return json.toString();

	}
	@RequestMapping(value = "/unauthor.html", method = RequestMethod.POST)
	@ResponseBody
	public String unauthor(HttpServletRequest request) {
		/*
		 * Pe pe = new Pe(); VePeBind vepebind = new VePeBind();
		 * List<Map<String, Object>> pelist = null; List<Map<String, Object>>
		 * velist = null; pe.setPeId("10f1d484-8862-4a5e-b1fc-1231ecd44cf3");
		 * pe.setPeState(-1); pe.setPeLifecycle(-1); pe.setPeTime(-1);
		 * 
		 * pelist = peService.query(pe, 0, 100);
		 * vepebind.setPe_id("10f1d484-8862-4a5e-b1fc-1231ecd44cf3");
		 * vepebind.setState(1); velist = vePeService.query(vepebind, 0, 1);
		 * List list = new ArrayList(); JSONArray json = new JSONArray();
		 * if(velist.size()>0){ ProvideManager provideManager = new
		 * ProvideManager();
		 * provideManager.setProductid((String)pelist.get(0).get("pe_id"));
		 * provideManager.setApplyuser("yp"); provideManager.setBelong("yp");
		 * provideManager.setDevicename((String)pelist.get(0).get("pe_name"));
		 * 
		 * if(pelist.get(0).get("pe_lifecycle").toString().equals("0")){
		 * provideManager.setLifecycle("正常"); } else
		 * if(pelist.get(0).get("pe_lifecycle").toString().equals("1")){
		 * provideManager.setLifecycle("待激活"); } else
		 * if(pelist.get(0).get("pe_lifecycle").toString().equals("2")){
		 * provideManager.setLifecycle("故障"); }
		 * 
		 * if(pelist.get(0).get("pe_state").toString().equals("0")){
		 * provideManager.setStatus("在线"); }
		 * if(pelist.get(0).get("pe_state").toString().equals("1")){
		 * provideManager.setStatus("故障"); } else
		 * if(pelist.get(0).get("pe_state").toString().equals("2")){
		 * provideManager.setStatus("离线"); }
		 * provideManager.setVe((String)velist.get(0).get("ve_id"));
		 * provideManager.setVisable("Public"); list.add(provideManager); } json
		 * = JSONArray.fromObject(list); System.out.println(json); return
		 * json.toString();
		 */
		Pe pe ;
		List list = new ArrayList();
		JSONArray json = new JSONArray();
		VePeBind vepebind = new VePeBind();
		int vepebindLength;
		List<Map<String, Object>> pelist = null;
		List<VePeBind> velist = null;
		vepebind.setState("2");
		velist = vePeService.query(vepebind, 0, 100);
       
        vepebindLength = velist.size();
        while(vepebindLength>0){
        	pe = new Pe();
        	ProvideManager provideManager = new ProvideManager();
        	pe.setPeId(velist.get(vepebindLength-1).getPe_id().toString());
        	pe.setPeState(-1);
    		pe.setPeLifecycle(-1);
    		pe.setPeTime(-1);
    		pelist = peService.query(pe, 0, 100);
    		
    		provideManager.setVe((String) velist.get(0).getVe_id());
			provideManager.setVisable((String) pelist.get(0).get("pe_public"));
			provideManager.setProductid((String) pelist.get(0).get("pe_id"));
			provideManager.setApplyuser("yp");
			provideManager.setBelong((String) pelist.get(0).get("pe_owner"));
			provideManager.setDevicename((String) pelist.get(0).get("pe_name"));
            if (pelist.get(0).get("pe_lifecycle").toString().equals("0")) {
				provideManager.setLifecycle("正常");
			} else if (pelist.get(0).get("pe_lifecycle").toString().equals("1")) {
				provideManager.setLifecycle("待激活");
			} else if (pelist.get(0).get("pe_lifecycle").toString().equals("2")) {
				provideManager.setLifecycle("故障");
			}
            if (pelist.get(0).get("pe_state").toString().equals("0")) {
				provideManager.setStatus("在线");
			}
			if (pelist.get(0).get("pe_state").toString().equals("1")) {
				provideManager.setStatus("故障");
			} else if (pelist.get(0).get("pe_state").toString().equals("2")) {
				provideManager.setStatus("离线");
			}
			list.add(provideManager);
			vepebindLength--;
        }
		json = JSONArray.fromObject(list);
		logger.info(json.toString());
		return json.toString();

	}

	/*
	 * 对应于授权管理页面里的已授权查询
	 */
	@RequestMapping(value = "/provideManager.html", method = RequestMethod.POST)
	@ResponseBody
	public String provideManager(HttpServletRequest request) {
		/*
		 * Pe pe = new Pe(); VePeBind vepebind = new VePeBind();
		 * List<Map<String, Object>> pelist = null; List<Map<String, Object>>
		 * velist = null; pe.setPeId("10f1d484-8862-4a5e-b1fc-1231ecd44cf3");
		 * pe.setPeState(-1); pe.setPeLifecycle(-1); pe.setPeTime(-1);
		 * 
		 * pelist = peService.query(pe, 0, 100);
		 * vepebind.setPe_id("10f1d484-8862-4a5e-b1fc-1231ecd44cf3");
		 * vepebind.setState(1); velist = vePeService.query(vepebind, 0, 1);
		 * List list = new ArrayList(); JSONArray json = new JSONArray();
		 * if(velist.size()>0){ ProvideManager provideManager = new
		 * ProvideManager();
		 * provideManager.setProductid((String)pelist.get(0).get("pe_id"));
		 * provideManager.setApplyuser("yp"); provideManager.setBelong("yp");
		 * provideManager.setDevicename((String)pelist.get(0).get("pe_name"));
		 * 
		 * if(pelist.get(0).get("pe_lifecycle").toString().equals("0")){
		 * provideManager.setLifecycle("正常"); } else
		 * if(pelist.get(0).get("pe_lifecycle").toString().equals("1")){
		 * provideManager.setLifecycle("待激活"); } else
		 * if(pelist.get(0).get("pe_lifecycle").toString().equals("2")){
		 * provideManager.setLifecycle("故障"); }
		 * 
		 * if(pelist.get(0).get("pe_state").toString().equals("0")){
		 * provideManager.setStatus("在线"); }
		 * if(pelist.get(0).get("pe_state").toString().equals("1")){
		 * provideManager.setStatus("故障"); } else
		 * if(pelist.get(0).get("pe_state").toString().equals("2")){
		 * provideManager.setStatus("离线"); }
		 * provideManager.setVe((String)velist.get(0).get("ve_id"));
		 * provideManager.setVisable("Public"); list.add(provideManager); } json
		 * = JSONArray.fromObject(list); System.out.println(json); return
		 * json.toString();
		 */
		Pe pe ;
		List list = new ArrayList();
		JSONArray json = new JSONArray();
		VePeBind vepebind = new VePeBind();
		int vepebindLength;
		List<Map<String, Object>> pelist = null;
		List<VePeBind> velist = null;
		vepebind.setState("1");
		velist = vePeService.query(vepebind, 0, 100);
       
        vepebindLength = velist.size();
        while(vepebindLength>0){
        	pe = new Pe();
        	ProvideManager provideManager = new ProvideManager();
        	pe.setPeId(velist.get(vepebindLength-1).getPe_id().toString());
        	pe.setPeState(-1);
    		pe.setPeLifecycle(-1);
    		pe.setPeTime(-1);
    		pelist = peService.query(pe, 0, 100);
    		
    		provideManager.setVe((String) velist.get(0).getVe_id());
			provideManager.setVisable((String) pelist.get(0).get("pe_public"));
			provideManager.setProductid((String) pelist.get(0).get("pe_id"));
			provideManager.setApplyuser("yp");
			provideManager.setBelong((String) pelist.get(0).get("pe_owner"));
			provideManager.setDevicename((String) pelist.get(0).get("pe_name"));
            if (pelist.get(0).get("pe_lifecycle").toString().equals("0")) {
				provideManager.setLifecycle("正常");
			} else if (pelist.get(0).get("pe_lifecycle").toString().equals("1")) {
				provideManager.setLifecycle("待激活");
			} else if (pelist.get(0).get("pe_lifecycle").toString().equals("2")) {
				provideManager.setLifecycle("故障");
			}
            if (pelist.get(0).get("pe_state").toString().equals("0")) {
				provideManager.setStatus("在线");
			}
			if (pelist.get(0).get("pe_state").toString().equals("1")) {
				provideManager.setStatus("故障");
			} else if (pelist.get(0).get("pe_state").toString().equals("2")) {
				provideManager.setStatus("离线");
			}
			list.add(provideManager);
			vepebindLength--;
        }
		json = JSONArray.fromObject(list);
		logger.info(json.toString());
		return json.toString();

	}

	@RequestMapping(value = "/provideManager1.html", method = RequestMethod.POST)
	@ResponseBody
	public String provideManager1(HttpServletRequest request) {
		/*Pe pe = new Pe();
		VePeBind vepebind = new VePeBind();
		List<Map<String, Object>> pelist = null;
		List<Map<String, Object>> velist = null;
		pe.setPeId("10f1d484-8862-4a5e-b1fc-1231ecd44cf3");
		pe.setPeState(-1);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);
		List list = new ArrayList();
		pelist = peService.query(pe, 0, 100);
		vepebind.setPe_id("10f1d484-8862-4a5e-b1fc-1231ecd44cf3");
		vepebind.setState(0);
		JSONArray json = new JSONArray();
		velist = vePeService.query(vepebind, 0, 1);

		if (velist.size() > 0) {
			ProvideManager provideManager = new ProvideManager();
			provideManager.setProductid((String) pelist.get(0).get("pe_id"));
			provideManager.setApplyuser("yp");
			provideManager.setBelong("yp");
			provideManager.setDevicename((String) pelist.get(0).get("pe_name"));

			if (pelist.get(0).get("pe_lifecycle").equals("0")) {
				provideManager.setLifecycle("正常");
			} else if (pelist.get(0).get("pe_lifecycle").equals("1")) {
				provideManager.setLifecycle("待激活");
			} else if (pelist.get(0).get("pe_lifecycle").equals("2")) {
				provideManager.setLifecycle("故障");
			}

			else if (pelist.get(0).get("pe_state").toString().equals("0")) {
				provideManager.setStatus("在线");
			}

			else {
				provideManager.setStatus("未知");
			}
			if (pelist.get(0).get("pe_state").toString().equals("1")) {
				provideManager.setStatus("故障");
			} else if (pelist.get(0).get("pe_state").toString().equals("2")) {
				provideManager.setStatus("离线");
			}
			provideManager.setVe((String) velist.get(0).get("ve_id"));
			provideManager.setVisable("Public");
			list.add(provideManager);
		}
		json = JSONArray.fromObject(list);
		System.out.println(json);
		return json.toString();
*/
		Pe pe ;
		List list = new ArrayList();
		JSONArray json = new JSONArray();
		VePeBind vepebind = new VePeBind();
		int vepebindLength;
		List<Map<String, Object>> pelist = null;
		List<VePeBind> velist = null;
		vepebind.setState("0");
		velist = vePeService.query(vepebind, 0, 100);
       
        vepebindLength = velist.size();
        while(vepebindLength>0){
        	pe = new Pe();
        	ProvideManager provideManager = new ProvideManager();
        	pe.setPeId(velist.get(vepebindLength-1).getPe_id().toString());
        	pe.setPeState(-1);
    		pe.setPeLifecycle(-1);
    		pe.setPeTime(-1);
    		pelist = peService.query(pe, 0, 100);
    		
    		provideManager.setVe((String) velist.get(0).getVe_id());
			provideManager.setVisable((String) pelist.get(0).get("pe_public"));
			provideManager.setProductid((String) pelist.get(0).get("pe_id"));
			provideManager.setApplyuser("yp");
			provideManager.setBelong((String) pelist.get(0).get("pe_owner"));
			provideManager.setDevicename((String) pelist.get(0).get("pe_name"));
            if (pelist.get(0).get("pe_lifecycle").toString().equals("0")) {
				provideManager.setLifecycle("正常");
			} else if (pelist.get(0).get("pe_lifecycle").toString().equals("1")) {
				provideManager.setLifecycle("待激活");
			} else if (pelist.get(0).get("pe_lifecycle").toString().equals("2")) {
				provideManager.setLifecycle("故障");
			}
            if (pelist.get(0).get("pe_state").toString().equals("0")) {
				provideManager.setStatus("在线");
			}
			if (pelist.get(0).get("pe_state").toString().equals("1")) {
				provideManager.setStatus("故障");
			} else if (pelist.get(0).get("pe_state").toString().equals("2")) {
				provideManager.setStatus("离线");
			}
			list.add(provideManager);
			vepebindLength--;
        }
		json = JSONArray.fromObject(list);
		logger.info(json.toString());
		return json.toString();

	}

	/*
	 * 对应于授权管理页面里的未授权查询
	 */
	/*
	 * @RequestMapping(value = "/provideManager1.html", method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public String provideManager1(HttpServletRequest request) {
	 * Pe pe = new Pe(); VePeBind vepebind = new VePeBind(); List<Map<String,
	 * Object>> pelist = null; List<Map<String, Object>> velist = null;
	 * pe.setPeId("10f1d484-8862-4a5e-b1fc-1231ecd44cf3"); pe.setPeState(-1);
	 * pe.setPeLifecycle(-1); pe.setPeTime(-1); List list = new ArrayList();
	 * pelist = peService.query(pe, 0, 100);
	 * vepebind.setPe_id("10f1d484-8862-4a5e-b1fc-1231ecd44cf3");
	 * vepebind.setState(0); JSONArray json = new JSONArray(); velist =
	 * vePeService.query(vepebind, 0, 1); if(velist.size()>0){
	 * 
	 * 
	 * ProvideManager1 provideManager = new ProvideManager1();
	 * provideManager.setProductid1((String)pelist.get(0).get("pe_id"));
	 * provideManager.setAuthor1("yp"); provideManager.setBelong1("yp");
	 * provideManager.setDevicename1((String)pelist.get(0).get("pe_name"));
	 * provideManager
	 * .setLifecycle1(pelist.get(0).get("pe_lifecycle").toString());
	 * if(pelist.get(0).get("pe_state").toString().equals("0")){
	 * provideManager.setStatus1("在线"); }
	 * if(pelist.get(0).get("pe_state").toString().equals("1")){
	 * provideManager.setStatus1("故障"); }
	 * if(pelist.get(0).get("pe_state").toString().equals("2")){
	 * provideManager.setStatus1("离线"); }
	 * provideManager.setVe1((String)velist.get(0).get("ve_id"));
	 * provideManager.setVisable1(" ");
	 * 
	 * list.add(provideManager); } json = JSONArray.fromObject(list);
	 * System.out.println(json);
	 * 
	 * return json.toString();
	 * 
	 * 
	 * }
	 */
	/*
	 * 对应于设备查询页面
	 */
	@RequestMapping(value = "/viewdevice.html", method = RequestMethod.GET)
	@ResponseBody
	public String viewDevice(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		/*
		 * String deviceName = null; String deviceAvailableTime = null; String
		 * lifeCycle = null; String devicestate = null; String TypeLevel1 =
		 * null; String TypeLevel2 = null; String TypeLevel3 = null; JSONArray
		 * json = new JSONArray(); Pe pe = new Pe(); List<MyDevice> list = new
		 * ArrayList(); List<Map<String, Object>> pelist = null; TypeLevel1 =
		 * request.getParameter("TypeLevel1"); TypeLevel2 =
		 * request.getParameter("TypeLevel2"); TypeLevel3 =
		 * request.getParameter("TypeLevel3"); deviceName =
		 * request.getParameter("deviceName"); deviceAvailableTime =
		 * request.getParameter("deviceAvailableTime"); devicestate =
		 * request.getParameter("deviceStatus");
		 * 
		 * if (deviceName == null && deviceAvailableTime == null && devicestate
		 * == null && TypeLevel1 == null && TypeLevel2 == null && TypeLevel3 ==
		 * null) { json = JSONArray.fromObject(list); } else {
		 * 
		 * if(devicestate!=null&&devicestate!=""){
		 * pe.setPeState(Integer.parseInt(devicestate)); } else{
		 * pe.setPeState(-1); } if(deviceName!=null&&deviceName!=""){
		 * pe.setPeName(deviceName); }
		 * 
		 * 
		 * if(deviceAvailableTime!=null&&deviceAvailableTime!=""){
		 * pe.setPeLifecycle(Integer.parseInt(deviceAvailableTime)); } else{
		 * pe.setPeLifecycle(-1); } pe.setPeTime(-1); pelist =
		 * peService.query(pe, 0, 100); int i = pelist.size(); while(i>0){
		 * MyDevice myDevice = new MyDevice();
		 * myDevice.setDevicename((String)pelist.get(i-1).get("pe_name"));
		 * 
		 * myDevice.setID((String)pelist.get(i-1).get("pe_id"));
		 * myDevice.setLifecycle((String)pelist.get(i-1).get("pe_name"));
		 * myDevice
		 * .setStatus((String)pelist.get(i-1).get("pe_state").toString());
		 * myDevice.setVisable("是");
		 * myDevice.setBelongto(pelist.get(i-1).get("pe_lifecycle").toString());
		 * i--; list.add(myDevice); } json = JSONArray.fromObject(list);
		 * System.out.println(json); } return json.toString();
		 */
		String belongto = null;
		String deviceName = null;
		String devicelifecycle = null;
		/* String lifeCycle = null; */
		String devicestate = null;
		String TypeLevel1 = null;
		String TypeLevel2 = null;
		String TypeLevel3 = null;
		JSONArray json = new JSONArray();
		Pe pe = new Pe();
		List<MyDevice> list = new ArrayList();
		List<Map<String, Object>> pelist = null;
		TypeLevel1 = request.getParameter("TypeLevel1");
		TypeLevel2 = request.getParameter("TypeLevel2");
		TypeLevel3 = request.getParameter("TypeLevel3");
		deviceName = request.getParameter("deviceName");
		devicelifecycle = request.getParameter("devicelifecycle");
		devicestate = request.getParameter("devicestate");
		belongto = request.getParameter("belong");
		if (deviceName == null && devicelifecycle == null
				&& devicestate == null && TypeLevel1 == null
				&& TypeLevel2 == null && TypeLevel3 == null && belongto == null) {
			json = JSONArray.fromObject(list);
		} else {

			if (devicestate != null && devicestate != "") {
				pe.setPeState(Integer.parseInt(devicestate));
			} else {
				pe.setPeState(-1);
			}
			if (deviceName != null && deviceName != "") {
				pe.setPeName(deviceName);
			}
			if (belongto != null && belongto != "") {
				pe.setPeOwner(belongto);
			}

			if (devicelifecycle != null && devicelifecycle != "") {
				pe.setPeLifecycle(Integer.parseInt(devicelifecycle));
			} else {
				pe.setPeLifecycle(-1);
			}
			pe.setPeTime(-1);
			pelist = peService.query(pe, 0, 100);
			int i = pelist.size();
			while (i > 0) {
				MyDevice myDevice = new MyDevice();
				myDevice.setDevicename((String) pelist.get(i - 1)
						.get("pe_name"));

				myDevice.setID((String) pelist.get(i - 1).get("pe_id"));
				if (((String) pelist.get(i - 1).get("pe_lifecycle").toString())
						.equals("0")) {
					myDevice.setLifecycle("正常");
				} else if (((String) pelist.get(i - 1).get("pe_lifecycle")
						.toString()).equals("1")) {
					myDevice.setLifecycle("待激活");
				} else if (((String) pelist.get(i - 1).get("pe_lifecycle")
						.toString()).equals("2")) {
					myDevice.setLifecycle("冻结");
				}
				if (((String) pelist.get(i - 1).get("pe_state").toString())
						.equals("0")) {
					myDevice.setStatus("在线");
				} else if (((String) pelist.get(i - 1).get("pe_state")
						.toString()).equals("1")) {
					myDevice.setStatus("故障");
				} else if (((String) pelist.get(i - 1).get("pe_state")
						.toString()).equals("2")) {
					myDevice.setStatus("离线");
				}
				myDevice.setVisable("是");
				myDevice.setBelongto(pelist.get(i - 1).get("pe_owner")
						.toString());
				i--;
				list.add(myDevice);
			}
			json = JSONArray.fromObject(list);
			logger.info(json.toString());
		}
		return json.toString();
	}

	@RequestMapping(value = "/mydevice_delete.html", method = RequestMethod.GET)
	@ResponseBody
	public String mydeviceDelete(HttpServletRequest request) {
		String ID = request.getParameter("ID");
		logger.info("mydevice_delete: "+ID);
		Pe pe = new Pe();
		pe.setPeId(ID);
		pe.setPeState(-1);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);
		peService.delete(pe);
		return null;

	}

/*	@RequestMapping(value = "/unviewactive.html", method = RequestMethod.GET)
	@ResponseBody
	public String unviewActive(HttpServletRequest request) {
		System.out.println("mydevice_active");
		String ID = request.getParameter("ID");
		Pe pe = new Pe();
		pe.setPeId(ID);
		pe.setPeState(1);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);
		peService.update(pe);
		return null;

	}

	@RequestMapping(value = "/unviewunactive.html", method = RequestMethod.GET)
	@ResponseBody
	public String unviewUnactive(HttpServletRequest request) {
		System.out.println("mydevice_unactive");
		String ID = request.getParameter("ID");
		Pe pe = new Pe();
		pe.setPeId(ID);
		pe.setPeState(0);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);
		peService.update(pe);
		return null;

	}
	*/

	@RequestMapping(value = "/mydevice_active.html", method = RequestMethod.GET)
	@ResponseBody
	public String mydeviceActive(HttpServletRequest request) {
		logger.info("mydevice_active");
		String ID = request.getParameter("ID");
		Pe pe = new Pe();
		pe.setPeId(ID);
		pe.setPeState(1);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);
		peService.update(pe);
		return null;

	}

	@RequestMapping(value = "/mydevice_unactive.html", method = RequestMethod.GET)
	@ResponseBody
	public String mydeviceUnactive(HttpServletRequest request) {
		logger.info("mydevice_unactive");
		String ID = request.getParameter("ID");
		Pe pe = new Pe();
		pe.setPeId(ID);
		pe.setPeState(0);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);
		peService.update(pe);
		return null;

	}

	@RequestMapping(value = "/mydevice_update.html", method = RequestMethod.GET)
	@ResponseBody
	public String mydeviceUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("mydevice_update");
		String peid = request.getParameter("ID");
		Pe pe = new Pe();

		String path = request.getSession().getServletContext().getRealPath("/");
		File f = null;
		response.setContentType("text/html");
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		response.setStatus(302);
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.2.1.1-MyDeviceChange.jsp?peid="
						+ peid);
		return "a";

	}

	@RequestMapping(value = "/mydevice_query.html", method = RequestMethod.GET)
	@ResponseBody
	public String mydeviceQuery(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("mydevice_query");
		List<Map<String, Object>> pelist = null;
		String path = request.getSession().getServletContext().getRealPath("/");
		String ID = request.getParameter("ID");

		Pe pe = new Pe();
		pe.setPeId(ID);
		pe.setPeState(-1);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);
		pelist = peService.query(pe, 0, 1);
		String template_id = null;
		String device_picture = null;
		String device_name = null;
		String device_identifier = null;
		String belongto = null;
		String Country = null;
		String Prov = null;
		String City = null;
		String OtherAddress = null;
		String IsShouq = null;
		String IsVisible = null;
		String longitude = null;
		String latitude = null;
		String altitude = null;
		String device_description = null;
		String peid = null;
		String pe_lifecycle = null;
		String pe_state = null;
		String param;
		String lifecycle_temp;
		String state_temp;
		String pe_description = null;
		if (pelist.size() > 0) {
			device_name = ((String) pelist.get(0).get("pe_name") != null) ? (String) pelist
					.get(0).get("pe_name") : "";
			device_identifier = ((String) pelist.get(0).get("pe_name") != null) ? (String) pelist
					.get(0).get("pe_id") : "";
			device_picture = ((String) pelist.get(0).get("device_picture") != null) ? (String) pelist
					.get(0).get("device_picture") : "";
			belongto = ((String) pelist.get(0).get("pe_owner") != null) ? (String) pelist
					.get(0).get("pe_owner") : "";
			lifecycle_temp = (pelist.get(0).get("pe_lifecycle").toString() != null) ? (String) pelist
					.get(0).get("pe_lifecycle").toString()
					: "";
			pe_description = (pelist.get(0).get("pe_description").toString() != null) ? (String) pelist
					.get(0).get("pe_description").toString()
					: "";
			if(lifecycle_temp.equals("0")){
				pe_lifecycle = java.net.URLEncoder.encode("正常","utf-8");
			}
			if(lifecycle_temp.equals("1")){
				pe_lifecycle = java.net.URLEncoder.encode("待激活","utf-8");
			}
			if(lifecycle_temp.equals("2")){
				pe_lifecycle = java.net.URLEncoder.encode("冻结","utf-8");
			}
			state_temp = (pelist.get(0).get("pe_state").toString() != null) ? (String) pelist
					.get(0).get("pe_state").toString()
					: "";
			if(state_temp.equals("0")){
				
				pe_state = java.net.URLEncoder.encode("在线","utf-8");
			}
			if(state_temp.equals("1")){
				pe_state = java.net.URLEncoder.encode("故障","utf-8");
			}
			if(state_temp.equals("2")){
				pe_state = java.net.URLEncoder.encode("离线","utf-8");
			}

		}
		param = "device_name=" + device_name + "&device_picture="
				+ device_picture + "&belongto=" + belongto + "&pe_lifecycle="
				+ pe_lifecycle + "&pe_state=" + pe_state + "&longitude="
				+ "100" + "&latitude=" + "100" + "&altitude=" + "100"+"&device_identify="+device_identifier
				+"&device_description="+pe_description;
		logger.info("------------------------");
		logger.info(param);
		logger.info("------------------------");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		response.setStatus(302);
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.2.1.1-DeviceInformationViewer.jsp?"
						+ param);
		return "a";

	}

	@RequestMapping(value = "/providemanagerquery.html", method = RequestMethod.GET)
	@ResponseBody
	public String providemanagerQuery(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		/*System.out.println("mydevice_query");
		String path = request.getSession().getServletContext().getRealPath("/");
		File f = null;
		response.setContentType("text/html");
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		response.setStatus(302);
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.2.1.1-DeviceInformationViewer.jsp?device_name="
						+ "test");
		return "a";*/
		List<Map<String, Object>> pelist = null;
		String path = request.getSession().getServletContext().getRealPath("/");
		String ID = request.getParameter("ID");
		Pe pe = new Pe();
		pe.setPeId(ID);
		pe.setPeState(-1);
		pe.setPeLifecycle(-1);
		pe.setPeTime(-1);
		pelist = peService.query(pe, 0, 1);
		String template_id = null;
		String device_picture = null;
		String device_name = null;
		String device_identifier = null;
		String belongto = null;
		String Country = null;
		String Prov = null;
		String City = null;
		String OtherAddress = null;
		String IsShouq = null;
		String IsVisible = null;
		String longitude = null;
		String latitude = null;
		String altitude = null;
		String device_description = null;
		String peid = null;
		String pe_lifecycle = null;
		String pe_state = null;
		String param;
		String lifecycle_temp;
		String state_temp;
		String pe_description = null;
		if (pelist.size() > 0) {
			device_name = ((String) pelist.get(0).get("pe_name") != null) ? (String) pelist
					.get(0).get("pe_name") : "";
			device_identifier = ((String) pelist.get(0).get("pe_name") != null) ? (String) pelist
					.get(0).get("pe_id") : "";
			device_picture = ((String) pelist.get(0).get("device_picture") != null) ? (String) pelist
					.get(0).get("device_picture") : "";
			belongto = ((String) pelist.get(0).get("pe_owner") != null) ? (String) pelist
					.get(0).get("pe_owner") : "";
			lifecycle_temp = (pelist.get(0).get("pe_lifecycle").toString() != null) ? (String) pelist
					.get(0).get("pe_lifecycle").toString()
					: "";
			pe_description = (pelist.get(0).get("pe_description").toString() != null) ? (String) pelist
							.get(0).get("pe_description").toString()
							: "";
			if(lifecycle_temp.equals("0")){
				pe_lifecycle = java.net.URLEncoder.encode("正常","utf-8");
			}
			if(lifecycle_temp.equals("1")){
				pe_lifecycle = java.net.URLEncoder.encode("待激活","utf-8");
			}
			if(lifecycle_temp.equals("2")){
				pe_lifecycle = java.net.URLEncoder.encode("冻结","utf-8");
			}
			state_temp = (pelist.get(0).get("pe_state").toString() != null) ? (String) pelist
					.get(0).get("pe_state").toString()
					: "";
			if(state_temp.equals("0")){
				
				pe_state = java.net.URLEncoder.encode("在线","utf-8");
			}
			if(state_temp.equals("1")){
				pe_state = java.net.URLEncoder.encode("故障","utf-8");
			}
			if(state_temp.equals("2")){
				pe_state = java.net.URLEncoder.encode("离线","utf-8");
			}

		}
		param = "device_name=" + device_name + "&device_picture="
				+ device_picture + "&belongto=" + belongto + "&pe_lifecycle="
				+ pe_lifecycle + "&pe_state=" + pe_state + "&longitude="
				+ "100" + "&latitude=" + "100" + "&altitude=" + "100"+"&device_identify="+device_identifier
				+"&device_description="+pe_description;
		logger.info("-------------------------");
		logger.info(param);
		logger.info("-------------------------");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		response.setStatus(302);
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.2.1.1-DeviceInformationViewer.jsp?"
						+ param);
		return "a";

	}

	@RequestMapping(value = "/viewdevice_shenqing.html", method = RequestMethod.GET)
	@ResponseBody
	public String viewdeviceShenqing(HttpServletRequest request) {
		String ID = request.getParameter("ID");
		logger.info("viewdevice_shenqing: "+ID);

		return null;

	}

	@RequestMapping(value = "/providemanager_jujue.html", method = RequestMethod.GET)
	@ResponseBody
	public String providemanagerJujue(HttpServletRequest request) {
		String ID = request.getParameter("ID");
		logger.info("providemanager_jujue: "+ID);
		String co_id = null;
		VePeBind vepebind = new VePeBind();
		List<Map<String, Object>> pelist = null;
		List<VePeBind> velist = null;
		vepebind.setPe_id(ID);
		vepebind.setState("1");
		velist = vePeService.query(vepebind, 0, 1);
		if(velist.size()>0){
		   co_id = (String) velist.get(0).getBind_id();
		   System.out.println(co_id);
		   //vepebind.setCo_id(co_id);
		   vepebind.setBind_id(co_id); //change by wanghan
		   vepebind.setState("0");
		   vePeService.update(vepebind);
		}
		return null;

	}

	@RequestMapping(value = "/providemanager_author", method = RequestMethod.GET)
	@ResponseBody
	public String providemanagerAuthor(HttpServletRequest request) {
		String ID = request.getParameter("ID");
		logger.info("providemanager_jujue: "+ID);
		String co_id = null;
		VePeBind vepebind = new VePeBind();
		List<Map<String, Object>> pelist = null;
		List<VePeBind> velist = null;
		vepebind.setPe_id(ID);
		vepebind.setState("0");
		velist = vePeService.query(vepebind, 0, 1);
		if(velist.size()>0){
		   co_id = (String) velist.get(0).getBind_id();
		 //vepebind.setCo_id(co_id);
		   vepebind.setBind_id(co_id); //change by wanghan
		   vepebind.setState("1");
		   vePeService.update(vepebind);
		}
		return null;
	}
	@RequestMapping(value = "/unviewprovidemanager_jujue.html", method = RequestMethod.GET)
	@ResponseBody
	public String unviewprovidemanagerJujue(HttpServletRequest request) {
		String ID = request.getParameter("ID");
		logger.info("providermanager_jujue: "+ID);
		String co_id = null;
		VePeBind vepebind = new VePeBind();
		List<Map<String, Object>> pelist = null;
		List<VePeBind> velist = null;
		vepebind.setPe_id(ID);
		vepebind.setState("2");
		velist = vePeService.query(vepebind, 0, 1);
		if(velist.size()>0){
		   co_id = (String) velist.get(0).getBind_id();
		   logger.info(co_id);
		   vepebind.setBind_id(co_id); //change by wanghan
		   vepebind.setState("0");
		   vePeService.update(vepebind);
		}
		return null;

	}

	@RequestMapping(value = "/unviewprovidemanager_author", method = RequestMethod.GET)
	@ResponseBody
	public String unviewprovidemanagerAuthor(HttpServletRequest request) {
		String ID = request.getParameter("ID");
		logger.info("providemanager_jujue: "+ID);
		String co_id = null;
		VePeBind vepebind = new VePeBind();
		List<Map<String, Object>> pelist = null;
		List<VePeBind> velist = null;
		vepebind.setPe_id(ID);
		vepebind.setState("2");
		velist = vePeService.query(vepebind, 0, 1);
		if(velist.size()>0){
		   co_id = (String) velist.get(0).getBind_id();
		   vepebind.setBind_id(co_id); //change by wanghan
		   vepebind.setState("1");
		   vePeService.update(vepebind);
		}
		return null;
	}

	@RequestMapping(value = "/historydata.html", method = RequestMethod.POST)
	@ResponseBody
	public String historyData(HttpServletRequest request) {
		HistoryDataPojo historyDataPojo = new HistoryDataPojo();
		historyDataPojo.setContent("test");
		historyDataPojo.setDataid("1");
		historyDataPojo.setInterfaces("1");
		historyDataPojo.setOperateve("1");
		historyDataPojo.setStatus("ok");
		historyDataPojo.setTime("2014-11-14");
		JSONArray json = new JSONArray();
		List list = new ArrayList();
		list.add(historyDataPojo);
		json = JSONArray.fromObject(list);
		logger.info(json.toString());

		return json.toString();

	}

	@RequestMapping(value = "/devicestatus.html", method = RequestMethod.POST)
	@ResponseBody
	public String deviceStatus(HttpServletRequest request) {
		DeviceAuthorStatusPojo deviceAuthStatusPojo = new DeviceAuthorStatusPojo();
		deviceAuthStatusPojo.setDescription("test");
		deviceAuthStatusPojo.setDeviceid("1");
		deviceAuthStatusPojo.setItemname("1");
		deviceAuthStatusPojo.setStatus("1");
		deviceAuthStatusPojo.setTime("2014");
		deviceAuthStatusPojo.setType("a");
		JSONArray json = new JSONArray();
		List list = new ArrayList();
		list.add(deviceAuthStatusPojo);
		json = JSONArray.fromObject(list);
		logger.info(json.toString());

		return json.toString();

	}
	/*@RequestMapping(value = "/registpemodel.html", method = RequestMethod.GET)
	@ResponseBody
	public String registPemodel(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("enter_registpemodel");

		PEModelPojo peModelPojo = new PEModelPojo();
		InterfacePojo[] interfacePojoArray ;
		InterfacePojo interfacePojo ;
		ParamPojo[] paramPojoArray;
		ParamPojo paramPojo;
		ParamPojo return_param = new ParamPojo();
		String Model_ID;
		String Model;
		String Name;
		String Type;
		String Pic_url;
		String Description;
		String count;
		
		String Interface_id;
		String is_control;
		String is_transparent;
		String need_response;
		String is_dir_down;
		
		String encode;
		String interface_description;
		String key;
		String param_type;
		String pe_option;
		String pe_unit;
		String min_val;
		String max_val;
		String pe_String;
		String pe_Boolean;
		
		String return_encode;
		String return_interface_description;
		String return_key;
		String return_param_type;
		String return_pe_option;
		String return_pe_unit;
		String return_min_val;
		String return_max_val;
		String return_pe_String;
		String return_pe_Boolean;
		
		String paramm;
		JSONObject json = null;
		int countn=0;
	    
	     
	     
		Model_ID  = request.getParameter("Model_ID");
		Model_ID = new String(Model_ID.getBytes("iso8859-1"),"UTF-8");
		Model = request.getParameter("Model");
		Name = request.getParameter("Name");
		Type = request.getParameter("Type");
		Pic_url = request.getParameter("Pic_url");
		Description = request.getParameter("Description");
		
	    count = request.getParameter("count");//获得interfaces的个数
	    System.out.println("count:"+count);
	    if((count!=null)&&(!count.equals(""))){
	    	countn = Integer.parseInt(count);
	    }
	    interfacePojoArray = new InterfacePojo[countn];
	    
	    for(int i=0;i<countn;i++){
	   
	    	int n = i;
	    	int tempcount=0;
	     	interfacePojo = new InterfacePojo();
	    	paramm = request.getParameter("paramm_"+n);
	    	if((paramm!=null)&&(!paramm.equals(""))){
	    	    tempcount = Integer.parseInt(paramm);//获得每个interface里面参数的个数
	    	}
	    	Interface_id = request.getParameter("Interface_id_"+n);
	    	is_control = request.getParameter("is_control_"+n);
			is_transparent = request.getParameter("is_transparent_"+n);
			need_response = request.getParameter("need_response_"+n);
			is_dir_down =  request.getParameter("is_dir_down_"+n);
			
			interfacePojo.setInterfaceID(Interface_id);
			interfacePojo.setIs_control(is_control);
			interfacePojo.setIs_dir_down(is_dir_down);
			interfacePojo.setIs_transparent(is_transparent);
			interfacePojo.setNeed_response(need_response);
			
			return_encode = request.getParameter("return_encode_"+n);
			return_interface_description= request.getParameter("return_interface_description_"+n);
			return_key= request.getParameter("return_key_"+n);
			return_param_type= request.getParameter("return_param_type_"+n);
			return_pe_option= request.getParameter("return_option_"+n);
			return_pe_unit= request.getParameter("return_unit_"+n);
			return_min_val= request.getParameter("return_min_val_id_"+n);
			return_max_val= request.getParameter("return_max_val_"+n);
			return_pe_String= request.getParameter("return_String_"+n);
			return_pe_Boolean= request.getParameter("return_Boolean_"+n);
			return_param.setDescription(return_interface_description);
			return_param.setEncode(return_encode);
			return_param.setKey(return_key);
			return_param.setMax_val(return_max_val);
			return_param.setMin_val(return_min_val);
			return_param.setOption(return_pe_option);
			return_param.setPe_Boolean(return_pe_Boolean);
			return_param.setPe_String(return_pe_String);
			return_param.setPe_unit(return_pe_unit);
			return_param.setType(return_param_type);
			
			
			paramPojoArray = new ParamPojo[tempcount];
			
			for(int j=0;j<tempcount;j++){
				 int m = j;
				 paramPojo = new ParamPojo();
				 
				 encode = request.getParameter("encode_"+n+"_"+m);
				 interface_description= request.getParameter("interface_description_"+n+"_"+m);
				 key= request.getParameter("key_"+n+"_"+m);
				 param_type= request.getParameter("param_type_"+n+"_"+m);
				 pe_option= request.getParameter("option_"+n+"_"+m);
				 pe_unit= request.getParameter("unit_"+n+"_"+m);
				 min_val= request.getParameter("min_val_id_"+n+"_"+m);
				 max_val= request.getParameter("max_val_"+n+"_"+m);
				 pe_String= request.getParameter("String_"+n+"_"+m);
				 pe_Boolean= request.getParameter("Boolean_"+n+"_"+m);
				
				 paramPojo.setDescription(interface_description);
				 paramPojo.setEncode(encode);
				 paramPojo.setKey(key);
				 paramPojo.setMax_val(max_val);
				 paramPojo.setMin_val(min_val);
				 paramPojo.setOption(pe_option);
				 paramPojo.setPe_Boolean(pe_Boolean);
				 paramPojo.setPe_String(pe_String);
				 paramPojo.setPe_unit(pe_unit);
				 paramPojo.setType(param_type);
				 paramPojoArray[j] = paramPojo;
			}
			interfacePojo.setParam(paramPojoArray);
			interfacePojoArray[i] = interfacePojo;
			interfacePojo.setReturn_param(return_param);
	    }
	    peModelPojo.setInterfaces(interfacePojoArray);
	    peModelPojo.setDescription(Description);
	    peModelPojo.setModel(Model);
	    peModelPojo.setModel_id(Model_ID);
	    peModelPojo.setName(Name);
	    peModelPojo.setPic_url(Pic_url);
	    json = JSONObject.fromObject(peModelPojo);
	    
	    System.out.println(json);
	    String writer_to_file = json.toString();
	   
		File f = new File("F:"+File.separator+"pemodel.txt");
	    try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			BufferedWriter bufw = new BufferedWriter(new FileWriter(f));
			
			bufw.write(writer_to_file);
			bufw.flush();
			bufw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.setStatus(302);
		response.addHeader("location","/IoT_Harbor/jsp/Module1/PEModelRegist.jsp");
		response.addHeader("location",
				"/IoT_Harbor/jsp/Module1/1.1.1-SelectTemplate.jsp");
		return "";

	}

*/
	@RequestMapping(value = "/onvifDiscovery.html", method = RequestMethod.GET)
	@ResponseBody
	public List<String> onvifDiscovery(){
		
		return OnvifCam.discovery();
	}
	
	/*
	 * template_check
	 */
	@RequestMapping(value = "/template_check.html", method = RequestMethod.GET)
	@ResponseBody
	public String protocolCheck(HttpServletRequest request) {
		PEInterfaceService peInterfaceService = (PEInterfaceService) GetBean.getBean("peInterfaceService");
		PeTemplateParamService peTemplateParamService = (PeTemplateParamService) GetBean.getBean("peTemplateParamService");

		List<Map<String, Object>> interface_list = null;
		List<Map<String, Object>> attribute_list = null;
		
		PeInterface peInterface = new PeInterface();
		PeTemplateParam peTemplateParam = new PeTemplateParam();
		
		String template_id = null;
		template_id = request.getParameter("template_id");
		
		peInterface.setTemplateId(template_id);
		peTemplateParam.setTemplate_id(template_id);
		
		attribute_list = peTemplateParamService.queryAll(peTemplateParam);
		interface_list = peInterfaceService.queryall(peInterface);
		int length = interface_list.size();
		
		JSONObject json = new JSONObject();
		json.put("template_status", "true");
		
		if(length > 0){
			JSONArray array = JSONArray.fromObject(interface_list);
			
			json.put("protocol", array);
		}
		
		length = attribute_list.size();
		
		if(length > 0){
			JSONArray array = JSONArray.fromObject(attribute_list);
			
			json.put("attribute", array);
		}
		logger.info(json.toString());
		return json.toString();
		/*String temp = "[";
		String is_exit = "true";
		String protocol = "false";
		length--;
		int count=0;
		if(length>=0){
		   
			  temp = "[";
		   for(int i=length;i>=0;i--){
			  is_exit = "true";
			   
			
			  PeInterface get_peInterface = null;
			  protocol = (String) interface_list.get(i).get("interface_type");
			 
			  if(protocol.equals("onvif")||protocol.equals("ezviz")) {
				 count++;
				 temp+="{";
				 String interface_id =  (String) interface_list.get(i).get("interface_id");
				 String interface_type = (String) interface_list.get(i).get("interface_type");
				 String interface_remark = (String) interface_list.get(i).get("interface_remark");
				 temp+="\"interface_id\":\""+interface_id+"\",\"interface_type\":\""+interface_type+"\", \"interface_description\":\""+interface_remark+"\"";
			     temp+="},";
			  }
		  }
		   temp=temp.substring(0, temp.length()-1);
		   temp+="]";
		   
		}
		
		if(count==0){
			temp="\"\"";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{\"template_status\":\""+is_exit+"\",\"protocol\":"+temp+"}");
		System.out.println(sb);
		
		return sb.toString();*/

	}
}
