/**
 * 
 */
package com.cetc.test.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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

import com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam;
import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.communicate.InterfaceTokenGenerator;
import com.cetc.iot.communicate.PEConnector4VE;
import com.cetc.iot.communicate.test.PESendTester;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "/applicationContext.xml")
/*@org.springframework.test.context.ContextConfiguration(locations={"classpath:applicationContext.xml"})*/
/**
 * @author ZTB
 *
 */
public class OnvifCamTest {

	private  Logger logger = Logger.getLogger(OnvifCamTest.class.getName());
	OnvifCam onvifcam = new OnvifCam("192.168.12.237", "admin", "12345", "1234567890", "123");
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam#OnvifCam(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	@Ignore
	public void testOnvifCam() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam#getCam(java.lang.String, java.lang.String)}.
	 */
	@Test
	@Ignore
	public void testGetCam() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam#action(java.lang.String, com.cetc.iot.communicate.PESession)}.
	 */
	@Test
	public void testAction() {
			System.out.println("Cam Test begin!!");
			try {
				onvifcam.action("{'easy_control':'left'}", null);
				onvifcam.action("{'easy_control':'up'}", null);
				onvifcam.action("{'easy_control':'down'}", null);
				onvifcam.action("{'easy_control':'right'}", null);
				System.out.println("OK");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * Test method for {@link com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam#discovery()}.
	 */
	@Test
	@Ignore
	public void testDiscovery() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam#moveX(java.lang.String)}.
	 */
	@Test
	@Ignore
	public void testMoveX() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam#moveY(java.lang.String)}.
	 */
	@Test
	@Ignore
	public void testMoveY() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam#zoom(java.lang.String)}.
	 */
	@Test
	@Ignore
	public void testZoom() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.cetc.iot.accesssystem.downinterface.protocol.onvif.OnvifCam#stepMove(float, float, float)}.
	 */
	@Test
	@Ignore
	public void testStepMove() {
		fail("Not yet implemented");
	}

}
