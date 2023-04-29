package dev.chiptune.springboot.controller;

import dev.chiptune.springboot.model.User;
import dev.chiptune.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

}
