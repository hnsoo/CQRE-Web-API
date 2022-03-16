package sch.cqre.api.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sch.cqre.api.domain.RentalEntity;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class RentalDTO {
    private Long userId;
    private Long bookId;
    private Timestamp borrowAt;
    private Timestamp returnAt;

    public RentalDTO(RentalEntity rentalEntity) {
        this.userId= rentalEntity.getUserId();
        this.bookId = rentalEntity.getBookId();
        this.borrowAt = rentalEntity.getBorrowAt();
        this.returnAt = rentalEntity.getReturnAt();
    }
}
