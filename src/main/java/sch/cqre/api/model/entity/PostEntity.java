package sch.cqre.api.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Post", schema = "main")
public class PostEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "post_id")
	private int postId;
	@Basic
	@Column(name = "author_id")
	private int authorId;
	@Basic
	@Column(name = "post_title")
	private String postTitle;
	@Basic
	@Column(name = "post_content")
	private String postContent;
	@Basic
	@Column(name = "views")
	private Object views;
	@Basic
	@Column(name = "likes")
	private Object likes;
	@Basic
	@Column(name = "thumbnail")
	private String thumbnail;
	@Basic
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Basic
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Object getViews() {
		return views;
	}

	public void setViews(Object views) {
		this.views = views;
	}

	public Object getLikes() {
		return likes;
	}

	public void setLikes(Object likes) {
		this.likes = likes;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
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
