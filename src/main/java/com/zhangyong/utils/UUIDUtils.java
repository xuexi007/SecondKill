package com.zhangyong.utils;

import java.util.UUID;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/10/7 20:10
 */
public class UUIDUtils {
    //生成任意一个不带横杠的UUID;
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
