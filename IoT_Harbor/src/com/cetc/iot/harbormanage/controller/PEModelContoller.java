package com.cetc.iot.harbormanage.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cetc.iot.accesssystem.PostProcessor;
import com.cetc.iot.database.model.PeInterface;
import com.cetc.iot.database.model.PeTemplate;
import com.cetc.iot.harbormanage.pojo.AttributePojo;
import com.cetc.iot.harbormanage.pojo.InterfacePojo;
import com.cetc.iot.harbormanage.pojo.PEModelPojo;
import com.cetc.iot.harbormanage.pojo.ParamPojo;
import com.cetc.iot.harbormanage.pojo.TypePojo;
import com.cetc.iot.harbormanage.service.PEGeolocationService;
import com.cetc.iot.harbormanage.service.PEInterfaceService;
import com.cetc.iot.harbormanage.service.PEService;
import com.cetc.iot.harbormanage.service.PETemplateService;
import com.cetc.iot.harbormanage.service.VEPEBindService;
import com.cetc.iot.servicesystem.util.GetBean;

@Controller
public class PEModelContoller {
	
	private static Logger logger = Logger.getLogger(PEModelContoller.class.getName());
	
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

	static String tomcatpath;

	@RequestMapping(value = "/generatepemodel.html", method = RequestMethod.POST)
	@ResponseBody
	public String registPemodel(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("enter_registepemodel");
		tomcatpath = request.getSession().getServletContext().getRealPath("");
		PEModelPojo peModelPojo = new PEModelPojo();
		InterfacePojo[] interfacePojoArray = null;
		InterfacePojo interfacePojo;
		/*
		 * ParamPojo[] paramPojoArray = null; ParamPojo[] returnParamPojoArray =
		 * null;
		 */
		List paramPojoArray = new ArrayList<ParamPojo>();
		List returnParamPojoArray = new ArrayList<ParamPojo>();
		ParamPojo paramPojo;
		ParamPojo return_param = new ParamPojo();
		String Model_ID;
		String Model;
		String Name;
		String Type;
		String Pic_url;
		String Description;
		String count;
		int cnt = 0;
		String http_rule;
		String Interface_id;
		String is_control;
		String is_transparent;
		String need_response;
		String is_dir_down;
		String interface_direction;
		String interface_protocol;

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
		String return_paramm;
		JSONObject json = null;
		int countn = 0;
		/*
		 * 鑾峰緱鍩烘湰淇℃伅锛堝浐瀹氭牸寮忥級
		 */
		Model_ID = request.getParameter("model_ID");
		Model = request.getParameter("model");
		Name = request.getParameter("name");
		Type = request.getParameter("type");
		Pic_url = request.getParameter("pic_url");
		Description = request.getParameter("description");

		count = request.getParameter("count");// 鑾峰緱interfaces鐨勪釜鏁�
		logger.info("count: "+count);
		if ((count != null) && (!count.equals(""))) {
			countn = Integer.parseInt(count);
		}

		if (countn >= 1) {
			interfacePojoArray = new InterfacePojo[countn - 1];
		}

		try {
			for (int i = 1; i < countn; i++) {

				cnt++;

				int n = i;
				int tempcount = 0;
				int return_tempcount = 0;
				interfacePojo = new InterfacePojo();
				paramm = request.getParameter("paramm_" + n);
				return_paramm = request.getParameter("return_paramm_" + n);
				if ((paramm != null) && (!paramm.equals(""))) {
					tempcount = Integer.parseInt(paramm);
					tempcount++;
				}
				if ((return_paramm != null) && (!return_paramm.equals(""))) {
					return_tempcount = Integer.parseInt(return_paramm);
				}
				Interface_id = request.getParameter("Interface_id_" + n);
				is_control = request.getParameter("is_control_" + n);
				is_transparent = request.getParameter("is_transparent_" + n);
				need_response = request.getParameter("need_response_" + n);
				is_dir_down = request.getParameter("is_dir_down_" + n);
				interface_direction = request.getParameter("interface_direction_" + n);
				interface_protocol = request.getParameter("interface_protocol_"+ n);
				if(interface_protocol != null && interface_protocol.equals("http")){
					String protocol_rule = request.getParameter("http_rule_"+n);
					if(protocol_rule!=null&&protocol_rule!=""){
					    interfacePojo.setProtocol_rule(protocol_rule);
					}
				}
				interface_description = request.getParameter("interface_description_" + n);
				interfacePojo.setInterfaceID(Interface_id);
				interfacePojo.setInterface_description(interface_description);
				interfacePojo.setInterface_direction(interface_direction);
				interfacePojo.setInterface_protocol(interface_protocol);

				for (int j = 1; j <= return_tempcount; j++) {

					int m = j;
					return_param = new ParamPojo();
					return_encode = request.getParameter("return_encode_" + n+ "_" + m);
					return_interface_description = request.getParameter("return_interface_description_" + n+ "_" + m);
					return_key = request.getParameter("return_key_" + n + "_"+ m);
					return_param_type = request.getParameter("return_param_type_" + n + "_" + m);
					return_pe_option = request.getParameter("return_option_"+ n + "_" + m);
					return_pe_unit = request.getParameter("return_unit_" + n+ "_" + m);
					return_min_val = request.getParameter("return_min_val_" + n+ "_" + m);
					return_max_val = request.getParameter("return_max_val_" + n+ "_" + m);
					return_pe_String = request.getParameter("return_String_"+ n + "_" + m);
					return_pe_Boolean = request.getParameter("return_Boolean_"+ n + "_" + m);

					if (return_interface_description != null|| 
						return_encode != null || 
						return_key != null|| 
						return_max_val != null || 
						return_min_val != null|| 
						return_pe_option != null|| 
						return_pe_Boolean != null|| 
						return_pe_String != null|| 
						return_pe_unit != null|| 
						return_param_type != null) {

						return_param.setDescription(return_interface_description);
						return_param.setKey(return_key);
						return_param.setMax_val(return_max_val);
						return_param.setMin_val(return_min_val);
						return_param.setOption(return_pe_option);
						return_param.setPe_Boolean(return_pe_Boolean);
						return_param.setPe_String(return_pe_String);
						return_param.setPe_unit(return_pe_unit);
						return_param.setType(return_param_type);
						returnParamPojoArray.add(return_param);

					}
				}

				for (int j = 1; j <= tempcount; j++) {

					int m = j;
					paramPojo = new ParamPojo();

					encode = request.getParameter("encode_" + n + "_" + m);
					interface_description = request.getParameter("interface_description_" + n + "_"+ m);
					key = request.getParameter("key_" + n + "_" + m);
					param_type = request.getParameter("param_type_" + n + "_"+ m);
					pe_option = request.getParameter("param_option_" + n + "_"+ m);
					pe_unit = request.getParameter("param_unit_" + n + "_" + m);
					min_val = request.getParameter("param_min_val_" + n + "_"+ m);
					max_val = request.getParameter("param_max_val_" + n + "_"+ m);
					pe_String = request.getParameter("param_String_" + n + "_"+ m);
					pe_Boolean = request.getParameter("param_Boolean_" + n+ "_" + m);

					if (interface_description != null || 
						encode != null|| 
						key != null || 
						max_val != null|| 
						min_val != null || 
						pe_option != null|| 
						pe_Boolean != null || 
						pe_String != null||
						pe_unit != null || 
						param_type != null) {

						paramPojo.setDescription(interface_description);
						paramPojo.setKey(key);
						paramPojo.setMax_val(max_val);
						paramPojo.setMin_val(min_val);
						paramPojo.setOption(pe_option);
						paramPojo.setPe_Boolean(pe_Boolean);
						paramPojo.setPe_String(pe_String);
						paramPojo.setPe_unit(pe_unit);
						paramPojo.setType(param_type);
						paramPojoArray.add(paramPojo);
					}

				}

				ParamPojo[] tempArray;
				tempArray = new ParamPojo[returnParamPojoArray.size()];
				for (int temp = 0; temp < returnParamPojoArray.size(); temp++) {
					tempArray[temp] = (ParamPojo) returnParamPojoArray.get(temp);
				}
				interfacePojo.setReturn_param(tempArray);
				ParamPojo[] tempArray2;
				tempArray2 = new ParamPojo[paramPojoArray.size()];
				for (int temp = 0; temp < paramPojoArray.size(); temp++) {
					tempArray2[temp] = (ParamPojo) paramPojoArray.get(temp);
				}
				interfacePojo.setParam(tempArray2);
				interfacePojoArray[i - 1] = interfacePojo;
				returnParamPojoArray.clear();
				paramPojoArray.clear();
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InterfacePojo[] interfacePojoArrayTemp = new InterfacePojo[cnt];
		int interfaceTempCount = 0;
		for (int i = 0; i < countn - 1; i++) {
			if (interfacePojoArray[i] != null) {
				interfacePojoArrayTemp[interfaceTempCount++] = interfacePojoArray[i];
			}
		}
		AttributePojo[] attributeArray = null;
		String attribute_count;
		int attribute_count_int = 0;
		attribute_count = request.getParameter("attribute_count");
		if(attribute_count!=null&&attribute_count!=""){
			attribute_count_int = Integer.parseInt(attribute_count);
		}
		if(attribute_count_int>1){
			attributeArray  = new AttributePojo[attribute_count_int-1];
		}
		int attributeCount=0;
		for(int i=1;i<=attribute_count_int-1;i++){
			
			AttributePojo attributePojo = new AttributePojo();
			String attribute_description;
			String attribute_key;
			String attribute_type;
		
			String attribute_unit;
			String attribute_min_val;
			String attribute_max_val;
		    String attribute_option;
		
			int n = i;
			attribute_description = request.getParameter("attribute_description_"+i);
			attribute_key = request.getParameter("attribute_key_"+i);
			attribute_type = request.getParameter("attribute_type_"+i);
			attribute_unit = request.getParameter("attribute_unit_"+i+"_0");
			attribute_min_val = request.getParameter("attribute_min_val_"+i+"_0");
			attribute_max_val = request.getParameter("attribute_max_val_"+i+"_0");
			attribute_option = request.getParameter("attribute_option_"+i+"_0");
			
			attributePojo.setDescription(attribute_description);
			attributePojo.setKey(attribute_key);
			attributePojo.setType(attribute_type);
			attributePojo.setOption(attribute_option);
			attributePojo.setUnit(attribute_unit);
			attributePojo.setValue_max(attribute_max_val);
			attributePojo.setValue_min(attribute_min_val);
			if(attribute_key==null&&
			   attribute_description==null&&
			   attribute_type==null&&
			   attribute_min_val==null&&
			   attribute_max_val==null&&
			   attribute_option==null){
				
			}
			else {
				attributeArray[attributeCount++] = attributePojo;
			}
		}
		
		AttributePojo[] attributeArraytemp = new AttributePojo[attributeCount];
		for(int i=0;i<attributeCount;i++){
			attributeArraytemp[i] = attributeArray[i];
		}
		
		peModelPojo.setInterfaces(interfacePojoArrayTemp);
		peModelPojo.setDescription(Description);
		peModelPojo.setModel(Model);
		peModelPojo.setModel_id(Model_ID);
		peModelPojo.setName(Name);
		TypePojo typepojo = new TypePojo();
		typepojo.setBigtype(Type);
		peModelPojo.setType(typepojo);
		peModelPojo.setPic_url(Pic_url);
		peModelPojo.setAttribute(attributeArraytemp);
		// Protocol Dependent process
		peModelPojo = PostProcessor.process(peModelPojo);

		
		
		json = JSONObject.fromObject(peModelPojo);

		logger.info(json.toString());
		String writer_to_file = json.toString();
    
		
		PEModelContoller peModelContoller1 = new PEModelContoller();
		String path = peModelContoller1.writeFile(writer_to_file);


		return path;

	}

	public String writeFile(String write_to_file) {
		
		String path = tomcatpath;
		UUID uuid = UUID.randomUUID();
		String temp = uuid.toString();
		temp = temp.replaceAll("-", "");
		path += File.separator + temp + ".txt";
		File f = new File(path);
		System.out.println(path);
		try {
			f.createNewFile();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try {
			BufferedWriter bufw = new BufferedWriter(new FileWriter(f));
			bufw.write(write_to_file);
			bufw.flush();
			bufw.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		String fname = temp + ".txt";
		return fname;
	}

	public void uploadFile(JSONObject model) throws IOException {
		logger.info("uploadfile");
		PeTemplate peTemplate = new PeTemplate();

		InterfacePojo[] peInterfaceArray;
		int peInterfaceArrayLength;
		byte[] b;
		int len = 0;
		int temp = 0;

		String content = "";
		String interfaceID;
		ParamPojo[] paramPojoArray;
		int paramPojoArrayLength;

		JSONObject json = model;
		PEModelPojo pemodel = (PEModelPojo) JSONObject.toBean(json,PEModelPojo.class);
		peInterfaceArray = pemodel.getInterfaces();
		peInterfaceArrayLength = peInterfaceArray.length;
		PEInterfaceService peInterfaceService1 = (PEInterfaceService) GetBean.getBean("peInterfaceService");
		PETemplateService peTemplateService1 = (PETemplateService) GetBean.getBean("peTemplateService");
		while (peInterfaceArrayLength > 0) {
			PeInterface peInterface = new PeInterface();
			interfaceID = peInterfaceArray[peInterfaceArrayLength - 1].getInterfaceID();
			peInterface.setTemplateId(pemodel.getModel_id());
			peInterface.setInterfaceRemark("");
			peInterface.setInterfaceChannel(-1);
			peInterface.setInterfaceContent(content);
			peInterface.setInterfaceFashion(-1);
			peInterface.setInterfaceName("");
			peInterface.setInterfaceResponse(-1);
			peInterface.setInterfaceType("StandardProtocol");
			peInterface.setResult("");
			peInterface.setResultType("");
			peInterface.setInterfaceId(interfaceID);
			peInterfaceArrayLength--;
			peInterfaceService1.add(peInterface);
			content = "";
		}

		peTemplate.setTemplateId(pemodel.getModel_id());
		peTemplate.setTemplateModel(pemodel.getModel());
		peTemplate.setTemplateName(pemodel.getName());
		peTemplate.setTemplatePicUrl(pemodel.getPic_url());
		peTemplate.setTemplateRemark(pemodel.getDescription());
		peTemplate.setTemplateTime(new Date());
		peTemplate.setTemplateType(pemodel.getType().getBigtype());

		peTemplateService1.add(peTemplate);

	}

}
