package com.zhangyong.controller;

import com.zhangyong.service.SecondKillUserService;
import com.zhangyong.service.impl.RedisServiceImpl;
import com.zhangyong.service.impl.SecondKillUserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>ClassName: 商品 Controller </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/10/7 22:40
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private SecondKillUserService userService;
    @Autowired
    private RedisServiceImpl redisService;

    @RequestMapping("/to_list")
    public String toGoodsList(Model model,
                              @CookieValue(value = SecondKillUserServiceImpl.COOKIE_NAME_TOKEN, required = false) String cookieToken,
                              @RequestParam(value = SecondKillUserServiceImpl.COOKIE_NAME_TOKEN, required = false) String paramToken) {
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            //若cookie 为空,则返回登录页面登录;
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;

        return "goods_list";
    }
}
