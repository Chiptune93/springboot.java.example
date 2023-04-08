package dev.chiptune.springboot.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "deleteUserRes", description = "유저 삭제 응답 데이터")
@Getter
@Setter
public class deleteUserRes {
    @Schema(description = "삭제 된 사용자 번호", defaultValue = "", allowableValues = {}, example = "")
    private int userNo;
}
