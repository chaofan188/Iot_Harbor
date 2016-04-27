package com.cetc.test.xiruitest.aes;

import org.apache.commons.codec.binary.Base64;



public class AESCoderTest {
	public static void main(String[] args) throws Exception{
		/*String input = "abcdefghijklmnopqrstuvwxyz";
		byte[] inputData = input.getBytes();
		System.out.println("原文："+input);
		byte[] key = AESCoder.initKey();
		System.out.println("密钥："+Base64.encodeBase64String(key));
		inputData = AESCoder.encrypt(inputData, key);
		System.out.println("加密后："+Base64.encodeBase64String(inputData));
		byte[] outputData = AESCoder.decrypt(inputData, key);
		String output = new String (outputData);
		System.out.println("解密后："+output);
		System.out.println(input.equals(output));*/
		
		String keyBase64 = "JYZ/O/6Em9BemGprMzMsdrY0hX5oh90StBwGWMho2J4=";
		byte[] key = Base64.decodeBase64(keyBase64);
////		byte[] input = AESCoder.initKey();
////		System.out.println(input.length);
		String inputString = "111222333444555";
//		System.out.println(inputString.length());
		byte[] output = AESCoder.encrypt(inputString.getBytes(), key);
		System.out.println(output.length);
		
		/*String keyBase64 = "JYZ/O/6Em9BemGprMzMsdrY0hX5oh90StBwGWMho2J4=";
		byte[] key = Base64.decodeBase64(keyBase64);
		System.out.println(key.length);
		String input = "zxcvbnm";
		byte[] inputData = input.getBytes();
		System.out.println("原文："+input);
		System.out.println("密钥："+Base64.encodeBase64String(key));
		inputData = AESCoder.encrypt(inputData, key);
		System.out.println("加密后："+Base64.encodeBase64String(inputData));
		byte[] outputData = AESCoder.decrypt(inputData, key);
		String output = new String (outputData);
		System.out.println("解密后："+output);
		System.out.println(input.equals(output));*/
//		String keyBase64 = "JYZ/O/6Em9BemGprMzMsdrY0hX5oh90StBwGWMho2J4=";
//		byte[] key = Base64.decodeBase64(keyBase64);
//		byte[] input = AESCoder.initKey();
//		System.out.println(input.length);
//		byte[] output = AESCoder.encrypt(input, key);
//		System.out.println(output.length);
//		byte[] result = AESCoder.decrypt(output, key);
//		System.out.println(result.length);
//		String inputString = new String(input,"ISO-8859-1");
//		String resultString = new String(result,"ISO-8859-1");
//		System.out.println(inputString.equals(resultString));
//		String keyBase64 = "JYZ/O/6Em9BemGprMzMsdrY0hX5oh90StBwGWMho2J4=";
//		byte[] key = Base64.decodeBase64(keyBase64);
//		String input = "";
//		String inputString = "\0\0\0asdfaf\0\0\0";
//		System.out.println(input.length());
//		System.out.println(inputString.length());
//		byte[] input1 = input.getBytes("ISO-8859-1");
//		byte[] input2 = inputString.getBytes("ISO-8859-1");
//		byte[] output1 = AESCoder.encrypt(input1, key);
//		byte[] output2 = AESCoder.encrypt(input2, key);
//		for (byte temp: output1){
//			System.out.print(temp);
//		}
//		System.out.println();
//		for (byte temp: output2){
//			System.out.print(temp);
//		}
//		String outputString1 = new String(AESCoder.decrypt(output1, key),"ISO-8859-1");
//		String outputString2 = new String(AESCoder.decrypt(output2, key),"ISO-8859-1");
//		System.out.println(outputString1);
//		System.out.println(outputString2);
//		System.out.println(outputString1.length());
//		System.out.println(outputString2.length());
//		String input1 = "aaa";
//		String input2 = "aaa";
//		byte[] inputByte1 = input1.getBytes("ISO-8859-1");
//		byte[] inputByte2 = input2.getBytes("ISO-8859-1");
//		byte[] outputByte1 = AESCoder.encrypt(inputByte1, key);
//		byte[] outputByte2 = AESCoder.encrypt(inputByte2, key);
//		for (byte temp: outputByte1){
//			System.out.print(temp);
//			System.out.print(" ");
//		}
//		System.out.println();
//		for (byte temp: outputByte2){
//			System.out.print(temp);
//			System.out.print(" ");
//		}
//		System.out.println();
//		byte[] key = AESCoder.initKey();
//		String keyBase64 = Base64.encodeBase64String(key);
//		System.out.println(keyBase64);
//		String keyString = "r9auB3fHfnGDaPSTFsBNiSOfyJv4udExpFhhgEQX1V4=";
	}
}
