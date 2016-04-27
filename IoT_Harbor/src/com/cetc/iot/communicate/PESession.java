package com.cetc.iot.communicate;

import javax.jms.Destination;
import javax.jms.Session;

public class PESession {
	
	Session session;
	String interfaceID;
	String peID;
	String correlationID;
	Destination destination;
	boolean timeout;
	/**
	 * whether this peSession is used for ActiveMQ
	 */
	boolean isActiveMQ = true;
	/**
	 * if peSession is not used for ActiveMQ, this object is used to synchronized
	 */
	Object object;
	/**
	 * is peSession is not used for ActiveMQ, this String is used to keep return string
	 */
	String data;
	
	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}
	
	public boolean getTimeout() {
		return this.timeout;
	}

	public PESession(Session session, String interfaceID, String peID,
			String jmsCorrelationID, Destination jmsReplyTo) {
		// TODO Auto-generated constructor stub
		this.session = session;
		this.interfaceID = interfaceID;
		this.peID = peID;
		this.correlationID = jmsCorrelationID;
		this.destination = jmsReplyTo;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getInterfaceID() {
		return interfaceID;
	}

	public void setInterfaceID(String interfaceID) {
		this.interfaceID = interfaceID;
	}

	public String getPeID() {
		return peID;
	}

	public void setPeID(String peID) {
		this.peID = peID;
	}

	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public boolean isActiveMQ() {
		return isActiveMQ;
	}

	public void setActiveMQ(boolean isActiveMQ) {
		this.isActiveMQ = isActiveMQ;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
