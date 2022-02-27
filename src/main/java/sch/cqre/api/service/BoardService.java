package sch.cqre.api.service;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.jwt.Role;
import sch.cqre.api.repository.BoardDAO;
import sch.cqre.api.repository.HashTagDAO;
import sch.cqre.api.repository.PostHashTagDAO;
import sch.cqre.api.repository.UserRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardDAO boardDAO;
    private final HashTagDAO hashtagDAO;
    private final PostHashTagDAO postHashTagDAO;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JsonMessager jsonMessager;


    @Transactional
    public ResponseEntity writeProc(String title, String content, String hashtag){
        /*
                로직 : DAO.writePost (게시물 작성) -> DAO.taggingPost (헤시태깅)

         */
        JSONObject jsonPostData = new JSONObject();
        JSONObject jsonTagData = new JSONObject();
        JSONArray results = new JSONArray();

        String[] hashtagSplit = hashtag.split(","); //해시태그를 ,로 분리합니다
        int postId = boardDAO.writePost(title, content); //게시물 작성과 함께 postId를 불러옴
       // int tagId[] = new int [hashtagSplit.length];
        if (postId < 0) return jsonMessager.errStr("postError");

        for (int i = 0; i < hashtagSplit.length; i++){
            String tag = hashtagSplit[i];
            if (!tag.isEmpty()) {
                int tagId = hashtagDAO.getTagId(tag); // 해시태그id를 조회하고 없으면 만듬

                postHashTagDAO.taggingPost(postId, tagId);
                jsonTagData.put("tagName", tag);
                jsonTagData.put("tagid", tagId);
                    // hashtag id, post id를 DB에 넣음
            }

        }


        jsonPostData.put("title", title);
        jsonPostData.put("content", content);
        jsonPostData.put("postId", postId);


        results.appendElement(jsonPostData);
        results.appendElement(jsonTagData);

        return jsonMessager.successArray(results);

    }


    @Transactional
    public ResponseEntity modifyProc(int postId, String title, String content, String hashtag){
         /*
               로직 : 본인 게시물인지 확인 -> 게시물 수정 -> 기존 해시태그 제거 -> 해시태그 재등록
         */

        JSONObject jsonPostData = new JSONObject();
        JSONObject jsonTagData = new JSONObject();
        JSONArray results = new JSONArray();


        if(!boardDAO.existPost(postId))
            return jsonMessager.errStr("modifyError");

        //내가 쓴 게시물인지 확인
        if (!boardDAO.isMyPost(postId))
            return jsonMessager.errStr("notMyPostError");
        log.warn("passed isMyPost");

        //게시물 수정
        if (boardDAO.modifyPost(postId, title, content) == -1) //게시물 수정 실패 했을때
            return jsonMessager.errStr("modifyError");
        log.warn("passed modifyPost");

        //기존 해시태그 제거
        if (postHashTagDAO.removeTag(postId) == -1) //해시태그 제거에 실패했으면
            return jsonMessager.errStr("modifyError");


        log.warn("passed removeTag");

        //해시태그 다시 등록
        String[] hashtagSplit = hashtag.split(","); //해시태그를 ,로 분리합니다
        for (int i = 0; i < hashtagSplit.length; i++){
            String tag = hashtagSplit[i];
            if (!tag.isEmpty()) {
                int tagId = hashtagDAO.getTagId(tag); // 해시태그id를 조회하고 없으면 만듬

                postHashTagDAO.taggingPost(postId, tagId);
                jsonTagData.put("tagName", tag);
                jsonTagData.put("tagid", tagId);
                // hashtag id, post id를 DB에 넣음
            }

        }

        jsonPostData.put("title", title);
        jsonPostData.put("content", content);
        jsonPostData.put("postId", postId);

        results.appendElement(jsonPostData);
        results.appendElement(jsonTagData);



        return jsonMessager.successArray(results);

    }

    @Transactional
    public ResponseEntity deleteProc(int postId){
        if (boardDAO.deletePost(postId)){
            return jsonMessager.successStr("success");
        } else {
            return jsonMessager.errStr("fail");
        }
    }

/*
    @Transactional
    public ResponseEntity viewProc(int postId){

        //글의 존재 여부 조회
        if (!boardDAO.existPost(postId))
            return jsonMessager.errStr("invaildInput");


        //게시물에 admin해시태그가 있는데 접근자가 관리자 권한이 없는 경우
        if (!boardDAO.isAdminPost(String.valueOf(postId)) && !(userService.getRole() == Role.define.role_ADMIN || userService.getRole() == Role.define.role_MANAGER))
            return jsonMessager.errStr("invaildInput");

        JSONObject jsonPostData = new JSONObject();
        JSONObject jsonTagData = new JSONObject();
        JSONObject jsonFileData = new JSONObject();



        JSONArray results = new JSONArray();
        results.appendElement(jsonPostData);
        results.appendElement(jsonTagData);
        results.appendElement(jsonFileData);


        return jsonMessager.errStr("asd");



    }
    todo:게시물 보기 개발
*/



    public boolean isAllowedReaction(String reaction){
        //허용된 리액션인지 확인

        String[] allowedReaction = {"useful", "hard", "thanks"};

        for (int i=0; i<allowedReaction.length; i++) {
            if (allowedReaction[i].equals(reaction))
                return true;
        }

        return false;
    }

    @Transactional
    public ResponseEntity reactionProc(int postId, String reaction){
        /*
        -> 허용된 리액션 정보인지 확인 ->
        글 존재 하는지 확인 ->  내 글인지 확인 (본인 글이면 못하게)
        -> 중복 리액션 확인 -> 추가
         */

        int userId = userService.getMyInfo().getUserId();

        if (!isAllowedReaction(reaction))
            return jsonMessager.errStr("invaildInput");
        if (!boardDAO.existPost(postId))
            return jsonMessager.errStr("invaildInput");
        if (boardDAO.isMyPost(postId))
            return jsonMessager.errStr("noSelfReaction");
        if (reactionDAO.isVaildReaction(userId, postId, reaction))
            return jsonMessager.errStr("invaildInput");

        log.warn(userId + " " + postId + " " + reaction);
        ReactionEntity reactionForm = new ReactionEntity();
        reactionForm.setUserId(userId);
        reactionForm.setPostId(postId);
        reactionForm.setReaction(reaction);

        if (reactionDAO.reaction(userId, postId, reaction) > 0)
            return jsonMessager.successStr("success");
        return jsonMessager.errStr("fail");

    }

    @Transactional
    public ResponseEntity unReactionProc(int postId, String reaction){
        /*
        -> 허용된 리액션 정보인지 확인 ->
        글 존재하는지 확인 -> 삭제하고자 하는 리액션 존재 확인 ->
        삭제
         */

        int userId = userService.getMyInfo().getUserId();

        if (!isAllowedReaction(reaction))
            return jsonMessager.errStr("invaildInput");
        if (!boardDAO.existPost(postId))
            return jsonMessager.errStr("invaildInput");
        if (boardDAO.isMyPost(postId))
            return jsonMessager.errStr("invaildInput");



        if (!reactionDAO.isVaildReaction(userId, postId, reaction)) //발견된 리액션이 없으면
            return jsonMessager.errStr("invaildInput");

        reactionDAO.unReaction(userId, postId, reaction); //삭제

        if (!reactionDAO.isVaildReaction(userId, postId, reaction)) //발견된 리액션이 없으면
            return jsonMessager.successStr("success");

        return jsonMessager.errStr("fail");


    }




}
