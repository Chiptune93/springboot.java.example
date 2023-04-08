package dev.chiptune.springboot.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dev.chiptune.springboot.data.DataJsonClass;
import dev.chiptune.springboot.repository.userRepo;

/**
 * @apiNote Service Sample
 * @version 1.0
 * @author DK
 * @since 2022.07
 */
@Service
public class ApiDataService {

    @Autowired
    userRepo repo;

    public List<DataJsonClass> getV1(HashMap<String, Object> param) {
        List<String> field = Arrays.asList(param.get("field").toString().split(","));
        for (String x : field) {
            param.put(x, true);
        }
        return repo.getV1(param);
    }

    public List<DataJsonClass> getV2(HashMap<String, Object> param) {
        List<String> field = Arrays.asList(param.get("field").toString().split(","));
        for (String x : field) {
            param.put(x, true);
        }
        return repo.getV2(param);
    }

    public String getData2(HashMap<String, Object> param) {
        JsonArray ja = new JsonArray();
        List<DataJsonClass> list = repo.getV1(param);
        for (int i = 0; i < list.size(); i++) {
            JsonObject jo = new JsonObject();
            jo.addProperty("userId", list.get(i).getUserId());
            jo.addProperty("userName", list.get(i).getUserName());
            jo.addProperty("userEmail", list.get(i).getUserEmail());
            jo.addProperty("userAge", list.get(i).getUserAge());
            jo.addProperty("userAddress", list.get(i).getUserAddress());
            jo.addProperty("userEnterDate", list.get(i).getUserEnterDate().toString());
            ja.add(jo);
        }
        return ja.toString();
    }
}
