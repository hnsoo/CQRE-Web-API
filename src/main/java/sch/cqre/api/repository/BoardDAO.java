package sch.cqre.api.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BoardDAO {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final JsonMessager jsonMessager;

    private final PostHashTagDAO postHashTagDAO;


    @Transactional
    public int  writePost(String title, String content){
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

        return boardRepository.save(writeForm).getPostId();
    }



    @Transactional
    public int modifyPost(int postId, String title, String content){

        PostEntity beforeWriteForm  = boardRepository.findOnceByPostId(postId);
        if (beforeWriteForm == null)
            return -1;

        PostEntity afterWriteForm  = new PostEntity();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String updateAt = format.format(time);
        int authorId  = userRepository.findOnceByEmail(userService.getEmail()).getUserId();//userRepository.findOnceByEmail(userService.getEmail()).getUserId();

        afterWriteForm.setPostId(postId);
        afterWriteForm.setAuthorId(authorId);
        afterWriteForm.setPostTitle(title);
        afterWriteForm.setPostContent(content);
        afterWriteForm.setLikes(beforeWriteForm.getLikes());
        afterWriteForm.setViews(beforeWriteForm.getViews());
        afterWriteForm.setCreatedAt(beforeWriteForm.getCreatedAt());
        afterWriteForm.setUpdatedAt(Timestamp.valueOf(updateAt));

        return boardRepository.save(afterWriteForm).getPostId();
    }


    public boolean existPost(int postId){
        //postId로 매칭되는 게시물 있는지 확인
        return boardRepository.countByPostId(postId) == 1;
    }

    public boolean isMyPost(int postId){
        //내가 쓴 게시물인지 확인하는 함수
        //맞으면 true, 아니면 false
        //게시물 수정 처리

        PostEntity post = boardRepository.findOnceByPostId(postId);
        return userRepository.countByuserIdAndEmail(post.getAuthorId(), userService.getEmail()) != 0;
    }



    @Transactional
    public boolean isAdminPost(String postId){
        HashTagEntity adminHashtagDB = hashTagRepository.findOnceByName("admin");
        if (adminHashtagDB == null) //해시태그 테이블에서 admin해시태그가 등록되어있지 않으면
            return false;

        int adminHashtagID = adminHashtagDB.getHashtagId();

        PostHashTagEntity match_postid_adminhashtag = postHashTagRepository.findByPostIdAndHashtagId(Integer.parseInt(postId), adminHashtagID);
        if (match_postid_adminhashtag == null)
            return false;
        return true;
    }


    public boolean deletePost(int postId){
        /*
        게시물 유효 검사
         -> 내가 쓴 게시물인지 확인 or 관리자 권한 확인
                -> 해시태그 삭제
                        -> 게시물 삭제
         */

        if(!existPost(postId))
            return false;

        //내가 쓴 게시물 or 관리자권한 확인
        if (!(isMyPost(postId) || (userService.getRole() == Role.define.role_ADMIN || userService.getRole() == Role.define.role_MANAGER)))
            return false;

        //해시태그 제거
        postHashTagDAO.removeTag(postId);

        //게시물 삭제
        boardRepository.deleteBypostId(postId);

        //todo : 파일삭제

        return true;
    }






}
