package com.zhangyong.config.redis;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/15 8:08
 */
public abstract class BasePrefix implements KeyPrefix {

    private long expireSeconds;

    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    /**
     * @param
     * @auther zhangyong@shopin.cn
     * @desc 默认0表示永不过期
     * @date 2018/8/15  8:09
     * @from JDK 1.8
     */
    @Override
    public long expireSeconds() {

        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
