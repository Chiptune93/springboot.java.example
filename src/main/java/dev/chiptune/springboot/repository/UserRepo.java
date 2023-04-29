package dev.chiptune.springboot.repository;

import java.util.List;

import dev.chiptune.springboot.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo {
    List<User> getAllUser();
}
