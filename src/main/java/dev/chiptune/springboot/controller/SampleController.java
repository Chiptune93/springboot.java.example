package dev.chiptune.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dev.chiptune.springboot.service.SampleService;

@RestController
public class SampleController {
    @Autowired
    SampleService svc;

    @GetMapping(value = "/index.do")
    public ModelAndView main() {
        return new ModelAndView("/index.main", "result", svc.getUser());
    }
}
