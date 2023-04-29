package dev.chiptune.springboot.service;

import dev.chiptune.springboot.model.User;
import dev.chiptune.springboot.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<User> getAllUser() {
        return userRepo.getAllUser();
    }
}
