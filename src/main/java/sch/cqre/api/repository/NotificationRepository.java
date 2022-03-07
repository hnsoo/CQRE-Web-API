package sch.cqre.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sch.cqre.api.domain.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
	Integer countByNotiId(Long notiId);
	List<NotificationEntity> findByReceiverId(Long receiverId);
	List<NotificationEntity> findByReceiverIdAndWhether(Long receiverId, Boolean whether);
	List<NotificationEntity> findAllByReceiverId(Long receiverId);
}
