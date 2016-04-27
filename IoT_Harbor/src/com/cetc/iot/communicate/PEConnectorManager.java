package com.cetc.iot.communicate;

import java.util.Random;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.destination.DestinationResolver;

import com.cetc.iot.accesssystem.upinterface.PEData;

/* * This class is used in VE side.For Sending message to PE
 * , and subscribe topic form PE.
 * 
 *@author wanghan
 * 
 */
//@Component
public class PEConnectorManager {
	
	private static Logger logger = Logger.getLogger(PEConnectorManager.class.getName());
	

	private static PEConnectorManager manager = null;

	// @Autowired
	// @Qualifier("jmsTemplateQueueSend")
	// JmsTemplate jmsTemplate;

	final String context[] = { "applicationContext.xml" };
	ApplicationContext ctx = new ClassPathXmlApplicationContext(context);

	JmsTemplate queueTemplate = (JmsTemplate) ctx
			.getBean("jmsTemplateQueueSend");
	JmsTemplate topicTemplate = (JmsTemplate) ctx
			.getBean("jmsTemplateTopicListener");

	private static final int TIMEOUT = 20000;

	// private static Map<String, Destination> destinationMap = new
	// HashedMap<String, Destination>();
	// ReadWriteLock destinationLock = new ReentrantReadWriteLock();

	public static PEConnectorManager getInstance() {
		if (manager == null) {
			manager = new PEConnectorManager();
		}
		return manager;
	}

	private final class ProduerConsumer implements SessionCallback<Message> {

		private final PEData msg;
		private final DestinationResolver destinationResolver;

		public ProduerConsumer(final PEData msg,
				final DestinationResolver destinationResolver) {
			this.msg = msg;

			this.destinationResolver = destinationResolver;
		}

		@Override
		public Message doInJms(Session session) throws JMSException {

			MessageConsumer consumer = null;
			MessageProducer producer = null;

			try {
				final String correlationId = UUID.randomUUID().toString();
				final Destination requestDestination = destinationResolver
						.resolveDestinationName(session, "pe.request", false);
				final Destination replyDestination = session
						.createTemporaryQueue();

				consumer = session.createConsumer(replyDestination,
						"JMSCorrelationID = '" + correlationId + "'");
				final ObjectMessage message = session.createObjectMessage();
				message.setObject(msg);
				message.setJMSCorrelationID(correlationId);
				message.setJMSReplyTo(replyDestination);
				producer = session.createProducer(requestDestination);
				producer.send(requestDestination, message);

				return consumer.receive(TIMEOUT);
			} finally {
				JmsUtils.closeMessageConsumer(consumer);
				JmsUtils.closeMessageProducer(producer);
			}
		}

	}

	public PEData send(final String peID, final String interfaceID,
			final String data) throws JMSException {
		// This function should be threads safe.
		// final String peToken = genToken(peID, interfaceID);
		//
		// // Get Destination by peToken
		// // Destination destination = getDestination(peToken);
		//
		// // template.send(destination, new MessageCreator() {
		// template.send(new MessageCreator() {
		//
		// @Override
		// public Message createMessage(Session session) throws JMSException {
		// // TODO Auto-generated method stub
		//
		// Queue queue = session.createQueue(peToken);
		// session.createProducer(queue);
		//
		// Message msg = session.createTextMessage(data);
		//
		// Destination tempDestination = session.createTemporaryQueue();
		// MessageConsumer consumer = session
		// .createConsumer(tempDestination);
		// msg.setJMSDestination(tempDestination);
		//
		// msg.setJMSCorrelationID(createRandomString());
		//
		// return session.createTextMessage(data);
		// }
		//
		// });
		PEData peData = new PEData();
		peData.data = data;
		peData.peID = peID;
		peData.interfaceID = interfaceID;
		logger.info(queueTemplate);
		ObjectMessage msg = (ObjectMessage) queueTemplate.execute(
				new ProduerConsumer(peData, queueTemplate
						.getDestinationResolver()), true);
		if (msg != null) {
			return (PEData) msg.getObject();
		} else {
			return null;
		}
	}

	// private Destination getDestination(String peToken) {
	// // TODO Auto-generated method stub
	// destinationLock.readLock().lock();
	// Destination destination = destinationMap.get(peToken);
	//
	// if (destination == null) {
	// synchronized (destination) {
	// destinationLock.readLock().unlock();
	//
	// destinationLock.writeLock().lock();
	// destinationMap.put(peToken, new ActiveMQQueue(peToken));
	// destinationLock.writeLock().unlock();
	// }
	// } else {
	//
	// destinationLock.readLock().unlock();
	// }
	// return destination;
	//
	// }

	public String subscribe(String peID, String interfaceID,
			MessageListener listener) throws JMSException {

		ConnectionFactory factory = topicTemplate.getConnectionFactory();
		Connection connection = factory.createConnection();

		connection.start();
		TopicSession session = (TopicSession) connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		System.out.println(session.toString());
		String subscribeID = UUID.randomUUID().toString();

		Topic topic = SubscriberHolder.getTopic(peID, interfaceID);
		if (topic == null) {
			topic = SubscriberHolder.addTopic(peID, interfaceID);
		}
		TopicSubscriber subscriber = session
				.createSubscriber(topic, null, true);

		SubscriberHolder.addSession(subscribeID, session, subscriber);

		subscriber.setMessageListener(listener);

		return subscribeID;
	};

	private String createRandomString() {
		Random random = new Random(System.currentTimeMillis());
		long randomLong = random.nextLong();

		return Long.toHexString(randomLong);
	}

	public void unsubscribe(String subscribeID) throws JMSException {
		Session session = SubscriberHolder.getSession(subscribeID);
		logger.info("取消订阅："+subscribeID);
		logger.info(session);
		TopicSubscriber subscriber = SubscriberHolder
				.getSubscriber(subscribeID);
		if (subscriber != null) {
			subscriber.close();
		}
		if (session != null) {
			session.close();
		}
		SubscriberHolder.delSession(subscribeID);
	}

	public void sendNoResponse(String peID, String interfaceID, String data) {
		// TODO Auto-generated method stub
		final PEData peData = new PEData();
		peData.data = data;
		peData.peID = peID;
		peData.interfaceID = interfaceID;

		queueTemplate.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				ObjectMessage message = session.createObjectMessage();

				message.setObject(peData);
				
				return message;
			}
		});
		return;
	}

}
