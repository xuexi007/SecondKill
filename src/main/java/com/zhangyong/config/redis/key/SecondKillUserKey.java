package com.zhangyong.config.redis.key;

import com.zhangyong.config.redis.BasePrefix;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/10/7 22:28
 */
public class SecondKillUserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public SecondKillUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SecondKillUserKey token = new SecondKillUserKey(TOKEN_EXPIRE, "tk");
}
