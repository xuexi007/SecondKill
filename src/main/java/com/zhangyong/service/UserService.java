package com.zhangyong.service;

import com.zhangyong.domain.User;

/**
 * <p>ClassName:</p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @Date 2018/6/26 7:40
 */
public interface UserService {
    public User selectUserById(int id);

    public boolean tx();
}
