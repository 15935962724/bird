package com.bird.controller.weixin;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class WxMessengers {

    static byte[] AES_KEY;
    static String token = "Token000abcdef"; // d3bb47dc46254d253cad2f3e60ecca73,weixin_token
    static
    {
        try
        {
            AES_KEY = new BASE64Decoder().decodeBuffer("EncodingAESKey000abcdefghijklmnopqrstuvwxyz" + "=");
        } catch(Throwable ex)
        {
        }
    }

    static String enc(String str,String appId) throws Exception
    {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonce = String.valueOf( (Double) Class.forName("java.lang.Math").getMethod("ran" + "dom").invoke(null));

        // 拼装
        byte[] by = str.getBytes("utf-8");
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        for(int i = 0;i < 16;i++)
            // 随机数
            ba.write(by[i]);
        ba.write(enc(by.length));
        ba.write(by);
        ba.write(appId.getBytes("utf-8"));

        // 补位
        int BLOCK_SIZE = 32;
        int pad = BLOCK_SIZE - (ba.size() % BLOCK_SIZE);
        for(int i = 0;i < pad;i++)
            ba.write(pad);

        // 加密
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(AES_KEY,"AES"),
                    new IvParameterSpec(AES_KEY,0,16));
        by = cipher.doFinal(ba.toByteArray());
        String encrypt = new BASE64Encoder().encode(by).replaceAll("\r\n","");

        // 签名
        String[] arr =
                {token,timestamp,nonce,encrypt};

        return "<xml>\n" + "<Encrypt><![CDATA[" + encrypt + "]]></Encrypt>\n"
                + "<MsgSignature><![CDATA[" + sign(arr)
                + "]]></MsgSignature>\n" + "<TimeStamp>" + timestamp
                + "</TimeStamp>\n" + "<Nonce><![CDATA[" + nonce
                + "]]></Nonce>\n" + "</xml>";
    }

    static String sign(String[] arr)
    {
        StringBuffer sb = new StringBuffer();
        Arrays.sort(arr);
        for(int i = 0;i < arr.length;i++)
            sb.append(arr[i]);
        return SHA1(sb.toString());
    }

    static String dec(String encrypt) throws Exception
    {
        // 解密
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE,new SecretKeySpec(AES_KEY,"AES"),
                    new IvParameterSpec(AES_KEY,0,16));
        byte[] by = cipher.doFinal(new BASE64Decoder().decodeBuffer(encrypt));

        // 长度
        byte[] len = new byte[4];
        System.arraycopy(by,16,len,0,len.length);

        // 内容
        len = new byte[dec(len)];
        System.arraycopy(by,20,len,0,len.length);
        return new String(len,"UTF-8");
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


    public static int dec(byte[] var0) {
        int var1 = 0;

        for(int var2 = 0; var2 < 4; ++var2) {
            var1 <<= 8;
            var1 |= var0[var2] & 255;
        }

        return var1;
    }

}
