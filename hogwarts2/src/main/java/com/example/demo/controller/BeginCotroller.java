package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class BeginCotroller {
    @GetMapping("/")
    public String index() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/info/staff")
    public void info() {

    }

    @GetMapping("/notice")
    public String notice() {
        return "notice/notice";
    }

    @GetMapping("/notice/read")
    public String noticeRead(@RequestParam int id, Model model) {
        log.info("id: {}", id);
        return "notice/read";
    }

    @GetMapping("/test")
    public String test() {
        return "test/boardWrite";
    }
}
