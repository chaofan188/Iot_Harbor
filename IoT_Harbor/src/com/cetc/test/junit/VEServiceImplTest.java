/**
 * 
 */
package com.cetc.test.junit;

import static org.junit.Assert.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

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
import com.cetc.iot.database.model.VE;
import com.cetc.iot.database.model.VeGeoLocation;
import com.cetc.iot.servicesystem.service.VEService;
import com.cetc.iot.servicesystem.service.VETemplateService;
import com.cetc.iot.servicesystem.service.impl.VEServiceImpl;

/**
 * @author ZTB
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "/applicationContext.xml")
public class VEServiceImplTest {
	private  Logger logger = Logger.getLogger(VEServiceImplTest.class.getName());
/*	Calendar calendar=Calendar.getInstance();
	java.util.Date date=calendar.getTime();*/
	/*public SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");*/
	private  Date date= new Date();
/*	private  VETemplateService = new VETemplateService();*/
	@Autowired
	private VEService vEService;
	private  VeGeoLocation vegeolocation = new VeGeoLocation();
	
	VE ve = new VE("1","2","3","4","5",date,"6","7","8","9",null);
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.cetc.iot.servicesystem.service.impl.VEServiceImpl#delete(java.lang.String)}.
	 */
	@Test
	@Ignore
	public void testDelete() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.cetc.iot.servicesystem.service.impl.VEServiceImpl#add(com.cetc.iot.database.model.VE, com.cetc.iot.database.model.VeGeoLocation)}.
	 */
	@Test
	public void testAdd() {
		vEService.add(ve,vegeolocation);
	}

	/**
	 * Test method for {@link com.cetc.iot.servicesystem.service.impl.VEServiceImpl#update(com.cetc.iot.database.model.VE, com.cetc.iot.database.model.VeGeoLocation)}.
	 */
	@Test
	@Ignore
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.cetc.iot.servicesystem.service.impl.VEServiceImpl#queryCOUNTNUM(com.cetc.iot.database.model.VE)}.
	 */
	@Test
	@Ignore
	public void testQueryCOUNTNUM() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.cetc.iot.servicesystem.service.impl.VEServiceImpl#getVeByVeId(java.lang.String)}.
	 */
	@Test
	@Ignore
	public void testGetVeByVeId() {
		fail("Not yet implemented");
	}

}
