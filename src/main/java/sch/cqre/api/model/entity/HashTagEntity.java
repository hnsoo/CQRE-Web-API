package sch.cqre.api.model.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HashTag", schema = "main")
public class HashTagEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "hashtag_id")
	private int hashtagId;
	@Basic
	@Column(name = "name")
	private String name;

	public int getHashtagId() {
		return hashtagId;
	}

	public void setHashtagId(int hashtagId) {
		this.hashtagId = hashtagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HashTagEntity that = (HashTagEntity)o;
		return hashtagId == that.hashtagId && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hashtagId, name);
	}
}
