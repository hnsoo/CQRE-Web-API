package sch.cqre.api.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class RentalEntityPK implements Serializable {
	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "borrow_at")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer borrowAt;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		RentalEntityPK that = (RentalEntityPK)o;
		return userId == that.userId && borrowAt == that.borrowAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, borrowAt);
	}
}