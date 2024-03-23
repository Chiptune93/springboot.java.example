package dev.chiptune.springboot.controller;

import dev.chiptune.springboot.data.SampleEntity;
import dev.chiptune.springboot.repository.SampleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SampleController {

    final
    SampleRepository sampleRepository;

    public SampleController(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @GetMapping("/test")
    public List<SampleEntity> test() {
        System.out.println(sampleRepository.findAll());
        return toList(sampleRepository.findAll());
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

}
