package sch.cqre.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import sch.cqre.api.domain.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
	List<NotificationEntity> deleteByReceiverId(Integer receiverId);
	List<NotificationEntity> findByReceiverId(Integer receiverId);
	List<NotificationEntity> deleteByReceiverIdAndWhether(Integer receiverId, Integer whether);
}
