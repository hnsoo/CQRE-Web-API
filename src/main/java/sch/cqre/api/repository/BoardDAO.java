package sch.cqre.api.repository;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.BoardDto;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.jwt.Role;
import sch.cqre.api.service.JsonMessager;
import sch.cqre.api.service.UserService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final JsonMessager jsonMessager;


    @Transactional
    public ResponseEntity writePost(String title, String content){
        //form 유효성 확인
        /*if (boardForm.getPostTitle().isBlank() || boardForm.getPostContent().isBlank() ){
            return
        } */

        //게시물 작성 처리

        PostEntity writeForm = new PostEntity();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String CreateAt = format.format(time);
        int authorId  = userRepository.findOnceByEmail(userService.getEmail()).getUserId();

        writeForm.setAuthorId(authorId);
        writeForm.setPostTitle(title);
        writeForm.setPostContent(content);
        writeForm.setLikes(0);
        writeForm.setViews(0);
        writeForm.setCreatedAt(Timestamp.valueOf(CreateAt));
        writeForm.setUpdatedAt(Timestamp.valueOf(CreateAt));

        boardRepository.save(writeForm);

        return jsonMessager.success("posted");
    }

    @Transactional
    public PostEntity modify(BoardDto form){
        //게시물 수정 처리
        PostEntity boardForm = form.toEntity();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String UpdatedAt = format.format(time);
        boardForm.setUpdatedAt(Timestamp.valueOf(UpdatedAt));

        return boardRepository.save(boardForm);
    }


}
