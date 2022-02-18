package sch.cqre.api.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PostHashTag", schema = "main")
@IdClass(PostHashTagEntityPK.class)
public class PostHashTagEntity {
	@Id
	@Column(name = "post_id")
	private Integer postId;
	@Id
	@Column(name = "hashtag_id")
	private Integer hashtagId;

	@Builder
	public PostHashTagEntity(Integer postId, Integer hashtagId) {
		this.postId = postId;
		this.hashtagId = hashtagId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PostHashTagEntity that = (PostHashTagEntity)o;
		return postId == that.postId && hashtagId == that.hashtagId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(postId, hashtagId);
	}
}
