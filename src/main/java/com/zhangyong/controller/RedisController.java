package com.zhangyong.controller;

import com.zhangyong.config.redis.key.UserKey;
import com.zhangyong.domain.User;
import com.zhangyong.result.Result;
import com.zhangyong.service.UserService;
import com.zhangyong.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/8 22:43
 */
@Controller
@RequestMapping("/demo")
public class RedisController {

    @Autowired
    UserService userService;
    @Autowired
    RedisServiceImpl redisService;

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User(1, "我静", 18);
        // key：Userkey:id1 确保不同的模块key是不一样的;
        boolean set = redisService.set(UserKey.getById, 1 + "", user);
        return Result.success(set);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }
}
