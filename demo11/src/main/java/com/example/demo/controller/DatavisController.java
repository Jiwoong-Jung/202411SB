package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

/**
 *
 * @author U51199
 *
 */
@Controller
public class DatavisController implements ServletContextAware {

    @Autowired
    ServletContext context;

    @GetMapping("/welcome")
    public String welcome(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model) {
        model.addAttribute("name", name);
        return "welcome/welcome";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String home(@RequestParam(name="name", required=false, defaultValue="User") String name, Model model)
            throws ServletException, IOException {
        model.addAttribute("name", name);
//        String d3Path = new DatavisServlet().getRealPath("/vendor/d3/data/data.tsv");
//        String uploadPath = context.getRealPath("") + File.separator + "/vendor/d3/data/data.tsv";
//        model.addAttribute("data", uploadPath);
        List<Integer> array = new ArrayList<Integer>();
        array.add(100);
        array.add(200);
        array.add(300);
        array.add(400);
        array.add(100);
        array.add(200);
        array.add(300);
        array.add(400);
        model.addAttribute("data", array);
        return "dashboard/dashboard";
    }

    @Override
    public void setServletContext(ServletContext arg0) {
        // TODO Auto-generated method stub

    }

    @GetMapping("/aaa")
    public String aaa(Model model) {
        return "dashboard/aaa";
    }

    @GetMapping("/bbb")
    public String bbb(Model model) {
        return "dashboard/bbb";
    }

    @GetMapping("/ccc")
    public String ccc(Model model) {
        return "dashboard/ccc";
    }

    @GetMapping("/ddd")
    public String ddd(Model model) {
        return "dashboard/ddd";
    }

}
