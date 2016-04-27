package com.cetc.iot.servicesystem.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cetc.iot.database.model.CompleteTpl;
import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.database.model.VETemplate;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.database.model.VeWebsocketServiceParam;
import com.cetc.iot.database.model.VeWebsocketServiceReturnParam;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class XML2Tpl {
	
	private static Logger logger = Logger.getLogger(XML2Tpl.class.getName());
	
	public CompleteTpl xml2tpl(String xmlName){
	
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new File(xmlName));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	
		Element root = doc.getRootElement();
		CompleteTpl cTpl = new CompleteTpl();
		VETemplate tpl = new VETemplate();
		//暂时还没应用商店，所以tpl_owner属性还不能赋值，这里就写死了，以后改
		tpl.setTpl_owner("ve");
		String tplId ="";
		List<String> identifyIDList = new ArrayList<String>();
		Multimap<String, String> IdentifyIdServiceName=ArrayListMultimap.create();
		//TemplateBind templateBind = new TemplateBind();
		Iterator<?> rootIter = root.elementIterator();
		//
		while(rootIter.hasNext()){
			//node1迭代
			Element node1 = (Element) rootIter.next();
			String key1 = node1.getName();
			String value1 = node1.getText();
			if(key1.equalsIgnoreCase("tplID")){
				tpl.setTpl_id(value1);
				tplId = value1;
				logger.info("tpl_id--> "+value1);
			}else if(key1.equalsIgnoreCase("name")){
				tpl.setTpl_name(value1);
				logger.info("tpl_name--> "+value1);
			}else if(key1.equalsIgnoreCase("state")){
				tpl.setTpl_state(value1);
				logger.info("tpl_state--> "+value1);
			}else if(key1.equalsIgnoreCase("type")){
				tpl.setTpl_type_id(value1);
				logger.info("tpl_type--> "+value1);
			}else if(key1.equalsIgnoreCase("version")){
				tpl.setTpl_version(value1);
				logger.info("tpl_version--> "+value1);
			}else if(key1.equalsIgnoreCase("openness")){
				tpl.setTemplate_openness(value1);
				logger.info("tpl_openness--> "+value1);
			}else if(key1.equalsIgnoreCase("alias")){
				tpl.setTpl_alias(value1);
				logger.info("tpl_alias--> "+value1);
			}else if(key1.equalsIgnoreCase("enrolltime")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date time =null;
				try {
					time = sdf.parse(sdf.format(new Date()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				tpl.setTpl_enroll_time(time);
				logger.info("tpl_enroll_time--> "+value1);
			}else if(key1.equalsIgnoreCase("description")){
				tpl.setTpl_description(value1);
				logger.info("tpl_description--> "+value1);
			}else if(key1.equalsIgnoreCase("icon")){
				tpl.setTpl_icon(value1);
				logger.info("tpl_icon--> " + value1);
			}else if(key1.equalsIgnoreCase("display")){
				tpl.setTpl_display(value1);
				logger.info("tpl_display--> "+value1);
			}else if(key1.equalsIgnoreCase("peTemplate")){
				Set<TemplateBind> templateBindSet = new HashSet<TemplateBind>();
			 	Iterator<?> node1Iter = node1.elementIterator();
				while(node1Iter.hasNext()){
					Element node2 = (Element) node1Iter.next();
					/*String key2 = node2.getName();
					String value2 = node2.getText();*/
					
					if(node2.getName().equalsIgnoreCase("bindNum")){
						// do nothing
				    }else if(node2.getName().equalsIgnoreCase("description")){
				    	//do nothing
				    }else if(node2.getName().equalsIgnoreCase("item")){
						TemplateBind templateBind = new TemplateBind();
						//templateBind.setVe_template_id(templateId);
						
						Iterator<?> node2Iter = node2.elementIterator();
						//迭代item
						while(node2Iter.hasNext()){
							Element node3 = (Element) node2Iter.next();
							String key3 = node3.getName();
							String value3 = node3.getText();
							if(key3.equalsIgnoreCase("identifyID")){
								templateBind.setIdentify_id(value3);
								logger.info("tpl_identifyID--> "+value3);
							}else if(key3.equalsIgnoreCase("templateID")){
								templateBind.setPe_template_id(value3);
								logger.info("tpl_petemplateID--> "+value3);
							}else if(key3.equalsIgnoreCase("bind_type")){
								templateBind.setBind_type(value3);
								logger.info("tpl_bind_type--> "+value3);
							}else if(key3.equalsIgnoreCase("bind_min")){
								templateBind.setBind_min(Integer.parseInt(value3));
								logger.info("tpl_bind_type--> "+value3);
							}else if(key3.equalsIgnoreCase("bind_max")){
								templateBind.setBind_max(Integer.parseInt(value3));
								logger.info("tpl_bind_type--> "+value3);
							}
						}//end while
						templateBindSet.add(templateBind);
					}//end if
				
				}
				//向cTpl中赋值templateBind
				cTpl.setTemplateBinds(templateBindSet);
				
			}else if(key1.equalsIgnoreCase("service")){
				Iterator<?> node1Iter = node1.elementIterator();
				
				Set<VeWebsocketService> serviceSet = new HashSet<VeWebsocketService>();
				Set<VeWebsocketServiceParam> paramSet = new HashSet<VeWebsocketServiceParam>();
				Set<VeWebsocketServiceReturnParam> returnParamSet=new HashSet<VeWebsocketServiceReturnParam>();
				//解析service表
				while(node1Iter.hasNext()){
					//node2迭代
					Element node2 = (Element) node1Iter.next();
					if(node2.getName().equalsIgnoreCase("item")){
						VeWebsocketService service = new VeWebsocketService();
						
						
						Iterator<?> node2Iter = node2.elementIterator();
						String service_name = "";
						//解析item
						while(node2Iter.hasNext()){
							//node3�����ǩ��eg:serviceID
							Element node3 = (Element) node2Iter.next();
							String key3 = node3.getName();
							String value3 = node3.getText();
							 if(key3.equalsIgnoreCase("identifyIDs")){
								 Iterator<?> node3Iter = node3.elementIterator();
								
								 while(node3Iter.hasNext()){
									 Element node4 = (Element) node3Iter.next();
									 if(node4.getName().equalsIgnoreCase("identifyID")){
										 identifyIDList.add(node4.getTextTrim());
									 }
								 }
							 }else if(key3.equalsIgnoreCase("service_name")){
								service.setService_name(value3);
								service_name = value3;
								for(String identifyId:identifyIDList){
									IdentifyIdServiceName.put(identifyId,value3);	
								}
								identifyIDList.clear();
								logger.info("tpl_serviceName--> "+value3);
							}else if(key3.equalsIgnoreCase("params")){
								Iterator<?> node3Iter = node3.elementIterator();
								
								//解析params
								while(node3Iter.hasNext()){
									//node4迭代
									Element node4 = (Element) node3Iter.next();
									if(node4.getName().equalsIgnoreCase("param")){
										VeWebsocketServiceParam param = new VeWebsocketServiceParam();
										//******这里是把tplId赋值给了param_id,service_name赋值给了service_id,目的是建立service和param的关联
										param.setParam_id(tplId);
										param.setService_id(service_name);
										Iterator<?> node4Iter = node4.elementIterator();
										
								while(node4Iter.hasNext()){
									Element node5 = (Element) node4Iter.next();
									String key5 = node5.getName();
									String value5 = node5.getText();
									if(key5.equalsIgnoreCase("type")){
										param.setParam_type(value5);
										logger.info("Param_type--> "+value5);
									}else if(key5.equalsIgnoreCase("name")){
										param.setParam_name(value5);
										logger.info("Param_name--> "+value5);
									}else if(key5.equalsIgnoreCase("values")){
										String value="";
										Iterator<?> node5Iter = node5.elementIterator();
										while(node5Iter.hasNext()){
											Element node6 = (Element) node5Iter.next();
											String key6 = node6.getName();
											String value6 = node6.getText();
											if(key6.equalsIgnoreCase("value")){
												value +=value6+";";
											}
										}
										param.setParam_value(value);
									}//else if end
									
		           					}//node4 over
								paramSet.add(param);
									}else if(node4.getName().equalsIgnoreCase("returnParam")){

										VeWebsocketServiceReturnParam returnParam = new VeWebsocketServiceReturnParam();
										//******这里是把tplId赋值给了returnparam_id,service_name赋值给了service_id,目的是建立service和returnparam的关联
										returnParam.setReturnParam_id(tplId);
										returnParam.setService_id(service_name);
										Iterator<?> node4Iter = node4.elementIterator();
										
								while(node4Iter.hasNext()){
									Element node5 = (Element) node4Iter.next();
									String key5 = node5.getName();
									String value5 = node5.getText();
									if(key5.equalsIgnoreCase("type")){
										returnParam.setReturnParam_type(value5);
										logger.info("returnParam_type--> "+value5);
									}else if(key5.equalsIgnoreCase("name")){
										returnParam.setReturnParam_name(value5);
										logger.info("returnParam_name--> "+value5);
									}else if(key5.equalsIgnoreCase("description")){
										returnParam.setReturnParam_description(value5);
									}//else if end
									
		           					}//node4 over
								returnParamSet.add(returnParam);
										
									}//node4 if over
								  }
								
								
								}else if(key3.equalsIgnoreCase("description")){
								service.setService_description(value3);
								logger.info("tpl_service_description--> "+value3);
							}
						}//end while
						serviceSet.add(service);
					}//end if
				}//end while
				//System.out.println("paramSet长度:"+paramSet.size());
				cTpl.setParams(paramSet);
				cTpl.setServices(serviceSet);
				cTpl.setIdentifyIdServiceName(IdentifyIdServiceName);
				cTpl.setReturnParam(returnParamSet);
			}
			
		}//end while
		cTpl.setVeTpl(tpl);
		return cTpl;
	}
	
	public static void main(String[] args){
		XML2Tpl x2t = new XML2Tpl();
		x2t.xml2tpl("VETpl.xml");
		
	}
}
