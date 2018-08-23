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

    //过期时间
    private long expireSeconds;
    //前缀
    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    //默认构造器中 key的过期时间设置为0,代表永不过期;
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
        //获取不同key的所属类名来区分key
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
