package sch.cqre.api.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.service.UserService;
//import sch.cqre.api.validator.EmailCheckValidator;


@Controller
@RequiredArgsConstructor
public class SignupController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final UserService userService;

    @GetMapping("/login")
        public String login(){

            return "login";
        }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }





}
