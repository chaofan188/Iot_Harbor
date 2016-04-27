package com.cetc.iot.communicate.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.communicate.InterfaceTokenGenerator;
import com.cetc.iot.communicate.PEConnector4VE;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class PESendTester {

	private static Logger logger = Logger.getLogger(PESendTester.class.getName());
	
	
	@Autowired
	@Qualifier("jmsTemplateTopicSend")
	JmsTemplate template;
	
	@Test
	public void testQueueSend() {
		try {
			PEData data = PEConnector4VE.sendWithResponse("pe123", "if123", "1234567");
			logger.info(data.data);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class test123 implements MessageListener,Runnable{

		Object obj;
		
		@Override
		public void onMessage(Message arg0) {
			// TODO Auto-generated method stub
			obj.notify();
			
		}
		
		public void test() throws JMSException{
			
			String peID = null;
			String interfaceID = null;
			
			PEConnector4VE.subscribe(peID, interfaceID, new test123());
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				obj.wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testSubscribe(){
		try {
			PEConnector4VE.subscribe("pe123", "if123", new MessageListener() {
				
				@Override
				public void onMessage(Message msg) {
					// TODO Auto-generated method stub
					ObjectMessage message = (ObjectMessage) msg;
					PEData data = null;
					try {
						data = (PEData) message.getObject();
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logger.info("Receive One Message: "+data.data);

				}
			} );
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			
		}
	}
	
	@Test
	public void testSubscribeSend(){
		final PEData pe = new PEData();
		pe.data = "Subscribe Done!get message";
		pe.peID = "pe123";
		pe.interfaceID = "if123";
		
		Topic topic = new ActiveMQTopic(InterfaceTokenGenerator.gen("pe123", "if123"));

		template.send(topic, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
			
				ObjectMessage message = session.createObjectMessage();
				
				message.setObject(pe);
				
				return message;
			}
		});
	}
}
