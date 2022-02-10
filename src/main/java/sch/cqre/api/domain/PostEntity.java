package sch.cqre.api.domain;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Post", schema = "main")
public class PostEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "post_id")
	private Long postId;
	@Basic
	@Column(name = "author_id")
	private Long authorId;
	@Basic
	@Column(name = "post_title")
	private String postTitle;
	@Basic
	@Column(name = "post_content")
	private String postContent;
	@Basic
	@Column(name = "views")
	private int views;
	@Basic
	@Column(name = "likes")
	private int likes;
	@Basic
	@Column(name = "thumbnail")
	private String thumbnail;
	@Basic
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Basic
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PostEntity that = (PostEntity)o;
		return postId == that.postId && authorId == that.authorId && Objects.equals(postTitle, that.postTitle)
			&& Objects.equals(postContent, that.postContent) && Objects.equals(views, that.views)
			&& Objects.equals(likes, that.likes) && Objects.equals(thumbnail, that.thumbnail)
			&& Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(postId, authorId, postTitle, postContent, views, likes, thumbnail, createdAt, updatedAt);
	}
}
