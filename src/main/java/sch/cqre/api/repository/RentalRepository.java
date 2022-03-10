package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.RentalEntity;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {
    RentalEntity findByBookIdAndUserId(Integer bookId, Integer userId);
}
