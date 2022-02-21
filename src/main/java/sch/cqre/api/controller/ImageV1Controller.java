package sch.cqre.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/test")
@RolesAllowed("hasRole('ROLE_ADMIN')")
public class ImageV1Controller {
    public String asd(){
        return "a";
    }

}
