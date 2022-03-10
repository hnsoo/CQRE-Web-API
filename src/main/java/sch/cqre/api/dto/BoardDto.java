package sch.cqre.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sch.cqre.api.domain.CommentEntity;
import sch.cqre.api.domain.ReactionEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class BoardDto {

  //  private long postId;
    /*
    private int authorId;
    private String postTitle;
    private String postContent;
    private int views;
    private int likes;
    private String thumbnail;
    private Timestamp createdAt;
    private Timestamp updatedAt;

     */


/*
    @Transient
    private CommentEntity comment;
    @Transient
    private ReactionEntity reaction;
    @Transient
    private PostHashTagEntity postHashTag;
*/


    @Setter@Getter
    public static class ReactionRequest{
        @NotNull
        long postId;

        @NotBlank
        String reaction;
    }

    @Setter@Getter
    public static class unReactionRequest{
        @NotNull
        long postId;
    }


    @Getter
    @Setter
    public static class ViewPostResponse {
        private String postTitle;
        private String postContent;
        private int views;
        private int likes;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        List<CommentEntity> commentLists = new ArrayList<CommentEntity>();

    }


    @Setter @Getter
    public static class CommentList{
        private long commentAuthor;
        private String commentTitle;
        private String commentContent;
        private Timestamp createdAt;
        private Timestamp updatedAt;

    }


    @Setter
    @Getter
    public static class WritePostRequest{

        @NotBlank
        private String title;
        @NotBlank
        private String content;
        @NotBlank
        private String hashtag;

    }


    @Setter
    @Getter
    public static class ModifyPostRequest extends WritePostRequest{
        @NotNull
        private long postId;

        //  private WritePostRequest writePostRequest;
    }


    @Setter
    @Getter
    public static class ModifyPostResponse extends ReactionEntity {

    }

    @Setter
    @Getter
    public static class WriteReactionRequest {
        private long post_id;
        private String Reaction;
    }


    @Setter
    @Getter
    public static class WriteReactionResponse extends WriteReactionRequest{

    }


    @Setter
    @Getter
    public static class ModifyReactionResponse extends WriteReactionRequest{


    }




}
