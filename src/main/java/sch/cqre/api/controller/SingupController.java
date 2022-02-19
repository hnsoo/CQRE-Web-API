package sch.cqre.api.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.service.UserService;
import sch.cqre.api.validator.EmailCheckValidator;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SingupController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final UserService userService;

    private final EmailCheckValidator emailCheckValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(emailCheckValidator);
      //  binder.addValidators(nicknameCheckValidator);
       // binder.addValidators(emailCheckValidator);
    }

    @RequestMapping("/user/user-join") // Temp view
    public String userjoinPage(){
        return "joinPage";
    }

    @GetMapping("/auth/join")
    public String join(){
        return "/user/user-join";
    }

    @GetMapping("/auth/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/auth/joinProc")
    public String joinProc(@RequestParam("studentId") String studentId,
                         @RequestParam("password") String password, @RequestParam("email") String email,
                         @RequestParam("nickname") String nickname, @RequestParam("profile") String profile,
                           Errors errors, Model model){

        UserDto signUpForm = new UserDto();

        if (errors.hasErrors()) {

            model.addAttribute("studentId", studentId);
            model.addAttribute("password", password);
            model.addAttribute("email", email);
            model.addAttribute("nickname", nickname);
            model.addAttribute("profile", profile);

            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }



            signUpForm.setStudentId(Long.valueOf(studentId.trim()));
            signUpForm.setPassword(password);
            signUpForm.setEmail(email);
            signUpForm.setNickname(nickname);
            signUpForm.setProfile(profile);

            /* 회원 가입 페이지로 다시 리턴 */
            return "/user/user-join";
        }

        userService.createUser(signUpForm);

        /*
        studentId=20194581
        password=123
        email=penekhun@gmail.com
        nickname=hi
        profile=asd.png


         */






        //model.addAttribute("body", new UserDto());



        return "redirect:/auth/login";//(String) model.getAttribute("userId");

    }











}
