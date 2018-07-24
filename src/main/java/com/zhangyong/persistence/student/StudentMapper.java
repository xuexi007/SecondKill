package com.zhangyong.persistence.student;

import com.zhangyong.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/7/24 16:33
 */
@Mapper
public interface StudentMapper {
    public List<Student> selectStudentList();
}
