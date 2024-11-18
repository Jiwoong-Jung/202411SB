package edu.du.sb1115;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyContrllor {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @RequestMapping(value = "/dataSend",method = RequestMethod.POST)
    public String dataSend(Model model, MessageDTO dto){
        model.addAttribute("msg",dto.getResult()+"/ 서버에서 붙여준 값입니다");
        return "/test :: #resultDiv";
    }

}
