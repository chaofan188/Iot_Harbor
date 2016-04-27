package com.cetc.iot.harbormanage.controller;


  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
  

@Controller  
public class DownloadController {  
  
	private static Logger logger = Logger.getLogger(DownloadController.class.getName());
	
	
    @RequestMapping("download.html")  
    public void downloadFile(/*String fileName,*/HttpServletRequest request,HttpServletResponse response){  
        response.setCharacterEncoding("utf-8");  
        response.setContentType("multipart/form-data"); 
        logger.info("enter");
        String fileName = request.getParameter("fileName");
        response.setHeader("Content-Disposition", "attachment;fileName="+"pemodel_"+fileName+".txt");  
        try {  
        	String name = request.getParameter("name");
        	logger.info(name);
        	String path = request.getSession().getServletContext().getRealPath("");
        	path+=File.separator+name;
            InputStream inputStream=new FileInputStream(path);  
            OutputStream os=response.getOutputStream();  
            byte[] b=new byte[1024];  
            int length;  
            while((length=inputStream.read(b))>0){  
                os.write(b,0,length);  
            }  
            inputStream.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  
