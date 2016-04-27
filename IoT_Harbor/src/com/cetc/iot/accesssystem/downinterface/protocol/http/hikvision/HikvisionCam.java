package com.cetc.iot.accesssystem.downinterface.protocol.http.hikvision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.cetc.iot.servicesystem.util.GetProperty;

public class HikvisionCam {
	
	private String hikvisionHostIP=GetProperty.getValue("hikvisionHostIP");
	private String hikvisionCamIP=GetProperty.getValue("hikvisionCamIP");
	private String url = "http://"+hikvisionHostIP+":2002/event?CAM="+hikvisionCamIP+"&CMD=";
	/**
	 * @param command可以是zoom_up/zoom_down：缩放
      focus_up/focus_down：聚焦
      iris_up/iris_down：光圈
      view_up/view_down/view_left/view_right
      view_up_left/view_down_left/view_up_right/view_down_right
      set_speed/set_delay：设置速度和控制时间，默认值为：60和300ms
	 */
	public String controlByGet(String command,String param){
		String urlNameString =url+command+"&Param="+param;
		StringBuffer resultBuffer =new StringBuffer();
		BufferedReader in = null;
		try {
			URL realUrl = new URL(urlNameString);
			URLConnection connection =realUrl.openConnection();
			HttpURLConnection httpurlconnection =(HttpURLConnection) connection;
			httpurlconnection.setRequestProperty("Accept-charset", "utf-8" );
			httpurlconnection.setRequestProperty("content-Type", "application/x-www-form-urlencoded");
			/*Map<String,List<String>> map = connection.getHeaderFields();
			for(String key:map.keySet()){
				System.out.println(key+"--->"+map.get(key));
			}*/
			in = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
			/*String line;
			while((line = in.readLine())!=null){
				result +=line;
			}*/
			
			String tempLine=null;
			
			while((tempLine=in.readLine())!=null){
				resultBuffer.append(tempLine);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(in!=null){
					in.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return resultBuffer.toString();
	}
	public String controlByGet(String command){
		StringBuffer resultBuffer =new StringBuffer();
		BufferedReader in = null;
		String urlNameString =url+command;
		try {
			URL realUrl = new URL(urlNameString);
			URLConnection connection =realUrl.openConnection();
			HttpURLConnection httpurlconnection =(HttpURLConnection) connection;
			httpurlconnection.setRequestProperty("Accept-charset", "utf-8" );
			httpurlconnection.setRequestProperty("content-Type", "application/x-www-form-urlencoded");
			/*Map<String,List<String>> map = connection.getHeaderFields();
			for(String key:map.keySet()){
				System.out.println(key+"--->"+map.get(key));
			}*/
			in = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
			/*String line;
			while((line = in.readLine())!=null){
				result +=line;
			}*/
			
			String tempLine=null;
			
			while((tempLine=in.readLine())!=null){
				resultBuffer.append(tempLine);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(in!=null){
					in.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return resultBuffer.toString();
	
	}
	
	public static void main(String[] args) {
		HikvisionCam hi = new HikvisionCam();
		System.out.println(hi.controlByGet("zoom_up"));
		
		//hi.controlByGet("set_speed","60");
	}

}
