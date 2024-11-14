package edu.du.sb1114;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class MyController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    public LocalDateTime hello() {
        return LocalDateTime.now();
    }

    @GetMapping("/Account/{name}")
    @ResponseBody
    public String account(@PathVariable String name) {
        log.info(name);
        return name;
    }
}
