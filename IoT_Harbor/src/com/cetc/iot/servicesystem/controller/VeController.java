package com.cetc.iot.servicesystem.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cetc.iot.database.dao.CityDao;
import com.cetc.iot.database.dao.CountryDao;
import com.cetc.iot.database.dao.ProvinceDao;
import com.cetc.iot.database.model.City;
import com.cetc.iot.database.model.Country;
import com.cetc.iot.database.model.Pe;
import com.cetc.iot.database.model.Province;
import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.database.model.VeGeoLocation;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.harbormanage.service.PEService;
import com.cetc.iot.servicesystem.service.TemplateBindService;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.VETemplateService;
import com.cetc.iot.servicesystem.service.VePeBindService;
import com.cetc.iot.servicesystem.service.impl.CallCenterSession;
import com.cetc.iot.servicesystem.util.ConvertVeId;
import com.cetc.iot.servicesystem.util.RandomGenerator;

/**
 * ve的管理类 
 * 
 * @author xia
 * 
 */

@Controller
public class VeController {

	public enum VEParam {
		VeId, VeName, VeKey, VeState, VePrivacy, VeInitTime, VeDescription, VeCreatorId, VeTemplateId
	}

	@Autowired
	private VEService veService;

	@Autowired
	private PEService peService;

	@Autowired
	private TemplateBindService templateBindService;

	@Autowired
	private VePeBindService vepebindservice;

	@Autowired
	private VETemplateService veTemplateService;
	
	@Autowired
	private  CountryDao countrydao ;
	
	@Autowired
	private  CityDao citydao ;
	
	@Autowired
	private  ProvinceDao provincedao ;
	
