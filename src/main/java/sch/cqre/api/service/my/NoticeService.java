package sch.cqre.api.service.my;

import java.util.List;

import org.aspectj.weaver.ast.Not;
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
				notificationEntity.setWhether(1);
			}
		}
		return result;
	}

	@Transactional
	public NotificationEntity checkNotice(Integer notId) {
		NotificationEntity notificationEntity = this.notificationRepo.findById(notId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		notificationEntity.setWhether(1);
		return notificationEntity;
	}
	public void deleteReadNotice(Integer ReceiverId) {
		this.notificationRepo.deleteByReceiverIdAndWhether(ReceiverId, 1);
	}
	public void deleteAllNotice(Integer userId) {
		this.notificationRepo.deleteByReceiverId(userId);
	}
	public Boolean deleteOneNotice(Integer notId) {
		this.notificationRepo.deleteById(notId);
		this.notificationRepo.findById(notId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.OK));
		return false;
	}
}
