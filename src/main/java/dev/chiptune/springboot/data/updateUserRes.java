package dev.chiptune.springboot.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "updateUserRes", description = "유저 업데이트 응답 데이터")
@Getter
@Setter
public class updateUserRes {
    @Schema(description = "업데이트 된 사용자 번호", defaultValue = "", allowableValues = {}, example = "")
    private int userNo;
}
