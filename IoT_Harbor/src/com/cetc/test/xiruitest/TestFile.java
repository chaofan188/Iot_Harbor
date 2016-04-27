package com.cetc.test.xiruitest;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TestFile {
	public static void main (String [] args) throws Exception {
		PrintWriter pw = new PrintWriter(new FileWriter("E:\\test.log",true));
		pw.write("hello!!!\n");
		pw.flush();
		pw = new PrintWriter(new FileWriter("E:\\test.txt",true));
		pw.write("asdfasfsdf\n");
		pw.flush();
	}
}
