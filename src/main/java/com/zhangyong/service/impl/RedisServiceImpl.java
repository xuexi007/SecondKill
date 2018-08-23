package com.zhangyong.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangyong.config.redis.KeyPrefix;
import com.zhangyong.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/20 18:21
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    JedisPool jedisPool;


    /**
     * @param prefix
     * @param key
     * @param clazz
     * @auther zhangyong@shopin.cn
     * @desc key+前缀构建实际存储key
     * @date 2018/8/20  16:47
     * @from JDK 1.8
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * @param key
     * @param value
     * @auther zhangyong@shopin.cn
     * @desc 将键值对放入到缓存中;
     * @date 2018/8/9  7:18
     * @from JDK 1.8
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            long expireSeconds = prefix.expireSeconds();
            //如果过期时间设置为<=0,则认为是永不过期的;
            if (expireSeconds <= 0) {
                jedis.set(realKey, str);
            } else {
                //设置有效期的存储
                jedis.setex(realKey, (int) expireSeconds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * @param value
     * @auther zhangyong@shopin.cn
     * @desc 实体类转字符串
     * @date 2018/8/9  7:32
     * @from JDK 1.8
     */
    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return value + "";
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return value + "";
        } else {
            return JSON.toJSONString(value);
        }

    }

    /**
     * @param str
     * @auther zhangyong@shopin.cn
     * @desc 将字符串返回到Bean对象;
     * @date 2018/8/9  7:02
     * @from JDK 1.8
     */
    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * @param jedis
     * @auther zhangyong@shopin.cn
     * @desc 将jedis关闭, 使其返回到连接池中;
     * @date 2018/8/9  7:01
     * @from JDK 1.8
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * @param prefix
     * @param key
     * @auther zhangyong@shopin.cn
     * @desc 判断key值是否存在
     * @date 2018/8/20  18:23
     * @from JDK 1.8
     */
    private <T> boolean exist(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * @param prefix
     * @param key
     * @auther zhangyong@shopin.cn
     * @desc 增加key [原子操作]
     * @date 2018/8/20  18:34
     * @from JDK 1.8
     */
    private <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * @param prefix
     * @param key
     * @auther zhangyong@shopin.cn
     * @desc 减少值
     * @date 2018/8/20  18:34
     * @from JDK 1.8
     */
    private <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }
}
