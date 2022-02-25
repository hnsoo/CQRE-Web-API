package sch.cqre.api.dto;

import lombok.Builder;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.UserEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;


@Builder
public class BoardDto {

    private long postId;
    private long authorId;
    private String postTitle;
    private String postContent;
    private int views;
    private int likes;
    private String thumbnail;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public BoardDto(PostEntity board){
       // this.postId = board.getPostId(); @id
        this.authorId = board.getAuthorId();
        this.postTitle = board.getPostTitle();
        this.postContent = board.getPostContent();
        this.views = board.getViews();
        this.likes = board.getLikes();
        this.thumbnail = "";//board.getThumbnail();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
    }


    public PostEntity toEntity(){
        return PostEntity.builder()
                .authorId(authorId)
                .postTitle(postTitle)
                .postContent(postContent)
                .views(views)
                .likes(likes)
                .thumbnail(thumbnail)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }


}
