package com.cetc.iot.servicesystem.util;

import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.cetc.iot.database.model.Pe;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VeKeyConfig;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.database.model.VeWebsocketServiceParam;
import com.cetc.iot.database.model.VeWebsocketServiceReturnParam;
import com.cetc.iot.harbormanage.service.PEService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceParamService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceReturnParamService;
import com.cetc.iot.servicesystem.service.VeKeyConfigService;
import com.cetc.iot.servicesystem.service.VePeBindService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;
@Component
public class Ve2Xml {

	//	@Autowired
	//	private static com.cetc.iot.harbormanage.service.PEService PEService;
	/*@Autowired
	private static VEWebsocketServiceService vesps;*/


	@SuppressWarnings("deprecation")
	//m是1表示VE注册流程  m是2是VE查询流程
	public static String ve2Xml(VE ve,int m){
     if(ve!=null){
		String websocketUrl = GetProperty.getValue("websocketUrl");
		//System.out.println("websocketUrl:"+websocketUrl);
		String template_id = ve.getTemplate_id();
		String outline ="<?xml version=\"1.0\" encoding=\"gb2312\"?>"+ "<ve:VE xmlns:pe=\"http://192.168.22.39/namespace/pe\" " +
				"xmlns:ve=\"http://192.168.22.39/namespace/ve\" xmlns:param=\"http://192.168.22.39/namespace/param\" xmlns:service=\"http://192.168.22.39/namespace/service\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
				"</ve:VE>";
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(outline);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();

		Element eleVeId = root.addElement("ve:veID");
		if(ve.getVe_id() != null){
			String harborName = GetProperty.getValue("harborName");
			eleVeId.addText(harborName + "/" +ve.getVe_id());
		}

		Element eleName = root.addElement("ve:name");
		if(ve.getVe_name() != null){
			eleName.addText(ve.getVe_name());
		}
		Element eleAtom = root.addElement("ve:atom");
		if(ve.getVe_atom() != null){
			eleAtom.addText(ve.getVe_atom());
		}
		Element eleTplId = root.addElement("ve:VETemplateId");
		if(ve.getTemplate_id() != null){
			eleTplId.addText(ve.getTemplate_id());
		}
		Element eleKey = root.addElement("ve:key");
		if(ve.getVe_key() != null){
			eleKey.addText(ve.getVe_key());
		}

		Element eleState = root.addElement("ve:state");
		if(ve.getVe_state() != null){
			eleState.addText(ve.getVe_state());
		}

		Element elePrivacy = root.addElement("ve:privacy");
		if(ve.getVe_privacy() != null){
			elePrivacy.addText(ve.getVe_privacy());
		}

		Element eleGeolocation = root.addElement("ve:geolocation");

		Element eleCoordinate = eleGeolocation.addElement("ve:coordinate");
		Element eleLongitude = eleCoordinate.addElement("ve:longitude");
		eleLongitude.addText(String.valueOf(ve.getGeo().getLongitude()));
		Element eleLatitude = eleCoordinate.addElement("ve:latitude");
		eleLatitude.addText(String.valueOf(ve.getGeo().getLatitude()));
		Element eleAltitude = eleCoordinate.addElement("ve:altitude");
		eleAltitude.addText(String.valueOf(ve.getGeo().getAltitude()));

		Element eleRegionalization = eleGeolocation.addElement("ve:regionalization");
		Element eleCountry = eleRegionalization.addElement("ve:country");
		eleCountry.addText(String.valueOf(ve.getGeo().getCountry_id()));
		Element eleProvince = eleRegionalization.addElement("ve:province");
		eleProvince.addText(String.valueOf(ve.getGeo().getProvince_id()));
		Element eleCity = eleRegionalization.addElement("ve:city");
		eleCity.addText(String.valueOf(ve.getGeo().getCity_id()));
		Element eleother = eleRegionalization.addElement("ve:other");
		if(ve.getGeo().getOtherInfo() != null){
			eleother.addText(ve.getGeo().getOtherInfo());
		}
		Element elecorrespondTo = root.addElement("ve:correspondTo");
		String ve_id = ve.getVe_id();
		VePeBindService bs = (VePeBindService) GetBean.getBean("bindService");
		//拼装ve_id给VePeBind对象  query出ve绑定的pe列表
		VePeBind vepebind = new VePeBind();
		vepebind.setVe_id(ve_id);
		//List<Map<String, Object>> rList = bs.getBindInfoByVEID(ve_id);
		List<VePeBind> rList = bs.query(vepebind, 0, bs.queryCOUNTNUM(vepebind));
		if(rList.size()==0){
			Element elePe = elecorrespondTo.addElement("ve:pe");
			Element eleCoid = elePe.addElement("pe:bindId");
			eleCoid.addText("");
			Element eleModel = elePe.addElement("pe:model");
			eleModel.addText("");
			Element elePeID = elePe.addElement("pe:peID");
			elePeID.addText("");
		}else{
			for(int i=0;i<rList.size();i++){
				String pe_id = rList.get(i).getPe_id();
				Pe pe = new Pe();
				pe.setPeId(pe_id);
				pe.setPeLifecycle(-1);
				pe.setPeState(-1);
				pe.setPeTime(-1);
				PEService ps = (PEService) GetBean.getBean("peService");
				
				List<Map<String,Object>> list1 =  ps.query(pe, 0, 1);
				if(list1.size()==0){
					continue;
				}
				Map<String, Object>peMap =  list1.get(0);
				Element elePe = elecorrespondTo.addElement("ve:pe");
				Element eleCoid = elePe.addElement("pe:bindId");
				eleCoid.addText(rList.get(i).getBind_id());
				Element eleModel = elePe.addElement("pe:model");
				eleModel.addText(peMap.get("template_id").toString());
				Element elePeID = elePe.addElement("pe:peID");
				elePeID.addText(pe_id);
			}
		}

		//elePeID.addText(ve.getVe_PEID());
		Element elecontain = root.addElement("ve:contain");
		elecontain.addText("");
		//拼装veservice表
		Element eleService = root.addElement("ve:services");
		eleService.setAttributeValue("url", websocketUrl);
		VEWebsocketServiceService vesps = (VEWebsocketServiceService) GetBean.getBean("veWebsocketServiceService");
		VeKeyConfigService vkcs = (VeKeyConfigService) GetBean.getBean("veKeyConfigService");
		//List<Map<String, Object>> serviceList = vesps.getServiceByTplId(template_id);
		VeWebsocketService veWebsocketService = new VeWebsocketService();
		veWebsocketService.setTemplate_id(template_id);
		List<VeWebsocketService> serviceList = vesps.query(veWebsocketService, 0, vesps.queryCOUNTNUM(veWebsocketService));
		if(serviceList.size()==0){
			eleService.addText("");
		}else{
			for(int i=0;i<serviceList.size();i++){
				Element eleItem = eleService.addElement("service:item");
				Element eleServiceID = eleItem.addElement("service:serviceID");
				if(serviceList.get(i).getService_id() != null){
					eleServiceID.addText(serviceList.get(i).getService_id());

					if(m==1){
						Element eleServiceIDkey = eleItem.addElement("service:serviceIDkey");

						VeKeyConfig vk = new VeKeyConfig();
						vk.setVe_id(ve_id);
						vk.setService_id(serviceList.get(i).getService_id());
						VeKeyConfig a = vkcs.query(vk, 0, 1000).get(0);
						eleServiceIDkey.addText(a.getBase_key());
					}else{
						// do nothing
					}
				}

				Element eleCommand = eleItem.addElement("service:serviceName");
				if(serviceList.get(i).getService_name() != null){
					eleCommand.addText(serviceList.get(i).getService_name());
				}
				Element eleParams = eleItem.addElement("service:params");
				VEWebsocketServiceParamService vesparams = (VEWebsocketServiceParamService) GetBean.getBean("veWebsocketServiceParamService");
				VEWebsocketServiceReturnParamService vesrparams = (VEWebsocketServiceReturnParamService) GetBean.getBean("veWebsocketServiceReturnParamService");
				VeWebsocketServiceParam veWebsocketServiceParam = new VeWebsocketServiceParam();
				VeWebsocketServiceReturnParam veWebsocketServiceReturnParam = new VeWebsocketServiceReturnParam();
				veWebsocketServiceParam.setService_id(serviceList.get(i).getService_id());
				List<VeWebsocketServiceParam> paramList = vesparams.query(veWebsocketServiceParam, 0, vesparams.queryCOUNTNUM(veWebsocketServiceParam));
				List<VeWebsocketServiceReturnParam> returnParamList = vesrparams.query(veWebsocketServiceReturnParam, 0, vesrparams.queryCOUNTNUM(veWebsocketServiceReturnParam));
				if(paramList==null||paramList.size()==0){
					eleParams.addText("");
				}else{
					for(int j=0;j<paramList.size();j++){
						Element eleParam = eleParams.addElement("service:param");
						Element eleParamType = eleParam.addElement("param:type");
						eleParamType.addText(paramList.get(j).getParam_type());
						Element eleParamName = eleParam.addElement("param:name");
						eleParamName.addText(paramList.get(j).getParam_name());
						String values = paramList.get(j).getParam_value();
						if(values!=null&&values!=""&&values!="null"){
							Element eleParamValues = eleParam.addElement("param:values");
							String temp [] = values.split(";");
							for(String a : temp){
								Element eleParamValue = eleParamValues.addElement("param:value");
								eleParamValue.addText(a);
							}
						}
					}

				}
				if(returnParamList.size()==0||returnParamList==null){
					//do nothing
				}else{
					for(int j=0;j<returnParamList.size();j++){
						Element eleReturnParam = eleParams.addElement("service:returnParam");
						Element eleReturnParamType = eleReturnParam.addElement("returnParam:type");
						eleReturnParamType.addText(returnParamList.get(j).getReturnParam_type());
						Element eleReturnParamName = eleReturnParam.addElement("returnParam:name");
						eleReturnParamName.addText(returnParamList.get(j).getReturnParam_name());
						Element eleReturnParamDescription = eleReturnParam.addElement("returnParam:description");
						eleReturnParamDescription.addText(returnParamList.get(j).getReturnParam_description());
					}

				}
				Element eleDescription = eleItem.addElement("service:description");
				
				eleDescription.addText(serviceList.get(i).getService_description()==null?"":serviceList.get(i).getService_description());
				/*Element eleResult = eleItem.addElement("ve:result");
				Element eleResultType = eleResult.addElement("ve:type");
				eleResultType.addText((serviceList.get(i).get("command_result_type")!=null)?serviceList.get(i).get("command_result_type").toString():"");
				Element eleDescription = eleItem.addElement("ve:description");
				if(serviceList.get(i).get("service_description") != null){
					eleDescription.addText(serviceList.get(i).get("service_description").toString());
				}*/

			}
		}

		Element eleDescription = root.addElement("ve:description");
		String description = String.valueOf(ve.getVe_description());
		if(description != null){
			//现在编码是UTF-8
			eleDescription.addText(description);
		}
		Element eleEnrollTime = root.addElement("ve:enrollTime");
		//String time = String.valueOf(ve.getVe_init_time());
		String time =GetFormatDate.getFormatDate(ve.getVe_init_time());
		if(time != null){
			eleEnrollTime.addText(time);
		}
		Element eleCreator = root.addElement("ve:creator");
		String creatorId = String.valueOf(ve.getVe_creator_id());
		if(creatorId != null){
			eleCreator.addText(creatorId);
		}
		return doc.asXML();
	 }else
	   return "the veid you write does not exist ";
	}
}
