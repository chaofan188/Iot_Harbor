package com.cetc.iot.util;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.servicesystem.service.impl.AtomicRegister;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * this class is used for upper developer to get data 
 * @author xzc
 * Create Time: 2014-12-03
 * Author: xzc
 * Details: Add openData function for someone who wants the info.
 *          Add getDataAndFlush function for someone who wait to get the info.
 *          Add closeData function for someone who does not want the info.
 *          Add close function for websocket close.
 * 
 */
public class GetData {

	private static Logger logger = Logger.getLogger(GetData.class.getName());
	
	
	private String data;
	public void setData(String data){
		this.data = data;
	}
	private GetData(){
	}
	/**
	 * this Map is used to save the information between peID+"#"+webSocketID and getDataObject
	 */
	private static ConcurrentHashMap<String, GetData> peIDWebSocketIDDataObjectMap = new ConcurrentHashMap<String, GetData>();
	/**
	 * this multimap is used to keep the information between webSocketID and peID
	 */
	private static Multimap<String, String> webSocketIDPEIDMap = ArrayListMultimap.create();
	/**
	 * this lock is used to synchronized protect the multimap
	 */
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	
	
	/**
	 * this method is used for someone who wants the info
	 * @param peID
	 * @param webSocketID
	 */
	public static boolean openData(String peID, String webSocketID){
		System.out.println("New OpenData Operation for peID: "+peID+", webSocketID: "+webSocketID);
		//Check whether the webSocketID has already cared the peID
		//if it is the first time, then create the relation
		//if it is not the first time, then do nothing
		if (!peIDWebSocketIDDataObjectMap.containsKey(peID+"#"+webSocketID)){
			GetData getData = new GetData();
			UpperOperation.addPEIDGetDataObjectMap(peID, getData);
			peIDWebSocketIDDataObjectMap.put(peID+"#"+webSocketID, getData);
			lock.writeLock().lock();
			webSocketIDPEIDMap.put(webSocketID, peID);
			lock.writeLock().unlock();
			logger.info("New Relation Add for peID: "+peID + ", webSocketID: "+webSocketID);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * this method is used for someone who wait to get the info and then flush.
	 * if return null, should quit.
	 * @param peID
	 * @param webSocketID
	 * @return the String someone will get
	 * @throws FileNotFoundException 
	 */
	public static String getDataAndFlush(String peID, String webSocketID)  {
		try {
			logger.info("New GetData Operation for peID: "+peID+", webSocketID: "+webSocketID);
			Object object = UpperOperation.getMutexObject(peID);
			try {
				synchronized (object) {
					object.wait();
			}
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			GetData getDataObject = peIDWebSocketIDDataObjectMap.get(peID+"#"+webSocketID);
			String dataString = getDataObject.data;
			getDataObject.data = null;
			return dataString;
		} catch (Exception e){
			logger.info("Null pointer exception! ");
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new FileWriter("E:\\exception.log",true));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace(pw);
			}
			pw.write(new Date().toString()+"\n");
			e.printStackTrace(pw);
			pw.write("PEID: "+peID+" WebSocketID: "+webSocketID+"\n");
			pw.flush();
			pw.close();
			return "null pointer exception!";
		}
	}
	
	
	/**
	 * this method is used for someone who does not want the info
	 * @param peID
	 * @param webSocketID
	 */
	public static void closeData(String peID, String webSocketID){
		logger.info("New CloseData Operation for peID: "+peID+", webSocketID: "+webSocketID);
		//Check whether the relation between peID and webSocketID exists
		//if exists, delete the relation
		//if does not exist, do nothing
		if (peIDWebSocketIDDataObjectMap.containsKey(peID+"#"+webSocketID)){
			UpperOperation.deletePEIDGetDataObjectMap(peID, peIDWebSocketIDDataObjectMap.get(peID+"#"+webSocketID));
			peIDWebSocketIDDataObjectMap.remove(peID+"#"+webSocketID);
			lock.writeLock().lock();
			webSocketIDPEIDMap.remove(webSocketID, peID);
			lock.writeLock().unlock();
			logger.info("New Relation Delete for peID: "+peID + ", webSocketID: "+webSocketID); 
		}
	}
	/**
	 * this method is used for websocket close
	 * @param webSocketID
	 */
	public static void close(String webSocketID){
		//Delete all the relation with this webSocketID
		logger.info("New Close Operation for webSocketID: "+webSocketID);
		lock.writeLock().lock();
		Collection<String> needDeletePEID = webSocketIDPEIDMap.get(webSocketID);
		for (String temp : needDeletePEID){
			UpperOperation.deletePEIDGetDataObjectMap(temp, peIDWebSocketIDDataObjectMap.get(temp+"#"+webSocketID));
			peIDWebSocketIDDataObjectMap.remove(temp+"#"+webSocketID);
			logger.info("New Relation Delete for peID: " + temp + ", webSocketID: "+webSocketID);
		}
		webSocketIDPEIDMap.removeAll(webSocketID);
		lock.writeLock().unlock();
		
	}
}
