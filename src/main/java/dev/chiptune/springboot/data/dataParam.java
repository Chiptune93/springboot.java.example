package dev.chiptune.springboot.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "dataParam", description = "유저 조회 파라미터")
@Getter
@Setter
public class dataParam {
    @Schema(description = "요청 개수", defaultValue = "", allowableValues = {}, example = "1")
    private int num;
}
