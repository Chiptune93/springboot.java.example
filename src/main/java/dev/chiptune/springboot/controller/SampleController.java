package dev.chiptune.springboot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SampleController {

    /**
     * Annotation to cors
     */
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public int SampleGetData() {
    	/* ... */
        return 200;
    }
}