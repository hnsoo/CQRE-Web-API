package sch.cqre.api.dto;

import lombok.*;
import sch.cqre.api.jwt.Role;

import javax.validation.constraints.*;

public class AccountDto {

    @Setter
    @Builder
    @AllArgsConstructor
    public static class Info {
        private int userId;
        private String email;
    }

    @Setter
    @Getter
    public static class LoginResponse{
        private int userId;
        @Email
        private String email;
        private int studentId;
        @NotBlank
        private String nickname;
        @NotBlank
        private String role;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest{
        @Email
        @NotBlank
        private String email;

        @NotBlank
        @Size(min = 8, max = 100)
        private String password;
    }

    @Setter
    @Getter
    @Builder
    public static class SignupRequest{
        @Email//이메일만 입력가능
        private String email;

        @Min(value = 20000000, message = "학번을 제대로 입력해주세요")
        @Max(value = 29999999, message = "학번을 제대로 입력해주세요")
        private int studentId;

        @NotBlank
        @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$", message = "한글, 영어, 숫자만 입력 가능합니다") //닉네임 한글 영어 숫자만 사용가능
        @Size(min=2,max = 10, message = "2자부터 10자까지 가능합니다")
        private String nickname;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]{8,20}$", message = "영문+숫자 조합으로 8~20자만 가능합니다") //비밀번호는 숫자+영문 조합으로 8~20자
        private String password;

        private final String role = Role.define.role_GUEST; //가입시 권한 guest 자동 설정
    }





}
