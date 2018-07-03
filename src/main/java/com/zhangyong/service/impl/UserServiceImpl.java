package com.zhangyong.service.impl;

import com.zhangyong.domain.User;
import com.zhangyong.persistence.UserDao;
import com.zhangyong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>ClassName:</p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @Date 2018/6/26 7:41
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Override
    public User selectUserById(int id) {
        User usr = userDao.getUserById(id);
        logger.info("根据ID值所查出user信息为:" + usr.toString());
        return usr;
    }

    @Override
    @Transactional
    public boolean tx() {

        User u1 = new User();
        u1.setId(2);
        u1.setName("张XX");
        userDao.insert(u1);

        User u2 = new User();
        u2.setId(1);
        u2.setName("招XX");
        userDao.insert(u2);
        return true;
    }
}
