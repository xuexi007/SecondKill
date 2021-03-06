package com.zhangyong.controller;

import com.zhangyong.domain.User;
import com.zhangyong.result.ResultBean;
import com.zhangyong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>ClassName: </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net </p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @Date 2018/6/26 7:42
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public ResultBean<User> selectUserById() {
        return new ResultBean<User>(userService.selectUserById(1));
    }

    @RequestMapping("/tx")
    @ResponseBody
    public ResultBean<Boolean> insertUser() {
        return new ResultBean<Boolean>(userService.insertTx());
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultBean<Boolean> deleteUserById(@PathVariable int id) {
        return new ResultBean<Boolean>(userService.deleteUserById(id));
    }

    @RequestMapping("selectUserList")
    @ResponseBody
    public ResultBean<List<User>> selectUserList() {
        return new ResultBean<List<User>>(userService.selectUserList());
    }

}
