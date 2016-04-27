package com.cetc.iot.servicesystem.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class IOUtil {
	public static void writeStr2File(String xml,String path){
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
				Writer os = new OutputStreamWriter(fos,"UTF-8");
				os.write(xml);
				os.flush();
				fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
