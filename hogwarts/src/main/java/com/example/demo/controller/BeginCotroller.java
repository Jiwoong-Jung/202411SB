package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BeginCotroller {
    @GetMapping("/")
    public String index() {
        return "main";
    }

    @GetMapping("/info/staff")
    public void info() {

    }
}
