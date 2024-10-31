package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/")
    public String index() {
        return "/sample/all";
    }

    @GetMapping("/event2")
    public String event2() {
        return "/event";
    }
}
