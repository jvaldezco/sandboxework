package ework.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Secure {
	private static SecretKeySpec secretKey;
	private static byte[] key;
	static byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    static IvParameterSpec ivspec = new IvParameterSpec(iv);
	
	public static void setKey(String myKey) {
		MessageDigest sha = null;
		
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch(NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch(UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
	}
	
	public static String encryptData(String data, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
		} catch(Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
	
	public static String decryptData(String data, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
	
	public static void setPBDKF2Key(String myKey) throws InvalidKeySpecException {
		setPBDKF2Key(myKey, "");
	}
	
	public static void setPBDKF2Key(String myKey, String salt) throws InvalidKeySpecException {
		try {
			
			String sz = (salt.equals("")) ? System.getenv("QA_ACC_SL") : salt;
			
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(myKey.toCharArray(), sz.getBytes(), 2048, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	               
		} catch(NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
	}
	public static String encryptPBDKF2Data(String data, String secret) {
		return encryptPBDKF2Data(data, secret, "");
	}
	
	public static String encryptPBDKF2Data(String data, String secret, String salt) {
		try {
			
			if(salt.equals("")) {
				setPBDKF2Key(secret);
			} else {
				setPBDKF2Key(secret, salt);
			}
			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
		} catch(Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
	
	public static String decryptPBDKF2Data(String data, String secret) {
        try {
        	setPBDKF2Key(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}