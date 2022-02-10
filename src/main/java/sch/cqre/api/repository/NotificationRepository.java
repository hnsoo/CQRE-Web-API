package sch.cqre.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sch.cqre.api.domain.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
	List<NotificationEntity> deleteByReceiverId(Long receiverId);
	List<NotificationEntity> findByReceiverId(Long receiverId);
	List<NotificationEntity> deleteByReceiverIdAndReadOrNot(Long receiverId, int readOrNot);
}
