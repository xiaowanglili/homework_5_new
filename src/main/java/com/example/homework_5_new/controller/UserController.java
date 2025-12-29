package com.example.homework_5_new.controller;

import com.example.homework_5_new.model.User;
import com.example.homework_5_new.service.UserService;
import com.example.homework_5_new.util.CaptchaUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model) {
        // 从 session 获取错误消息并传递给模板
        String error = (String) session.getAttribute("error");
        if (error != null) {
            model.addAttribute("error", error);
            session.removeAttribute("error"); // 清除 session 中的错误
        }
        return "login"; // 对应 templates/login.html
    }

    @GetMapping("/register")
    public String registerPage(HttpSession session, Model model) {
        // 从 session 获取错误消息并传递给模板
        String error = (String) session.getAttribute("error");
        if (error != null) {
            model.addAttribute("error", error);
            session.removeAttribute("error"); // 清除 session 中的错误
        }
        return "register"; // 对应 templates/register.html
    }

    @GetMapping("/captcha")
    public void captcha(HttpSession session, HttpServletResponse resp) throws IOException {
        String captcha = CaptchaUtil.generateText();
        session.setAttribute("captcha", captcha);
        resp.setContentType("image/png");
        BufferedImage image = CaptchaUtil.generateImage(captcha);
        ImageIO.write(image, "png", resp.getOutputStream());
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String captcha,
                        HttpSession session) {
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captcha)) {
            session.setAttribute("error", "验证码错误");
            return "redirect:/user/login";
        }
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("currentUser", user);
            return "redirect:/discussion/list";
        }
        session.setAttribute("error", "用户名或密码错误");
        return "redirect:/user/login";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean ok = userService.register(user);
        if (ok) return "redirect:/user/login";
        session.setAttribute("error", "用户名已存在");
        return "redirect:/user/register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}