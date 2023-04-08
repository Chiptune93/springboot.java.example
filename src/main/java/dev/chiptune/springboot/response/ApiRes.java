package dev.chiptune.springboot.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "ApiRes", description = "Api Response Format")
@Getter
@Setter
public class ApiRes<T> {
    @Schema(description = "요청결과 상태", nullable = false, example = "success")
    private String result_status;
    @Schema(description = "요청결과", nullable = false, example = "")
    private T result;

    public ApiRes(T result) {
        this.result_status = "success";
        this.result = result;
    }

}
