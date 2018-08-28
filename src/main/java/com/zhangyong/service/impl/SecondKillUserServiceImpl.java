package com.zhangyong.service.impl;

import com.zhangyong.domain.SecondKillUser;
import com.zhangyong.persistence.SecondKillUserDao;
import com.zhangyong.service.SecondKillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
