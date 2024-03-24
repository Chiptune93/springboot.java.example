package dev.chiptune.springboot.repository.data;

import dev.chiptune.springboot.data.SampleEntity;
import org.springframework.data.repository.CrudRepository;

public interface SampleRepository extends CrudRepository<SampleEntity, Integer> {
}
