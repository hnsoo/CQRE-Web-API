package sch.cqre.api.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import sch.cqre.api.domain.UserEntity;

@Data
@NoArgsConstructor
public class UserDto implements UserDetails {

    private Long userId;
    private Long studentId;
    private String password;
    private String email;
    private String nickname;
    private String role;

    public UserDto(UserEntity user){
        this.userId = user.getUserId();
        this.studentId = user.getStudentId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.role = user.getRole();
     //   this.profile = user.getProfile();
    }


    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(userId)
                .studentId(studentId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .role(role)
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
