package sch.cqre.api.domain;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Rental", schema = "main")
public class RentalEntity {
	@Id
	@Column(name = "user_id")
	private Long userId;
	@Basic(optional = false)
	@Column(name = "borrow_at", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp borrowAt;
	@Basic(optional = false)
	@Column(name = "return_at")
	private Timestamp returnAt;
	@Id
	@Column(name = "book_id")
	private int bookId;

	@Builder
	public RentalEntity(Timestamp borrowAt, Timestamp returnAt) {
		this.borrowAt = borrowAt;
		this.returnAt = returnAt;
	}

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
