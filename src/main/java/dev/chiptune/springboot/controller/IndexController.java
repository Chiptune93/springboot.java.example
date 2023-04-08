package dev.chiptune.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

    /*
     * @GetMapping("/")
     * public ModelAndView index() {
     * return new ModelAndView("/index.html");
     * }
     */

    @GetMapping("/")
    public ModelAndView index2() {
        return new ModelAndView("/index");
    }

}
