package sch.cqre.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Setter @Getter
@Builder
@AllArgsConstructor
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Post", schema = "main")
public class PostEntity{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private long postId;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="author_id", referencedColumnName = "user_id")
	//@Column(name = "author_id")
	private UserEntity user;


	@Basic(optional = false)
	@Column(name = "post_title")
	private String postTitle;

	@Basic(optional = false)
	@Column(name = "post_content")
	private String postContent;

	@Column(name = "views", columnDefinition = "int unsigned default 0")
	private int views;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "created_at")
	@CreatedDate
	private Timestamp createdAt;

	@Column(name = "updated_at")
	@LastModifiedDate
	private Timestamp updatedAt;

//	@Builder.Default


//	@Transient
//	@JoinColumn(insertable=false, updatable = false)
//	List<CommentEntity> comments = new ArrayList<>();

/*
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(insertable=false, updatable = false)
	public List<PostHashTagEntity> hashtags = new ArrayList<PostHashTagEntity>();
*/

	/*
	@Transient
	@JoinColumn(insertable=false, updatable = false)
	List<ReactionEntity> reactions = new ArrayList<>();*/

//	@Builder.Default
/*	@Transient
	@JoinColumn(insertable=false, updatable = false)
	List<PostHashTagEntity> hashtags = new ArrayList<>();*//*

//
//	@Builder.Default

	@JoinColumn(insertable=false, updatable = false)
	List<ReactionEntity> reactions = new ArrayList<>();








/*	@PreUpdate
	public void mappingUserAndPost(){
		//this.setAuthorId();
	}*/

}
