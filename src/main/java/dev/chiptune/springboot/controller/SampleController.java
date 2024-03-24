package dev.chiptune.springboot.controller;

import dev.chiptune.springboot.data.SampleEntity;
import dev.chiptune.springboot.repository.data.SampleRepository;
import dev.chiptune.springboot.repository.mapper.MybatisRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SampleController {

    final
    SampleRepository sampleRepository;

    final MybatisRepository mybatisRepository;

    public SampleController(SampleRepository sampleRepository, MybatisRepository mybatisRepository) {
        this.sampleRepository = sampleRepository;
        this.mybatisRepository = mybatisRepository;
    }

    @GetMapping("/test")
    public List<SampleEntity> test() {
        System.out.println(sampleRepository.findAll());
        return toList(sampleRepository.findAll());
    }

    @GetMapping("/testMybatis")
    public List<SampleEntity> testMybatis() {
        System.out.println(mybatisRepository.getAll());
        return mybatisRepository.getAll();
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

}
