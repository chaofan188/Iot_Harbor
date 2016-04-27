package com.cetc.iot.harbormanage.controller;


import java.util.HashMap;
import java.util.Map;

public class M3u8UrlController extends PublicControllerTest {
    // 获取token范例入口
    
    public String getM3u8Url(String cameraName,String key,String secret) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("pageSize", "2");
		paramsMap.put("pageStart", "0");
        paramsMap.put("cameraName", cameraName);
        Map<String, Object> map = paramsInit("squareVideoListByUser", paramsMap,key,secret);
        String result = doPost(map);
        return result;
    }
 
}
