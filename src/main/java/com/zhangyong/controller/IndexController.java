package com.zhangyong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "Echizen");
        return "hello";
    }
}
