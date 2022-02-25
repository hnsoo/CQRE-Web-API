package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.BoardDto;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.repository.BoardDAO;
import sch.cqre.api.repository.UserRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardDAO boardDAO;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JsonMessager jsonMessager;


    @Transactional
    public ResponseEntity writeProc(String title, String content){
        if(title.isBlank() || content.isBlank()){
            return jsonMessager.err("invaildInput");
        }

        return boardDAO.writePost(title, content);
    }

}
