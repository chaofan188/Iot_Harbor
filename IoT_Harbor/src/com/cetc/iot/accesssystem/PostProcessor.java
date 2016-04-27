package com.cetc.iot.accesssystem;

import org.apache.log4j.Logger;

import com.cetc.iot.harbormanage.pojo.InterfacePojo;
import com.cetc.iot.harbormanage.pojo.PEModelPojo;
import com.cetc.iot.harbormanage.pojo.ParamPojo;


/**
 * @author wanghan
 * 此类用于后处理PE模板文件，根据不同协议的需求进行处理
 */
public class PostProcessor {
	
	private static Logger logger = Logger.getLogger(PostProcessor.class.getName());
	
	public static PEModelPojo process(PEModelPojo peModelPojo){
		
		//TODO:Move these protocol logic depended code out of processor
		//ONVIF process
		InterfacePojo interfaces[] = peModelPojo.getInterfaces();
		
		for(InterfacePojo interfacePojo: interfaces){
			if(interfacePojo.getInterface_protocol().equalsIgnoreCase("Onvif")){
				logger.info("ONVIF protocol found on : "+interfacePojo.getInterfaceID());
				interfacePojo.setReturn_param(null);
				interfacePojo.setInterface_direction("bidirection");
				ParamPojo paramArray[] = new ParamPojo[1];
				paramArray[0] = new ParamPojo();
				paramArray[0].setKey("easy_control");
				paramArray[0].setType("enum");
				paramArray[0].setOption("left;right;up;down;zoomIn;zoomOut;getRTSPAddr");
				paramArray[0].setDescription("Standard onvif camera operation interface(Lite edition);Left:左转;right:右转;up:上转;down:下转;zoomIn:放大;zoomOut:缩小;getRTSPAddr:获取摄像头RTSP地址");
				interfacePojo.setParam(paramArray);
			}
			
			
			if(interfacePojo.getInterface_protocol().equalsIgnoreCase("ezviz")){
				logger.info("EZVIZ protocol found on: "+interfacePojo.getInterfaceID());
				interfacePojo.setReturn_param(null);
				interfacePojo.setInterface_direction("bidirection");
				ParamPojo paramArray[] = new ParamPojo[1];
				paramArray[0] = new ParamPojo();
				paramArray[0].setKey("ezviz_control");
				paramArray[0].setType("enum");
				paramArray[0].setOption("getM3u8Url");
				paramArray[0].setDescription("Standard ezviz camera operation interface(Lite edition)   ");
				interfacePojo.setParam(paramArray);
			}
		}
		return peModelPojo;
	}
}
