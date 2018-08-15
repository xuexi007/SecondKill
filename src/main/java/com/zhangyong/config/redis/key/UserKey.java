package com.zhangyong.config.redis.key;

import com.zhangyong.config.redis.BasePrefix;

/**
 * <p>ClassName: redis缓存用户自定义key类 </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/15 8:13
 */
public class UserKey extends BasePrefix {
    private UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
