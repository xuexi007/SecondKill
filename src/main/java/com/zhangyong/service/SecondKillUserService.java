package com.zhangyong.service;

import com.zhangyong.domain.SecondKillUser;
import com.zhangyong.result.CodeMsg;
import com.zhangyong.vo.LoginVo;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/28 7:34
 */
public interface SecondKillUserService {
    public SecondKillUser getById(long id);

    public CodeMsg login(LoginVo loginVo);
}
