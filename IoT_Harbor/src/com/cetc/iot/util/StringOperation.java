package com.cetc.iot.util;

import java.io.ByteArrayOutputStream;

import com.cetc.test.xiruitest.commonstest.base64.Base64Coder;


/**
 * this class is used for some string operation
 * @author xzc
 * Create Time: 2015-04-01
 * Author: xzc
 * Details: Add stringToAscIIInt function to convert string to ascii integer
 *          Add stringToAscIILong function to convert string to ascii long
 *          Add stringToHexString function to convert string to HexString
 *          Add hexStringToString function to convert hexString to String
 *          Add encode function to convert string to hexString
 *          Add decode function to convert hexString to string
 */
public class StringOperation {
	
	
	private static final String HEXSTRING = "0123456789abcdef";
	
	
	/**
	 * convert string to ASCII int
	 * @param input
	 * @return
	 */
	public static int stringToAscIIInt(String input){
		int result = 0;
		for (int i=0; i<input.length();i++){
			result *= 256;
			result += (int)input.charAt(i);
		}
		return result;
	}
	
	/**
	 * convert string to ASCII long
	 * @param input
	 * @return
	 */
	public static long stringToAscIILong (String input){
		long result = 0;
		for (int i=0;i<input.length();i++){
			result = result*256;
			result = result + (int)input.charAt(i);
		}
		return result;
	}
	
	/**
	 * convert string to HexString
	 * @param input
	 * @return
	 */
	public static String stringToHexString(String input) {
		try {
			byte[] bytes = input.getBytes("ISO-8859-1");
			StringBuilder sb = new StringBuilder(bytes.length*2);
			for (int i=0;i<bytes.length;i++){
				sb.append(HEXSTRING.charAt((bytes[i]&0xf0)>>4));
				sb.append(HEXSTRING.charAt((bytes[i]&0x0f)>>0));
			}
			return sb.toString();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * convert HexString to String
	 * @param input
	 * @return
	 */
	public static String hexStringToString(String input){
		ByteArrayOutputStream baos = new ByteArrayOutputStream(input.length()/2);
		for (int i=0;i<input.length();i+=2){
			baos.write(0xff&((HEXSTRING.indexOf(input.charAt(i))<<4)|(HEXSTRING.indexOf(input.charAt(i+1)))));
		}
		String result = "";
		try {
			result = new String(baos.toByteArray(),"ISO-8859-1");
		} catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * convert string to HexString
	 * @param input
	 * @return
	 */
	public static String encode(String input){
		try {
			byte[] bytes = input.getBytes("ISO-8859-1");
			StringBuilder sb = new StringBuilder(bytes.length*2);
			for (int i=0;i<bytes.length;i++){
				sb.append(HEXSTRING.charAt((bytes[i]&0xf0)>>4));
				sb.append(HEXSTRING.charAt((bytes[i]&0x0f)>>0));
			}
			return sb.toString();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * convert hexString to string
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String decode(String input) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream(input.length()/2);
		for (int i=0;i<input.length();i+=2){
			baos.write(0xff&((HEXSTRING.indexOf(input.charAt(i))<<4)|(HEXSTRING.indexOf(input.charAt(i+1)))));
		}
		return new String(baos.toByteArray(),"ISO-8859-1");
	}
	
	
	public static void main(String[] args) throws Exception{

//		byte[] temp = new byte[2];
//		temp[0] = -69;
//		temp[1] = -54;
//		String input = new String(temp,"ISO-8859-1");
//		byte[] output = input.getBytes("ISO-8859-1");
//		System.out.println(input);
//		System.out.println(output.length);
//		System.out.println(output[0]);
//		System.out.println(Base64Coder.encode(input));
		
//		byte[] base64Result = Base64.encodeBase64(temp);
//		System.out.println(new String(base64Result));
		
//		String input = "8b";
//		String output = decode(input);
//		System.out.println(Base64Coder.encode(output));
//		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(187);
		String input = new String(baos.toByteArray(),"ISO-8859-1");
		System.out.println(Base64Coder.encode(input));
		
	}
	
}
