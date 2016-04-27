package com.cetc.iot.servicesystem.service;

import java.util.ArrayList;

/**
 * @author nci_my
 *主题，ve定义主题，供用户订阅
 */
public class Topic {
	private ArrayList<Subor> subors;
	
	public void addSubor(Subor s){
		subors.add(s);
	}
	public void delSubor(Subor s){
		subors.remove(s);
	}
	public void notifySubors(){
		for(int i = 0; i < subors.size(); i++){
			//下行的本地调用将被远程调用方式替代
			subors.get(i).receive(this, null);
		}
	}
	public void notifySubors(Object arg){
		for(int i = 0; i < subors.size(); i++){
			//下行的本地调用将被远程调用方式替代
			subors.get(i).receive(this, arg);
		}
	}
	public boolean hasChanged(){
		return false;
	}
}
