package com.cetc.test;

import java.io.PipedInputStream;

public class Reciever extends Thread {
	private PipedInputStream inStream = new PipedInputStream();

	public PipedInputStream getInStream() {
		return inStream;
	}

	public void run() {
		byte[] buf = new byte[4096];

		do {
			try {
				Thread.sleep(2000);

				int len = inStream.read(buf);
				System.out.println("recieve message " + new String(buf, 0, len));
				// inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);
	}
}
