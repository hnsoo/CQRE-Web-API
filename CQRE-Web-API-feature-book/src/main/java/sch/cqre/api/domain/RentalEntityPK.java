package sch.cqre.api.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class RentalEntityPK implements Serializable {
    @Column(name = "user_id")
    @Id
    private int userId;

    @Column(name = "book_id")
    @Id
    private int bookId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalEntityPK that = (RentalEntityPK) o;

        if (userId != that.userId) return false;
        if (bookId != that.bookId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + bookId;
        return result;
    }
}
