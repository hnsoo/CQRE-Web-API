package sch.cqre.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.NotificationEntity;
import sch.cqre.api.repository.NotificationRepository;
import sch.cqre.api.response.CheckNotificationResponse;
import sch.cqre.api.response.DeleteNotificationResponse;

@Service
@AllArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepo;

	public List<NotificationEntity> searchByUserId(Integer userId) {
		return notificationRepo.findAllByReceiverId(userId);
	}

	public List<CheckNotificationResponse> readAllNotice(Integer userId) {
		List<CheckNotificationResponse> result = new ArrayList<>();
		// 내 알림 불러오기
		List<NotificationEntity> notifications = this.notificationRepo.findByReceiverId(userId);
		// 알림이 없을 경우
		if (notifications == null){
			result.add(new CheckNotificationResponse(null, null, "fail", "존재하는 알림이 없음"));
			return result;
		}
		for (NotificationEntity notification : notifications) {
			// 읽음 처리 후 결과 객체를 리스트에 저장
			result.add(this.checkNotification(notification.getNotiId()));
		}
		return result;

	}

	@Transactional
	public CheckNotificationResponse checkNotification(Integer notiId) {
		// 존재하는 알림인지 확인
		if (notificationRepo.countByNotiId(notiId) != 1)
			return new CheckNotificationResponse(notiId, null, "error", "존재하지 않는 알림");
		NotificationEntity notification = notificationRepo.getById(notiId);

		// 안 읽없으면 읽음 처리
		if(!notification.getWhether())
			notification.setWhether(true);

		// 결과 반환
		return new CheckNotificationResponse(notiId, notification.getNotiPost(), "success", "알림 확인 성공");
	}

	public List<DeleteNotificationResponse> deleteReadNotification(Integer userId) {
		List<DeleteNotificationResponse> result = new ArrayList<>();
		// 나의 읽은 알림들 로드
		List<NotificationEntity> notifications = notificationRepo.findByReceiverIdAndWhether(userId, true);
		for(NotificationEntity notification : notifications){
			// 읽은 알림 삭제 및 결과 객체를 리스트에 반환
			result.add(this.deleteOneNotice(notification.getNotiId()));
		}
		return result;
	}

	public List<DeleteNotificationResponse> deleteAllNotice(Integer userId) {
		List<DeleteNotificationResponse> result = new ArrayList<>();
		// 내 알림들 로드
		List<NotificationEntity> notifications = notificationRepo.findByReceiverId(userId);
		// 내 알림이 없을 경우
		if (notifications == null){
			result.add(new DeleteNotificationResponse(null, "error", "존재하는 알림이 없음"));
			return result;
		}
		for(NotificationEntity notification : notifications){
			// 알림 삭제 및 결과 코드를 리스트에 저장
			result.add(this.deleteOneNotice(notification.getNotiId()));
		}
		return result;
	}

	public DeleteNotificationResponse deleteOneNotice(Integer notiId) {
		//존재하는 알림인지 확인
		if (notificationRepo.countByNotiId(notiId) != 1)
			return new DeleteNotificationResponse(notiId, "error", "존재하지 않는 알림");
		//존재하면 삭제
		notificationRepo.deleteById(notiId);
			return new DeleteNotificationResponse(notiId, "success", "알림 삭제 성공");
	}
}
