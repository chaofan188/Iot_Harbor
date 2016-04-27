package com.cetc.iot.communicate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

import com.cetc.iot.accesssystem.upinterface.UpperOperation;

/*
 * PESender is used to send data to VE.
 * Physical Device  -->  Netty(IoT protocol) --> PESender --> ActiveMQ --> VE.
 */

@Component
public class PESender {
	
	private static Logger logger = Logger.getLogger(PESender.class.getName());
	
	@Autowired
	private static SubscriberHolder topicHolder;
	
	@Autowired
	private static JmsTemplate jmsTemplate;

	public PESender() {
		// TODO Spring dependency injection

		SubscriberHolder holder = null;
		JmsTemplate template = null;

		topicHolder = holder;
		jmsTemplate = template;
	}

	public static void msgReceive(String peID, String interfaceID,
			final String data, PESession peSession) {
		/*
		 * if session is null ,it is a data packet,the publish it.
		 */
		if (peSession == null) {
			Topic topic = topicHolder.getTopic(peID, interfaceID);
			if (topic == null) {
				logger.info("NO TOPIC FOUND for this pe/interface, maybe not this pe is not actived. ");
				return;
			} else {
				jmsTemplate.send(topic, new MessageCreator() {

					@Override
					public Message createMessage(Session session)
							throws JMSException {

						return session.createTextMessage(data);
					}
				});
			}
		} else {
			/*
			 * If session is not null, it is a ACK from QUEUE, send it back.
			 */
			Session session = peSession.getSession();

			MessageProducer producer = null;
			try {
				producer = session.createProducer(peSession.getDestination());

				final TextMessage textMessage = session
						.createTextMessage(data);
				textMessage.setJMSCorrelationID(peSession.getCorrelationID());
				textMessage.setJMSReplyTo(peSession.getDestination());
				producer.send(peSession.getDestination(), textMessage);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JmsUtils.closeMessageProducer(producer);
				JmsUtils.closeSession(session);
			}
		}
	}
}
