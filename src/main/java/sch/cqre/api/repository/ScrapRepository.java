package sch.cqre.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sch.cqre.api.domain.NotificationEntity;
import sch.cqre.api.domain.ScrapEntity;

public interface ScrapRepository extends JpaRepository<ScrapRepository, Integer> {
	List<ScrapEntity> findByUserId(Integer UserId);
}
