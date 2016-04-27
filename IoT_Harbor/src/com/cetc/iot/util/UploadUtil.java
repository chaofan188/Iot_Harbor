package com.cetc.iot.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cetc.iot.servicesystem.service.impl.AtomicRegister;

public class UploadUtil {
	
	private static Logger logger = Logger.getLogger(UploadUtil.class.getName());
	
	public static String upload(MultipartFile mFile,String path,String name){
		//��ȡ�ļ�����ļ���׺
		String fileName = mFile.getOriginalFilename();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		String uploadUrl;
		
		File picFile = new File(path);
		if(!picFile.exists()){
			picFile.mkdir();
		}
		if("xml".equals(fileType)){
			uploadUrl = path + new Date().getTime()+ name +fileType;
		}else{
			uploadUrl = path + fileName;
		}
		logger.info("uploadUrl: "+uploadUrl);
		File uploadFile = new File(uploadUrl);
		try {
			if(!uploadFile.exists()){
				FileCopyUtils.copy(mFile.getBytes(), uploadFile);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uploadUrl;
	}
}
