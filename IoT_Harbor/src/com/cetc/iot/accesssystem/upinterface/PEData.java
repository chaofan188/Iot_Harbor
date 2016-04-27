package com.cetc.iot.accesssystem.upinterface;

import java.io.Serializable;

public class PEData implements Serializable{
	
	/*
	 * ��ݳ���
	 */
	public int length;
	
	/*
	 * ��ݶ�ӦPE��ID
	 */
	public String peID;
	/*
	 * �ӿڵ�ID
	 */
	public String interfaceID;
	
	/*
	 * ������Ƿ�����Ӧ���
	 */
	public boolean isResponse;
	
	/*
	 * ������ݴ��������Ự��ID
	 */
	public String sessionID;
	
	/*
	 * ���߿���ͨ���������ͨ��
	 */
	public boolean isData;
	
	/*
	 * �Ƿ�Ҫ����Ӧ
	 */
	public boolean requireResponse;
	
	
	/*
	 * ����غ�
	 */
	public String data;
	
}
