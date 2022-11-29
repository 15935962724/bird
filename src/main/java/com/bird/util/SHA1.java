/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.bird.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * SHA1 class
 *
 * 计算公众平台的消息签名接口.
 */
public class SHA1 {

	/**
	 * 用SHA1算法生成安全签名
	 * @param token 票据
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @param encrypt 密文
	 * @return 安全签名
	 * @throws AesException 
	 */
	public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException
			  {
		try {
			String[] array = new String[] { token, timestamp, nonce, encrypt };
			StringBuffer sb = new StringBuffer();
			// 字符串排序
			Arrays.sort(array);
			for (int i = 0; i < 4; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ComputeSignatureError);
		}
	}

	public static String SHA1(String var0) {
		try {
			MessageDigest var1 = MessageDigest.getInstance("SHA1");
			var1.update(var0.getBytes());
			var0 = enc(var1.digest());
		} catch (NoSuchAlgorithmException var2) {
		}

		return var0;
	}




	public static String enc(byte[] var0) {
		StringBuilder var1 = new StringBuilder();

		for(int var2 = 0; var2 < var0.length; ++var2) {
			String var3 = Integer.toHexString(var0[var2] & 255);
			if (var3.length() == 1) {
				var1.append("0");
			}

			var1.append(var3);
		}

		return var1.toString();
	}


	public static byte[] enc(int var0) {
		byte[] var1 = new byte[]{(byte)(var0 >>> 24), (byte)(var0 >>> 16), (byte)(var0 >>> 8), (byte)var0};
		return var1;
	}





}
