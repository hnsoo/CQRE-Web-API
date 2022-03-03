package sch.cqre.api.domain;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bookss", schema = "testdb")
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;

	@Basic(optional = false)
	@Column(name = "name")
	private String name;

	@Basic(optional = false)
	@Column(name = "publisher")
	private String publisher;

	@Basic(optional = false)
	@Column(name = "author")
	private String author;

	@Basic(optional = false)
	@Column(name = "amount")
	private byte amount;

	@Basic(optional = false)
	@Column(name = "remain_amount")
	private byte remainAmount;

	@Builder
	public BookEntity(String name, String publisher, String author, byte amount, byte remainAmount) {
		this.name = name;
		this.publisher = publisher;
		this.author = author;
		this.amount = amount;
		this.remainAmount = remainAmount;
	}

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
