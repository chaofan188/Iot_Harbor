package com.cetc.iot.accesssystem.util;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * ���ļ��������PE data-point���������ID
 * ��ɹ���Ϊ��PE����11 + ������ʱ������� + ��λ˳�����к�
 */
public class DataIDGenerator {

	final private static FieldPosition HELPER_POSITION = new FieldPosition(0);
	final private static NumberFormat headFormat = new DecimalFormat("00");
	final private static Format dateFormat = new SimpleDateFormat("YYMMddHHmmssS");
	final private static NumberFormat numberFormat = new DecimalFormat("0000");
	private static int seq = 0;
	private static int MAX = 9999;
	private static int PEMark = 11;

	/*
	 * 
	 */
	public static synchronized String generateDataID() {

		Calendar now = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		headFormat.format(PEMark, sb, HELPER_POSITION);
		dateFormat.format(now.getTime(), sb, HELPER_POSITION);
		numberFormat.format(seq, sb, HELPER_POSITION);

		if (seq == MAX) {
			seq = 0;
		} else {
			seq++;
		}
		System.out.println(sb.toString());

		return sb.toString();
	}

	public static void main(String[] args) {

		DataIDGenerator.generateDataID();
		DataIDGenerator.generateDataID();
		DataIDGenerator.generateDataID();
		DataIDGenerator.generateDataID();


		// TODO Auto-generated method stub
	}
}
