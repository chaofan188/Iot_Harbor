package com.cetc.iot.harbormanage.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import edu.emory.mathcs.backport.java.util.Arrays;

public class PublicControllerTest {
	
	private static Logger logger = Logger.getLogger(PublicControllerTest.class.getName());


	protected Map<String, Object> paramsInit(String method,
			Map<String, Object> paramsMap,String param_key,String param_secret) {

		Map<String, Object> map = new HashMap<String, Object>();
		long time = System.currentTimeMillis() / 1000;

       
		String key = param_key;
		
	    String secret = param_secret;
        
	   
	    //at.1hj2ot2e9p6lg0cu0n0caful9yqik5rr-6ex0j7z90i-00801g5-pdwx4iz8n
		StringBuilder paramString = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();) {
			String key1 = it.next();
			String param = key1 + ":" + paramsMap.get(key1);
			paramList.add(param);
		}
		String[] params = paramList.toArray(new String[paramList.size()]);
		//对参数进行排�?
		Arrays.sort(params);
		for (String param : params) {
			paramString.append(param).append(",");
		}
        		
		paramString.append("method").append(":").append(method).append(",");
		paramString.append("time").append(":").append(time).append(",");
		paramString.append("secret").append(":").append(secret);
		logger.info(paramString.toString().trim());

		String sign = DigestUtils.md5Hex(paramString.toString().trim());
        //system参数\\
		Map<String, Object> systemMap = new HashMap<String, Object>();
		systemMap.put("ver", "1.0");
		systemMap.put("sign", sign);
		systemMap.put("key", key);
		systemMap.put("time", time);
        //map参数
		map.put("system", systemMap);
		map.put("method", method);
		map.put("params", paramsMap);
		map.put("id", "123456");
      
		return map;
	}

	protected String doPost(Map<String, Object> map) {
		String json = JSON.toJSONString(map);
		ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
		Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		HttpClient client = new HttpClient();
		String result = null;
		PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");
		
		try {
			RequestEntity entity = new StringRequestEntity(json,"application/json", "UTF-8");
			method.setRequestEntity(entity);
			client.executeMethod(method);
      
	        
			InputStream inputStream = method.getResponseBodyAsStream();
			result = IOUtils.toString(inputStream);
			logger.info("result: "+result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		 return result;
	}
   
}
