package sch.cqre.api.service.my;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.NotificationEntity;
import sch.cqre.api.repository.NotificationRepository;

@Service
@AllArgsConstructor
public class NoticeService {
	private final NotificationRepository notificationRepo;

	public List<NotificationEntity> searchAllByUserId(Integer userId) {
		return this.notificationRepo.findByReceiverId(userId);
	}

	@Transactional
	public List<NotificationEntity> readAllNotice(Integer userId) {
		List<NotificationEntity> result = this.notificationRepo.findByReceiverId(userId);
		if (result != null) {
			for (NotificationEntity notificationEntity : result) {
				notificationEntity.setReadOrNot(1);
			}
		}
		return result;
	}

	@Transactional
	public String checkNotice(Integer notId) {
		NotificationEntity notificationEntity = this.notificationRepo.findById(notId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		notificationEntity.setReadOrNot(1);
		return notificationEntity.getNotUrl();
	}
	public void deleteReadNotice(Integer userId) {
		this.notificationRepo.deleteByReceiverIdAndReadOrNot(userId, 1);
	}
	public void deleteAllNotice(Integer userId) {
		this.notificationRepo.deleteByReceiverId(userId);
	}
	public void deleteOneNotice(Integer notId) {
		this.notificationRepo.deleteById(notId);
	}
}
