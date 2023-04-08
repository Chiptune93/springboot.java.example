package dev.chiptune.springboot.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * Return Data Sample Class
 */
@Getter
@Setter
/* null이 아닌것만 return */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataJsonClass {

    private String userId;
    private String userName;
    private String userEmail;
    private String userAge;
    private String userAddress;
    /* date format 고정 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date userEnterDate;

}
