package com.zhangyong.controller;

import com.zhangyong.result.CodeMsg;
import com.zhangyong.result.Result;
import com.zhangyong.service.SecondKillUserService;
import com.zhangyong.utils.ValidateUtils;
import com.zhangyong.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @RequestMapping("/do_Login")
    @ResponseBody
    public Result<Boolean> doLogin(LoginVo loginVo) {
        logger.info(loginVo.toString());
        String inputPassword = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(inputPassword)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (!ValidateUtils.isMobile(mobile)) {
            return Result.error(CodeMsg.MOBILE_ERROR);
        }
        CodeMsg codeMsg = secondKillUserService.login(loginVo);
        if (codeMsg.getCode() == 0) {
            logger.info("登录成功,服务端返回信息为:" + Result.success(true));
            return Result.success(true);
        } else {
            logger.info("登录失败,服务端返回信息为:" + Result.error(codeMsg));
            return Result.error(codeMsg);
        }
    }
}
