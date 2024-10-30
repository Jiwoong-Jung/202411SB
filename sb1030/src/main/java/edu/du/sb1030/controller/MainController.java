package edu.du.sb1030.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class MainController {

    @GetMapping("/main")
    public void main() {

    }
    @GetMapping("/admin")
    public void admin() {

    }
}
