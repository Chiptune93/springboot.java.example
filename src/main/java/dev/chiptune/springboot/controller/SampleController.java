package dev.chiptune.springboot.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/api/secure")
    public String securedEndpoint() {
        return "You are authenticated!";
    }
}
