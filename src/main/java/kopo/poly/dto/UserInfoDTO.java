package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



//회원가입 DTO
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Getter
@Setter
public class UserInfoDTO {
    private String user_seq;
    private String user_email;
    private String user_id;
    private String user_pw;
    private String user_pwdck;
    private String user_name;
    private String user_age;
    private String user_gender;
    private String user_dt;

    public void getUser_id(String id) {
        this.user_id = id;
    }
}
