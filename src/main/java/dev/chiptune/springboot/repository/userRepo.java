package dev.chiptune.springboot.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import dev.chiptune.springboot.data.DataJsonClass;

/**
 * @apiNote Repository Sample
 * @version 1.0
 * @author DK
 * @since 2022.07
 */
@Repository
public interface userRepo {

    public List<DataJsonClass> getV1(HashMap<String, Object> param);

    public List<DataJsonClass> getV2(HashMap<String, Object> param);
}
