package sch.cqre.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sch.cqre.api.domain.ScrapEntity;

public interface ScrapRepository extends JpaRepository<ScrapEntity, Integer> {
	Optional<List<ScrapEntity>> findByUserId(Integer userId);
}
