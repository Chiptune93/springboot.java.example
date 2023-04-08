package dev.chiptune.springboot.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "ApiNoAuth", description = "Api Response No Auth")
@Getter
@Setter
public class ApiNoAuth {
    @Schema(description = "요청결과 상태", nullable = false, example = "fail")
    private String error_status;
    @Schema(description = "요청결과 메세지", nullable = false, example = "권한이 없습니다.")
    private String error_message;
}
