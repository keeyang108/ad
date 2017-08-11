package com.kee.ad.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Kee on 2016/10/28.
 */
public class EncryptUtil {

    private final static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);
    private final static String salt = "keeyang108@sina";

    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String encrypt(String data, String sKey) throws Exception {
        if(data != null && data.length() != 0) {
            SecretKeySpec skeySpec = generateKey(sKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(1, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            String encodeStr = Base64.getEncoder().encodeToString(encrypted);
            return encodeStr;
        } else {
            return data;
        }
    }

    public static String encrypt(String data) throws Exception {
        return encrypt(data,salt);
    }

    public static String decrypt(String data, String sKey) throws Exception {
        if(data != null && data.length() != 0) {
            try {
                SecretKeySpec ex = generateKey(sKey);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
                cipher.init(2, ex, iv);
                byte[] encrypted1 = Base64.getDecoder().decode(data);
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception var8) {
                logger.error("解密失败：" + data + ", exception:{}", var8.getMessage());
                return data;
            }
        } else {
            return data;
        }
    }

    public static String decrypt(String data) throws Exception {
        return StringUtils.isNotBlank(data)?decrypt(data, salt):data;
    }


    private static SecretKeySpec generateKey(String secretKey) throws NoSuchAlgorithmException {

        if(secretKey.length() >= 16) {
            secretKey = secretKey.substring(0, 16);
        } else {
            secretKey = StringUtils.leftPad(secretKey, 16, "O");
        }
        byte[] raw1 = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw1, "AES");
        return skeySpec;
    }
}
