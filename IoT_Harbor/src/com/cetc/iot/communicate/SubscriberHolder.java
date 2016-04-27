package com.cetc.iot.communicate;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.collections4.map.HashedMap;

public class SubscriberHolder {
	private static Map<String, Topic> topicMap = new HashedMap<String, Topic>();
	private static ReadWriteLock topicLock = new ReentrantReadWriteLock();
	private static Map<String, Session> sessionMap = new HashedMap<String, Session>(); 
	private static Map<String, TopicSubscriber> subscribeMap = new HashedMap<String, TopicSubscriber>();

	public static Topic addTopic(String peID, String interfaceID) {
		// TODO Auto-generated method stub

		topicLock.writeLock().lock();
		String peToken = InterfaceTokenGenerator.gen(peID, interfaceID);
		Topic topic = new ActiveMQTopic(peToken);
		topicMap.put(peToken, topic);
		topicLock.writeLock().unlock();
		return topic;
	}

	public static Topic getTopic(String peID, String interfaceID) {
		// TODO Auto-generated method stub
		topicLock.readLock().lock();
		String peToken = InterfaceTokenGenerator.gen(peID, interfaceID);
		Topic topic = topicMap.get(peToken);
		topicLock.readLock().unlock();
		return topic;
	}

	public static  void addSession(String suscribeID, TopicSession session, TopicSubscriber subscriber) {
		// TODO Auto-generated method stub
		sessionMap.put(suscribeID, session);
		subscribeMap.put(suscribeID, subscriber);
	}
	
	public static Session getSession(String subscribeID){
		return sessionMap.get(subscribeID);
	}
	
	public static TopicSubscriber getSubscriber(String subscribeID){
		return subscribeMap.get(subscribeID);
	}
	
	public static void delSession(String subscrbeID){
		sessionMap.remove(subscrbeID);
		subscribeMap.remove(subscrbeID);
	}
}