	private static Logger logger = Logger
			.getLogger(CallCenterSession.class.getName());
	private static SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/peForRegistVe.html", method = RequestMethod.POST)
	@ResponseBody
	public String peForRegistVe(HttpServletRequest request) {

		List<Map<String, Object>> allList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<TemplateBind> templateList = new ArrayList<TemplateBind>();
		JSONArray json = new JSONArray();
		Pe pe = new Pe();
		try {
			request.setCharacterEncoding("UTF-8");
			String veTemplateId = request.getParameter("veTemplateId");
			int page = 0;
			int size = 10;
			TemplateBind templatebind = new TemplateBind();
			templatebind.setVe_template_id(veTemplateId);
			templateList = templateBindService.query(templatebind, page, size);
			for (int i = 0; i < templateList.size(); i++) {
				pe.setTemplateId((String) templateList.get(i)
						.getPe_template_id());
				pe.setPeLifecycle(-1);
				pe.setPeState(0);
				pe.setPeTime(-1);
				list = peService.query(pe, page, size);
				allList.addAll(list);
			}

			json = JSONArray.fromObject(allList);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}

		return json.toString();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/registVe.html", method = RequestMethod.POST)
	@ResponseBody
	public String registVe(HttpServletRequest request) {
		VeGeoLocation veGeoLocation = new VeGeoLocation();
		VE ve = new VE();
		VePeBind vpb = new VePeBind();
		try {
			request.setCharacterEncoding("UTF-8");
			String LoopNum = request.getParameter("hidden");
			String veName = request.getParameter("veName");
			String privacy = request.getParameter("privacy");
			String description = request.getParameter("description");
			String tplId = request.getParameter("tplId");
			String country = request.getParameter("country");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String altitude = request.getParameter("altitude");
			String otherInfo = request.getParameter("otherInfo");
			// veGeoLocation.setCountry(country);

			// 生成key
			String key = RandomGenerator.getRandomStringByDateTime();

			ve.setTemplate_id(tplId);
			ve.setVe_creator_id("18");
			ve.setVe_description(description);
			// ve.setVe_init_time(ve_init_time);
			ve.setVe_key(key);
			ve.setVe_name(veName);
			ve.setVe_privacy(privacy);
			ve.setVe_state("002");
			veGeoLocation.setAltitude(Float.parseFloat(altitude));
			veGeoLocation.setCity_id(Integer.parseInt(city));
			veGeoLocation.setCountry_id(Integer.parseInt(country));
			veGeoLocation.setProvince_id(Integer.parseInt(province));
			veGeoLocation.setLatitude(Float.parseFloat(latitude));
			veGeoLocation.setLongitude(Float.parseFloat(longitude));
			veGeoLocation.setOtherInfo(otherInfo);
			ve.setGeo(veGeoLocation);

			veService.add(ve, veGeoLocation);
			String veId = veService.query(ve, 0, 100).get(0).getVe_id();
			for (int i = 0; i <= Integer.parseInt(LoopNum); i++) {
				String identify_id = request.getParameter("identify_id" + i);
				String peList[] = request.getParameterValues("pe" + i);
				for (int j = 0; j < peList.length; j++) {
					vpb.setVe_id(veId);
					vpb.setIdentify_id(identify_id);
					vpb.setPe_id(peList[j]);
					vpb.setState("0");
					// 这里测试用 以后要真实数据
					vpb.setBind_type("001");
					vepebindservice.add(vpb);
				}

			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getALLVe.html", method = RequestMethod.GET)
	@ResponseBody
	public String getALLVe(HttpServletRequest request) {
		String result = "";

		try {
			request.setCharacterEncoding("UTF-8");

			List<Map<String, Object>> list = veService.queryALLVeForMap();

			// 找到原子VE对应的PE的状态，复合VE依然沿用本身状态
			String veId = "";
			List<Map<String, Object>> peList = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < list.size(); i++) {
				result = "";
				Map<String, Object> veMap = list.get(i);
				veId = (String) veMap.get("ve_id");

				VE ve = veService.getVeByVeId(veId);
				String veAtom = ve.getVe_atom();
				if(veMap.containsKey("geolocation_id")){
					veMap.remove("geolocation_id");
				}
				//把key是country_id替换成country_name
				if(veMap.containsKey("country_id")){
					String country_name = "";
					if(veMap.get("country_id")==null){
						 country_name ="country not exist";
					}else{
						int country_id = (int) veMap.get("country_id");
						Country country = new Country();
						country.setCountryId(country_id);
						List<Map<String,Object>> countrylist  = countrydao.query(country, 0, 10);
						Map<String,Object> a =  countrylist.size()!=0?countrylist.get(0):null;
						 country_name = (String) (a!=null?a.get("country_name"):"country not exist");
					}
					
					veMap.remove("country_id");
					veMap.put("country_name", country_name);
				}
				//把key是province_id替换成province_name
				if(veMap.containsKey("province_id")){
					String province_name = "";
					if(veMap.get("province_id")==null){
						province_name ="province not exist";
					}else{
					int province_id = (int) veMap.get("province_id");
					Province province = new Province();
					province.setProvinceId(province_id);
					List<Map<String,Object>> provincelist  = provincedao.query(province, 0, 10);
					Map<String,Object> a =  provincelist.size()!=0?provincelist.get(0):null;
					 province_name = (String) (a!=null?a.get("province_name"):"province not exist");
					}
					veMap.remove("province_id");
					veMap.put("province_name", province_name);
				}
				//把key是city_id替换成city_name
				if(veMap.containsKey("city_id")){
					String city_name = "";
					if(veMap.get("city_id")==null){
						city_name ="city not exist";
					}else{
					int city_id = (int) veMap.get("city_id");
					City city = new City();
					city.setCityId(city_id);
					List<Map<String,Object>> citylist  = citydao.query(city, 0, 10);
					Map<String,Object> a =  citylist.size()!=0?citylist.get(0):null;
					city_name = (String) (a!=null?a.get("city_name"):"city not exist");
					}
					veMap.remove("city_id");
					veMap.put("city_name", city_name);
				}
				if (veMap.containsKey("ve_init_time")) {
					veMap.remove("ve_init_time");
					veMap.put("ve_init_time",
							FORMAT.format(ve.getVe_init_time()));
				}
                //原子VE
				if ("1".equals(veAtom)) {
					VePeBind vePeBind = new VePeBind();
					vePeBind.setVe_id(veId);
					List<VePeBind> bindList = vepebindservice.query(vePeBind,
							0, 1000);
					for (int j = 0; j < bindList.size(); j++) {
						peList = peService
								.getPeById(bindList.get(j).getPe_id());
						if (peList.size() == 0) {
							logger.info("pe table does not exist pe which peid is "
									+ bindList.get(j).getPe_id());
							return "error";
						}
						if (Integer.parseInt(peList.get(0).get("pe_state")
								.toString()) == 0) {
							result = "006";// 006不可用
							break;
						}
					}
					if (!"006".equalsIgnoreCase(result)) {
						veMap.replace("ve_state", "001");
					} else {
						veMap.replace("ve_state", result);
					}
				}//原子VE处理END
			}
			JSONArray array = JSONArray.fromObject(list);

			return array.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
/*
 * getALLAtomVe不加条件获取所有原子ve ， 加多条件可多条件查询ve,URL拼接参数是
 * VeId, VeName, VeKey, VeState, VePrivacy, VeInitTime, VeDescription, VeCreatorId, VeTemplateId 
 * @auther xiazhengyang 
 * 
 * */
	@RequestMapping(value = "/getALLAtomVe.html", method = RequestMethod.GET)
	@ResponseBody
	public String getALLAtomVe(HttpServletRequest request) {
		String result = "";
		String error="error";
		try {
			request.setCharacterEncoding("UTF-8");
			VE ve = new VE();
			ve.setVe_atom("1");
			Map<String, String[]> map = request.getParameterMap();
			if (map.size() == 0) {
				// do nothing
			} else {
				Set<Entry<String, String[]>> mapset = map.entrySet();
				Iterator<Entry<String, String[]>> it = mapset.iterator();
				while (it.hasNext()) {
					Entry<String, String[]> entry = it.next();
					String key = entry.getKey();
					String values[] = entry.getValue();
					VEParam veParam = VEParam.valueOf(key);
					if(veParam==null){
						error="你输入的条件key不符合约定！";
						return "你输入的条件key不符合约定！";
					}else{
						switch (veParam) {
						case VeId:
							ve.setVe_id(values[0]);
							break;
						case VeName:
							ve.setVe_name(values[0]);
							break;
						case VeKey:
							ve.setVe_key(values[0]);
							break;
						case VeState:
							ve.setVe_state(values[0]);
							break;
						case VePrivacy:
							ve.setVe_privacy(values[0]);
							break;
						case VeInitTime:
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							Date date = sdf.parse(values[0]);
							ve.setVe_init_time(date);
							break;
						case VeDescription:
							ve.setVe_description(values[0]);
							break;
						case VeCreatorId:
							ve.setVe_creator_id(values[0]);
							break;
						case VeTemplateId:
							ve.setTemplate_id(values[0]);
							break;
						default:
							break;
						}
					}
					

				}

			}

			List<VE> list = veService.query(ve, 0, veService.queryCOUNTNUM(ve));
			List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> peList = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < list.size(); i++) {
				result = "001";
				String veId = ((VE) list.get(i)).getVe_id();
				Map<String, Object> veMap = new HashMap<String, Object>();
				veMap.put("ve_id", list.get(i).getVe_id());
				veMap.put("ve_name", list.get(i).getVe_name());
				veMap.put("ve_key", list.get(i).getVe_key());
				
				VePeBind vePeBind = new VePeBind();
				vePeBind.setVe_id(veId);
				List<VePeBind> bindList = vepebindservice.query(vePeBind, 0,
						1000);
				for (int j = 0; j < bindList.size(); j++) {
					peList = this.peService.getPeById(bindList.get(j)
							.getPe_id());
					if (peList.size() == 0) {
						System.out.println("pe table does not exist pe which peid is"
										+ ((VePeBind) bindList.get(j))
												.getPe_id());
						return "error";
					}
					if (Integer.parseInt((peList.get(0)).get("pe_state")
							.toString()) == 0) {
						result = "006";
						break;
					}
				}
				veMap.put("ve_state", result);
				veMap.put("ve_privacy", list.get(i).getVe_privacy());
				// f.format(list.get(i).getVe_init_time());
				System.out.println("time:"
						+ FORMAT.format(list.get(i).getVe_init_time()));
				veMap.put("ve_init_time",
						FORMAT.format(list.get(i).getVe_init_time()));
				// veMap.put("ve_init_time",
				// list.get(i).getVe_init_time().toString());
				veMap.put("ve_description", list.get(i).getVe_description());
				veMap.put("ve_creator_id", list.get(i).getVe_creator_id());
				veMap.put("template_id", list.get(i).getTemplate_id());
				veMap.put("ve_atom", list.get(i).getVe_atom());
				if (list.get(i).getGeo() != null) {
					veMap.put("altitude", list.get(i).getGeo().getAltitude());
					
					//添加country_name
					String country_name = "";
					int country_id = list.get(i).getGeo().getCountry_id();
					if(country_id==0){
						 country_name ="country not exist";
					}else{
						Country country = new Country();
						country.setCountryId(country_id);
						List<Map<String,Object>> countrylist  = countrydao.query(country, 0, 10);
						Map<String,Object> a =  countrylist.size()!=0?countrylist.get(0):null;
						 country_name = (String) (a!=null?a.get("country_name"):"country not exist");
					}
					veMap.put("country_name", country_name);
					//添加province_name
					String province_name = "";
					int province_id = list.get(i).getGeo().getProvince_id();
					if(province_id==0){
						province_name ="province not exist";
					}else{
						Province province = new Province();
						province.setProvinceId(province_id);
						List<Map<String,Object>> provincelist  = provincedao.query(province, 0, 10);
						Map<String,Object> a =  provincelist.size()!=0?provincelist.get(0):null;
						province_name = (String) (a!=null?a.get("province_name"):"province not exist");
					}
					veMap.put("province_name", province_name);
					//添加city_name
					String city_name = "";
					int city_id = list.get(i).getGeo().getCity_id();
					if(city_id==0){
						city_name ="city not exist";
					}else{
						City city = new City();
						city.setCityId(city_id);
						List<Map<String,Object>> citylist  = citydao.query(city, 0, 10);
						Map<String,Object> a =  citylist.size()!=0?citylist.get(0):null;
						city_name = (String) (a!=null?a.get("city_name"):"city not exist");
					}
					veMap.put("city_name", city_name);
					
					veMap.put("latitude", list.get(i).getGeo().getLatitude());
					veMap.put("longitude", list.get(i).getGeo().getLongitude());
					veMap.put("otherInfo", list.get(i).getGeo().getOtherInfo());
					
				}

				listmap.add(veMap);
			}
			JSONArray array = JSONArray.fromObject(listmap);

			return array.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return error;
		}
	}

	/*@RequestMapping(value = "/getAtomVeWithSpecificTemplateId.html", method = RequestMethod.GET)
	@ResponseBody
	public String getAtomVeWithSpecificTemplateId(HttpServletRequest request) {
		String result = "";
		try {
			request.setCharacterEncoding("UTF-8");
			VE ve = new VE();
			ve.setVe_atom("1");
			Map<String, String[]> map = request.getParameterMap();
			if (map.size() == 0) {
				// do nothing
			} else {
				Set<Entry<String, String[]>> mapset = map.entrySet();
				Iterator<Entry<String, String[]>> it = mapset.iterator();
				while (it.hasNext()) {
					Entry<String, String[]> entry = it.next();
					String key = entry.getKey();
					String values[] = entry.getValue();
					VEParam veParam = VEParam.valueOf(key);

					switch (veParam) {
					case VeId:
						ve.setVe_id(values[0]);
						break;
					case VeName:
						ve.setVe_name(values[0]);
						break;
					case VeKey:
						ve.setVe_key(values[0]);
						break;
					case VeState:
						ve.setVe_state(values[0]);
						break;
					case VePrivacy:
						ve.setVe_privacy(values[0]);
						break;
					case VeInitTime:
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date date = sdf.parse(values[0]);
						ve.setVe_init_time(date);
						break;
					case VeDescription:
						ve.setVe_description(values[0]);
						break;
					case VeCreatorId:
						ve.setVe_creator_id(values[0]);
						break;
					case VeTemplateId:
						ve.setTemplate_id(values[0]);
						break;
					default:
						break;
					}

				}

			}

			List<VE> list = veService.query(ve, 0, veService.queryCOUNTNUM(ve));
			List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> peList = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < list.size(); i++) {
				result = "001";
				String veId = ((VE) list.get(i)).getVe_id();
				Map<String, Object> veMap = new HashMap<String, Object>();
				veMap.put("ve_id", list.get(i).getVe_id());
				veMap.put("ve_name", list.get(i).getVe_name());
				veMap.put("ve_key", list.get(i).getVe_key());
				VePeBind vePeBind = new VePeBind();
				vePeBind.setVe_id(veId);
				List<VePeBind> bindList = vepebindservice.query(vePeBind, 0,
						1000);
				for (int j = 0; j < bindList.size(); j++) {
					peList = this.peService.getPeById(bindList.get(j)
							.getPe_id());
					if (peList.size() == 0) {
						System.out
								.println("pe table does not exist pe which peid is"
										+ ((VePeBind) bindList.get(j))
												.getPe_id());
						return "error";
					}
					if (Integer.parseInt((peList.get(0)).get("pe_state")
							.toString()) == 0) {
						result = "006";
						break;
					}
				}
				veMap.put("ve_state", result);
				veMap.put("ve_privacy", list.get(i).getVe_privacy());
				// f.format(list.get(i).getVe_init_time());
				System.out.println("time:"
						+ FORMAT.format(list.get(i).getVe_init_time()));
				veMap.put("ve_init_time",
						FORMAT.format(list.get(i).getVe_init_time()));
				// veMap.put("ve_init_time",
				// list.get(i).getVe_init_time().toString());
				veMap.put("ve_description", list.get(i).getVe_description());
				veMap.put("ve_creator_id", list.get(i).getVe_creator_id());
				veMap.put("template_id", list.get(i).getTemplate_id());
				veMap.put("ve_atom", list.get(i).getVe_atom());
				if (list.get(i).getGeo() != null) {
					veMap.put("altitude", list.get(i).getGeo().getAltitude());
					veMap.put("city_id", list.get(i).getGeo().getCity_id());
					veMap.put("country_id", list.get(i).getGeo()
							.getCountry_id());
					veMap.put("geolocation_id", list.get(i).getGeo()
							.getGeolocation_id());
					veMap.put("latitude", list.get(i).getGeo().getLatitude());
					veMap.put("longitude", list.get(i).getGeo().getLongitude());
					veMap.put("otherInfo", list.get(i).getGeo().getOtherInfo());
					veMap.put("province_id", list.get(i).getGeo()
							.getProvince_id());
				}

				listmap.add(veMap);
			}
			JSONArray array = JSONArray.fromObject(listmap);

			return array.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
*/
	/**
	 * 删除VE 对于原子VE删除同时删除PE，而复合VE只删除VE
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteVE.html", method = RequestMethod.GET)
	@ResponseBody
	public String deleteVe(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			String veId = request.getParameter("veId");
			veId = ConvertVeId.simplifyVeId(veId);

			VE ve = veService.getVeByVeId(veId);
			String result = "fail";
			if ("1".equals(ve.getVe_atom())) {// 若为原子VE删除VE并删除PE
				VePeBind vePeBind = new VePeBind();
				Pe pe = new Pe();
				vePeBind.setVe_id(veId);
				List<VePeBind> list = vepebindservice.query(vePeBind, 0, 100);
				pe.setPeId(list.get(0).getPe_id());
				result = peService.deleteAll(pe);
				if ("success".equals(result)) {
					result = veService.delete(veId);
					System.out.println("+++++" + result);
					if ("success".equals(result)) {
						logger.info("Atom VE delete success");
						return result;
					} else {
						logger.error("Atom VE Delete failed！");
						return result;
					}
				} else {
					logger.error("PE delete fails");
					return result;
				}
			} else {// 不是原子VE则只删除VE
				result = veService.delete(veId);
				if ("success".equals(result)) {
					logger.info("VE delete success");
					return result;
				} else {
					logger.error("VE Delete failed！");
					return result;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAccess.html", method = RequestMethod.POST)
	@ResponseBody
	public String getAccess(HttpServletRequest request) {
		String result = "";
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return result;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/stopAccess.html", method = RequestMethod.POST)
	@ResponseBody
	public String stopAccess(HttpServletRequest request) {
		String result = "";
		try {
			request.setCharacterEncoding("UTF-8");
			// String id = request.getParameter("id");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return result;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/stopAllAccess.html", method = RequestMethod.POST)
	@ResponseBody
	public String stopAllAccess(HttpServletRequest request) {
		String result = "";
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return result;
	}

	@RequestMapping(value = "/getPEList.html", method = RequestMethod.POST)
	@ResponseBody
	public String getPEList(HttpServletRequest request) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String tplId = request.getParameter("tplId");

		// 根据商店分配的ID查系统ID
		VETemplate veTemplate = new VETemplate();
		veTemplate.setTpl_id(tplId);
		tplId = veTemplateService.query(veTemplate, 0, 10).get(0)
				.getTemplate_id();

		TemplateBind templateBind = new TemplateBind();
		templateBind.setVe_template_id(tplId);
		List<TemplateBind> bindList = templateBindService.query(templateBind,
				0, 100);
		for (int i = 0; i < bindList.size(); i++) {
			String pe_template_id = bindList.get(i).getPe_template_id();
			List<Map<String, Object>> peList = peService.getPeFilter(
					pe_template_id, "", "", 0, 1000);

			Map<String, Object> bindMap = new HashMap<String, Object>();
			bindMap.put("template_bind_id", bindList.get(i)
					.getTemplate_bind_id());
			bindMap.put("identify_id", bindList.get(i).getIdentify_id());
			bindMap.put("ve_template_id", bindList.get(i).getVe_template_id());
			bindMap.put("pe_template_id", pe_template_id);
			bindMap.put("service_id", bindList.get(i).getService_id());
			bindMap.put("bind_max", bindList.get(i).getBind_max());
			bindMap.put("bind_min", bindList.get(i).getBind_min());
			bindMap.put("bind_type", bindList.get(i).getBind_type());
			bindMap.put("peList", peList);
			listmap.add(bindMap);
		}
		JSONArray json = JSONArray.fromObject(listmap);
		logger.info("the json is: " + json.toString());
		return json.toString();
	}
}
