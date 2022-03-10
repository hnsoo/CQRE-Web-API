package sch.cqre.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.repository.UserRepository;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    // @Autowired MockMvc mockMvc;
    // @Autowired ObjectMapper objectMapper;

    @DisplayName("회원가입 테스트")
    @Test
    public void 회원가입(){
        //given
        final int userid = 1;
        final int studentID = 20191111;
        final String email = "testcode@testcode.com";
        final String nickname = "testcode";
        final String password = "testcode1234";
        final String role = "ROLE_GUEST";

        UserEntity userEntity = UserEntity.builder()
                .userId(userid)
                .studentId(studentID)
                .email(email)
                .nickname(nickname)
                .password(password)
                .role(role)
                .build();
        userRepository.save(userEntity);

        //when
        UserEntity resultUser = userRepository.findOnceByEmail(email);

        //then
        Assertions.assertEquals(resultUser.getEmail(), email);
    }

}