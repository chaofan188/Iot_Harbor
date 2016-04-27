package com.cetc.test.xiruitest.md5;

import com.cetc.iot.util.StringOperation;
import com.cetc.test.xiruitest.commonstest.base64.Base64Coder;

public class MD5CoderTest {
	public static void main(String [] args) throws Exception{
		String str = StringOperation.hexStringToString("bb");
		String base64String = Base64Coder.encode(str);
		System.out.println(str);
		System.out.println(base64String);
		String result = MD5Coder.encodeMD5Hex(base64String);
		System.out.println(result);
	}
}
