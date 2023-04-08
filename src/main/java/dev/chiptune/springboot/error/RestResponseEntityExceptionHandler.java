package dev.chiptune.springboot.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/**
 * @apiNote Error Handling -> Server Error 핸들링
 * @version 1.0
 * @author DK
 * @since 2022.07
 */
@Component
public class RestResponseEntityExceptionHandler extends DefaultErrorAttributes {

    /**
     * @apiNote 에러 발생 시, repsonse 통일을 위한 메소드
     * @param webRequest
     * @param options
     * @return
     *         result_status = FAIL
     *         result_message = 잘못된 요청 URL 입니다.
     */
    @Override
    public Map<String, Object> getErrorAttributes(
            WebRequest webRequest, ErrorAttributeOptions options) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("data", null);
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("result_status", "fail");
        errorAttributes.put("result_code", Integer.parseInt(errorAttributes.get("status").toString()));
        errorAttributes.put("result_message", "허용되지 않은 접근 입니다.");
        errorAttributes.put("result", result);
        errorAttributes.remove("timestamp");
        errorAttributes.remove("status");
        errorAttributes.remove("message");
        errorAttributes.remove("error");
        errorAttributes.remove("path");
        if (errorAttributes.containsKey("trace"))
            errorAttributes.remove("trace");
        return errorAttributes;
    }

}
