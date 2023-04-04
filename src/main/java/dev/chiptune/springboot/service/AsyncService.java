package dev.chiptune.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

  @Async("threadPoolTaskExecutor")
  public String async() {
    return "async methods";
  }
}
