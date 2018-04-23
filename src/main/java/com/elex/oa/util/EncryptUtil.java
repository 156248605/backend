package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/4/18 10:29
*/
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class EncryptUtil {
    private static final String key = "*&^%$#@!";
    private static String charset = "utf-8";
    private static String isoCharset = "iso-8859-1";

    public EncryptUtil() {
    }

    public static String encryptMd5(String inStr) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
            byte[] e = md.digest(inStr.getBytes());
            return new String(Base64.encodeBase64(e));
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static synchronized String encryptSha256(String inputStr) {
        try {
            MessageDigest e = MessageDigest.getInstance("SHA-256");
            byte[] digest = e.digest(inputStr.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(digest));
        } catch (Exception var3) {
            return null;
        }
    }

    public static String hexToBase64(String input) throws DecoderException {
        String out = null;

        try {
            byte[] bytes = Hex.decodeHex(input.toCharArray());
            out = new String(Base64.encodeBase64(bytes));
        } catch (Exception var3) {
            ;
        }

        return out;
    }

    public static String decrypt(String message) throws Exception {
        byte[] bytesrc = stringToBytes(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec("*&^%$#@!".getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec("*&^%$#@!".getBytes("UTF-8"));
        cipher.init(2, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte, "UTF-8");
    }

    private static byte[] stringToBytes(String temp) {
        byte[] digest = new byte[temp.length() / 2];

        for(int i = 0; i < digest.length; ++i) {
            String byteString = temp.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte)byteValue;
        }

        return digest;
    }

    public static String encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec("*&^%$#@!".getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec("*&^%$#@!".getBytes("UTF-8"));
        cipher.init(1, secretKey, iv);
        String str = bytesToString(cipher.doFinal(message.getBytes("UTF-8")));
        return str;
    }

    private static String bytesToString(byte[] b) {
        StringBuffer hexString = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            String plainText = Integer.toHexString(255 & b[i]);
            if(plainText.length() < 2) {
                plainText = "0" + plainText;
            }

            hexString.append(plainText);
        }

        return hexString.toString();
    }

    public static KeyPairObject genKeyPair() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        String privateKeyStr = SerializeUtil.object2String(privateKey);
        String publicKeyStr = SerializeUtil.object2String(publicKey);
        return new KeyPairObject(privateKeyStr, publicKeyStr);
    }

    private static byte[] handleData(Key k, byte[] data, int encrypt) throws Exception {
        if(k != null) {
            Cipher cipher = Cipher.getInstance("RSA");
            byte[] resultBytes;
            if(encrypt == 1) {
                cipher.init(1, k);
                resultBytes = cipher.doFinal(data);
                return resultBytes;
            }

            if(encrypt == 0) {
                cipher.init(2, k);
                resultBytes = cipher.doFinal(data);
                return resultBytes;
            }

            System.out.println("参数必须为: 1 加密 0解密");
        }

        return null;
    }

    public static String encryptPublicKey(String key, String data) throws Exception {
        RSAPublicKey pubKey = (RSAPublicKey)SerializeUtil.getObjectFromString(key, RSAPublicKey.class);
        byte[] result = handleData(pubKey, data.getBytes(charset), 1);
        String str = new String(Base64Util.encode(result), isoCharset);
        return str;
    }

    public static String decryptPrivateKey(String privateKey, String data) throws Exception {
        RSAPrivateKey priKey = (RSAPrivateKey)SerializeUtil.getObjectFromString(privateKey, RSAPrivateKey.class);
        byte[] tmpBytes = data.getBytes(charset);
        byte[] tmp = Base64Util.decode(tmpBytes);
        byte[] deresult = handleData(priKey, tmp, 0);
        return new String(deresult, isoCharset);
    }

    public static String encryptPrivateKey(String key, String data) throws Exception {
        RSAPrivateKey pubKey = (RSAPrivateKey)SerializeUtil.getObjectFromString(key, RSAPrivateKey.class);
        byte[] result = handleData(pubKey, data.getBytes(charset), 1);
        byte[] tmp = Base64Util.encode(result);
        String str = new String(tmp, isoCharset);
        return str;
    }

    public static String decryptPublicKey(String publicKey, String data) throws Exception {
        RSAPublicKey pubKey = (RSAPublicKey)SerializeUtil.getObjectFromString(publicKey, RSAPublicKey.class);
        byte[] tmp = data.getBytes(isoCharset);
        byte[] tmpBytes = Base64Util.decode(tmp);
        byte[] deresult = handleData(pubKey, tmpBytes, 0);
        return new String(deresult, charset);
    }

    public static void main(String[] args) throws Exception {
        String publicKey = "rO0ABXNyABRqYXZhLnNlY3VyaXR5LktleVJlcL35T7OImqVDAgAETAAJYWxnb3JpdGhtdAASTGphdmEvbGFuZy9TdHJpbmc7WwAHZW5jb2RlZHQAAltCTAAGZm9ybWF0cQB+AAFMAAR0eXBldAAbTGphdmEvc2VjdXJpdHkvS2V5UmVwJFR5cGU7eHB0AANSU0F1cgACW0Ks8xf4BghU4AIAAHhwAAAAojCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA2I7aAFOtZIRa4wdYHStxMHiOKrgOMIHAilbxHEtW0wJNNJIkOuwY+daq2WdgRZlC7sgl/a4eHYRK2p+ilkcBivMJg+qND2+ihqoiiLUgyuEuJa4oRmv8GaJ+K8KXHztf5Tk3ygU7r8jy4vEUGFxMYHIeAflnXIeBQg2DwNsTtA0CAwEAAXQABVguNTA5fnIAGWphdmEuc2VjdXJpdHkuS2V5UmVwJFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0AAZQVUJMSUM=";
        String privateKey = "rO0ABXNyABRqYXZhLnNlY3VyaXR5LktleVJlcL35T7OImqVDAgAETAAJYWxnb3JpdGhtdAASTGphdmEvbGFuZy9TdHJpbmc7WwAHZW5jb2RlZHQAAltCTAAGZm9ybWF0cQB+AAFMAAR0eXBldAAbTGphdmEvc2VjdXJpdHkvS2V5UmVwJFR5cGU7eHB0AANSU0F1cgACW0Ks8xf4BghU4AIAAHhwAAACezCCAncCAQAwDQYJKoZIhvcNAQEBBQAEggJhMIICXQIBAAKBgQDYjtoAU61khFrjB1gdK3EweI4quA4wgcCKVvEcS1bTAk00kiQ67Bj51qrZZ2BFmULuyCX9rh4dhEran6KWRwGK8wmD6o0Pb6KGqiKItSDK4S4lrihGa/wZon4rwpcfO1/lOTfKBTuvyPLi8RQYXExgch4B+Wdch4FCDYPA2xO0DQIDAQABAoGAFPWmhdeTdaIVxdllHtWgi+dvIxVTUkCMqRcHGQz1p1CWtlrapNVLCYtMV+RYfgP6ZW/7tVTP112BfS1sKA1RSbrtIQG7TnVhBLqSjvbhX1aZRNaEMTGzO7ZRz5jVEoULwqqKmQMh2MMPbSsFu8HzEkzQjedMCt1QvVweJdP2UIECQQDurbhlz3pnK5YAzxUYLuh3NhxvZ/GmmVz6v8vfjO2JUfvC/3ppgMh37tFBjUKCQQO3KI+LVn4B/JEoKarqEe3tAkEA6EYqP25EplTfUsiiQDnBe1bc+r6RJzMi8bOPxIBFjC+VVyxC8kQ18MBBiKoeT0ulDx02YY6AbeI3inEiFc8aoQJAH4ntF+b2sbNcuvaiPvPT3AzWbRI7KFyToL6/XebtbHvc3MONlWtjEhYIqLTV2QhmSUmezja7p9+L/taisxNzcQJBAK9iE5Jzo3hoi3wJrKGMOrDz5MWcUSPlM9SHPd4k8N6qKzx4WlBt+sC/mnwj3+EGACsKZr6BCC5wanmpdRA8oiECQQDpI2cPAOE2zdiE5znB0PyS1rIYf0fA46eksuMuYKBfQzT7z0/eP0tyLXhgs4c55HA79HY/dIlcYdfDy88AmO1CdAAGUEtDUyM4fnIAGWphdmEuc2VjdXJpdHkuS2V5UmVwJFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0AAdQUklWQVRF";
        String encStr = encryptPrivateKey(privateKey, "admin@redxun.cn");
        System.out.println(encStr);
        String tmp = decryptPublicKey(publicKey, encStr);
        System.out.println(tmp);
        String encStr1 = encryptPrivateKey(privateKey, "测试加解密");
        String tmp1 = decryptPublicKey(publicKey, encStr1);
        System.out.println(tmp1);
    }
}

