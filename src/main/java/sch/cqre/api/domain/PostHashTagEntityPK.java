package sch.cqre.api.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import lombok.Data;

@Data
public class PostHashTagEntityPK implements Serializable {

	//@Id
	@Column(name = "post_id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;

	//@Id
	@Column(name = "hashtag_id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hashtagId;

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
