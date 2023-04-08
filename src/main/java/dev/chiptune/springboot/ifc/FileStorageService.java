package dev.chiptune.springboot.ifc;

import java.util.HashMap;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

public interface FileStorageService {
    void init();

    /**
     * Basic File Upload
     * jsp form --> multipartRequest
     * Server File Dir upload
     * 
     * @param req
     */
    void save(MultipartRequest req);

    /**
     * File Upload 2
     * upload 1 + file information + insert DB
     */
    void save2(MultipartRequest req);

    /**
     * File Upload 3
     * upload2 + exception + functionalize
     * 
     * @param req
     */
    HashMap<String, String> save3(MultipartRequest req);

    /**
     * File Upload 4
     * MultipartFile[] upload
     * @param files
     * @return
     */
    HashMap save4(MultipartFile[] files);

    /**
     * File Download
     * 
     * @param seq
     * @return
     */
    ResponseEntity<Resource> download(String seq);

}
