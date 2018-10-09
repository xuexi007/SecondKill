package com.zhangyong.controller;

import com.zhangyong.result.Result;
import com.zhangyong.service.SecondKillUserService;
import com.zhangyong.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/23 6:19
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    SecondKillUserService secondKillUserService;

    /**
     * @param null
     * @auther zhangyong@shopin.cn
     * @desc 登录页展示;
     * @date 2018/9/13  7:52
     * @from JDK 1.8
     */
    @RequestMapping("to_login")
    public String toLogin() {
        return "login";
    }

    /**
     * @param loginVo
     * @auther zhangyong@shopin.cn
     * @desc 登录操作
     * @date 2018/9/13  7:52
     * @from JDK 1.8
     */
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        logger.info(loginVo.toString());
        secondKillUserService.login(response,loginVo);
        return Result.success(true);
    }
}
