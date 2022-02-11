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
@Table(name = "Comment", schema = "main")
public class CommentEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "comment_id")
	private Long commentId;
	@Basic(optional = false)
	@Column(name = "post_id")
	private Long postId;
	@Basic(optional = false)
	@Column(name = "author_id")
	private Long authorId;
	@Basic(optional = false)
	@Column(name = "comment_content")
	private String commentContent;
	@Basic(optional = false)
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Basic(optional = false)
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Builder
	public CommentEntity(Long postId, Long authorId, String commentContent, Timestamp createdAt,
		Timestamp updatedAt) {
		this.postId = postId;
		this.authorId = authorId;
		this.commentContent = commentContent;
		this.createdAt = createdAt;
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
