package com.zhangyong.persistence;

import com.zhangyong.domain.User;
import org.apache.ibatis.annotations.*;

/**
 * <p>ClassName:</p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @Date 2018/6/26 7:38
 */
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getUserById(@Param("id") int id);

    @Insert("insert into user(id,name) values(#{id},#{name}) ")
    public int insert(User user);

    @Delete("delete from user where id = #{id}")
    public boolean deleteUserById(@Param("id") int id);
}
