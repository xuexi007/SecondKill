package com.zhangyong.service.impl;

import com.sun.deploy.net.HttpResponse;
import com.zhangyong.domain.SecondKillUser;
import com.zhangyong.persistence.SecondKillUserDao;
import com.zhangyong.result.CodeMsg;
import com.zhangyong.service.SecondKillUserService;
import com.zhangyong.utils.MD5Util;
import com.zhangyong.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private SecondKillUserDao secondKillUserDao;

    @Override
    public SecondKillUser getById(long id) {
        return secondKillUserDao.getById(id);
    }

    @Override
    public CodeMsg login(@Valid LoginVo loginVo) {
        if (loginVo == null) {
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        SecondKillUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            //根据手机号码查询,用户不存在;
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!formPass.equals(calcPass)) {
            //二次加密之后的密码和DB中存储的密码不一致,说明密码错误;
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
