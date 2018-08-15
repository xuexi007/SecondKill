package com.zhangyong.service;

import com.alibaba.fastjson.JSON;
import com.zhangyong.config.redis.KeyPrefix;
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
 * @date 2018/8/8 22:44
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;


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
    public <T> boolean set(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            jedis.set(key, str);
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
}
