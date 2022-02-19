package sch.cqre.api.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

//    @Autowired
//    private final EmailCheckValidator emailCheckValidator;


    /* 커스텀 유효성 검증을 위해 추가 */
   /* @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(emailCheckValidator);
      //  binder.addValidators(nicknameCheckValidator);
       // binder.addValidators(emailCheckValidator);
    }
    */



    @GetMapping("/auth/join")
    public String join(){
        return "/register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/register") // Temp view
    public String joinPage(){
        return "join";
    }

    @RequestMapping("/register")
    public String joinProc(@Validated UserDto userinfo,
                           BindingResult bindingResult,
                           Errors errors){


        /* @RequestParam("studentId") String studentId,
                         @RequestParam("password") String password, @RequestParam("email") String email,
                         @RequestParam("nickname") String nickname, @RequestParam("profile") String profile

         */
        //UserDto signUpForm = new UserDto();

        if(bindingResult.hasErrors()) {
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError e : list) {
                logger.warn("[C] Singup : " + e.getDefaultMessage());
            }
            return "join"; //다시 회원가입창으로
        }

            /* 회원가입 실패시 입력 데이터 값을 유지 */
            //model.addAttribute("userDto", userDto);


            /*
            model.addAttribute("studentId", StudentId());
            model.addAttribute("password", password);
            model.addAttribute("email", email);
            model.addAttribute("nickname", nickname);
            model.addAttribute("profile", profile);
*/
            /*
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

             */

        userService.createUser(userinfo);
        return "redirect:/login";
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
