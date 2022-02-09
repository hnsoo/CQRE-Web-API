package sch.cqre.api.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Rental", schema = "main")
public class RentalEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id")
	private Long userId;
	@Basic
	@Column(name = "borrow_at")
	private Timestamp borrowAt;
	@Basic
	@Column(name = "return_at")
	private Timestamp returnAt;
	@Basic
	@Column(name = "book_id")
	private int bookId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		RentalEntity that = (RentalEntity)o;
		return userId == that.userId && bookId == that.bookId && Objects.equals(borrowAt, that.borrowAt)
			&& Objects.equals(returnAt, that.returnAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, borrowAt, returnAt, bookId);
	}
}
