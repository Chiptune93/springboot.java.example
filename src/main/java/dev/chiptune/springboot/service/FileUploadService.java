package dev.chiptune.springboot.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dev.chiptune.springboot.ifc.FileStorageService;
import dev.chiptune.springboot.repository.FileUploadRepository;
import dev.chiptune.springboot.util.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@Service
public class FileUploadService implements FileStorageService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Autowired
    FileUploadRepository rpt;

    @Autowired
    FileUtil fu;

    @Override
    public void init() {

    }

    /**
     * 파일업로드1
     */
    @Override
    public void save(MultipartRequest req) {
        MultipartFile file = req.getFile("singleFile");
        try {
            if (file.isEmpty()) {
                throw new Exception("ERROR : file is empty");
            }
            Path root = Paths.get(uploadPath);
            System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + root + "＃＃＃＃＃＃＃＃＃＃＃");
            System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] : " + uploadPath + "＃＃＃＃＃＃＃＃＃＃＃");
            if (!Files.exists(root)) {
                try {
                    Files.createDirectories(Paths.get(uploadPath));
                } catch (Exception e) {
                    throw new Exception("ERROR : can't makr dir");
                }
            }
            try {
                InputStream is = file.getInputStream();
                Files.copy(is, root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                throw new Exception("ERROR : can't makr dir");
            }
        } catch (Exception e) {
            throw new RuntimeException("ERROR : can't save file !");
        }
    }

    /**
     * 파일업로드2
     */
    @Override
    public void save2(MultipartRequest req) {
        MultipartFile file = req.getFile("singleFile2");
        try {
            if (file.isEmpty()) {
                throw new Exception("ERROR : file is empty");
            }
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                try {
                    Files.createDirectories(Paths.get(uploadPath));
                } catch (Exception e) {
                    throw new Exception("ERROR : can't makr dir");
                }
            }
            try {
                InputStream is = file.getInputStream();
                Files.copy(is, root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                throw new Exception("ERROR : can't makr dir");
            }
            // 파일정보 저장
            String fileName = file.getOriginalFilename();
            String fileSize = Long.toString(file.getSize());
            String fileType = file.getContentType();
            String filePath = uploadPath + "/" + fileName;

            HashMap<String, String> fileMap = new HashMap<String, String>();
            fileMap.put("fileName", fileName);
            fileMap.put("fileSize", fileSize);
            fileMap.put("fileType", fileType);
            fileMap.put("filePath", filePath);

            rpt.insertFile(fileMap);

        } catch (Exception e) {
            throw new RuntimeException("ERROR : can't save file !");
        }
    }

    /**
     * 파일업로드3
     */
    @Override
    public HashMap<String, String> save3(MultipartRequest req) {
        HashMap<String, String> result = new HashMap<String, String>();
        MultipartFile file = req.getFile("singleFile3");
        Path uploadPath = fu.getUploadPath("image");
        result = fu.upload(file, uploadPath);
        return result;
    }

    /**
     * 파일업로드4
     * 
     * @param files
     * @return
     */
    @Override
    public HashMap save4(MultipartFile[] files) {
        // 최종 결과 담을 맵 객체
        HashMap result = new HashMap();
        // 업로드된 파일 리스트 객체
        List<HashMap> uploadFileList = new ArrayList<HashMap>();
        // 업로드 경로 설정
        Path uploadPath = fu.getUploadPath("image");
        // 다중 파일 업로드 관리를 위한 master file seq
        int masterSeq = fu.getMasterSeq();
        // Array 반복 하여 파일 업로드 실행
        Arrays.asList(files).stream().forEach(file -> {
            uploadFileList.add(fu.multipleUpload(file, uploadPath, masterSeq));
        });
        // 결과를 담아 result 리턴.
        result.put("fileMasterSeq", masterSeq);
        result.put("uploadFileList", uploadFileList);
        return result;
    }

    /**
     * 파일 다운로드
     */
    public ResponseEntity<Resource> download(String seq) {
        return fu.fileDownload(seq);
    }

}
