package sch.cqre.api.model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ScrapEntityPK implements Serializable {
	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(name = "post_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
		ScrapEntityPK that = (ScrapEntityPK)o;
		return userId == that.userId && postId == that.postId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, postId);
	}
}
