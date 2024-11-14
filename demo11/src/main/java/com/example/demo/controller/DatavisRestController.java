package com.example.demo.controller;

import com.example.demo.model.Circle;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DatavisRestController {

    /*
     * commented to avoid overriding the home page / index.html
     */
//    @RequestMapping("/")
//    public String randomGreeting() {
//        return "Hi";
//    }

    @RequestMapping("/int")
    public int intN() {
        return 1;
    }

    @GetMapping("/json")
    @ResponseBody
    public Circle circle(@RequestParam(name="radius", required=false, defaultValue="1") int radius) {
        return new Circle(radius);
    }
    
    /**
     * rest client 
     */
    @Bean
    RestTemplate restTemplate() {
    	return new RestTemplate();
    }
    
    @Autowired
    @Lazy
    RestTemplate restTemplate;
    
    @RequestMapping("/restTest")
    public String rest(@RequestParam(name="location", required=false, defaultValue="san") String location) {
    	String testString = this.restTemplate.getForObject("https://www.metaweather.com/api/location/search/?query=" + location, String.class);
    	return testString;
    }
    
    
    

}
