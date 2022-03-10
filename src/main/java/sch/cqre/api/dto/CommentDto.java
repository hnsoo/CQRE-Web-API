package sch.cqre.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentDto {

    @Setter
    @Getter
    public static class CommentWriteRequest{
        @NotNull
        private long postId;

        @NotBlank(message = "댓글을 입력해주세요")
        private String commentContent;
    }

    @Setter @Getter
    public static class CommentModifyRequest{
        @NotNull
        private long userId;

        @NotNull
        private long commentId;

        @NotBlank(message = "댓글을 입력해주세요")
        private String commentContent;
    }

    public static class CommentWriteResponse extends CommentWriteRequest{

    }

    public static class CommentModifyResponse extends CommentModifyRequest{

    }

    public static class CommentDeleteRequest {
        @NotBlank
        private Long postId;
    }

    public static class CommentDeleteResponse extends CommentDeleteRequest {

    }

}
