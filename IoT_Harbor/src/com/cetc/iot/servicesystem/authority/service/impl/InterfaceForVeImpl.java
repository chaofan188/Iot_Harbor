package com.cetc.iot.servicesystem.authority.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetc.iot.database.model.TemplateBind;
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VePeBind;
import com.cetc.iot.database.model.VePeBindInterface;
import com.cetc.iot.database.model.VeWebsocketService;
import com.cetc.iot.servicesystem.authority.model.VeInterfaceRelation;
import com.cetc.iot.servicesystem.authority.service.InterfaceForVE;
import com.cetc.iot.servicesystem.service.TemplateBindService;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.VEWebsocketServiceService;
import com.cetc.iot.servicesystem.service.VePeBindInterfaceService;
import com.cetc.iot.servicesystem.service.VePeBindService;

/**
 * 
 * @ClassName: IntercaceForVeImpl 
 * @Description: InterfaceForVE的实现类
 * @author ZhangYong
 * @date 2016年4月17日 上午10:55:05
 */
@Service("interfaceForVeImpl")
public class InterfaceForVeImpl implements InterfaceForVE {
	
	@Autowired
	private VEService veService;
	
	@Autowired
	private VePeBindService vePeBindService;
	
	@Autowired
	private TemplateBindService templateBindService;
	
	@Autowired
	private VePeBindInterfaceService bindInterfaceService;
	
	@Autowired
	private VEWebsocketServiceService websocketServiceService;
	
	
	@Override
	public VeInterfaceRelation getVeInterfaceRelationByVeID(String veId) {
		
		VeInterfaceRelation veInterfaceRelation = new VeInterfaceRelation();
		Map<String, Map<String, String>> serviceIdsMap = new HashMap<String, Map<String, String>>();
		Map<String, String> peIdInterfaceMap= new HashMap<String,String>();
		
		VE ve = veService.getVeByVeId(veId);
		if(ve == null){
			return null;
		}
		veInterfaceRelation.setVeId(veId);
		veInterfaceRelation.setIsAtomic(ve.getVe_atom());
		
		//1.根据veId查询该ve使用的veTemplate
		String veTemplateId = ve.getTemplate_id();
		
		
		//如果是复合VE
		if(ve.getVe_atom().equals("0")){
			
			//2.根据veTemplate确定serviceId
			VeWebsocketService websocketService = new VeWebsocketService();
			websocketService.setTemplate_id(veTemplateId);
			List<VeWebsocketService> serviceList = websocketServiceService.query(websocketService, 0, 100);
			
			if(serviceList.size()>=1){
				for(int i=0;i<serviceList.size();i++){
					//3.根据veTemplateId,serviceId确定veTemplate所绑定的peTemplate
					String serviceId = serviceList.get(i).getService_id();
					TemplateBind templateBind = new TemplateBind();
					templateBind.setVe_template_id(veTemplateId);
					templateBind.setService_id(serviceId);
					List<TemplateBind> listBind = templateBindService.query(templateBind, 0, 100);
					for(int j=0;j<listBind.size();j++){
						//4.根据bindId确定interfaceId
						TemplateBind bind = listBind.get(j);
						VePeBindInterface bindInterface = new VePeBindInterface();
						bindInterface.setBindId(bind.getTemplate_bind_id());
						List<Map<String, Object>> list = bindInterfaceService.query(bindInterface);
						String interfaceId = "";
						if(list.size()>=1){
							interfaceId = list.get(0).get("interface_id").toString();
						}
						List<Map<String, Object>> queryPeList = bindInterfaceService.queryPeList(veId, serviceId, bind.getPe_template_id());
						if(queryPeList!=null){
							for(int k = 0;k<queryPeList.size();k++){
								peIdInterfaceMap.put(queryPeList.get(k).get("pe_id").toString(),interfaceId);
							}
						}
					}
					serviceIdsMap.put(serviceId, peIdInterfaceMap);
				}
			}
			veInterfaceRelation.setServiceIdsMap(serviceIdsMap);
			//peIdInterfaceMap.clear();
			//serviceIdsMap.clear();
			/*//3.根据每一个模板的绑定bind_id，查询该绑定关系对应的interfaceId
			for(int i = 0;i<listBind.size();i++){
				//根据serviceId，veId查询ve,pe绑定表，查询该服务下对应的pe,pe可能有多个
				VePeBind vePeBind = new VePeBind();
				vePeBind.setVe_id(veId);
				vePeBind.setService_id(bind.getService_id());
				List<VePeBind> peList = vePeBindService.query(vePeBind, 0, 100);
				
				if(serviceIdsMap.containsKey(bind.getService_id())){
					peIdInterfaceMap = serviceIdsMap.get(bind.getService_id());
				}
				for(int j = 0;j<peList.size();j++){
					//每个pe对应具体的interfaceId
					peIdInterfaceMap.put(peList.get(j).getPe_id(), interfaceId);
				}
				
				serviceIdsMap.put(bind.getService_id(), peIdInterfaceMap);
			}
			veInterfaceRelation.setServiceIdsMap(serviceIdsMap);*/
		}else if(ve.getVe_atom().equals("1")){	//如果是原子ve          原子ve，各个关系都是1对1的
			//1、根据veId查询ve、pe绑定表，得到serviceId和peId
			VePeBind vePeBind = new VePeBind();
			vePeBind.setVe_id(veId);
			List<VePeBind> list = vePeBindService.query(vePeBind, 0, 10);
			String peId = list.get(0).getPe_id();
			String serviceId = list.get(0).getService_id();
			
			//2、根据serviceId查询serviceName，通过serviceName解析出interfaceId
			VeWebsocketService veWebsocketService = new VeWebsocketService();
			veWebsocketService.setService_id(serviceId);
			List<VeWebsocketService> listService = websocketServiceService.query(veWebsocketService, 0, 10);
			String interfaceId = listService.get(0).getService_name().split("_")[0];
			peIdInterfaceMap.put(peId, interfaceId);
			serviceIdsMap.put(serviceId, peIdInterfaceMap);
			veInterfaceRelation.setServiceIdsMap(serviceIdsMap);
		}
		
		return veInterfaceRelation;
	}

}
