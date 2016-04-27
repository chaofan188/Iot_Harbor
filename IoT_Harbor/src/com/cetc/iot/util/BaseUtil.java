package com.cetc.iot.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BaseUtil {

	public static Boolean isNotEmpty(String str){
		if(str == null||"".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	
	public static Boolean isEmpty(String str){
		if(str == null||"".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	
//	public static void getLocalIP1(){
//		try {
//			Enumeration<NetworkInterface> e1 = NetworkInterface.getNetworkInterfaces();
//			while(e1.hasMoreElements()){
//				NetworkInterface ni = (NetworkInterface) e1.nextElement();
//				Enumeration<?> e2= ni.getInetAddresses();
//				System.out.println(ni.getName());
//				System.out.println(":");
//				while(e2.hasMoreElements()){
//					InetAddress ia = (InetAddress) e2.nextElement();
//					if(ia instanceof Inet6Address){
//						System.out.println("����Ip��"+ ia.getHostAddress());
//						continue;
//					}if(e2.hasMoreElements()){
//						System.out.println(",");
//					}
//				}
//				System.out.println("\n");
//			}
//		} catch (SocketException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static String getLocalIP(){
		try {
			String ip = null;
			Enumeration<NetworkInterface> e1 = NetworkInterface.getNetworkInterfaces();
			while(e1.hasMoreElements()){
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				Enumeration<?> e2= ni.getInetAddresses();
				while(e2.hasMoreElements()){
					InetAddress ia = (InetAddress) e2.nextElement();
					if(ia!=null&&ia instanceof Inet4Address&&!ia.getHostAddress().equals("127.0.0.1")){
						ip = ia.getHostAddress();
					}
				}
			}
			return ip;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
	}
	
	public static Map<String, Object> transBean2Map(Object obj) {  
		  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
  
            }  
        } catch (Exception e) {  
        	e.printStackTrace();
        }  
  
        return map;  
  
    }
	
	public static Map<String, Object> compareMap(Map<String, Object> map, Object obj) {
        Map<String, Object> mapValue = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (map.containsKey(name)) {
                mapValue.put(name, map.get(name));
            }
        }
        return mapValue;
    }
	
	public static void main(String[] args) {
		BaseUtil b = new BaseUtil();
		String a = b.getLocalIP();
		System.out.println("==="+a);
		
	}
}
