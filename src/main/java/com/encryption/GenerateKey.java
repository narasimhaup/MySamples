package com.encryption;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GenerateKey {
    public static final String AES = "AES";
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public static void main(String args[]) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(GenerateKey.AES);
        keyGen.init(64);
        SecretKey sk = keyGen.generateKey();
        String temp = new String(Base64.getEncoder().encode(sk.getEncoded()));
        System.out.println("key String:" + temp);
        String key = byteArrayToHexString(sk.getEncoded());
        System.out.println("key:" + key);
    }
}
