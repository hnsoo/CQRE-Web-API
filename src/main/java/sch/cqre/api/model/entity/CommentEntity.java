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
@Table(name = "Comment", schema = "main")
public class CommentEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "comment_id")
	private int commentId;
	@Basic
	@Column(name = "post_id")
	private Integer postId;
	@Basic
	@Column(name = "author_id")
	private Integer authorId;
	@Basic
	@Column(name = "comment_content")
	private String commentContent;
	@Basic
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Basic
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
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
		CommentEntity that = (CommentEntity)o;
		return commentId == that.commentId && Objects.equals(postId, that.postId) && Objects.equals(
			authorId, that.authorId) && Objects.equals(commentContent, that.commentContent)
			&& Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentId, postId, authorId, commentContent, createdAt, updatedAt);
	}
}
