package sch.cqre.api.model.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "Scrap", schema = "main")
@IdClass(ScrapEntityPK.class)
public class ScrapEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id")
	private int userId;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "post_id")
	private int postId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
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
