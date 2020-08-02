package com.example.jpademo.controller;


import com.example.jpademo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
    @Autowired
    @Qualifier("greeting")
    String greeting;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("greeting", greeting);
        return "students/home";
    }

    @GetMapping("/welcome")
    public String getHome1(Model model) {
        model.addAttribute("greeting", greeting);
        return "students/home";
    }

    @GetMapping("/index")
    public String GetHome() {
        return "students/home";
    }
}
