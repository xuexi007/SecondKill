package com.zhangyong.controller;

import com.zhangyong.utils.LoadProperties;
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
        String in = LoadProperties.readValue("mm_url");
        return in;
    }
}
