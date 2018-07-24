package com.zhangyong.service;

import com.zhangyong.domain.Student;

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
public interface StudentService {

    public List<Student> selectStudentList();
}
