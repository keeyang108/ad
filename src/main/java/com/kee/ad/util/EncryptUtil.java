package com.kee.ad.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * Created by Kee on 2016/10/28.
 */
public class EncryptUtil {

    private final static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);
    private final static String salt = "fdaf314423#$()W#!$FDSFAFAF34134fdsFQEEQR0-";

    /**
     * MD5加密
     *
     * @param message
     * @return
     */
    public static String MD5Encrypt(String message) {

        try {
            byte[] bytes = (message + salt).getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] md = messageDigest.digest();
            String result = Hex.encodeHexString(md);
            return result;
        } catch (Exception e) {
            logger.error("************MD5加密异常，错误信息：%s", e.getMessage());
            return null;
        }

    }
}
