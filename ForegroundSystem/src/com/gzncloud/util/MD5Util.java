package com.gzncloud.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密算法
 *
 */
public class MD5Util {
	// 声明常量
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * MD5加密
	 * 
	 * @param text
	 * @return String
	 */
	public static String encode(String text) {
		try {// AES加密 RSA加密
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] result = messageDigest.digest(text.getBytes()); // 对文本进行加密
			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : result) {
				int i = b & 0xff; // 取字节的后八位有效值
				String string = Integer.toHexString(i);
				if (string.length() < 2) {
					string = "0" + string;
				}
				stringBuilder.append(string);
			}
			// 返回加密结果
			return stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(messageDigest.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(messageDigest.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {

		}
		return resultString;
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultStringBuffer = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultStringBuffer.append(byteToHexString(b[i]));

		return resultStringBuffer.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	// 16位MD5加密
	public static String MD5Encode16(String val) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(val.getBytes());
		byte[] m = md5.digest();// 加密
		return getString16(m);
	}

	private static String getString16(byte[] b) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			int a = b[i];
			if (a < 0)
				a += 256;
			if (a < 16)
				buf.append("0");
			buf.append(Integer.toHexString(a));
		}
		return buf.toString().substring(8, 24);
	}
}
