package sch.cqre.api.model.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PostHashTagEntityPK implements Serializable {
	@Column(name = "post_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	@Column(name = "hashtag_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hashtagId;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getHashtagId() {
		return hashtagId;
	}

	public void setHashtagId(int hashtagId) {
		this.hashtagId = hashtagId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PostHashTagEntityPK that = (PostHashTagEntityPK)o;
		return postId == that.postId && hashtagId == that.hashtagId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(postId, hashtagId);
	}
}
