package com.zhangyong.service.impl;

import com.zhangyong.config.redis.key.SecondKillUserKey;
import com.zhangyong.domain.SecondKillUser;
import com.zhangyong.exception.GlobalException;
import com.zhangyong.persistence.SecondKillUserDao;
import com.zhangyong.result.CodeMsg;
import com.zhangyong.service.SecondKillUserService;
import com.zhangyong.utils.MD5Util;
import com.zhangyong.utils.UUIDUtils;
import com.zhangyong.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/28 7:36
 */
@Service
public class SecondKillUserServiceImpl implements SecondKillUserService {

    private static final Logger logger = LoggerFactory.getLogger(SecondKillUserServiceImpl.class);
    private static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    private SecondKillUserDao secondKillUserDao;

    @Autowired
    private RedisServiceImpl redisService;

    @Override
    public SecondKillUser getById(long id) {
        return secondKillUserDao.getById(id);
    }

    @Override
    public boolean login(HttpServletResponse response, @Valid LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        //经过一次MD5加密
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        SecondKillUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            //根据手机号码查询,用户不存在;
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        logger.info("从数据库中查询出用户密码为:" + dbPass);
        String saltDB = user.getSalt();
        //经过第二次MD5加密,结合盐值
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        logger.info("两次MD5加密之后密码为:" + calcPass);
        if (!dbPass.equals(calcPass)) {
            //二次加密之后的密码和DB中存储的密码不一致,说明密码错误;
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtils.uuid();
        redisService.set(SecondKillUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(SecondKillUserKey.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }
}
