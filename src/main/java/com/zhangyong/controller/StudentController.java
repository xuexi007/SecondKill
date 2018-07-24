package com.zhangyong.controller;

import com.zhangyong.domain.Student;
import com.zhangyong.result.ResultBean;
import com.zhangyong.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/7/24 16:35
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("selectStudentList")
    public ResultBean<List<Student>> selectStudentList() {
        return new ResultBean<List<Student>>(studentService.selectStudentList());
    }
}
