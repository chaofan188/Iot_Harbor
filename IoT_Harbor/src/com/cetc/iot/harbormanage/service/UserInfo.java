package com.cetc.iot.harbormanage.service;

import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.cetc.iot.database.model.User;



public class UserInfo {
	
	private static Logger logger = Logger.getLogger(UserInfo.class.getName());
	
	private static final int TIME_OUT_LIMIT = 30000000;
	
	private static Map<String, User> userInfoMap = new ConcurrentHashMap<String, User>();
	
	private static Map<String, Timer> userTimerMap = new ConcurrentHashMap<String, Timer>();
	
	private class OverTimeOperation extends java.util.TimerTask{
		private String userName;
		public OverTimeOperation(String userName){
			this.userName = userName;
		}
		public void run(){
			logger.info("userName: "+userName+" TIME_OUT! ");
			userInfoMap.remove(userName);
			userTimerMap.remove(userName);
		}
	}
	
	
	public User getUserInfo(String userName){
		User user = userInfoMap.get(userName);
		if (user != null){
			update(userName);
		}
		return user;
	}
	public void add(String userName,User user){
		Timer timer = new Timer();
		timer.schedule(new OverTimeOperation(userName), TIME_OUT_LIMIT);
		userTimerMap.put(userName, timer);
		userInfoMap.put(userName, user);
	}
	public void delete(String userName){
		userInfoMap.remove(userName);
		Timer timer = userTimerMap.get(userName);
		if (timer != null){
			timer.cancel();
		}
		userTimerMap.remove(userName);
		logger.info("Timer "+userName+" remove! ");
	}
	public void update(String userName,User user){
		userInfoMap.put(userName,user);
		Timer timer = userTimerMap.get(userName);
		if (timer != null){
			timer.cancel();
			timer = new Timer();
			timer.schedule(new OverTimeOperation(userName), TIME_OUT_LIMIT);
			userTimerMap.put(userName, timer);
		}
		else {
			logger.info("Timer for userName: "+userName+" not exists! Timer will be create! ");
			Timer reTimer = new Timer();
			reTimer.schedule(new OverTimeOperation(userName), TIME_OUT_LIMIT);
			userTimerMap.put(userName, reTimer);
		}
	}
	
	public void update(String userName){
		Timer timer = userTimerMap.get(userName);
		if (timer != null){
			timer.cancel();
			timer = new Timer();
			timer.schedule(new OverTimeOperation(userName), TIME_OUT_LIMIT);
			userTimerMap.put(userName, timer);
		}
		else {
			logger.info("Timer for userName: "+userName+" not exists! Timer will be create! ");
			Timer reTimer = new Timer();
			reTimer.schedule(new OverTimeOperation(userName), TIME_OUT_LIMIT);
			userTimerMap.put(userName, reTimer);
		}
	}
}
