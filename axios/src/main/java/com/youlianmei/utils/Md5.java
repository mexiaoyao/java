package com.youlianmei.utils;

import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.security.MessageDigest;

public class Md5 {
    /**
     * md5加密
     * @param str 待加密字符串
     * @param salt 盐
     * @param rate 需要加密次数
     * @return 16进制加密字符串
     */
    public static String encrypt2ToMD5(String str, String salt, Integer rate) {
        if(StringUtils.isNotEmpty(str)){
            rate = null==rate?1:rate;
            str += null==salt?"":salt;
            try {
                for (int i = 0; i < rate; i++) {
                    // 此 MessageDigest 类为应用程序提供信息摘要算法的功能
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    // 转换为MD5码
                    byte[] digest = md5.digest(str.getBytes("utf-8"));
                    str = ByteUtils.toHexString(digest);
                }
                return str;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return str;
    }
}
