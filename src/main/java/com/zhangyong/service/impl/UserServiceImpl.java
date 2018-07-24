package com.zhangyong.service.impl;

import com.zhangyong.domain.User;
import com.zhangyong.persistence.user.UserMapper;
import com.zhangyong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    UserMapper userMapper;

    @Override
    public User selectUserById(int id) {
        User usr = userMapper.getUserById(id);
        logger.info("根据ID值所查出user信息为:" + usr.toString());
        return usr;
    }

    @Override
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public boolean insertTx() {
        User u1 = new User();
        u1.setId(2);
        u1.setUsername("张XX");
        userMapper.insert(u1);
        User u2 = new User();
        u2.setId(1);
        u2.setUsername("招XX");
        userMapper.insert(u2);
        return true;
    }

    @Override
    public boolean deleteUserById(int id) {
        boolean b = userMapper.deleteUserById(id);
        return b;
    }
}
