package com.congdinh.vivuspringboot.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping()
    public String home(
            @RequestParam(value = "name", required = false, defaultValue = "Cong") String name,
            @RequestParam(value = "age", required = false, defaultValue = "10") int age,
            Model model) {
        var numbers = List.of(1, 2, 3, 4, 5);
        model.addAttribute("message", "Hello FJB 03");
        model.addAttribute("numbers", numbers);
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "home/index";
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("home/about");
        modelAndView.addObject("companyName", "ViVu Store");
        return modelAndView;
    }

    @GetMapping("/contact/{id}")
    public String contact(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "home/contact";
    }
}
