package dev.chiptune.springboot.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "addUserRes", description = "유저 추가 응답 데이터")
@Getter
@Setter
public class addUserRes {
    @Schema(description = "추가 된 사용자 번호", defaultValue = "", allowableValues = {}, example = "")
    private int userNo;
}
