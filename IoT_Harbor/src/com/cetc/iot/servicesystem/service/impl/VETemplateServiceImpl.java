package com.cetc.iot.servicesystem.service.impl;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.dao.ServiceDao;
import com.cetc.iot.database.dao.TemplateDao;
import com.cetc.iot.database.model.CompleteTpl;
import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.database.model.VePeBindInterface;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.database.model.VeWebsocketServiceParam;
import com.cetc.iot.database.model.VeWebsocketServiceReturnParam;
import com.cetc.iot.servicesystem.service.TemplateBindService;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.VETemplateService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceParamService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceReturnParamService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;
import com.cetc.iot.servicesystem.service.VePeBindInterfaceService;
import com.cetc.iot.servicesystem.util.BuildIDUtil;
import com.cetc.iot.servicesystem.util.VeTemplateStateChangedManagement;
import com.google.common.collect.Multimap;

@Service("veTplService")
public class VETemplateServiceImpl implements VETemplateService {
	@Autowired
	private TemplateDao tplDao;
	@Autowired
	private VEWebsocketServiceService vwss;
	@Autowired
	private VEWebsocketServiceParamService vwsps;
	@Autowired
	private VEWebsocketServiceReturnParamService vwsrps;
	@Autowired
	private TemplateBindService tbs;
	@Autowired
	private VEService veService;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private VePeBindInterfaceService vePeBindInterfaceService;
	@Override
	public List<VETemplate> query(VETemplate veTemplate, int page, int size) {

		return tplDao.query(veTemplate, page, size);
	}

