package com.zhangyong.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/27 22:49
 */
public class ValidateUtils {
    //验证手机号
    // 首位数字为1,次位数字为3,4,5,7,8;末尾为0-9 ,一共要有9位;
    private static final Pattern mobile_pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");

    public static boolean isMobile(String src) {
        //手机号为空,返回false
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(src);
        return matcher.matches();
    }
}
