package com.zhangyong.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Value("${mm_url}")
    private String index;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "index,Shopin,zhang";
    }
}
