package edu.du.sb1030.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class MainController {

    @GetMapping("/view/main")
    public void main() {

    }
    @GetMapping("/view/admin")
    public void admin() {

    }

    @GetMapping("/main")
    public String main2() {
        return "/login/main";
    }
}
