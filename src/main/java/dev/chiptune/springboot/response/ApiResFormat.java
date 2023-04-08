package dev.chiptune.springboot.response;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

/**
 * @apiNote 요청에 대한 공통 응답 포맷
 * @version 1.0
 * @author DK
 * @since 2022.07
 */
@Getter
@Setter
public class ApiResFormat {
    // result_status -> fail / success
    private String result_status;
    // result_code -> 200 / 4XX / 5XX
    private int result_code;
    // result_message -> NOT ACCEPTED , EXCEPTION ...
    private String result_message;
    // result -> result obj
    private HashMap<String, Object> result = new HashMap<String, Object>();

    /* 결과 예시 */
    /* 
    {
        "result_status": "success",
        "result_code": 200,
        "result_message": "요청에 성공하였습니다.",
        "result": {
            ...
        }
    } 
    */

    public ApiResFormat() {

    }

    public ApiResFormat(String status, int resultCode, String resultMessage, Object resultObj) {
        this.result_status = status;
        this.result_code = resultCode;
        this.result_message = resultMessage;
        result.put("data", resultObj);
    }

    public ApiResFormat(int resultCode, Object resultObj) {
        this.result_status = "success";
        this.result_code = resultCode;
        this.result_message = "요청에 성공하였습니다.";
        result.put("data", resultObj);
    }

    public ApiResFormat(int resultCode, String resultMessage, Object resultObj) {
        this.result_status = "success";
        this.result_code = resultCode;
        this.result_message = resultMessage;
        result.put("data", resultObj);
    }

    public ApiResFormat(String resultMessage, Object resultObj) {
        this.result_status = "success";
        this.result_code = 200;
        this.result_message = resultMessage;
        result.put("data", resultObj);
    }

    public ApiResFormat(Object resultObj) {
        this.result_status = "success";
        this.result_code = 200;
        this.result_message = "요청에 성공하였습니다.";
        result.put("data", resultObj);
    }
}
