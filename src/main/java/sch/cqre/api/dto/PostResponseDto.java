package sch.cqre.api.dto;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sch.cqre.api.domain.PostEntity;

@Getter
@NoArgsConstructor
public class PostResponseDto{
	private Integer postId;
	private Integer authorId;
	private String postTitle;
	private String postContent;
	private int views;
	private String thumbnail;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public PostResponseDto(PostEntity postEntity) {
		this.postId = postEntity.getPostId();
		this.authorId = postEntity.getAuthorId();
		this.postTitle = postEntity.getPostTitle();
		this.postContent = postEntity.getPostContent();
		this.views = postEntity.getViews();
		this.thumbnail = postEntity.getThumbnail();
		this.createdAt = postEntity.getCreatedAt();
		this.updatedAt = postEntity.getUpdatedAt();
	}
}
