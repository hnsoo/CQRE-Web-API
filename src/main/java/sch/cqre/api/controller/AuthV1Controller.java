package sch.cqre.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sch.cqre.api.dto.AccountDto;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.exception.ErrorCode;
import sch.cqre.api.service.UserService;

@Controller
@RequestMapping("/api/v1/auth/*")
@RequiredArgsConstructor
@Slf4j
public class AuthV1Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final UserService userService;


    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity signupRestMap(@RequestBody @Validated  AccountDto.SignupRequest signupRequest) {
        return ResponseEntity.ok(userService.signUpProc(signupRequest));
    }

/*
    Todo : 로그아웃 구현
    @GetMapping("/logout")
    public ResponseEntity signupProc
*/

    @PostMapping("/login")
    public ResponseEntity loginRestMap(@RequestBody @Validated AccountDto.LoginRequest loginRequestDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new CustomException(ErrorCode.INVALID_INPUT);

        return userService.loginProc(loginRequestDto);
    }
}
