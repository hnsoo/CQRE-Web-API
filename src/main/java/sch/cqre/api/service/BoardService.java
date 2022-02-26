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




}
