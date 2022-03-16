package sch.cqre.api.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Rental", schema = "main", catalog = "")
@IdClass(RentalEntityPK.class)
public class RentalEntity {
	@Id
	@Column(name = "user_id")
	private Long userId;

	@Basic
	@Column(name = "borrow_at")
	@CreationTimestamp
	private Timestamp borrowAt;

	@Basic
	@Column(name = "return_at")
	@UpdateTimestamp
	private Timestamp returnAt;

	@Id
	@Column(name = "book_id")
	private Long bookId;

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//
//		RentalEntity that = (RentalEntity) o;
//
//		if (userId != that.userId) return false;
//		if (bookId != that.bookId) return false;
//		if (borrowAt != null ? !borrowAt.equals(that.borrowAt) : that.borrowAt != null) return false;
//		if (returnAt != null ? !returnAt.equals(that.returnAt) : that.returnAt != null) return false;
//
//		return true;
//	}

//	@Override
//	public int hashCode() {
//		int result = userId;
//		result = 31 * result + (borrowAt != null ? borrowAt.hashCode() : 0);
//		result = 31 * result + (returnAt != null ? returnAt.hashCode() : 0);
//		result = 31 * result + bookId;
//		return result;
//	}
}
