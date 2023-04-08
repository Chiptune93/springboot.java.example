package dev.chiptune.springboot.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "ApiErr", description = "Api Response Err")
@Getter
@Setter
public class ApiErr {
    @Schema(description = "요청결과 상태", nullable = false, example = "fail")
    private String error_status;
    @Schema(description = "요청결과 메세지", nullable = false, example = "요청이 실패하였습니다.")
    private String error_message;

    public ApiErr(String error_status, String error_message) {
        this.error_status = error_status;
        this.error_message = error_message;
    }
}
