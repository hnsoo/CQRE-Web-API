package sch.cqre.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import sch.cqre.api.domain.RentalEntity;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class RentalDto {
    private Integer userId;
    private Integer bookId;
    private Timestamp borrowAt;
    private Timestamp returnAt;

    public RentalDto(RentalEntity rentalEntity) {
        this.userId= rentalEntity.getUserId();
        this.bookId = rentalEntity.getBookId();
        this.borrowAt = rentalEntity.getBorrowAt();
        this.returnAt = rentalEntity.getReturnAt();
    }
}
