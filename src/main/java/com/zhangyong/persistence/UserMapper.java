package com.zhangyong.persistence;

import com.zhangyong.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>ClassName:</p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @Date 2018/6/26 7:38
 */
@Mapper
public interface UserMapper {

    public User getUserById(@Param("id") int id);

    public List<User> selectUserList();

    public int insert(User user);

    public boolean deleteUserById(@Param("id") int id);
}
