package sch.cqre.api.service.my;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import sch.cqre.api.model.entity.NotificationEntity;
import sch.cqre.api.repository.NotificationRepository;

@Service
@AllArgsConstructor
public class NoticeService {
	private final NotificationRepository notificationRepo;

	@Transactional
	public List<NotificationEntity> readAllNotice(Long userId) {
		List<NotificationEntity> result = this.notificationRepo.findByReceiverId(userId);
		if (result != null) {
			for (NotificationEntity notificationEntity : result) {
				notificationEntity.setReadOrNot(1);
			}
		}
		return result;
	}
	@Transactional
	public String checkNotice(Long notId) {
		NotificationEntity notificationEntity = this.notificationRepo.findById(notId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		notificationEntity.setReadOrNot(1);
		return notificationEntity.getNotUrl();
	}
	public void deleteAllReadNotice(Long userId) {
		this.notificationRepo.deleteByReceiverIdAndReadOrNot(userId, 1);
	}
	public void deleteAllNotice(Long userId) {
		this.notificationRepo.deleteByReceiverId(userId);
	}
	public void deleteOneNotice(Long notId) {
		this.notificationRepo.deleteById(notId);
	}
}
