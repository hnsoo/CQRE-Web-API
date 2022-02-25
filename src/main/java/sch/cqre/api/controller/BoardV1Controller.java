package sch.cqre.api.controller;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sch.cqre.api.repository.BoardDAO;
import sch.cqre.api.service.BoardService;
import sch.cqre.api.service.JsonMessager;
import sch.cqre.api.service.UserService;

@Controller
@RequestMapping("/api/v1/board/*")
@RequiredArgsConstructor
@Slf4j
public class BoardV1Controller {

    private final UserService userService;
    private final JsonMessager jsonMessager;
    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity writeMap(@RequestParam(value = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(value = "content", required = false, defaultValue = "") String content){

        if (title.isBlank() || content.isBlank()){
            return jsonMessager.err("notVaildInput");
        }

        return boardService.writeProc(title, content);
    }


    @PostMapping("/read")
    public ResponseEntity readMap(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "hi~ " + userService.getRole());
        jsonObject.put("email", userService.getEmail());
        return ResponseEntity.ok().body(jsonObject);
    }


    @PostMapping("/list")
    public ResponseEntity listMap(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "hi~ " + userService.getRole());
        jsonObject.put("email", userService.getEmail());
        return ResponseEntity.ok().body(jsonObject);
    }

    @PostMapping("/file/upload")
    public ResponseEntity fileUploadMap(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "hi~ " + userService.getRole());
        jsonObject.put("email", userService.getEmail());
        return ResponseEntity.ok().body(jsonObject);
    }


}