	@Override
	public String delete(VETemplate veTemplate) {
		String template_id = veTemplate.getTemplate_id();
		VE ve = new VE();
		ve.setTemplate_id(template_id);
		List<VE> list = veService.query(ve, 0, veService.queryCOUNTNUM(ve));
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
			veService.delete(list.get(i).getVe_id());
		  }
		}
		return tplDao.delete(veTemplate);
	}

	@Override
	public String add(VETemplate veTemplate) {

		String template_id = BuildIDUtil.build("veTemplate");
		veTemplate.setTemplate_id(template_id);
		veTemplate.setTpl_state("001");
		return tplDao.add(veTemplate);

	}

	@Override
	public String update(VETemplate veTemplate) {
		// TODO Auto-generated method stub
		return tplDao.update(veTemplate);
	}

	@Override
	public VETemplate getTemplateByTemplateId(String templateId) {
		VETemplate veTemplate = new VETemplate();
		veTemplate.setTemplate_id(templateId);

		return query(veTemplate, 0, 1) == null ? null : query(veTemplate, 0, 1)
				.get(0);
	}

	@Override
	public String updateState(String templateId, String newState) {

		if (VeTemplateStateChangedManagement
				.CheckVeTemplateStateChangedIsReasonable(templateId, newState)) {
			// 拼接vetemplate对象
			VETemplate vetemplate = new VETemplate();
			vetemplate.setTemplate_id(templateId);
			vetemplate.setTpl_state(newState);
			if (update(vetemplate).equals("success")) {
				if (veService.getVeByTemplateId(templateId) != null) {
					for (VE temp : veService.getVeByTemplateId(templateId)) {
						String veId = (String) temp.getVe_id();
						veService.updateState(veId, newState);
					}
				}
				return "success";
			} else {
				return "fail";
			}
		} else
			return "fail";

	}

	@Override
	public boolean insertCompleteTpl(CompleteTpl completeTpl,
			Map<String, Object> methodParam) {
		// 判断ve模板是否重复提交或数据库已经存在有该模板
		VETemplate vetemplate = completeTpl.getVeTpl();
		Multimap<String, String> IdentifyIdServiceName = completeTpl
				.getIdentifyIdServiceName();
		// Map<String, String> serviceIdIdentifyId = new HashMap<String,
		// String>();
		/*Multimap<String, String> serviceIdIdentifyId = ArrayListMultimap
				.create();*/
		List<VETemplate> vetemplateList = query(vetemplate, 0, 1);
		if (vetemplateList.size() != 0) {
			return false;
		}
		String tplAlias = (String) methodParam.get("tplAlias");
		String tplOpenness = (String) methodParam.get("tplOpenness");
		String tpl_classpath = (String) methodParam.get("tpl_classpath");

		vetemplate.setTemplate_openness(tplOpenness);
		vetemplate.setTpl_classpath(tpl_classpath);
		vetemplate.setTpl_alias(tplAlias);
		Date tpl_enroll_time = new java.sql.Date(new java.util.Date().getTime());
		vetemplate.setTpl_enroll_time(tpl_enroll_time);
		if ((add(vetemplate).equals("success"))) {
			// String tpl_ID = (String)
			// tplDao.getTplByID(completeTpl.getVeTpl().getTpl_ID()).get("tpl_ID");
			String templateId = query(vetemplate, 0, 1).get(0).getTemplate_id();

			// 只有模板插入成功，下面的才有意义
			// ve_websocket_service
			Set<VeWebsocketService> services = completeTpl.getServices();
			if (services != null) {
				Iterator<VeWebsocketService> servIter = services.iterator();
				while (servIter.hasNext()) {
					VeWebsocketService service = (VeWebsocketService) servIter
							.next();
					// 0
					service.setTemplate_id(templateId);
					if (vwss.query(service, 0, 10).size() != 0) {
						return false;
					} else {
						// �˴��Ժ�Ҫ��
						vwss.add(service);

						// veTemplate_peTemplate_bind
						/*
						 * Set<String> serviceNameSet =
						 * IdentifyIdServiceName.keySet(); if
						 * (serviceNameSet.size() != 0) { for (String
						 * serviceName : serviceNameSet) { //
						 * 通过servicenAME找serviceId VeWebsocketService
						 * veWebsocketService = new VeWebsocketService();
						 * veWebsocketService.setService_name(serviceName);
						 * veWebsocketService.setTemplate_id(templateId); String
						 * serviceId = vwss.query(veWebsocketService, 0,
						 * 10).get(0).getService_id(); String identifyIdString =
						 * ""; Collection<String> IdentifyIdCollection =
						 * IdentifyIdServiceName .get(serviceName); if
						 * (IdentifyIdCollection.size() != 0) { for (String
						 * identifyId : IdentifyIdCollection) { if
						 * (identifyIdString.equals("")) { identifyIdString =
						 * identifyIdString+ identifyId; } else {
						 * identifyIdString = identifyIdString+ ";" +
						 * identifyId; }
						 * serviceIdIdentifyId.put(serviceId,identifyIdString);
						 * } } } if (serviceIdIdentifyId.size() != 0) {
						 * 
						 * Set<TemplateBind> templateBindSet =
						 * completeTpl.getTemplateBinds(); for(TemplateBind
						 * templateBind:templateBindSet){
						 * templateBind.setVe_template_id(templateId);
						 * tbs.add(templateBind); }
						 * 
						 * 
						 * TemplateBind templateBind =
						 * completeTpl.getTemplateBinds().iterator().next();
						 * Iterator<Map.Entry<String, String>> entryIterator =
						 * serviceIdIdentifyId.entrySet().iterator(); // for(int
						 * // i=0;i<serviceIdIdentifyId.size();i++){ while
						 * (entryIterator.hasNext()) { Entry<String, String>
						 * entry = entryIterator.next();
						 * templateBind.setVe_template_id(templateId);
						 * templateBind.setIdentify_id(entry.getKey());
						 * templateBind.setService_id(entry.getValue());
						 * tbs.add(templateBind); } } }
						 */

						// ve_websocket_service_param
						// 需要service_id 现在又service_name 和templateId
						Set<VeWebsocketServiceParam> params = completeTpl
								.getParams();
						if (params != null) {
							Iterator<VeWebsocketServiceParam> paramIter = params
									.iterator();
							while (paramIter.hasNext()) {
								VeWebsocketServiceParam param = (VeWebsocketServiceParam) paramIter
										.next();
								// param对象里含有 param_name param_type
								// service_id(service_name) param_id(tplId)
								// 字段，括号内是真正的字段。
								String service_name_fake = param
										.getService_id();
								// 通过templateId和service_name查得service_id
								String service_name = service.getService_name();
								if (service_name.equals(service_name_fake)) {
									// 把service_id赋值给param的service_id属性
									VeWebsocketService a = new VeWebsocketService();
									a.setTemplate_id(templateId);
									a.setService_name(service_name);
									VeWebsocketService veWebsocketService = vwss
											.query(a, 0, 100).get(0);
									String service_id = veWebsocketService
											.getService_id();
									param.setService_id(service_id);
									vwsps.add(param);
								}

							}
						}
						///////////////////////////////
						// ve_websocket_service_returnParam
						// 需要service_id 现在又service_name 和templateId
						Set<VeWebsocketServiceReturnParam> returnParams = completeTpl.getReturnParam();
						if (returnParams != null) {
							Iterator<VeWebsocketServiceReturnParam> returnParamIter = returnParams
									.iterator();
							while (returnParamIter.hasNext()) {
								VeWebsocketServiceReturnParam returnParam = (VeWebsocketServiceReturnParam) returnParamIter
										.next();
								// returnparam对象里含有 returnparam_name returnparam_type
								// service_id(service_name) returnparam_id(tplId)
								// 字段，括号内是真正的字段。
								String service_name_fake = returnParam
										.getService_id();
								// 通过templateId和service_name查得service_id
								String service_name = service.getService_name();
								if (service_name.equals(service_name_fake)) {
									// 把service_id赋值给returnparam的service_id属性
									VeWebsocketService a = new VeWebsocketService();
									a.setTemplate_id(templateId);
									a.setService_name(service_name);
									VeWebsocketService veWebsocketService = vwss
											.query(a, 0, 100).get(0);
									String service_id = veWebsocketService
											.getService_id();
									returnParam.setService_id(service_id);
									vwsrps.add(returnParam);
								}

							}
						}
						
						///////////////////////////////
					}//
				}
			}
			/*Iterator<Entry<String, String>> IdentifyIdServiceNameIter = IdentifyIdServiceName
					.entries().iterator();
			while (IdentifyIdServiceNameIter.hasNext()) {
				Entry<String, String> entry = IdentifyIdServiceNameIter.next();
				String serviceName = entry.getValue();
				System.out.println("serviceName:" + serviceName);
				VeWebsocketService veWebsocketService = new VeWebsocketService();
				veWebsocketService.setService_name(serviceName);
				veWebsocketService.setTemplate_id(templateId);
				String serviceId = vwss.query(veWebsocketService, 0, 10).get(0)
						.getService_id();
				// entry.setValue(serviceId);
				Iterator<TemplateBind> templateBindIter = completeTpl
						.getTemplateBinds().iterator();
				while (templateBindIter.hasNext()) {
					TemplateBind templateBind = templateBindIter.next();
					if (templateBind.getIdentify_id().equalsIgnoreCase(
							entry.getKey())
							&& templateBind.getService_id() == null) {
						templateBind.setService_id(serviceId);
						templateBind.setVe_template_id(templateId);
						tbs.add(templateBind);
					} else {
						break;
					}
				}
			}*/
			Iterator<TemplateBind> templateBindIter = completeTpl
					.getTemplateBinds().iterator();
			TemplateBind templateBind = null;
			Entry<String, String> entry = null;
			String serviceName = null;
			while (templateBindIter.hasNext()) {
				
				templateBind = templateBindIter.next();
				Iterator<Entry<String, String>> IdentifyIdServiceNameIter = IdentifyIdServiceName
						.entries().iterator();
				while (IdentifyIdServiceNameIter.hasNext()) {
					
					entry = IdentifyIdServiceNameIter.next();
					serviceName = entry.getValue();
					VeWebsocketService veWebsocketService = new VeWebsocketService();
					veWebsocketService.setService_name(serviceName);
					veWebsocketService.setTemplate_id(templateId);
					String serviceId = vwss.query(veWebsocketService, 0, 10)
							.get(0).getService_id();
					if (templateBind.getIdentify_id().equalsIgnoreCase(
							entry.getKey())
							&& templateBind.getService_id() == null) {
						templateBind.setService_id(serviceId);
						templateBind.setVe_template_id(templateId);
						
						//向添加模板绑定和interface的关系*****************************************
						String templateBindId = tbs.add(templateBind);
						Map<String, String> peTemplateInterface = completeTpl.getPeTemplateInterface();
						VePeBindInterface bindInterface = new VePeBindInterface();
						bindInterface.setBindId(templateBindId);
						bindInterface.setInterfaceId(peTemplateInterface.get(templateBind.getPe_template_id()));
						vePeBindInterfaceService.add(bindInterface);
					} else {
						continue;
					}
				}
			}
		}

		return true;

	}

	@Override
	public boolean insertCompleteTplForAtomVE(CompleteTpl completeTpl,
			Map<String, Object> methodParam) {
		// 判断ve模板是否重复提交或数据库已经存在有该模板
		VETemplate vetemplate = completeTpl.getVeTpl();
		Multimap<String, String> IdentifyIdServiceName = completeTpl
				.getIdentifyIdServiceName();
		// Map<String, String> serviceIdIdentifyId = new HashMap<String,
		// String>();
		/*Multimap<String, String> serviceIdIdentifyId = ArrayListMultimap
				.create();
		List<VETemplate> vetemplateList = query(vetemplate, 0, 1);*/
		/*if (vetemplateList.size() != 0) {
			return false;
		}*/
		String tplAlias = (String) methodParam.get("tplAlias");
		String tplOpenness = (String) methodParam.get("tplOpenness");
		String tpl_classpath = (String) methodParam.get("tpl_classpath");

		vetemplate.setTemplate_openness(tplOpenness);
		vetemplate.setTpl_classpath(tpl_classpath);
		vetemplate.setTpl_alias(tplAlias);
		Date tpl_enroll_time = new java.sql.Date(new java.util.Date().getTime());
		vetemplate.setTpl_enroll_time(tpl_enroll_time);
		if ((add(vetemplate).equals("success"))) {
			// String tpl_ID = (String)
			// tplDao.getTplByID(completeTpl.getVeTpl().getTpl_ID()).get("tpl_ID");
			String templateId = query(vetemplate, 0, 1).get(0).getTemplate_id();

			// 只有模板插入成功，下面的才有意义
			// ve_websocket_service
			Set<VeWebsocketService> services = completeTpl.getServices();
			if (services != null) {
				Iterator<VeWebsocketService> servIter = services.iterator();
				while (servIter.hasNext()) {
					VeWebsocketService service = (VeWebsocketService) servIter
							.next();
					// �ж�serviceID�ǲ����Ѿ�����
					service.setTemplate_id(templateId);
					if (vwss.query(service, 0, 10).size() != 0) {
						return false;
					} else {
						// �˴��Ժ�Ҫ��
						vwss.add(service);

						// veTemplate_peTemplate_bind
						/*
						 * Set<String> serviceNameSet =
						 * IdentifyIdServiceName.keySet(); if
						 * (serviceNameSet.size() != 0) { for (String
						 * serviceName : serviceNameSet) { //
						 * 通过servicenAME找serviceId VeWebsocketService
						 * veWebsocketService = new VeWebsocketService();
						 * veWebsocketService.setService_name(serviceName);
						 * veWebsocketService.setTemplate_id(templateId); String
						 * serviceId = vwss.query(veWebsocketService, 0,
						 * 10).get(0).getService_id(); String identifyIdString =
						 * ""; Collection<String> IdentifyIdCollection =
						 * IdentifyIdServiceName .get(serviceName); if
						 * (IdentifyIdCollection.size() != 0) { for (String
						 * identifyId : IdentifyIdCollection) { if
						 * (identifyIdString.equals("")) { identifyIdString =
						 * identifyIdString+ identifyId; } else {
						 * identifyIdString = identifyIdString+ ";" +
						 * identifyId; }
						 * serviceIdIdentifyId.put(serviceId,identifyIdString);
						 * } } } if (serviceIdIdentifyId.size() != 0) {
						 * 
						 * Set<TemplateBind> templateBindSet =
						 * completeTpl.getTemplateBinds(); for(TemplateBind
						 * templateBind:templateBindSet){
						 * templateBind.setVe_template_id(templateId);
						 * tbs.add(templateBind); }
						 * 
						 * 
						 * TemplateBind templateBind =
						 * completeTpl.getTemplateBinds().iterator().next();
						 * Iterator<Map.Entry<String, String>> entryIterator =
						 * serviceIdIdentifyId.entrySet().iterator(); // for(int
						 * // i=0;i<serviceIdIdentifyId.size();i++){ while
						 * (entryIterator.hasNext()) { Entry<String, String>
						 * entry = entryIterator.next();
						 * templateBind.setVe_template_id(templateId);
						 * templateBind.setIdentify_id(entry.getKey());
						 * templateBind.setService_id(entry.getValue());
						 * tbs.add(templateBind); } } }
						 */

						// ve_websocket_service_param
						// 需要service_id 现在又service_name 和templateId
						Set<VeWebsocketServiceParam> params = completeTpl
								.getParams();
						if (params != null) {
							Iterator<VeWebsocketServiceParam> paramIter = params
									.iterator();
							while (paramIter.hasNext()) {
								VeWebsocketServiceParam param = (VeWebsocketServiceParam) paramIter
										.next();
								// param对象里含有 param_name param_type
								// service_id(service_name) param_id(tplId)
								// 字段，括号内是真正的字段。
								String service_name_fake = param
										.getService_id();
								// 通过templateId和service_name查得service_id
								String service_name = service.getService_name();
								if (service_name.equals(service_name_fake)) {
									// 把service_id赋值给param的service_id属性
									VeWebsocketService a = new VeWebsocketService();
									a.setTemplate_id(templateId);
									a.setService_name(service_name);
									VeWebsocketService veWebsocketService = vwss
											.query(a, 0, 100).get(0);
									String service_id = veWebsocketService
											.getService_id();
									param.setService_id(service_id);
									vwsps.add(param);
								}

							}
						}
						
						//////////////////////////////////
						// ve_websocket_service_return_param
						// 需要service_id 现在又service_name 和templateId
						Set<VeWebsocketServiceReturnParam> returnParams = completeTpl.getReturnParam();
								
						if (returnParams != null) {
							Iterator<VeWebsocketServiceReturnParam> returnParamIter = returnParams
									.iterator();
							while (returnParamIter.hasNext()) {
								VeWebsocketServiceReturnParam returnParam = (VeWebsocketServiceReturnParam) returnParamIter
										.next();
								// returnparam对象里含有 returnparam_name returnparam_type
								// service_id(service_name) param_id(tplId)
								// 字段，括号内是真正的字段。
								String service_name_fake = returnParam
										.getService_id();
								// 通过templateId和service_name查得service_id
								String service_name = service.getService_name();
								if (service_name.equals(service_name_fake)) {
									// 把service_id赋值给returnparam的service_id属性
									VeWebsocketService a = new VeWebsocketService();
									a.setTemplate_id(templateId);
									a.setService_name(service_name);
									VeWebsocketService veWebsocketService = vwss
											.query(a, 0, 100).get(0);
									String service_id = veWebsocketService
											.getService_id();
									returnParam.setService_id(service_id);
									vwsrps.add(returnParam);
								}

							}
						}
						///////////////////////////////////
					}//
				}
			}

			Iterator<TemplateBind> templateBindIter = completeTpl
					.getTemplateBinds().iterator();
			TemplateBind templateBind = null;
			Entry<String, String> entry = null;
			String serviceName = null;
			while (templateBindIter.hasNext()) {
				
				templateBind = templateBindIter.next();
				Iterator<Entry<String, String>> IdentifyIdServiceNameIter = IdentifyIdServiceName
						.entries().iterator();
				while (IdentifyIdServiceNameIter.hasNext()) {
					
					entry = IdentifyIdServiceNameIter.next();
					serviceName = entry.getValue();
					VeWebsocketService veWebsocketService = new VeWebsocketService();
					veWebsocketService.setService_name(serviceName);
					veWebsocketService.setTemplate_id(templateId);
					String serviceId = vwss.query(veWebsocketService, 0, 10)
							.get(0).getService_id();
					if (templateBind.getIdentify_id().equalsIgnoreCase(
							entry.getKey())
							&& serviceName.equals(templateBind.getService_id())) {
						templateBind.setService_id(serviceId);
						templateBind.setVe_template_id(templateId);
						tbs.add(templateBind);
					} else {
						continue;
					}
				}
			}
		}

		return false;

	}

}
