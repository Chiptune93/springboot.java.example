package dev.chiptune.springboot.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "data", description = "유저 데이터")
@Getter
@Setter
public class data {
    @Schema(description = "유저 번호", defaultValue = "", allowableValues = {}, example = "1")
    private int userNo;
    @Schema(description = "유저 이름", defaultValue = "", allowableValues = {}, example = "user1")
    private String username;
}
