package com.cetc.iot.harbormanage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cetc.iot.database.dao.UserDao;
import com.cetc.iot.database.model.User;
import com.cetc.iot.harbormanage.service.UserInfo;

@Controller
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class.getName());
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS"); 
	
	@Autowired
	private UserDao userDao;	
	private static UserInfo userInfo = new UserInfo();
	@RequestMapping(value = "/userLogin.html",method = RequestMethod.POST)
	@ResponseBody
	public String userLogin(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String ip = request.getRemoteAddr();
		logger.info("Login IP: "+ip);
	    boolean result = userDao.loginResult(userName, password);
		if (result){
			logger.info("password ok! ");
			userInfo.add(userName,userDao.query(userName));
			if ("admin".equals(userName)){
				response.setStatus(302);
				response.addHeader("location", "/IoT_Harbor/jsp/Module2/indexAdmin.jsp?userName="+userName);
			}
			else {
				response.setStatus(302);
				response.addHeader("location", "/IoT_Harbor/jsp/Module2/index.jsp?userName="+userName);
			}
			return null;
		}
		else {
			response.setStatus(302);
			response.addHeader("location", "/IoT_Harbor/jsp/Module2/login_error.jsp");
			return null;
		}
	}
	
	@RequestMapping(value = "/userRegister.html",method = RequestMethod.POST)
	public String userRegister(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String re_password = request.getParameter("re-password");
		String ip = request.getRemoteAddr();
		logger.info("Register IP: "+ip);
		if (password == null || "".equalsIgnoreCase(password)){
			response.setStatus(302);
			response.addHeader("location", "/IoT_Harbor/jsp/Module2/register_error.jsp");
			return null;
		}
		else if (password.equals(re_password)){
			User user = new User();
			user.setUserName(userName);
			user.setUserPassword(password);
			user.setState("正常");
			user.setRegisterTime(FORMAT.format(new Date()));
			String result = userDao.add(user);
			logger.info("result: "+result);
			if ("success".equalsIgnoreCase(result)){
				response.setStatus(302);
				response.addHeader("location", "/IoT_Harbor/jsp/Module2/register_ok.jsp");
				return null;
			}
			else {
				response.setStatus(302);
				response.addHeader("location", "/IoT_Harbor/jsp/Module2/register_error.jsp");
				return null;
			}
		}else {
			response.setStatus(302);
			response.addHeader("location", "/IoT_Harbor/jsp/Module2/register_error.jsp");
			return null;
		}
	}
	
	@RequestMapping(value = "/userInfoChange.html",method = RequestMethod.GET)
	public String userInfoChange(HttpServletRequest request,HttpServletResponse response){
		logger.info("user info change request! ");
		String userName = request.getParameter("username");
		
		User checkUser = userInfo.getUserInfo(userName);
		if (checkUser == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		
		String email = request.getParameter("email");
		String realname = request.getParameter("realname");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		User user = userDao.query(userName);
		user.setUserRealName(realname);
		user.setEmail(email);
		user.setTel(tel);
		user.setAddress(address);
		String result = userDao.update(user);
		if ("success".equalsIgnoreCase(result)){
			userInfo.update(userName, user);
			response.setStatus(302);
			response.addHeader("location", "/IoT_Harbor/jsp/Module2/user_info_change_ok.jsp?userName="+userName+"&email="+email+"&realname="+realname+"&tel="+tel+"&address="+address);
		}
		else {
			response.setStatus(302);
			response.addHeader("location", "/IoT_Harbor/jsp/Module2/user_info_change_error.jsp?userName="+userName);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/passwordChange.html",method = RequestMethod.GET)
	public String passwordChange(HttpServletRequest request,HttpServletResponse response){
		logger.info("password change request! ");
		String userName = request.getParameter("username");
		
		User checkUser = userInfo.getUserInfo(userName);
		if (checkUser == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		
		String password = request.getParameter("password");
		String re_password = request.getParameter("re-password");
		if (password == null || "".equalsIgnoreCase(password)){
			response.setStatus(302);
			response.addHeader("location", "/IoT_Harbor/jsp/Module2/PasswordChanger.jsp?userName="+userName);
			return null;
		}
		else if (password.equals(re_password)){
			User user = userDao.query(userName);
			user.setUserPassword(password);
			String result = userDao.update(user);
			if ("success".equalsIgnoreCase(result)){
				userInfo.update(userName, user);
				response.setStatus(302);
				response.addHeader("location", "/IoT_Harbor/jsp/Module2/password_change_ok.jsp?userName="+userName);
				return null;
			}
			else {
				response.setStatus(302);
				response.addHeader("location", "/IoT_Harbor/jsp/Module2/password_change_error.jsp?userName="+userName);
				return null;
			}
		} else {
			response.setStatus(302);
			response.addHeader("location", "/IoT_Harbor/jsp/Module2/password_change_error.jsp?userName="+userName);
			return null;
		}
	}
	
	@RequestMapping(value = "/openUser.html", method = RequestMethod.GET)
	public String openUser(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("userName");
		
		User checkUser = userInfo.getUserInfo(userName);
		if (checkUser == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		
		
		User user = userDao.query(userName);
		user.setState("正常");
		String result = userDao.update(user);
		if ("success".equalsIgnoreCase(result)){
			userInfo.update(userName, user);
		}
		response.setStatus(302);
		response.addHeader("location", "/IoT_Harbor/jsp/Module2/UserManager.jsp?userName="+userName);
		return null;
	}
	
	
	@RequestMapping(value = "/closeUser.html", method = RequestMethod.GET)
	public String closeUser(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("userName");
		
		
		User checkUser = userInfo.getUserInfo(userName);
		if (checkUser == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		
		
		User user = userDao.query(userName);
		user.setState("冻结");
		String result = userDao.update(user);
		if ("success".equalsIgnoreCase(result)){
			userInfo.update(userName, user);
		}
		response.setStatus(302);
		response.addHeader("location", "/IoT_Harbor/jsp/Module2/UserManager.jsp?userName="+userName);
		return null;
	}
	
	@RequestMapping(value = "/userState.html", method = RequestMethod.GET)
	public ModelAndView userState(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("userName");
		
		
		User checkUser = userInfo.getUserInfo(userName);
		if (checkUser == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		
		
		User user = userDao.query(userName);
		user.setState("冻结");
		String result = userDao.update(user);
		if ("success".equalsIgnoreCase(result)){
			userInfo.update(userName, user);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("userState", user.getState());
		mav.addObject("userName", userName);
		mav.setViewName("/Module2/UserManager2");
		return mav;
	}
	
	@RequestMapping(value = "/logout.html" , method = RequestMethod.GET)
	public ModelAndView userLogout(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("userName");
		logger.info("user "+userName+" logout");
		userInfo.delete(userName);
		response.setStatus(302);
		response.addHeader("location", "/IoT_Harbor/harbor_login.jsp");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("../harbor_login");
		return mav;
	}
	
	@RequestMapping(value = "/queryAllUser.html" , method = RequestMethod.POST)
	@ResponseBody
	public String queryAllUser(HttpServletRequest request, HttpServletResponse response){
		User checkUser = userInfo.getUserInfo("admin");
		if (checkUser == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		
		List<User> list = userDao.queryAll();
		JSONArray jsonObject = JSONArray.fromObject(list);
		System.out.println(jsonObject);
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "/adminManageOpen.html" , method = RequestMethod.GET)
	public String adminManageOpen(HttpServletRequest request,HttpServletResponse response){
		
		User checkUser = userInfo.getUserInfo("admin");
		if (checkUser == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		
		String userName = request.getParameter("userName");
		User user = userDao.query(userName);
		user.setState("正常");
		String result = userDao.update(user);
		if ("success".equalsIgnoreCase(result)){
		}
		else {
			System.out.println("Database Error: User Info Change Error! ");
		}
		response.setStatus(302);
		response.addHeader("location", "/IoT_Harbor/jsp/Module2/AdminUserManager.jsp");
		return null;
	}
	
	@RequestMapping(value = "/adminManageClose.html" , method = RequestMethod.GET)
	public String adminManageClose(HttpServletRequest request,HttpServletResponse response){
		
		User checkUser = userInfo.getUserInfo("admin");
		if (checkUser == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		
		String userName = request.getParameter("userName");
		User user = userDao.query(userName);
		user.setState("冻结");
		String result = userDao.update(user);
		if ("success".equalsIgnoreCase(result)){
		}
		else {
			System.out.println("Database Error: User Info Change Error! ");
		}
		response.setStatus(302);
		response.addHeader("location", "/IoT_Harbor/jsp/Module2/AdminUserManager.jsp");
		return null;
	}
	
	@RequestMapping(value = "/getUserInfo.html" , method = RequestMethod.GET)
	public String getUserInfo(HttpServletRequest request, HttpServletResponse response){
		System.out.println("query user info! ");
		String userName = request.getParameter("userName");
		User user = userInfo.getUserInfo(userName);
		if (user == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		
		response.setStatus(302);
		response.addHeader("location", "/IoT_Harbor/jsp/Module2/UserInformationChanger.jsp?userName="+userName);
		
		return null;
	}
	
	@RequestMapping(value = "/getUserPasswordInfo.html" , method = RequestMethod.GET)
	public String getUserPasswordInfo(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		User user = userInfo.getUserInfo(userName);
		if (user == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		response.setStatus(302);
		response.addHeader("location", "/IoT_Harbor/jsp/Module2/PasswordChanger.jsp?userName="+userName);
		return null;
	}
	
	@RequestMapping(value = "/getUserManageInfo.html" , method = RequestMethod.GET)
	public String getUserManageInfo(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		User user = userInfo.getUserInfo(userName);
		if (user == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		response.setStatus(302);
		response.addHeader("location", "/IoT_Harbor/jsp/Module2/UserManager.jsp?userName="+userName);
		return null;
	}
	
	@RequestMapping(value = "/getAdminUserManageInfo.html" , method = RequestMethod.GET)
	public String getAdminUserManageInfo(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		User user = userInfo.getUserInfo(userName);
		if (user == null){
			response.setStatus(302);
			response.addHeader("location","/IoT_Harbor/jsp/Module2/timeout.jsp");
			return null;
		}
		response.setStatus(302);
		response.addHeader("location", "/IoT_Harbor/jsp/Module2/AdminUserManager.jsp?userName="+userName);
		return null;
	}
	
//	@RequestMapping(value = "/sendResetCommand.html" , method = RequestMethod.GET)
//	public String sendResetCommand(HttpServletRequest request, HttpServletResponse response){
//		System.out.println("Receive reset command!!!!!!!!!!!!!!!!!!!!  ");
//		String ip = request.getRemoteAddr();
//		System.out.println("Reset IP: "+ip);
//		String peID = "testcar";
//		PeController controller = null;
//		ControlChannel controlChannel = null;
//		controller = new PeControllerImpl(peID);
//		controlChannel = controller.getControlChannel();
//		JSONObject json = new JSONObject();
//		json.put("reset", "0");
//		controlChannel.control(json.toString(),"car_interface");
//		return null;
//	}
//	
}
