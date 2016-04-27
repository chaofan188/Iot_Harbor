package com.cetc.iot.communicate;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.cetc.iot.accesssystem.downinterface.MsgOperation;
import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;

/*
 * PEReciver is used to receive data form VE.
 * VE  -->  PEConnector4VE --> ActiveMQ --> PERevicer.
 */

@Component
public class PEReciver implements MessageListener, ApplicationContextAware {

	private static Logger logger = Logger.getLogger(PEReciver.class.getName());
	
	private JmsTemplate jmsTemplate;
	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		ctx = arg0;
		this.jmsTemplate = (JmsTemplate) ctx
				.getBean("jmsTemplateQueueListener");
	}

	@Override
	public void onMessage(Message arg0) {
		
		//System.out.println("asdfasdfasdfasfdafsdasdf");
		
		// TODO Auto-generated method stub
		if (arg0 instanceof ObjectMessage) {
			final ObjectMessage message = (ObjectMessage) arg0;

			try {
				logger.info("Message received by Listener: "+message.getJMSCorrelationID()+" - "+message.getObject());
				PEData data = (PEData) message.getObject();

				jmsTemplate.setDefaultDestination(message.getJMSReplyTo());

				Connection connection = jmsTemplate.getConnectionFactory()
						.createConnection();
				
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

				PESession peSession = new PESession(session, data.interfaceID,
						data.peID, message.getJMSCorrelationID(),
						message.getJMSReplyTo());
				// TODO:change message type form textMessage to ?

				// Only For Test
//				producer = session.createProducer(message.getJMSReplyTo());
//
//				PEData peReturnVal = new PEData();
//				peReturnVal.data = "Test is sucess";
//				peReturnVal.interfaceID = data.interfaceID;
//				peReturnVal.peID = data.peID;
//
//				final ObjectMessage objectMessage = session
//						.createObjectMessage(peReturnVal);
//				objectMessage.setJMSCorrelationID(message.getJMSCorrelationID());
//				objectMessage.setJMSReplyTo(message.getJMSReplyTo());
//				producer.send(message.getJMSReplyTo(), objectMessage);
//				System.out.println("now return msg to " + data.peID + ":"
//						+ data.interfaceID);
				// Test End
				
				MsgOperation.msgSend(data, peSession);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
