package com.cetc.iot.servicesystem.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cetc.iot.database.model.CompleteTpl;
import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.servicesystem.service.TemplateBindService;
import com.cetc.iot.servicesystem.service.VETemplateService;
import com.cetc.iot.servicesystem.util.GetBean;
import com.cetc.iot.servicesystem.util.GetPorjectPath;
import com.cetc.iot.servicesystem.util.GetProperty;
import com.cetc.iot.servicesystem.util.XML2Tpl;
import com.cetc.iot.util.UploadUtil;

/**
 * ve模板管理类
 * @author chengk
 *
 */

@Controller
public class VeTemplateController {
	private static Logger logger = Logger.getLogger(VeTemplateController.class.getName());
	@Autowired
	private VETemplateService veTemplateService;
	@Autowired
	private TemplateBindService templateBindService;
	
	/**
	 * 查询所有的VE模版列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryVeModelList.html", method = RequestMethod.GET)
	@ResponseBody
	public String queryAllVeTemplate(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		try {
			VETemplate vetemplate = new VETemplate();
			List<VETemplate> list = veTemplateService.query(vetemplate, 0, 100);
			JSONArray json1 = JSONArray.fromObject(list);
			
			JSONObject json2 = new JSONObject();
			json2.accumulate("page", page);
			json2.accumulate("rows", rows);
			json2.accumulate("veTemplate", json1.toString());
			return json2.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}		
		
		
		
	}
	
	/**
	 * 根据ID查询VE模版
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryVeModel.html", method=RequestMethod.GET)
	@ResponseBody
	public String queryVeTpl(HttpServletRequest request) {
		
		String tplId = request.getParameter("tplId");//模版商店分配的ID
		String result = "";
		VETemplate veTemplate = new VETemplate();
		try {
			veTemplate.setTpl_id(tplId);
			veTemplate = veTemplateService.query(veTemplate, 0, 1).get(0);
			
			Map<String,Object> templateMap = new HashMap<String,Object>();
			templateMap.put("template_id", veTemplate.getTemplate_id());
			templateMap.put("template_openness", veTemplate.getTemplate_openness());
			templateMap.put("tpl_alias", veTemplate.getTpl_alias());
			templateMap.put("tpl_classpath", veTemplate.getTpl_classpath());
			templateMap.put("tpl_description", veTemplate.getTpl_description());
			templateMap.put("tpl_developer", veTemplate.getTpl_developer());
			templateMap.put("tpl_display", veTemplate.getTpl_display());
			templateMap.put("tpl_enroll_time", new java.util.Date(veTemplate.getTpl_enroll_time().getTime()));
			templateMap.put("tpl_icon", veTemplate.getTpl_icon());
			templateMap.put("tpl_name", veTemplate.getTpl_name());
			templateMap.put("tpl_owner", veTemplate.getTpl_owner());
			templateMap.put("tpl_state", veTemplate.getTpl_state());
			templateMap.put("tpl_type_id", veTemplate.getTpl_type_id());
			templateMap.put("tpl_version", veTemplate.getTpl_version());
			
			JSONObject json = JSONObject.fromObject(templateMap);
//			System.out.println(json.toString());
			result = json.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return result;
	}
	
	/**
	 * VE模版关于描述的模糊查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryVeModelListByDes.html", method=RequestMethod.GET)
	@ResponseBody
	public String queryVeTplList(HttpServletRequest request) {
		String result = "";
		try {
			request.setCharacterEncoding("UTF-8");

			//		String type1 = request.getParameter("type1");
			//		String type2 = request.getParameter("type2");
			//		String type3 = request.getParameter("type3");
			//		String model = request.getParameter("model");
			//		String state = request.getParameter("state");
			//		String startTime = request.getParameter("beginTime") + " 00:00:00";
			//		String endTime = request.getParameter("endTime") + "23:59:59";
			//		String developer = request.getParameter("developer");
			String description = request.getParameter("description");
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows")); 

			VETemplate veTemplate = new VETemplate();
			veTemplate.setTpl_description(description);
//			Map<String,Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
			List<VETemplate> list = veTemplateService.query(veTemplate, page, rows);
//			for(int i = 0;i<list.size();i++){
////				map = BaseUtil.compareMap(map,(VETemplate)list.get(i));
//				map = BaseUtil.transBean2Map((VETemplate)list.get(i));
//				list2.add(map);
//			}
			for(int i = 0;i<list.size();i++){
				Map<String,Object> templateMap = new HashMap<String,Object>();
				veTemplate = list.get(i);
				templateMap.put("template_id", veTemplate.getTemplate_id());
				templateMap.put("template_openness", veTemplate.getTemplate_openness());
				templateMap.put("tpl_alias", veTemplate.getTpl_alias());
				templateMap.put("tpl_classpath", veTemplate.getTpl_classpath());
				templateMap.put("tpl_description", veTemplate.getTpl_description());
				templateMap.put("tpl_developer", veTemplate.getTpl_developer());
				templateMap.put("tpl_display", veTemplate.getTpl_display());
				templateMap.put("tpl_enroll_time", new java.util.Date(veTemplate.getTpl_enroll_time().getTime()));
				templateMap.put("tpl_icon", veTemplate.getTpl_icon());
				templateMap.put("tpl_name", veTemplate.getTpl_name());
				templateMap.put("tpl_owner", veTemplate.getTpl_owner());
				templateMap.put("tpl_state", veTemplate.getTpl_state());
				templateMap.put("tpl_type_id", veTemplate.getTpl_type_id());
				templateMap.put("tpl_version", veTemplate.getTpl_version());
				list2.add(templateMap);
			}
			JSONArray json = JSONArray.fromObject(list2);
			result = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return result;
	}
	
	
	/**
	 * 添加VE模版
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addVeModel.html", method=RequestMethod.POST)
	public String addVeModel(HttpServletRequest request) {

		try {
			request.setCharacterEncoding("UTF-8");
//			String tplName = request.getParameter("tplName");
//			String className = request.getParameter("className");
			String tplAlias = request.getParameter("tplAlias");
			String tplOpenness = request.getParameter("openness");
			
			//模板上传页填写的类名称
			String className = request.getParameter("className");
			
			String xmlUrl = "";
			@SuppressWarnings("unused")
			String classUrl = "";
			
			//shangַ
			String path1 = GetProperty.getValue("xmlDir");
			String path2 = GetPorjectPath.getPorjectPath();
			logger.info("path: "+path2);
			String classPackage = GetProperty.getValue("classpackage");
			 if(classPackage.contains("/")){
				 classPackage = classPackage.replace('/', '.');
			}else{
				classPackage = classPackage.replace('\\', '.');
			}
//			String path = request.getSession().getServletContext().getRealPath("/")+"ve/";
			
			String tpl_classpath = classPackage + className ;
			
			Map<String,Object> methodParam = new HashMap<String,Object>();
			methodParam.put("tplAlias", tplAlias);
			methodParam.put("tplOpenness",tplOpenness );
			methodParam.put("tpl_classpath",tpl_classpath);
			
			MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
			MultipartFile mFile1 = mulRequest.getFile("tplFile");
			MultipartFile mFile2 = mulRequest.getFile("classFile");
			
			if(!mFile1.isEmpty()){
				xmlUrl = UploadUtil.upload(mFile1, path1,"");
			}else{
				logger.info("the .xml file of VETemplate is null! ");
				return "error";
			}
			
			if(!mFile2.isEmpty()){
				classUrl = UploadUtil.upload(mFile2, path2,"");
			}else{
				logger.info("the .class file of VETemplate is null! ");
				return "error";
			}
			XML2Tpl xt = new XML2Tpl();
			CompleteTpl cTpl = xt.xml2tpl(xmlUrl);
			File file = new File(xmlUrl);
			if(file.exists()){
				file.delete();
			}
			VETemplateService vts = (VETemplateService) GetBean.getBean("veTplService");
			Boolean result = vts.insertCompleteTpl(cTpl, methodParam);
			if(result){
				return "success";
			}else{
				return "fail";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	/**
	 * 根据VE模版ID获取可以绑定的PE模版
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryPeModelByVeTplID.html", method=RequestMethod.GET)
	public String queryPeModelByVeTplID(HttpServletRequest request) {
		
		String tplId = request.getParameter("tplId");//模版商店分配的ID
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		//根据商店分配的ID查系统ID
		VETemplate veTemplate = new VETemplate();
		veTemplate.setTpl_id(tplId);
		tplId = veTemplateService.query(veTemplate, 0, 10).get(0).getTemplate_id();
		
		TemplateBind templateBind = new TemplateBind();
		templateBind.setVe_template_id(tplId);
		
		List<TemplateBind> bindList = templateBindService.query(templateBind, page, rows);
		JSONArray json = JSONArray.fromObject(bindList);
		logger.info(json.toString());
		return json.toString();
	}
	@ResponseBody
	@RequestMapping(value="/addVeTemplateXML.html",method=RequestMethod.POST)
	public String addVeTemplateXML(HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
		return null;
		
	}
}
