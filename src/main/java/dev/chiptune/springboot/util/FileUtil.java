package dev.chiptune.springboot.util;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

import dev.chiptune.springboot.repository.FileUploadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 유틸 클래스
 */
@Component
public class FileUtil {
    // 서버 업로드 경로
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    // 최대 파일 크기
    private long MAX_SIZE = 52428800;

    // 파일 정보 저장 시, 레파지토리
    @Autowired
    FileUploadRepository rpt;

    /**
     * 업로드 경로 구하기
     * 
     * @param type
     * @return
     */
    public Path getUploadPath(String type) {
        // 파일은 기본적으로 날짜 기준 (yyyymmdd) 으로 폴더를 구분
        LocalDate ld = LocalDate.now();
        String date = ld.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String typeFolder = "";
        // 타입에 따라 날짜 내부에 폴더 구분
        if (type.equals("image")) {
            typeFolder = "image";
        } else if (type.equals("document")) {
            typeFolder = "document";
        } else {
            typeFolder = "";
        }
        // 업로드 경로를 조합
        uploadPath += File.separator + date + File.separator + typeFolder;
        // 조합된 경로 체크
        Path dir = Paths.get(uploadPath);
        // 해당 경로 존재하는지 체크
        if (!Files.exists(dir)) {
            try {
                // 경로가 없다면 생성
                Files.createDirectories(dir);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return dir;
    }

    /**
     * 업로드 하기
     * 
     * @param file
     * @param path
     */
    public HashMap<String, String> upload(MultipartFile file, Path path) {
        HashMap<String, String> result = new HashMap<String, String>();
        // 파일 정보
        String fileName = file.getOriginalFilename();
        String fileSize = Long.toString(file.getSize());
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        String fileType = file.getContentType();
        String filePath = "";
        // 결과 정보
        String status = "";
        String message = "";
        // 예외처리 하기

        // 1. 파일 사이즈
        if (file.getSize() > MAX_SIZE) {
            status = "fail";
            message = "file over max upload size";
            result.put("status", status);
            result.put("message", message);
            return result;
        }

        // 2. 파일 확장자
        // 화이트 리스트 방식으로 파일 확장자 체크
        if (!Arrays.asList("jpg", "png", "gif", "jpeg", "bmp", "xlsx", "ppt", "pptx", "txt", "hwp")
                .contains(fileExt.toLowerCase())) {
            status = "fail";
            message = "file type is not allowed";
            result.put("status", status);
            result.put("message", message);
            return result;
        }

        // 3. 저장 파일 이름 랜덤화
        String tempName = fileName.substring(0, fileName.lastIndexOf("."));
        String encFileName = Base64.getEncoder().encodeToString(tempName.getBytes());
        System.out.println("File: FileUtil.java ~ line: (116)  ---> encFileName: " + encFileName);
        // 암호화된 경로로 패스 설정
        filePath = path.toString() + File.separator + encFileName + "." + fileExt;
        System.out.println("File: FileUtil.java ~ line: (119)  ---> filePath: " + filePath);

        // 4. 파일정보 맵에 담기.
        HashMap<String, String> fileInfo = new HashMap<String, String>();

        fileInfo.put("fileName", fileName);
        fileInfo.put("encFileName", encFileName);
        System.out.println("File: FileUtil.java ~ line: (126)  ---> encFileName: " + encFileName);
        fileInfo.put("fileSize", fileSize);
        fileInfo.put("fileExt", fileExt);
        fileInfo.put("fileType", fileType);
        fileInfo.put("filePath", filePath);

        try {
            InputStream is = file.getInputStream();
            System.out.println(
                    "File: FileUtil.java ~ line: (135)  ---> path : " + path.resolve(encFileName + "." + fileExt));
            System.out.println(
                    "File: FileUtil.java ~ line: (135)  ---> path : " + path.resolve(encFileName + "." + fileExt));
            System.out.println(
                    "File: FileUtil.java ~ line: (135)  ---> path : " + path.resolve(encFileName + "." + fileExt));
            Files.copy(is, path.resolve(encFileName + "." + fileExt), StandardCopyOption.REPLACE_EXISTING);

            // 파일 저장에 성공하면 DB에 저장하기
            int seq = rpt.insertFile(fileInfo);

            status = "success";
            message = "upload complete";
        } catch (Exception e) {
            e.printStackTrace();
            status = "fail";
            message = "upload fail";
        }
        result.put("status", status);
        result.put("message", message);
        return result;
    }

    /**
     * 파일 다중 업로드
     * 
     * @param file
     * @param path
     * @param masterSeq
     * @return
     */
    public HashMap<String, String> multipleUpload(MultipartFile file, Path path, int masterSeq) {
        HashMap result = new HashMap();
        // 파일 정보
        String fileName = file.getOriginalFilename();
        String fileSize = Long.toString(file.getSize());
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        String fileType = file.getContentType();
        String filePath = "";
        // 결과 정보
        String status = "";
        String message = "";
        String fileSeq = "";
        // 예외처리 하기

        // 1. 파일 사이즈
        if (file.getSize() > MAX_SIZE) {
            status = "fail";
            message = "file over max upload size";
            result.put("status", status);
            result.put("message", message);
            return result;
        }

        // 2. 파일 확장자
        // 화이트 리스트 방식으로 파일 확장자 체크
        if (!Arrays.asList("jpg", "png", "gif", "jpeg", "bmp", "xlsx", "ppt", "pptx", "txt", "hwp")
                .contains(fileExt.toLowerCase())) {
            status = "fail";
            message = "file type is not allowed";
            result.put("status", status);
            result.put("message", message);
            return result;
        }

        // 3. 저장 파일 이름 랜덤화
        String tempName = fileName.substring(0, fileName.lastIndexOf("."));
        String encFileName = Base64.getEncoder().encodeToString(tempName.getBytes());
        // 암호화된 경로로 패스 설정
        filePath = path.toString() + File.separator + encFileName + "." + fileExt;

        // 4. 파일정보 맵에 담기.
        HashMap fileInfo = new HashMap<String, String>();
        HashMap<String, String> uploadedFileInfo = new HashMap<String, String>();

        fileInfo.put("fileName", fileName);
        fileInfo.put("encFileName", encFileName);
        fileInfo.put("fileSize", fileSize);
        fileInfo.put("fileExt", fileExt);
        fileInfo.put("fileType", fileType);
        fileInfo.put("filePath", filePath);
        fileInfo.put("fileMasterSeq", masterSeq);

        try {
            InputStream is = file.getInputStream();
            Files.copy(is, path.resolve(encFileName + "." + fileExt), StandardCopyOption.REPLACE_EXISTING);

            // 파일 저장에 성공하면 DB에 저장하기
            fileSeq = Integer.toString(rpt.insertFile(fileInfo));
            uploadedFileInfo = rpt.info(Integer.parseInt(fileSeq));

            status = "success";
            message = "upload complete";

        } catch (Exception e) {
            e.printStackTrace();
            status = "fail";
            message = "upload fail";
        }
        result.put("status", status);
        result.put("message", message);
        result.put("fileMasterSeq", masterSeq);
        result.put("fileInfo", uploadedFileInfo);
        return result;
    }

    /**
     * 파일 다운로드 하기
     * 
     * @param seq
     * @return
     */
    public ResponseEntity<Resource> fileDownload(String seq) {

        /* 추가 되어야 할 구간 */
        /* Front 암호화 시퀀스 --> Back 복호화하여 조회 */
        /* 추가 되어야 할 구간 */

        // 파일 정보 가져오기
        HashMap<String, String> fileInfo = rpt.info(Integer.parseInt(seq));
        // 파일이 없을 경우 예외처리
        if (fileInfo == null) {
            // 파일 정보가 없는경우 404 에러 발생.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            // 가져온 파일 정보에 따라, 가져올 경로 설정
            Path path = Paths.get(fileInfo.get("file_path").toString());

            // Response 설정.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename(fileInfo.get("file_name").toString(), StandardCharsets.UTF_8).build());
            headers.add(HttpHeaders.CONTENT_TYPE, fileInfo.get("file_type").toString());
            headers.add(HttpHeaders.CONTENT_LENGTH, fileInfo.get("file_size").toString());

            Resource resource = new InputStreamResource(Files.newInputStream(path));

            // 파일 존재하는 경우, 정상 응답
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (Exception e) {
            // 처리 중, 오류 발생 시 500 에러 발생.
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 파일 업로드 마스터 시퀀스 구하기
     * 
     * @return
     */
    public int getMasterSeq() {
        int masterSeq = rpt.getMasterSeq();
        return masterSeq;
    }
}
