package com.cetc.test;

import java.util.Hashtable;

import com.cetc.iot.accesssystem.upinterface.impl.DataChannelImpl;

public class TestHashTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String, String> outputTable = new Hashtable<String, String>();
		
		outputTable.put("123", "one");
		outputTable.put("123", "two");
		outputTable.put("123", "three");

		System.out.println(outputTable.get("123"));
		System.out.println(outputTable.get("123"));
		System.out.println(outputTable.get("123"));
	}

}
