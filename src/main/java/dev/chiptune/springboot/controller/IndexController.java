package dev.chiptune.springboot.controller;

import dev.chiptune.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String test(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "/test";
    }
}
