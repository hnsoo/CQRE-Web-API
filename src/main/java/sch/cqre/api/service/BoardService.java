package sch.cqre.api.service;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.*;
import sch.cqre.api.dto.BoardDto;
import sch.cqre.api.dto.CommentDto;
import sch.cqre.api.exception.CustomExeption;
import sch.cqre.api.exception.ErrorCode;
import sch.cqre.api.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static sch.cqre.api.config.AppConfig.allowedReaction;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final UserService userService;
    private final BoardRepository boardRepository;
    private final PostHashTagRepository postHashTagRepository;
    private final ReactionRepository reactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final HashTagRepository hashTagRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public PostEntity writeProc(BoardDto.WritePostRequest writePostRequest){
        /*
                로직 : DAO.writePost (게시물 작성) -> DAO.taggingPost (헤시태깅)
                //todo 일반유저가 admin태그 못쓰게
         */

        PostEntity postForm = modelMapper.map(writePostRequest, PostEntity.class);
        postForm.setUser(userService.getMyInfo());
        postForm.setViews(0);

        boardRepository.save(postForm);
        long postId = postForm.getPostId();

        addPostHashtag(postId, writePostRequest.getHashtag());

        return postForm;
    }

    @Transactional
    public PostEntity modifyProc(BoardDto.ModifyPostRequest modifyPostRequest){
         /*
               로직 : 본인 게시물인지 확인 -> 게시물 수정 -> 기존 해시태그 제거 -> 해시태그 재등록
         */

        //게시물이 없으면 throw
        throwNotExistPost(modifyPostRequest.getPostId());

        //내가 쓴 게시물인지 확인
        thorwNotMyPost(modifyPostRequest.getPostId());

        //해시태그 삭제
        removeTag(modifyPostRequest.getPostId());

        //해시태그 재등록
        addPostHashtag(modifyPostRequest.getPostId(), modifyPostRequest.getHashtag());

        //게시물 수정
        PostEntity post = boardRepository.findOne(modifyPostRequest.getPostId());
        post.setPostTitle(modifyPostRequest.getTitle());
        post.setPostContent(modifyPostRequest.getContent());
        boardRepository.save(post);

        return post;
    }

    @Transactional
    public void deleteProc(long postId) {
                /*
        게시물 유효 검사
         -> 내가 쓴 게시물인지 확인 or 관리자 권한 확인
                -> 해시태그 삭제
                        -> 게시물 삭제
         */

        //게시물이 없으면 throw
        throwNotExistPost(postId);
        thorwNotMyPost(postId);
        //해시태그 제거
        removeTag(postId);

        //게시물 삭제
        boardRepository.deleteOne(postId);
    }



    @Transactional
    public JSONArray viewProc(long postId){

        //글의 존재 여부 조회
        throwNotExistPost(postId);

        //게시물에 admin해시태그가 있는데 접근자가 관리자 권한이 없는 경우
        if (isAdminPost(postId) && (userService.chkAdmin()))
            throw new CustomExeption(ErrorCode.INVALID_PERMISSION);


        PostEntity post = boardRepository.findOne(postId);

        JSONArray postArray = new JSONArray();

        JSONObject tempPostObj = new JSONObject();

        tempPostObj.put("title", post.getPostTitle());
        tempPostObj.put("content", post.getPostContent());
        tempPostObj.put("createdAt", post.getCreatedAt());
        tempPostObj.put("updatedAt", post.getUpdatedAt());
        tempPostObj.put("view", post.getViews());


        JSONObject tempReactionObj = new JSONObject();
        for (int i=0; i< allowedReaction.length; i++){
            tempReactionObj.put(allowedReaction[i], reactionRepository.countReaction(postId, allowedReaction[i]));
        }

        tempPostObj.put("reaction", tempReactionObj);

        JSONObject postObj = new JSONObject();
        postObj.put("post", tempPostObj);

        List<CommentEntity> commentEntity = commentRepository.findCommentList(postId);
        List<JSONObject> CommentArray = new ArrayList<>();

        for (CommentEntity commentList : commentEntity){
            JSONObject tempCommentObj = new JSONObject();
            tempCommentObj.put("author", "todo");
            tempCommentObj.put("content", commentList.getCommentContent());
            tempCommentObj.put("createdAt", commentList.getCreatedAt());
            tempCommentObj.put("updatedAt", commentList.getUpdatedAt());
            CommentArray.add(tempCommentObj);
        }


        postObj.put("comment", CommentArray);
        postArray.appendElement(postObj);

        //viewPost.setLikes(); todo: reaction


        return postArray;
    }

    public boolean isAllowedReaction(String reaction){
        //허용된 리액션인지 확인
        for (int i=0; i< allowedReaction.length; i++) {
            if (allowedReaction[i].equals(reaction))
                return true;
        }

        return false;
    }

    @Transactional
    public void reactionProc(long postId, String reaction){
        /*
        -> 허용된 리액션 정보인지 확인 ->
        글 존재 하는지 확인 ->  내 글인지 확인 (본인 글이면 못하게)
        -> 중복 리액션 확인 -> 추가
         */

        long userId = userService.getMyInfo().getUserId();

        if (!isAllowedReaction(reaction))
            throw new CustomExeption(ErrorCode.INVALID_INPUT);

        throwDuplicateReaction(postId, userId);
        throwNotExistPost(postId);
       // thorwMyPost(postId); todo: on

        ReactionEntity reactionForm = new ReactionEntity();
        //reactionForm.setPost(post);
        reactionForm.setPostId(postId);
        reactionForm.setUserId(userId);
        reactionForm.setReaction(reaction);

        reactionRepository.save(reactionForm);

    }

    @Transactional
    public void unReactionProc(long postId){
        /*
        -> 허용된 리액션 정보인지 확인 ->
        글 존재하는지 확인 -> 삭제하고자 하는 리액션 존재 확인 ->
        삭제
         */

        long userId = userService.getMyInfo().getUserId();

        throwNotExistPost(postId);

        //thorwMyPost(postId); //todo: on

        ReactionEntity reactionForm = reactionRepository.findReaction(userId, postId);
        reactionRepository.delete(reactionForm); //삭제
    }

    @Transactional
    public CommentDto.CommentWriteResponse commentWriteProc(CommentDto.CommentWriteRequest commentWriteRequest){
    /*
        댓글을 작성하는 로직
        -----------------
        게시물이 존재하는지 확인 -> 일반 유저가 admin글에 작성하는지 확인
            -> 게시물 작성
     */
        throwNotExistPost(commentWriteRequest.getPostId());

        if (isAdminPost(commentWriteRequest.getPostId()) && !userService.chkAdmin())
            throw new CustomExeption(ErrorCode.INVALID_PERMISSION);

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setPostId(commentWriteRequest.getPostId());
        commentEntity.setAuthorId(userService.getMyInfo().getUserId());
        commentEntity.setCommentContent(commentWriteRequest.getCommentContent());

        commentRepository.save(commentEntity);

        CommentDto.CommentWriteResponse result = modelMapper.map(commentEntity, CommentDto.CommentWriteResponse.class);

        return result;
    }

    @Transactional
    public CommentDto.CommentWriteResponse commentModifyProc(CommentDto.CommentModifyRequest commentModifyRequest){
    /*
        댓글을 작성하는 로직
        -----------------
        게시물이 존재하는지 확인 -> 본인 uid + 댓글 가져옴
            -> 댓글 수정
     */

        CommentEntity commentEntity = commentRepository.getMyComment(userService.getMyInfo().getUserId(), commentModifyRequest.getCommentId());

        commentEntity.setCommentContent(commentModifyRequest.getCommentContent());
        commentRepository.save(commentEntity);

        CommentDto.CommentWriteResponse result = modelMapper.map(commentEntity, CommentDto.CommentWriteResponse.class);

        return result;
    }


    public void thorwMyPost(long postId){
        //내가 쓴 게시물이면 throw.
        //게시물 수정 처리

        PostEntity post = boardRepository.findOne(postId);
        if (post.getUser().getUserId() == userService.getMyInfo().getUserId())
            throw new CustomExeption(ErrorCode.MY_POST);
    }

    public void thorwNotMyPost(long postId){
        //내가 쓴 게시물이 아니면 throw.... ---- 관리자는 예외
        //게시물 수정 처리

        if (userService.chkAdmin())
            return;

        PostEntity post = boardRepository.findOne(postId);
        if (post.getUser().getUserId() != userService.getMyInfo().getUserId())
            throw new CustomExeption(ErrorCode.NOT_MY_POST);
    }

    public void throwDuplicateReaction(long postId, long userId){
        //이미 리액션 했으면 throw
        ReactionEntity reaction = reactionRepository.findReaction(userId, postId);
        if (reaction != null)
            throw new CustomExeption(ErrorCode.DUPLICATE_REACTION);
    }

    public void throwNotExistPost(long postId){
        //게시물이 없으면 throw
        PostEntity post = boardRepository.findOne(postId);
        if (post == null)
            throw new CustomExeption(ErrorCode.INVALID_INPUT);
    }

    @Transactional
    public boolean isAdminPost(long postId){
        HashTagEntity adminHashtagDB = hashTagRepository.findName("admin");
        if (adminHashtagDB == null) //해시태그 테이블에서 admin해시태그가 등록되어있지 않으면
            return false;

        long adminHashtagID = adminHashtagDB.getHashtagId();

        PostHashTagEntity match_postid_adminhashtag = postHashTagRepository.findByPostAndHashTag(postId, adminHashtagID);
         if (match_postid_adminhashtag == null)
             return false;
        return true;
    }



    public PostEntity fineOne(Long postId){
        return boardRepository.findOne(postId);
    }


    public void removeTag(long postId){
        postHashTagRepository.deletePosthashTag(postId);
    }

    public long getTagId(String hashtag){
        HashTagEntity hashTagEntity = hashTagRepository.findName(hashtag);
        if (hashTagEntity == null){ //처음 쓰인 해시태그라면
            //HashTag테이블에 새로운 태그 생성

            HashTagEntity hashTagForm = new HashTagEntity();
            hashTagForm.setName(hashtag);

            hashTagRepository.save(hashTagForm);
            return hashTagForm.getHashtagId();

        }
            return hashTagEntity.getHashtagId();

    }

    public void addPostHashtag(long postId, String hashTag){
        String[] hashtagSplit = hashTag.toLowerCase(Locale.ROOT).split(","); //해시태그를 ,로 분리합니다

        for (int i = 0; i < hashtagSplit.length; i++){
            String tag = hashtagSplit[i];
            if (!tag.isEmpty()) {
                long tagId = getTagId(tag); // 해시태그id를 조회하고 없으면 만듬
                PostHashTagEntity postHashTagEntity = new PostHashTagEntity();
                postHashTagEntity.setPostId(postId);
                postHashTagEntity.setHashtagId(tagId);

                postHashTagRepository.save(postHashTagEntity);
                // hashtag id, post id를 DB에 넣음

            }
        }
    }





}
