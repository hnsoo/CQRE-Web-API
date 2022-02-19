package sch.cqre.api.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sch.cqre.api.domain.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@NoArgsConstructor
public class UserDto implements UserDetails {
    private Long userId;


    //TODO : 학번 정규식 적용
    private Long studentId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 2자 이상 10자 이하로 입력해주세요")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요")
    private String nickname;

    private String userType = "Guest"; // defaultValue = "Guest"

    private String profile;

    public UserDto(UserEntity user){
        //Long userId, Long studentId, String password, String email, String nickname, String userType, String profile
        this.userId = user.getUserId();
        this.studentId = user.getStudentId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.userType = user.getUserType();
        this.profile = user.getProfile();
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(userId)
                .studentId(studentId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .userType(userType)
                .profile(profile)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        //TODO : asdasd
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
