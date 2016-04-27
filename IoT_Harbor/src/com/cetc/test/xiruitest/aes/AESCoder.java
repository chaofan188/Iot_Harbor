package com.cetc.test.xiruitest.aes;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public abstract class AESCoder {
	public static final String KEY_ALGORITHM = "AES";
	public static final String CIPHER_ALGORITHM = "AES/ECB/ZeroBytePadding";
	private static Key toKey (byte[] key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}
	public static byte[] decrypt (byte[] data, byte[] key) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	public static byte[] encrypt (byte[] data, byte[] key) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		Key k = toKey(key);
		Cipher cipher =  Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	public static byte[] initKey() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		/*String test = "hello";
		SecureRandom sr = new SecureRandom(test.getBytes());
		kg.init(128,sr);*/
		kg.init(256);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}
}
