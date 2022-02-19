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

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Post", schema = "main")
public class PostEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "post_id")
	private Long postId;
	@Basic(optional = false)
	@Column(name = "author_id")
	private Long authorId;
	@Basic(optional = false)
	@Column(name = "post_title")
	private String postTitle;
	@Basic(optional = false)
	@Column(name = "post_content")
	private String postContent;
	@Basic
	@Column(name = "views", columnDefinition = "int unsigned default 0")
	private int views;
	@Basic
	@Column(name = "likes", columnDefinition = "int unsigned default 0")
	private int likes;
	@Basic
	@Column(name = "thumbnail")
	private String thumbnail;
	@Basic(optional = false)
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createdAt;
	@Basic(optional = false)
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Builder
	public PostEntity(Long authorId, String postTitle, String postContent, int views, int likes, String thumbnail,
		Timestamp createdAt, Timestamp updatedAt) {
		this.authorId = authorId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.views = views;
		this.likes = likes;
		this.thumbnail = thumbnail;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

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
