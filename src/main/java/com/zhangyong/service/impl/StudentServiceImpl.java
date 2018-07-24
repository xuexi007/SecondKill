package com.zhangyong.service.impl;

import com.zhangyong.domain.Student;
import com.zhangyong.persistence.student.StudentMapper;
import com.zhangyong.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/7/24 16:34
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Override
    public List<Student> selectStudentList() {
        return studentMapper.selectStudentList();
    }
}
