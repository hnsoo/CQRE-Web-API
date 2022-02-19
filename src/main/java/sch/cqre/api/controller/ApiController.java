package sch.cqre.api.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final UserService userService;

    @PostMapping("/api/singup")
    public ResponseEntity<UserEntity> signupProc(@Validated UserDto userinfo,
                                                 BindingResult bindingResult,
                                                 Errors errors){


        if(bindingResult.hasErrors()) {
            List<ObjectError> list = bindingResult.getAllErrors();
           // for (ObjectError e : list) {
            logger.warn("[C] Singup : " + list.get(0).toString());

            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("status","fail");
            header.add("message", list.get(0).toString()); // 첫번째 에러만 전달
            return new ResponseEntity(header, HttpStatus.FORBIDDEN);

            }

            return ResponseEntity.ok(userService.createUser(userinfo));
        }

        /* 회원가입 실패시 입력 데이터 값을 유지 */
        //model.addAttribute("userDto", userDto);




        //return "redirect:/login";
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
