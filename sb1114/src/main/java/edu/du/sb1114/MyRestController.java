package edu.du.sb1114;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class MyRestController {
    @GetMapping("/hello")
    public LocalDateTime hello() {
        return LocalDateTime.now();
    }

    @GetMapping("/Account/{name}")
    public String account(@PathVariable String name) {
        log.info(name);
        return name;
    }
}
