package com.cetc.test;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class TestPipe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sender sender = new Sender();
		Reciever reciever = new Reciever();

		PipedOutputStream outStream = sender.getOutStream();
		PipedInputStream inStream = reciever.getInStream();

		try {
			outStream.connect(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sender.start();
		reciever.start();
	}

}
