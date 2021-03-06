package com.zhangyong.controller;

import com.zhangyong.result.ResultBean;
import com.zhangyong.service.RedisService;
import com.zhangyong.service.UserService;
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
    RedisService redisService;

    @RequestMapping("/redis/set")
    @ResponseBody
    public ResultBean<Boolean> redisSet() {
        boolean set = redisService.set("1", "zhangyong");
        return new ResultBean<>(set);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public ResultBean<String> redisGet() {
        String key1 = redisService.get("1", String.class);
        return new ResultBean<String>(key1);
    }
}
