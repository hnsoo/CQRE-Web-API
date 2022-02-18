package sch.cqre.api.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sch.cqre.api.domain.UserEntity;

@Data
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private Long studentId;
    private String password;
    private String email;
    private String nickname;
    private String userType; // defaultValue = "Guest"
    private String profile;


    @Builder
    public UserDto(Long userId, Long studentId, String password, String email, String nickname, String userType, String profile){
        this.userId = userId;
        this.studentId = studentId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.userType = userType;
        this.profile = profile;
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(userId)
                .studentId(studentId)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .nickname(nickname)
                .userType(userType)
                .profile(profile)
                .build();
    }


}
