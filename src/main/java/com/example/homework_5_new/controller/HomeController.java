package com.example.homework_5_new.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/user/login";
    }

    // 这个可以删除，因为已经有 /user/login 了
    // 如果要保留，需要确保返回正确的视图名
    @GetMapping("/login")
    public String loginPage() {
        return "redirect:/user/login"; // 重定向到 user 的登录页面
    }
}