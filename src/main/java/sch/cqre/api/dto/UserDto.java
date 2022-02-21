package sch.cqre.api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.jwt.Role;

import javax.validation.constraints.*;
import java.util.Collection;

@Data
@NoArgsConstructor
public class UserDto implements UserDetails {
    private int userId;

    private int studentId;

    private String password;

    private String email;

    private String nickname;

    private String role;
    //UserRole userType;
    //TODO : 디폴트 변수 상수화 - hun
   // private String profile;

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
          //      .profile(profile)
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
