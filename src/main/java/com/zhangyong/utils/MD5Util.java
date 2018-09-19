package com.zhangyong.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/21 10:35
 */
public class MD5Util {

    /**
     * @param src
     * @auther zhangyong@shopin.cn
     * @desc 对明文进行加密
     * @date 2018/8/21  10:36
     * @from JDK 1.8
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    //加密盐值;
    private static final String salt = "ilovemyself";

    //输入转密 ，第一层加密
    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    // 第二层加密
    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * @param input
     * @param salt
     * @auther zhangyong@shopin.cn
     * @desc 用户输入转明文密码
     * @date 2018/8/23  6:15
     * @from JDK 1.8
     */
    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
    }
}
