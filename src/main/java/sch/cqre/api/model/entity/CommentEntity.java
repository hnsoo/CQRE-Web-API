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

import lombok.Data;

@Data
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
