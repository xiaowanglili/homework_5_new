package com.example.homework_5_new.controller;

import com.example.homework_5_new.model.Discussion;
import com.example.homework_5_new.model.User;
import com.example.homework_5_new.service.DiscussionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/discussion")
public class DiscussionController {
    private final DiscussionService discussionService;

    public DiscussionController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        if (session.getAttribute("currentUser") == null) return "redirect:/user/login";
        model.addAttribute("discussionList", discussionService.getAll());
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "discussionList"; // 对应 templates/discussionList.html
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int id, Model model, HttpSession session) {
        if (session.getAttribute("currentUser") == null) return "redirect:/user/login";
        model.addAttribute("discussion", discussionService.getById(id));
        return "discussionDetail"; // 对应 templates/discussionDetail.html
    }

    @GetMapping("/post")
    public String postPage(HttpSession session) {
        if (session.getAttribute("currentUser") == null) return "redirect:/user/login";
        return "postDiscussion"; // 对应 templates/postDiscussion.html
    }

    @PostMapping("/post")
    public String post(@RequestParam String title,
                       @RequestParam String content,
                       HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) return "redirect:/user/login";
        discussionService.add(title, content, user);
        return "redirect:/discussion/list?t=" + System.currentTimeMillis();
    }

    @PostMapping("/reply")
    public String reply(@RequestParam int discussionId,
                        @RequestParam String content,
                        HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) return "redirect:/user/login";
        discussionService.addReply(discussionId, content, user);
        return "redirect:/discussion/detail?id=" + discussionId + "&t=" + System.currentTimeMillis();
    }
}