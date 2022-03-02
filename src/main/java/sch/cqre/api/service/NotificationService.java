package sch.cqre.api.service;

import static sch.cqre.api.exception.ErrorCode.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.NotificationEntity;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.NotificationRepository;
import sch.cqre.api.response.DeleteNotificationResponse;

@Service
@AllArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepo;

	// 유저 UID 를 기반으로 알림 검색
	public List<NotificationEntity> searchByUserId(Integer userId) {
		return notificationRepo.findAllByReceiverId(userId)
			// 알림을 찾지 못할 경우 "알림 없음" 예외 처리
			.orElseThrow(() -> new CustomException(NOTIFICATION_NOT_FOUND));
	}

	public boolean readAllNotice(Integer userId) {
		// 내 알림 불러오기
		List<NotificationEntity> notifications = this.notificationRepo.findByReceiverId(userId)
			// 알림이 없을 경우 "알림 없음" 예외 처리
			.orElseThrow(() -> new CustomException(NOTIFICATION_NOT_FOUND));

		for (NotificationEntity notification : notifications) {
			// 읽음 처리
			this.checkNotification(notification.getNotiId());
		}
		return true;

	}

	@Transactional
	public NotificationEntity checkNotification(Integer notiId) {
		// 알림 UID 를 기반으로 알림 검색
		NotificationEntity notification = notificationRepo.findById(notiId)
			// 알림을 못 찾을 경우 "알림 없음" 예외 처리
			.orElseThrow(() -> new CustomException(NOTIFICATION_NOT_FOUND));

		// 안 읽없으면 읽음 처리
		if(!notification.getWhether())
			notification.setWhether(true);

		// 알림 반환
		return notification;
	}

	public Boolean deleteReadNotification(Integer userId) {
		// 나의 읽은 알림들 로드
		List<NotificationEntity> notifications = notificationRepo.findByReceiverIdAndWhether(userId, true)
			// 알림을 못 찾을 경우 "알림 없음" 예외 처리
			.orElseThrow(() -> new CustomException(NOTIFICATION_NOT_FOUND));
		for(NotificationEntity notification : notifications){
			// 읽은 알림 삭제 및 결과 객체를 리스트에 반환
			this.deleteOneNotice(notification.getNotiId());
		}
		return true;
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

	public Integer deleteOneNotice(Integer notiId) {
		// 존재하는 알림인지 확인
		if (notificationRepo.countByNotiId(notiId) != 1)
			throw new CustomException(NOTIFICATION_NOT_FOUND);
		// 삭제
		notificationRepo.deleteById(notiId);
		// 삭제 되었는지 확인
		if (notificationRepo.countByNotiId(notiId) != 0)
			// 삭제 실패시 "삭제 실패" 예외 처리
			throw new CustomException(FAIL_DELETE);

		return notiId;
	}
}
