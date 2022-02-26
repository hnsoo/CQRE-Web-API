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
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.repository.BoardDAO;
import sch.cqre.api.service.BoardService;
import sch.cqre.api.service.JsonMessager;
import sch.cqre.api.service.UserService;

@RestController
@RequestMapping("/api/v1/board/*")
@RequiredArgsConstructor
@Slf4j
public class BoardV1Controller {

    private final UserService userService;
    private final JsonMessager jsonMessager;
    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity writeMap(@RequestParam(value = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(value = "content", required = false, defaultValue = "") String content,
                                   @RequestParam(value = "hashtag", required = false, defaultValue = "") String hashtag) {

        if (title.isBlank() || content.isBlank() || hashtag.isBlank()) {
            return jsonMessager.errStr("notVaildInput");
        }

        return boardService.writeProc(title, content, hashtag);
    }


    @DeleteMapping("/delete")
    public ResponseEntity deleteMap(@RequestParam(value = "post_id", required = false, defaultValue = "0") int postId){
        /*
            본인이 작성한 게시물 or 관리자 확인 후,
            PostHashTag 지우고
            Post삭제
         */

        if (postId == 0)
            return jsonMessager.errStr("invaildInput");

        return boardService.deleteProc(postId);
    }



    @PutMapping("/modify") // 수정
    public ResponseEntity writeMap(@RequestParam(value = "uid", required = false, defaultValue = "0") int uid,
                                   @RequestParam(value = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(value = "content", required = false, defaultValue = "") String content,
                                   @RequestParam(value = "hashtag", required = false, defaultValue = "") String hashtag){

        if (uid == 0 || title.isBlank() || content.isBlank() || hashtag.isBlank())
            return jsonMessager.errStr("notVaildInput");

        return boardService.modifyProc(uid, title, content, hashtag);
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