package dev.chiptune.springboot.controller;

import java.io.IOException;
import java.util.HashMap;

import dev.chiptune.springboot.service.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@RestController
public class FileUploadController {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    FileUploadService fsvc;

    /**
     * 파일업로드1 - 단순 파일 서버 경로(프로젝트 경로) 업로드
     * 
     * @param req
     */
    @PostMapping("/upload.do")
    public void upload(MultipartRequest req) {
        System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + req + "＃＃＃＃＃＃＃＃＃＃＃");
        fsvc.save(req);
    }

    /**
     * 파일업로드2 - 서버에 업로드 후, 파일 정보 DB 저장
     * 
     * @param req
     */
    @PostMapping("/upload2.do")
    public void upload2(MultipartRequest req) {
        System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + req + "＃＃＃＃＃＃＃＃＃＃＃");
        fsvc.save2(req);
    }

    /**
     * 파일업로드3 - 서버에 업로드 후, 파일 정보 DB 저장 및 예외 처리 등
     * 
     * @param req
     */
    @PostMapping("/upload3.do")
    public HashMap<String, String> upload3(MultipartRequest req) {
        System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + req + "＃＃＃＃＃＃＃＃＃＃＃");
        return fsvc.save3(req);
    }

    /**
     * 파일업로드4 - 다중 업로드 지원
     * 
     * @param files
     * @return
     */
    @PostMapping("/upload4.do")
    public HashMap upload4(@RequestParam MultipartFile[] files) {
        System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + files + "＃＃＃＃＃＃＃＃＃＃＃");
        return fsvc.save4(files);
    }

    /**
     * 파일 다운로드 - DB 저장된 정보 기반으로 파일 다운로드
     * 
     * @param seq
     * @return
     * @throws IOException
     */
    @GetMapping("/download.do")
    @PostMapping("/download.do")
    public ResponseEntity<Resource> download(@RequestParam String seq) throws IOException {
        return fsvc.download(seq);
    }

}
