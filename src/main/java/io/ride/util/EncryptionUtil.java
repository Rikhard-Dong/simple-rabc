package io.ride.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午12:04
 */
public class EncryptionUtil {

    public static String md5(String plainText) {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5算法");
        } catch (NullPointerException e) {
            throw new RuntimeException("待加密码为空");
        }
        StringBuilder md5Code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        for (int i = 0; i < 32 - md5Code.length(); i++) {
            md5Code.insert(0, "0");
        }
        return md5Code.toString();
    }

    /*public static String base64Encode(String plainText) {
        return new BASE64Encoder().encode(plainText.getBytes());
    }

    public static String base64DeCode(String plainText) {
        BASE64Decoder decoder = new BASE64Decoder();

        try {
            return new String(decoder.decodeBuffer(plainText));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}
