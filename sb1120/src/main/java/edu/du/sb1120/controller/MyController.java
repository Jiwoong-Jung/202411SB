package edu.du.sb1120.controller;

import edu.du.sb1120.repository.MyDataMongo;
import edu.du.sb1120.repository.MyDataMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class MyController {

    @Autowired
    MyDataMongoRepository myDataMongoRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Find Page");
        model.addAttribute("msg", "MyDataMongo");
        List<MyDataMongo> list = myDataMongoRepository.findAll();
        log.info(list.toString());
        model.addAttribute("datalist", list);
        return "index";
    }
    @PostMapping("/")
    public String form(@RequestParam String name,
                       @RequestParam String memo) {
        MyDataMongo myDataMongo = new MyDataMongo(name, memo);
        myDataMongoRepository.save(myDataMongo);
        return "redirect:/";
    }

    @GetMapping("/find")
    public String find(Model model) {
        model.addAttribute("title", "Find Page");
        model.addAttribute("msg", "MyData 예제");
        List<MyDataMongo> list = myDataMongoRepository.findAll();
        model.addAttribute("datalist", list);
        return "find";
    }
    @PostMapping("/find")
    public String search(@RequestParam("find") String param, Model model) {
        if ("".equals(param)) {
            return "redirect:/find";
        } else {
            model.addAttribute("title", "Find result");
            model.addAttribute("msg", param + "의 검색 결과");
            model.addAttribute("value", param);
//            List<MyDataMongo> list = myDataMongoRepository.findByNameLike(param);
            List<MyDataMongo> list = myDataMongoRepository.listByName(param);
            model.addAttribute("datalist", list);
            return  "find";
        }
    }

    @GetMapping("/view")
    @ResponseBody
    public List<MyDataMongo> view() {
        return myDataMongoRepository.findAll();
    }


}
