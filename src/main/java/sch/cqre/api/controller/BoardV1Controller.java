package sch.cqre.api.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sch.cqre.api.service.JsonMessager;
import sch.cqre.api.service.UserService;

@Controller
@RequestMapping("/api/v1/board/*")
@RequiredArgsConstructor
public class BoardV1Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final UserService userService;

    private final JsonMessager jsonMessager;


/*
    @PostMapping("/view")
    public ResponseEntity viewMap(){

    }

 */
}