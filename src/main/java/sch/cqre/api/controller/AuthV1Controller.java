package sch.cqre.api.controller;


import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.service.UserService;

@Controller
@RequestMapping("/api/v1/auth/*")
@RequiredArgsConstructor
public class AuthV1Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final UserService userService;


    @Autowired
    PasswordEncoder passwordEncoder;



    @PostMapping("/signup")
        public ResponseEntity signupRestMap(@RequestParam("studentId") String studentId,
                                         @RequestParam("password") String password, @RequestParam("email") String email,
                                         @RequestParam("nickname") String nickname, @RequestParam("profile") String profile
                                         ){
                        /*
                        TODO : @Validated를 통한 UserDto userinfo 유효성 검증 로직 추가
                         */

            UserDto signUpForm = new UserDto();


            signUpForm.setStudentId(Integer.parseInt(studentId));
             signUpForm.setPassword(password);
            signUpForm.setEmail(email);
            signUpForm.setNickname(nickname);
      //      signUpForm.setProfile(profile);


            String validChk = userService.signupValidChk(signUpForm);
            if (validChk != "fine") {
                //회원가입 폼이 유효하지 않음
                MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
                header.add("status","fail");
                header.add("message", validChk); // 첫번째 에러만 전달
                return ResponseEntity.badRequest()
                        .body(validChk);
            }

            //유효성 검증이후 패스워드 암호화
            signUpForm.setPassword(passwordEncoder.encode(password));

            String duplicateChk = userService.signupDuplicateChk(signUpForm);
            if (duplicateChk != "fine") {
                //중복있음
                MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
                return ResponseEntity.badRequest()
                        .body(duplicateChk);
            }
                logger.warn("good~");
                return ResponseEntity.ok(userService.createUser(signUpForm));
        }

/*
    @GetMapping("/logout")
    public ResponseEntity signupProc

*/

    @PostMapping("/login")
        public ResponseEntity loginRestMap(@RequestParam("email") String email, @RequestParam("password") String password){
        return userService.loginProc(email, password); //ResponseEntity loginResult =
        }





}

    /*
    @GetMapping("/account/user-emails/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
        return false;
    }

    @GetMapping("/account/user-id/{studentId}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String studentId){
        return false;
    }

     */
