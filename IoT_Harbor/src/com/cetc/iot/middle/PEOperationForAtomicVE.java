package com.cetc.iot.middle;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.cetc.iot.accesssystem.downinterface.MsgOperation;
import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.communicate.PESession;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * this class is used for atomic ve to operate pe
 * @author xzc
 * Create Time: 2015-07-06
 * Author: xzc
 */
public class PEOperationForAtomicVE {
	
	private static Logger logger = Logger.getLogger(PEOperationForAtomicVE.class.getName());
	
	/**
	 * this map is used to keep the information between peID_interfaceID and the holding object
	 */
	private static Map<String,Object> peIDinterfaceID_ObjectMap = new ConcurrentHashMap<String, Object>();
	/**
	 * this map is used to keep the information between peID_interfaceID and the holding thread
	 */
	private static Map<String,Thread> sessionIDpeIDinterfaceID_ThreadMap = new ConcurrentHashMap<String, Thread>();
	/**
	 * this map is used to keep the information between the holding object and peID_interfaceID
	 */
	private static Map<Object,String> object_peIDInterfaceIDMap = new ConcurrentHashMap<Object, String>();
	/**
	 * this multimap is used to keep the information between object and sessionID
	 */
	private static Multimap<Object,String> object_sessionIDMap = ArrayListMultimap.create();
	/**
	 * this multimap is used to keep the information between sessionID and object
	 */
	private static Multimap<String, Object> sessionID_objectMap = ArrayListMultimap.create();
	/**
	 * this map is used to keep the information between object and data
	 */
	private static Map<Object, String> object_dataMap = new ConcurrentHashMap<Object, String>();
	/**
	 * this lock is used to protect the mulimap
	 */
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	/**
	 * this inner class is used to handle data from deive
	 * @author xzc
	 * Create Time: 2015-07-07
	 * Author: xzc
	 *
	 */
	public class GetDataFromDevice implements Runnable{
		private String peID;
		private String interfaceID;
		private String data;
		private PESession peSession;
		public GetDataFromDevice(String peID,String interfaceID,String data, PESession peSession){
			this.peID = peID;
			this.interfaceID = interfaceID;
			this.data = data;
			this.peSession = peSession;
		}
		public void run(){
			if (peID == null){
				logger.info("PEID null! ");
				return ;
			}
			if (interfaceID == null){
				logger.info("InterfaceID null! ");
				return ;
			}
			if (data == null){
				logger.info("Data null! ");
				return ;
			}
			if (peSession == null){
				logger.info("PeSession null! ");
				String peIDinterfaceID_ObjectMapKey = peID+"#"+interfaceID;
				if (peIDinterfaceID_ObjectMap.containsKey(peIDinterfaceID_ObjectMapKey)){
					Object object = peIDinterfaceID_ObjectMap.get(peIDinterfaceID_ObjectMapKey);
					object_dataMap.put(object, data);
					synchronized (object) {
						object.notifyAll();
					}
				}
				else {
					logger.info("Nobody cares the information: "+data);
				}
				return ;
			}
			logger.info("PeSession not null! ");
			peSession.setData(data);
			synchronized (peSession.getObject()) {
				peSession.getObject().notifyAll();
			}
		}
	}
	
	
	/**
	 * this method is used to start getting data
	 * @param sessionID ID for who wants to get data
	 * @param peID peID for which to get data
	 * @param interfaceID interfaceID for which to get data
	 * @return if success, return "success"; if fail, return "fail:reason"
	 */
	public synchronized static String openData(String sessionID, String peID, String interfaceID){
		
		logger.info("Open data! ");
		String peIDinterfaceID_ObjectMapKey = peID+"#"+interfaceID;
		//whether the peID_interfaceID is already listened
		if (peIDinterfaceID_ObjectMap.containsKey(peIDinterfaceID_ObjectMapKey)){
			Object object = peIDinterfaceID_ObjectMap.get(peIDinterfaceID_ObjectMapKey);
			lock.readLock().lock();
			//whether the peID_interfaceID is already listened by this sessionID
			if (object_sessionIDMap.containsEntry(object, sessionID)){
				lock.readLock().unlock();
				return "fail:already open";
			}
			lock.readLock().unlock();
			lock.writeLock().lock();
			object_sessionIDMap.put(object,sessionID);
			sessionID_objectMap.put(sessionID, object);
			lock.writeLock().unlock();
			return "success";
		}
		else {
			Object object = new Object();
			peIDinterfaceID_ObjectMap.put(peIDinterfaceID_ObjectMapKey, object);
			object_peIDInterfaceIDMap.put(object, peIDinterfaceID_ObjectMapKey);
			lock.writeLock().lock();
			object_sessionIDMap.put(object, sessionID);
			sessionID_objectMap.put(sessionID, object);
			lock.writeLock().unlock();
			return "success";
		}
	}
	/**
	 * this method is used to stop getting data
	 * @param sessionID ID for who wants to stop getting data
	 * @return
	 */
	public static void closeData(String sessionID){
		logger.info("Close Data by sessionID ");
		if (sessionID == null){
			return ;
		}
		lock.writeLock().lock();
		Collection<Object> collection = sessionID_objectMap.get(sessionID);
		Iterator<Object> iterator = collection.iterator();
		while (iterator.hasNext()){
			Object object = iterator.next();
			if (object_sessionIDMap.containsEntry(object, sessionID)){
				object_sessionIDMap.remove(object, sessionID);
				iterator.remove();
				if (object_sessionIDMap.containsKey(object)){
					//do nothing
				}
				else {
					object_dataMap.remove(object);
					if (object_peIDInterfaceIDMap.containsKey(object)){
						String peIDinterfaceID_ObjectMapKey = object_peIDInterfaceIDMap.get(object);
						peIDinterfaceID_ObjectMap.remove(peIDinterfaceID_ObjectMapKey);
					}
					object_peIDInterfaceIDMap.remove(object);
				}
			}
			else {
				logger.info("object already closed! ");
			}
		}
		Set<String> set = sessionIDpeIDinterfaceID_ThreadMap.keySet();
		Iterator<String> anotherIterator = set.iterator();
		while (anotherIterator.hasNext()){
			String sessionID_peID_interfaceID = anotherIterator.next();
			String temp[] = sessionID_peID_interfaceID.split("#");
			if (sessionID.equals(temp[0])){
				Thread thread = sessionIDpeIDinterfaceID_ThreadMap.get(sessionID_peID_interfaceID);
				thread.interrupt();
				logger.info("Thread: "+ thread.hashCode() + " is interrupted! ");
				anotherIterator.remove();
			}
		}
		lock.writeLock().unlock();
	}
	/**
	 * this method is used to stop getting data
	 * @param sessionID ID for who wants to stop getting data
	 * @param peID peID for which to stop getting data
	 * @param interfaceID interfaceID for which to stop getting data
	 * @return if success, return "success"; if fail, return "fail:reason"
	 */
	public static String closeData(String sessionID, String peID, String interfaceID){
		logger.info("Close Data! ");
		String peIDinterfaceID_ObjectMapKey = peID+"#"+interfaceID;
		if (peIDinterfaceID_ObjectMap.containsKey(peIDinterfaceID_ObjectMapKey)){
			Object object = peIDinterfaceID_ObjectMap.get(peIDinterfaceID_ObjectMapKey);
			lock.readLock().lock();
			//whether the peID_interfaceID is already listened by this sessionID
			if (object_sessionIDMap.containsEntry(object, sessionID)){
				lock.readLock().unlock();
				lock.writeLock().lock();
				object_sessionIDMap.remove(object, sessionID);
				sessionID_objectMap.remove(sessionID, object);
				//whether someone is listening the device
				if (object_sessionIDMap.containsKey(object)){
					//do nothing
				}
				else {//nobody listens the device, remove the information
					object_dataMap.remove(object);
					peIDinterfaceID_ObjectMap.remove(peIDinterfaceID_ObjectMapKey);
					object_peIDInterfaceIDMap.remove(object);
					synchronized (object) {
						object.notifyAll();
					}
				}
				lock.writeLock().unlock();
			}
			else {
				lock.readLock().unlock();
			}
		}
		String sessionID_peID_interfaceID = sessionID + "#" + peID + "#" + interfaceID;
		if (sessionIDpeIDinterfaceID_ThreadMap.containsKey(sessionID_peID_interfaceID)){
			Thread thread = sessionIDpeIDinterfaceID_ThreadMap.get(sessionID_peID_interfaceID);
			thread.interrupt();
			logger.info("Thread: " + thread.hashCode() + " is interrupted! ");
			sessionIDpeIDinterfaceID_ThreadMap.remove(sessionID_peID_interfaceID);
		}
		return "success";
	}
	/**
	 * this method is used to get data and flush the memory area. this method is blocked.
	 * @param sessionID ID for who wants to get data
	 * @param peID peID for which to get data
	 * @param interfaceID interfaceID for which to get data
	 * @param hashCode the hashCode for the thread to get data
	 * @return if success, return data; if fail, return null
	 */
	public static String getDataAndFlush(String sessionID, String peID, String interfaceID){
		logger.info("Get Data! ");
		try {
			String peIDinterfaceID_ObjectMapKey = peID + "#" + interfaceID;	
			if (peIDinterfaceID_ObjectMap.containsKey(peIDinterfaceID_ObjectMapKey)) {
				Object object = peIDinterfaceID_ObjectMap.get(peIDinterfaceID_ObjectMapKey);
				String result = null;
				try {
					synchronized (object) {
						object.wait();
					}
					result = object_dataMap.get(object);
				} catch (Exception e) {
					e.printStackTrace();
					result = null;
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * this method is used to send control command
	 * @param peID
	 * @param interfaceID
	 * @param data
	 * @return send success, return true; send fail, return false
	 */
	public static boolean deviceControl(String peID, String interfaceID, String data){
		PEData peData = new PEData();
		peData.peID = peID;
		peData.interfaceID = interfaceID;
		peData.data = data;
		return MsgOperation.msgSend(peData, null);
	}
	/**
	 * this method is used to send request_response control command, this method is blocked
	 * @param peID
	 * @param interfaceID
	 * @param data
	 * @return result string
	 */
	public static String deviceControlRequestResponse(String peID, String interfaceID, String data){
		PEData peData = new PEData();
		peData.peID = peID;
		peData.interfaceID = interfaceID;
		peData.data = data;
		PESession peSession = new PESession(null, interfaceID, peID, null, null);
		peSession.setActiveMQ(false);
		Object object = new Object();
		peSession.setObject(object);
		if (MsgOperation.msgSend(peData, peSession)){
			try {
				synchronized (object) {
					object.wait(5000);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			logger.info("Blocked wake up here! ");
			return peSession.getData();
		}
		else {
			logger.info("Send Request Response Msg Error! ");
			return null;
		}
	}
	
	/**
	 * when create new thread, call this method to judge whether this thread should be created
	 * if return true, the thread should not be created
	 * if return false, the thread could be created
	 * @param sessionID
	 * @param peID
	 * @param interfaceID
	 * @param thread
	 * @return
	 */
	public static boolean createNewThread(String sessionID, String peID, String interfaceID, Thread thread){
		String sessionID_peID_interfaceID = sessionID + "#" + peID + "#" + interfaceID;
		if (sessionIDpeIDinterfaceID_ThreadMap.containsKey(sessionID_peID_interfaceID)){
			return true;
		}
		sessionIDpeIDinterfaceID_ThreadMap.put(sessionID_peID_interfaceID, thread);
		return false;
	}
	
	
}
