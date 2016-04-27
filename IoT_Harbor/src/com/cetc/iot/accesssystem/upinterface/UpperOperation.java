package com.cetc.iot.accesssystem.upinterface;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.cetc.iot.communicate.PESession;
import com.cetc.iot.communicate.SubscriberHolder;
import com.cetc.iot.util.GetData;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * ����������netty����ͨ��������ݽ��ա�
 * 
 * @author wanghan ����ʱ�� 2014-09-09
 * 
 *         �������մ�ͨ���ϴ������
 */
public class UpperOperation {
	
	
	private static Logger logger = Logger.getLogger(UpperOperation.class.getName());
	
	/**
	 * ���ܣ�ͨ�����ͽ�����ݸ��ϲ� ˼·������ͨ��������ݱ��ĺ�
	 * 
	 * 
	 * @param PEData
	 *            �������
	 * 
	 */
	//public static PEManager manager = PEManager.getInstance();
	static final String context[] = { "applicationContext.xml" };
	static ApplicationContext ctx = new ClassPathXmlApplicationContext(context);
	static JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplateTopicSend");
	
	/**
	 * This multimap is used to save the information between peID and getDataObject.
	 * Through this, know which getDataObject cares the peID.
	 */
	private static Multimap<String, GetData> peIDGetDataObjectMap = ArrayListMultimap.create() ;
	/**
	 * This hashMap is used to save the information between peID and Object for mutex.
	 * Every getDataObject wait on an Object and when messages received , notifyall.
	 */
	private static ConcurrentHashMap<String, Object> peIDMutexObjectMap = new ConcurrentHashMap<String, Object>();
	/**
	 * This lock is used to synchronized protect the multimap
	 */
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	
	
	public static ReadWriteLock getLock(){
		return lock;
	}
	
	public static Multimap<String, GetData> getPEIDGetDataObjectMap(){
		return peIDGetDataObjectMap;
	}
	
	public static ConcurrentHashMap<String, Object> getPEIDMutexObjectMap(){
		return peIDMutexObjectMap;
	}
	
	/**
	 * This method is used for other classes to add the relation between peID and getDataObject
	 * @param peID
	 * @param getData
	 */
	public static void addPEIDGetDataObjectMap (String peID, GetData getData){
		lock.writeLock().lock();
		peIDGetDataObjectMap.put(peID, getData);
		//if the first one comes, create an object for this peID, then everyone could use this object to wait
		if (!peIDMutexObjectMap.containsKey(peID)){
			peIDMutexObjectMap.put(peID, new Object());
		}
		lock.writeLock().unlock();
	}
	/**
	 * This method is used for other classes to delete the relation between peID and getDataObject
	 * @param peID
	 * @param getData
	 */
	public static void deletePEIDGetDataObjectMap(String peID, GetData getData){
		lock.writeLock().lock();
		peIDGetDataObjectMap.remove(peID,getData);
		//if the last one goes, delete the object for this peID, no one will care the pe
		if (!peIDGetDataObjectMap.containsKey(peID)){
			peIDMutexObjectMap.remove(peID);
		}
		lock.writeLock().unlock();
	}
	/**
	 * This method is used for other classes to get the object to wait
	 * @param peID
	 * @return certain object for certain pe
	 */
	public static Object getMutexObject (String peID){
		return peIDMutexObjectMap.get(peID);
	}
	
	public void  msgReceive(final PEData data, PESession session) throws Exception{
		logger.info("Upper receive messages from lower! ");
		logger.info(data.interfaceID+"\t"+data.data);
		if(session == null){ //subscribe
			Topic topic = SubscriberHolder.getTopic(data.peID, data.interfaceID);
			if(topic == null){
				return;
			}
			logger.info("订阅主题："+topic);
			template.send(topic, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					ObjectMessage message = session.createObjectMessage();
					message.setObject(data);
					return message;
				}
			});
			
		} else { //message reply
			MessageProducer producer = null;
			
			if(session.getTimeout() == true){ //if timeout from communication, reture directly
				logger.info("ActiveMQ Get Timeout: MsgOperation! ");
				return;
			}
			
			try {
				logger.info("Now return message begin! Session is : "+session.getSession());
				final ObjectMessage objectMessage = session.getSession()
						.createObjectMessage(data);
				producer = session.getSession().createProducer(session.getDestination());
				
				objectMessage.setJMSCorrelationID(session.getCorrelationID());
				objectMessage.setJMSReplyTo(session.getDestination());
				producer.send(session.getDestination(), objectMessage);
				logger.info("UpperOperation return Message: producer: "+producer.toString()+session.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				producer.close();
				session.getSession().close();
			}
		}
	}
}
