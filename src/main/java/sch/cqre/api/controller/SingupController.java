package sch.cqre.api.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.service.UserService;

@RestController
@RequiredArgsConstructor
public class SingupController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final UserService userService;

    @RequestMapping("/singup")
    public String singup(@RequestParam("studentId") String studentId,
                         @RequestParam("password") String password, @RequestParam("email") String email,
                         @RequestParam("nickname") String nickname, @RequestParam("profile") String profile,
                         Model model){


        model.addAttribute("studentId", studentId);
        model.addAttribute("password", password);
        model.addAttribute("email", email);
        model.addAttribute("nickname", nickname);
        model.addAttribute("profile", profile);

        logger.warn("email is " + email);

        UserDto signUpForm = new UserDto();
        signUpForm.setStudentId(Long.valueOf(studentId.trim()));
        signUpForm.setPassword(password);
        signUpForm.setEmail(email);
        signUpForm.setNickname(nickname);
        signUpForm.setProfile(profile);

        userService.createUser(signUpForm);

        /*
        studentId=20194581
        password=123
        email=penekhun@gmail.com
        nickname=hi
        profile=asd.png


         */






        //model.addAttribute("body", new UserDto());



        return "/";//(String) model.getAttribute("userId");

    }


}
