package com.cetc.iot.accesssystem.downinterface.protocol.http;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import com.cetc.iot.database.model.PeParam;
import com.cetc.iot.database.model.ProtocolHttp;
import com.cetc.iot.harbormanage.service.PEParamService;
import com.cetc.iot.harbormanage.service.ProtocolHttpService;
import com.cetc.iot.servicesystem.util.GetBean;

public class HttpInitializer {

	private static Logger logger = Logger.getLogger(HttpInitializer.class.getName());
	
	public HttpInitializer(String peID, String interfaceID) {
		// TODO Auto-generated constructor stub
		ProtocolHttpService protocolHttpService = (ProtocolHttpService) GetBean
				.getBean("protocolHttpService");
		PEParamService paramService = (PEParamService) GetBean
				.getBean("peParamService");
		
		ProtocolHttp protocolHttp = new ProtocolHttp();
		protocolHttp.setId(-1);
		protocolHttp.setPe_id(peID);
		protocolHttp.setInterface_id(interfaceID);
		protocolHttp.setMethod(HttpToolkit.METHOD_GET);
		protocolHttp.setHttp_template_id(-1);
		List<Map<String, Object>> result = protocolHttpService
				.queryAll(protocolHttp);

		if (result.size() != 1) {
			logger.info("Error while creating http caching object! ");
			return;
		}

		HttpAdaptor adaptor = new HttpAdaptor();
		adaptor.rule = (String)result.get(0).get("rule");
		adaptor.method = (int)result.get(0).get("method");
		adaptor.peID = peID;
		adaptor.interfaceID = interfaceID;
		adaptor.url = (String)result.get(0).get("url");

		
		PeParam peParam = new PeParam();
		peParam.setPe_param_id(-1);
		peParam.setPe_id(peID);
		result = paramService.queryAll(peParam );
		
		Map<String, String> map = new HashedMap();
		for(Map<String, Object> item: result){
			String keyString = (String)item.get("pe_param_key");
			String valueString = (String)item.get("content");
			map.put(keyString, valueString);
		}
		
		adaptor.paramList = map;
		HttpAdaptor.httpList.put(peID + interfaceID, adaptor);
	}

}
