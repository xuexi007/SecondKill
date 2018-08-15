package com.zhangyong.config.redis;

/**
 * <p>ClassName:  通用前缀 </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/14 22:37
 */
public interface KeyPrefix {
    //过期时间
    public long expireSeconds();
    public String getPrefix();
}
