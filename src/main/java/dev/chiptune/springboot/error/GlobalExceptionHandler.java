package dev.chiptune.springboot.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.chiptune.springboot.response.ApiErr;

/**
 * @apiNote GlobalExceptionHandler -> 실제 서비스에서 예외처리 될 시 핸들링(공통 포맷)
 * @version 1.0
 * @author DK
 * @since 2020.07
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErr> globalException(Exception ex) {
        return new ResponseEntity<ApiErr>(new ApiErr("fail", "요청에 실패하였습니다."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
