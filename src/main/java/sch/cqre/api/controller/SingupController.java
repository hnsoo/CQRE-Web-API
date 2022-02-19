package sch.cqre.api.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.service.UserService;
//import sch.cqre.api.validator.EmailCheckValidator;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SingupController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final UserService userService;

    @GetMapping("/auth/join")
    public String join(){
        return "/register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

/*
    @GetMapping("/register") // Temp view
    public String joinPage(){
        return "join";
    }

 */

    @RequestMapping("/register")
    public String joinProc(@Validated UserDto userinfo,
                           BindingResult bindingResult,
                           Errors errors,  Model model){

            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("userDto", userinfo);
        return "redirect:/api/singup";
    }

        //emailCheckValidator.validate(event, bindingResult);


/*
        signUpForm.setStudentId(Long.valueOf(studentId.trim()));
        signUpForm.setPassword(password);
        signUpForm.setEmail(email);
        signUpForm.setNickname(nickname);
        signUpForm.setProfile(profile); */
        //logger.warn("info : " + userinfo.getStudentId());


        //userService.createUser(userinfo);

        /*
        studentId=20194581
        password=123
        email=penekhun@gmail.com
        nickname=hi
        profile=asd.png


         */

        //model.addAttribute("body", new UserDto());


}
