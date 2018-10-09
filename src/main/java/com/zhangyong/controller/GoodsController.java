package com.zhangyong.controller;

import com.zhangyong.domain.SecondKillUser;
import com.zhangyong.service.SecondKillUserService;
import com.zhangyong.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String toGoodsList(Model model) {
        model.addAttribute("user",new SecondKillUser());
        return "goods_list";
    }
}
