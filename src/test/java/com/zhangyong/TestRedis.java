package com.zhangyong;

import com.zhangyong.utils.ShopinRedisTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>ClassName:${CLASS} </p>
 * <p>Description: </p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @Date 2018/7/4 7:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SecondKillApplication.class)
public class TestRedis {
    @Autowired
    ShopinRedisTemplate<String> redisTemplate;

    @Test
    public void test() {
        redisTemplate.set("233", "123");
        System.out.println(redisTemplate.get("233"));
    }
}
