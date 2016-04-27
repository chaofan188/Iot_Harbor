package com.cetc.test.xiruitest;

import java.io.FileWriter;
import java.io.PrintWriter;


public class TestExceptionFile {
	public static void main (String[] args){
		try {
			String teString = null;
			teString.charAt(11);
		} catch (Exception e) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new FileWriter("E:\\TestFile.txt", true));
			} catch (Exception e1){
				e1.printStackTrace();
			}
			e.printStackTrace(pw);
			pw.flush();
			pw.close();
		}
	}
}
