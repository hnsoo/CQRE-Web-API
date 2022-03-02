package sch.cqre.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sch.cqre.api.domain.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
	Integer countByNotiId(Integer notiId);
	List<NotificationEntity> findByReceiverId(Integer receiverId);
	List<NotificationEntity> findByReceiverIdAndWhether(Integer receiverId, Boolean whether);
	Optional<List<NotificationEntity>> findAllByReceiverId(Integer receiverId);
}
