package sch.cqre.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.AccountDto;
import sch.cqre.api.repository.UserRepository;

import static sch.cqre.api.jwt.JwtFilter.AUTHORIZATION_HEADER;

@SpringBootTest
@Transactional
@WebAppConfiguration
@PropertySource("classpath:config.properties")
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;
    private AccountDto accountDto;

    // @Autowired MockMvc mockMvc;
    // @Autowired ObjectMapper objectMapper;


    //회원정보 사전 설정///
    public class Account{
    final int studentID = 20191000;
    final String email = "testcode@testcode.com";
    final String nickname = "testnick12";
    final String rawPassword = "testpassword1234";
    }
    private final Account account = new Account();
    ////////

    @DisplayName("Encoder 클래스 테스트")
    @Test
    public void Encoder클래스_테스트(){
        //given

        //when
        String encodedTestPW = passwordEncoder.encode(account.rawPassword);
        //then
        Assertions.assertTrue(passwordEncoder.matches(account.rawPassword, encodedTestPW));
    }

    @DisplayName("회원가입 테스트")
    @Test
    public void 회원가입(){
        //given


        final AccountDto.SignupRequest signupRequest = AccountDto.SignupRequest.builder()
                                                            .studentId(account.studentID)
                                                            .email(account.email)
                                                            .nickname(account.nickname)
                                                            .password(account.rawPassword)
                                                            .build();

        //when
        userService.signUpProc(signupRequest);
        UserEntity resultUser = userRepository.findOneByEmail(account.email);

        //then
       // Assertions.assertNotEquals(signUpProc_Return, null);
        Assertions.assertEquals(resultUser.getStudentId(), account.studentID);
        Assertions.assertEquals(resultUser.getEmail(), account.email);
        Assertions.assertEquals(resultUser.getNickname(), account.nickname);
        Assertions.assertTrue(passwordEncoder.matches(account.rawPassword, resultUser.getPassword()));
    }

    @DisplayName("로그인 테스트")
    @Test
    public void 로그인(){
        //given
        회원가입();
        final var loginRequest = new AccountDto.LoginRequest(account.email, account.rawPassword);

        //when
        var loginTest = userService.loginProc(loginRequest);

        //then
        Assertions.assertEquals(loginTest.getStatusCode(), HttpStatus.OK);

        var header = loginTest.getHeaders().get(AUTHORIZATION_HEADER).toString();
        Assertions.assertTrue(header.contains("Baerer "));
    }

    /*
    @DisplayName("getMyInfo 테스트")
    @Test
    public void 유저정보_가져오기(){
        //given
        회원가입();
        로그인();

        //when
        var user = userService.getMyInfo();

        //then
        Assertions.assertEquals(user.getEmail(), account.email);
        Assertions.assertEquals(user.getUserId(), account.studentID);
        Assertions.assertEquals(user.getNickname(), account.nickname);

    }

     */



}