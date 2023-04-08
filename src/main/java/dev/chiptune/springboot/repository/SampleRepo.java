package dev.chiptune.springboot.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.chiptune.springboot.data.SampleUser;

@Repository
public interface SampleRepo {
    List<SampleUser> getUser();
}
