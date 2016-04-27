package com.cetc.iot.util.aes;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
/**
 * this class is used to realize AES algorithm
 * @author xzc
 * Create Time: 2015-04-01
 *
 */
public abstract class AESCoder {
	public static final String KEY_ALGORITHM = "AES";
	public static final String CIPHER_ALGORITHM = "AES/ECB/ZeroBytePadding";
	private static Key toKey (byte[] key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}
	/**
	 * decrypt function
	 * @param data 
	 * @param key 
	 * @return real information
	 * @throws Exception
	 */
	public static byte[] decrypt (byte[] data, byte[] key) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	/**
	 * encrypt function
	 * @param data
	 * @param key
	 * @return information after encrypt
	 * @throws Exception
	 */
	public static byte[] encrypt (byte[] data, byte[] key) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		Key k = toKey(key);
		Cipher cipher =  Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	/**
	 * create key
	 * @return key
	 * @throws Exception
	 */
	public static byte[] initKey() {
		Security.addProvider(new BouncyCastleProvider());
		try {
			KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
			/*String test = "hello";
			SecureRandom sr = new SecureRandom(test.getBytes());
			kg.init(128,sr);*/
			kg.init(256);
			SecretKey secretKey = kg.generateKey();
			return secretKey.getEncoded();
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
