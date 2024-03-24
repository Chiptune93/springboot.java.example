package dev.chiptune.springboot.repository.mapper;

import dev.chiptune.springboot.data.SampleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MybatisRepository {

    @Select("SELECT id\n" +
            "             , age\n" +
            "             , name\n" +
            "             , city\n" +
            "        FROM\n" +
            "            postgres_person")
    List<SampleEntity> getAll();
}
