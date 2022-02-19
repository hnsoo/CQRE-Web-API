package sch.cqre.api.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;


    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 100)
    //TODO : access = JsonProperty.Access.WRITE_ONLY
    private String password;
}
