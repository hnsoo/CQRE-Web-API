package sch.cqre.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Getter @Setter
@Table(name = "Comment", schema = "main")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "comment_id", nullable = false)
	private Long commentId;
	@Basic(optional = false)

	@JsonIgnore
	@Column(name = "post_id")
	private Long postId;
/*	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_id")
	private ReactionEntity post;*/

	@Basic(optional = false)
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "author_id", referencedColumnName = "user_id")
	@Column(name = "author_id")
	private Long authorId;


	@Basic(optional = false)
	@Column(name = "comment_content")
	private String commentContent;

	@CreatedDate
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;


	@Transient
	private String author;


}
