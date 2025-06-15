package com.themoment.login.member.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignupDTO {
    @JsonProperty("student_name")
    private String student_name; // 사용자 이름

    @JsonProperty("student_id")
    private Long student_id;

    @JsonProperty("email")
    private String email;       // 이메일

    @JsonProperty("password")
    private String password;    // 비밀번호
}