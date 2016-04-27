package com.cetc.iot.web;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cetc.iot.database.model.Pe;
import com.cetc.iot.harbormanage.service.PEService;


@Controller
public class PeRegistController {
	private Pe pe = new Pe();
	private String state = "success";
	@Autowired
	private PEService peService;
	
	@RequestMapping(value = "/state.html", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		Date date = new Date();
		UUID ID = UUID.randomUUID();
		String peId = ID.toString();
		/*String pe_id =(String)request.getParameter("pe_id");*/
		String peOwner =(String)request.getParameter("peOwner");
		String peName =(String)request.getParameter("peName");
		String peUser =(String)request.getParameter("peUser");
		String pePictureUrl = (String)request.getParameter("pePictureUrl");;
		String peKey =(String)request.getParameter("peKey");
		String peState_temp = (String)request.getParameter("peState");
		String peGeolocationId =(String)request.getParameter("peGeolocationId");;
	    String peLifecycle_temp = (String)request.getParameter("peLifecycle");;
		String peTime_temp = (String)request.getParameter("peTime");;
	    String templateId = (String)request.getParameter("templateId");
	    int peState = Integer.parseInt(peState_temp);
	    int peLifecycle = Integer.parseInt(peLifecycle_temp);
	    int peTime = Integer.parseInt(peTime_temp);

	    pe.setPeGeolocationId(peGeolocationId);
	    pe.setPeId(peId);
	    pe.setPeKey(peKey);
	    pe.setPeLastTime(date);
	    pe.setPeLifecycle(peLifecycle);
	    pe.setPeName(peName);
	    pe.setPeOwner(peOwner);
	    pe.setPePictureUrl(pePictureUrl);
	    pe.setPeState(peState);
	    pe.setPeTime(peTime);
	    pe.setPeUser(peUser);
	    pe.setTemplateId(templateId);
	
	    try {
			peService.add(pe);
		} catch (Exception e) {
			mav.setViewName("state");
			mav.addObject("state","fail");
			return mav;
		}
	  
	    mav.setViewName("state");
		mav.addObject("state","success");
		return mav;
	
	   
	}
}
