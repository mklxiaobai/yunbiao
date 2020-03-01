package com.hengyi.yunbiao.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5Hex(text + key);
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }
}
