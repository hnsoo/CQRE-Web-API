package sch.cqre.api.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

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

}
