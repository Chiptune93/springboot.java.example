package dev.chiptune.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.chiptune.springboot.service.AsyncService;
import dev.chiptune.springboot.service.SyncService;

@RestController
public class DemoController {

  @Autowired
  SyncService syncService;

  @Autowired
  AsyncService asyncService;

  @GetMapping("/sync")
  public String sync() {
    return syncService.sync();
  }

  @GetMapping("/async")
  public String async() {
    return asyncService.async();
  }


}
