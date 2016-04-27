package com.cetc.iot.servicesystem.util;

public class GetPorjectPath {

	
	

	//获取当前项目的绝对路径
	public static String getPorjectPath(){
	 String projectName="IoT_Harbor\\WEB-INF\\classes";  
	 String compath = GetProperty.getValue("classpackage");
	 if(compath.contains("/")){
		 compath = compath.replace('/', '.');
	}
		compath = compath.replace('.', '\\');
	
	 
	 String tomcatpath;             //当前tomcat的bin目录的路径 如 D:\java\software\apache-tomcat-6.0.14\bin
	 String tempdir;
	 tomcatpath=System.getProperty("user.dir");
	 tempdir=tomcatpath.replace("bin", "webapps");  //把bin 文件夹变到 webapps文件里面 
	 tempdir+="\\"+projectName+"\\"+compath;  //拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro 
	 return tempdir;  
	}
}



