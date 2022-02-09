package sch.cqre.api.model.entity;

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
@Table(name = "Book", schema = "main")
public class BookEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "book_id")
	private Long bookId;
	@Basic
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "publisher")
	private String publisher;
	@Basic
	@Column(name = "author")
	private String author;
	@Basic
	@Column(name = "amount")
	private byte amount;
	@Basic
	@Column(name = "remain_amount")
	private byte remainAmount;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BookEntity that = (BookEntity)o;
		return bookId == that.bookId && amount == that.amount && remainAmount == that.remainAmount
			&& Objects.equals(name, that.name) && Objects.equals(publisher, that.publisher)
			&& Objects.equals(author, that.author);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, name, publisher, author, amount, remainAmount);
	}
}
