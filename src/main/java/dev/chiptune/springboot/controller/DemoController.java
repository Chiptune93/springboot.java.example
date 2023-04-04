package dev.chiptune.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.chiptune.springboot.aop.Logging;
import dev.chiptune.springboot.aop.NoLogging;

@RestController
public class DemoController {

    @GetMapping("/test")
    public String test() {
        return "Test Controller";
    }

    @NoLogging
    @GetMapping("/noLogging")
    public String noLogging() {
        return "No Logging Controller";
    }

    @Logging
    @GetMapping("/logging")
    public String logging() {
        return "Logging Controller";
    }
}
