package dev.chiptune.springboot.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository {
    int getMasterSeq();

    List<HashMap<String, String>> fileList(HashMap<String, String> parameterMap);

    int insertFile(HashMap<String, String> parameterMap);

    int deleteFile(HashMap<String, String> parameterMap);

    HashMap<String, String> info(int seq);
}
