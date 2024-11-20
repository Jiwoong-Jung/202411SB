package project1.mongo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project1.mongo.repository.MyDataMongo;
import project1.mongo.repository.MyDataMongoRepository;

import java.util.Optional;

@RestController
@Slf4j
public class MyRestController {

    @Autowired
    MyDataMongoRepository myDataMongoRepository;

    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") String id, @RequestBody MyDataMongo newData, Model model) {
        log.info("나 여기에 있다!");
        Optional<MyDataMongo> optionalData = myDataMongoRepository.findById(id);
        if (optionalData.isPresent()) {
            MyDataMongo existingData = optionalData.get();
            existingData.setName(newData.getName());
            existingData.setMemo(newData.getMemo());
            myDataMongoRepository.save(existingData);
            model.addAttribute("msg", "Update successful");
        } else {
            model.addAttribute("msg", "Data not found");
        }
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        Optional<MyDataMongo> optionalData = myDataMongoRepository.findById(id);
        if (optionalData.isPresent()) {
            myDataMongoRepository.deleteById(id);
            model.addAttribute("msg", "Delete successful");
        } else {
            model.addAttribute("msg", "Data not found");
        }
        return "redirect:/";
    }
}
