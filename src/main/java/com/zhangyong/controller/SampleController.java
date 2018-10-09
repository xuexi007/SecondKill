package com.zhangyong.controller;

import com.zhangyong.config.redis.key.UserKey;
import com.zhangyong.domain.User;
import com.zhangyong.result.Result;
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
 * @date 2018/10/8 7:14
 */
@Controller
public class SampleController {

    @Autowired
    private RedisServiceImpl redisService;

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(1);
        user.setUsername("1111");
        redisService.set(UserKey.getById, "" + 1, user);
        return Result.success(true);
    }
}
