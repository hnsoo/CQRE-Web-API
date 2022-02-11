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
@Table(name = "Scrap", schema = "main")
@IdClass(ScrapEntityPK.class)
public class ScrapEntity {
	@Id
	@Column(name = "user_id")
	private Long userId;
	@Id
	@Column(name = "post_id")
	private Long postId;

	@Builder
	public ScrapEntity(Long userId, Long postId) {
		this.userId = userId;
		this.postId = postId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ScrapEntity that = (ScrapEntity)o;
		return userId == that.userId && postId == that.postId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, postId);
	}
}
