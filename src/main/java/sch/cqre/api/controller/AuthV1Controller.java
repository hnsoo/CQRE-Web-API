package sch.cqre.api.controller;


import com.nimbusds.jose.shaded.json.JSONObject;
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
import sch.cqre.api.service.JsonMessager;
import sch.cqre.api.service.UserService;

@Controller
@RequestMapping("/api/v1/auth/*")
@RequiredArgsConstructor
public class AuthV1Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final UserService userService;


    @Autowired
    PasswordEncoder passwordEncoder;

    private final JsonMessager jsonMessager;



    @PostMapping("/signup")

        public ResponseEntity signupRestMap(@RequestParam(value="studentId", required = false, defaultValue = "") String studentId,
                                            @RequestParam(value = "password", required = false, defaultValue = "") String password,
                                            @RequestParam(value = "email" , required = false, defaultValue = "") String email,
                                         @RequestParam(value = "nickname" ,required = false,defaultValue = "") String nickname
                                         ){

            UserDto signUpForm = new UserDto();


            if (studentId.isBlank() || password.isBlank() || email.isBlank() || nickname.isBlank() ){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("message", "notVaildInput");
                jsonObject.put("status", "error");
                return ResponseEntity.badRequest().body(jsonObject);
            }

            signUpForm.setStudentId(Integer.parseInt(studentId));
             signUpForm.setPassword(password);
            signUpForm.setEmail(email);
            signUpForm.setNickname(nickname);
      //      signUpForm.setProfile(profile);


            String validChk = userService.signupValidChk(signUpForm);
            if (validChk != "fine") {
                //회원가입 폼이 유효하지 않음
                return jsonMessager.errStr(validChk);
            }

            //유효성 검증이후 패스워드 암호화
            signUpForm.setPassword(passwordEncoder.encode(password));

            String duplicateChk = userService.signupDuplicateChk(signUpForm);
            if (duplicateChk != "fine") {
                //중복있음
                MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
                return jsonMessager.errStr(duplicateChk);
            }
                logger.warn("good~");
                return ResponseEntity.ok(userService.createUser(signUpForm));
        }

/*
    @GetMapping("/logout")
    public ResponseEntity signupProc

*/

    @PostMapping("/login")
    public ResponseEntity loginRestMap(@RequestParam(value = "email" , required = false, defaultValue = "") String email,
                                       @RequestParam(value = "password", required = false, defaultValue = "") String password){
        logger.warn("loginRestMap called");
        return userService.loginProc(email, password); //ResponseEntity loginResult =
    }


    /*
    @GetMapping("/logout")
    public ResponseEntity logoutRestMap(){
        return userService.logoutProc(); //ResponseEntity loginResult =
    }

     */





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
