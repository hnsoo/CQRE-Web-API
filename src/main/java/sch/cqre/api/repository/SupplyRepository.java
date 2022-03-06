package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.cqre.api.domain.SupplyEntity;

import java.util.List;

public interface SupplyRepository extends JpaRepository<SupplyEntity, Integer> {
    List<SupplyEntity> findByNameContainingIgnoreCase(String keyword);
}
