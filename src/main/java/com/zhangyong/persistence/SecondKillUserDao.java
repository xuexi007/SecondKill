package com.zhangyong.persistence;

import com.zhangyong.domain.SecondKillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/8/28 7:05
 */
@Mapper
public interface SecondKillUserDao {

    @Select("select * from sk_user where id = #{id}")
    public SecondKillUser getById(@Param("id") long id);


}
