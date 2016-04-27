package com.cetc.iot.servicesystem.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cetc.iot.database.model.PeTemplate;
import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.harbormanage.service.PETemplateService;
import com.cetc.iot.servicesystem.service.TemplateBindService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;

@Controller
public class VeTemplateServiceController {
	
	private static Logger logger = Logger.getLogger(VeTemplateServiceController.class.getName());
	
	@Autowired
	private VEWebsocketServiceService vwss;
	@Autowired
	private TemplateBindService templateBindService;
	@Autowired
	private PETemplateService peTemplateService;
	/**
	 * 查询VE模版的服务列表
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/queryVeTemplateServiceList.html", method = RequestMethod.GET)
	@ResponseBody
	public String queryVeTemplateServices(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String veTemplateId = request.getParameter("ve_template_id");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows")); 
		VeWebsocketService service = new VeWebsocketService();
		service.setTemplate_id(veTemplateId);
		List<VeWebsocketService> services=vwss.query(service, page, rows);
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		for(int i=0;i<services.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("service_id", services.get(i).getService_id());
			map.put("template_id", services.get(i).getTemplate_id());
			map.put("service_name", services.get(i).getService_name());
			map.put("service_description",services.get(i).getService_description());
			list2.add(map);
		}
		JSONArray json = JSONArray.fromObject(list2);
		String result = json.toString();
		logger.info("查询出来的ve_template_id为："+veTemplateId+"的服务信息为："+result);
		return result;
	}
	/**
	 * 查询VE模版的服务列表
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/queryPETemplatesByVeTemplateService.html", method = RequestMethod.GET)
	@ResponseBody
	public String queryPETemplatesByVeTemplateService(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String veTemplateId = request.getParameter("ve_template_id");
		String veTemplateServiceId = request.getParameter("ve_template_service_id");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows")); 
		TemplateBind templateBind = new TemplateBind();
		templateBind.setVe_template_id(veTemplateId);
		templateBind.setService_id(veTemplateServiceId);
		List<TemplateBind> templateList=templateBindService.query(templateBind, page, rows);
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		for(int i=0;i<templateList.size();i++){
			String templateId = templateList.get(i).getPe_template_id();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("identify_id", templateList.get(i).getIdentify_id());
			if(templateList.get(i).getBind_type()=="0"){
				map.put("bind_type", "必要型");
			}else{
				map.put("bind_type", "非必要型");
			}
			
			map.put("pe_template_id", templateId);
			map.put("bind_max", templateList.get(i).getBind_max());
			map.put("bind_min",templateList.get(i).getBind_min());
			//find pe_template info
			PeTemplate peTemplate = new PeTemplate();
			peTemplate.setTemplateId(templateId);
			List<Map<String,Object>> peTemplateInfo = peTemplateService.query(peTemplate, 0, 10);
			if(peTemplateInfo.size()>0){
				String templateName = peTemplateInfo.get(0).get("template_name").toString();
				String templateRemark = peTemplateInfo.get(0).get("template_remark").toString();
				map.put("templateName", templateName);
				map.put("templateRemark", templateRemark);
			}
			
			list2.add(map);
		}
		JSONArray json = JSONArray.fromObject(list2);
		String result = json.toString();
		logger.info("查询出来的ve_template_service_id为："+veTemplateId+"的服务绑定的pe_template信息为："+result);
		return result;
	}
}
