package com.zhangyong.controller;

import com.zhangyong.result.CodeMsg;
import com.zhangyong.result.ResultBean;
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
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public ResultBean<Boolean> doLogin(LoginVo loginVo) {
        logger.info(loginVo.toString());
        String inputPassword = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(inputPassword)) {
            return ResultBean.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)) {
            return ResultBean.error(CodeMsg.MOBILE_EMPTY);
        }
        if (!ValidateUtils.isMobile(mobile)) {
            return ResultBean.error(CodeMsg.MOBILE_ERROR);
        }
        secondKillUserService.login(loginVo);
        return null;
    }

}
