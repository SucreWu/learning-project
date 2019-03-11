package com.wujie.learning.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/3/5 14:13
 * @version: V1.0
 */
public class CipherTest {
    private static final byte[] a = {100, 23, 84, 114, 72, 0, 4, 97, 73, 97, 2, 52, 84, 102, 18, 32};



    public static String decrypt(String encDataStr, byte[] key, byte[] iv) {
        byte[] encData = Base64.decodeBase64(encDataStr);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher;
        byte[] decbbdt = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
            decbbdt = cipher.doFinal(encData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decbbdt);
    }

    public static String Decrypt(String sSrc, byte[] raw) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("cKey".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(sSrc.getBytes());
            return new String(original);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static boolean isOdd(int i){
        return (i & 1) == 1;
    }

    public static void main(String[] args) {
        System.out.println(isOdd(2));
    }
}
