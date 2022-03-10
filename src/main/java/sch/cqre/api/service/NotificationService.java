package sch.cqre.api.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.NotificationEntity;
import sch.cqre.api.dto.NotiDto;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.NotificationRepository;

import java.util.List;
import java.util.stream.Collectors;

import static sch.cqre.api.exception.ErrorCode.DELETE_FAIL;
import static sch.cqre.api.exception.ErrorCode.NOTIFICATION_NOT_FOUND;

@Service
@AllArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepo;

	private ModelMapper modelMapper;

	// 유저 UID 를 기반으로 알림 검색
	public List<NotiDto.NotiResponse> searchByUserId(Long userId) {
		// 내 알림 불러오기
		List<NotificationEntity> notifications =  notificationRepo.findAllByReceiverId(userId);

		// 알림을 찾지 못할 경우 "알림 없음" 예외 처리
		if (notifications == null || notifications.isEmpty())
			throw new CustomException(NOTIFICATION_NOT_FOUND);
		// 객체 변환 List<NotificationEntity> -> List<NotificationResponseDto>
		return notifications.stream().map(p -> modelMapper.map(p, NotiDto.NotiResponse.class)).collect(Collectors.toList());
	}

	@Transactional
	public void readAllNotice(Long userId) {
		// 내 알림 불러오기
		List<NotificationEntity> notifications = this.notificationRepo.findByReceiverId(userId);

		// 알림이 없을 경우 "알림 없음" 예외 처리
		if (notifications == null || notifications.isEmpty())
			throw new CustomException(NOTIFICATION_NOT_FOUND);

		for (NotificationEntity notification : notifications) {
			// 읽음 처리
			this.checkNotification(notification.getNotiId());
		}
	}

	@Transactional
	public NotiDto.CheckNotiResponse checkNotification(Long notiId) {
		// 알림 UID 를 기반으로 알림 검색
		NotificationEntity notification = notificationRepo.findById(notiId)
			// 알림을 못 찾을 경우 "알림 없음" 예외 처리
			.orElseThrow(() -> new CustomException(NOTIFICATION_NOT_FOUND));

		// 안 읽없으면 읽음 처리
		if(!notification.getWhether())
			notification.setWhether(true);

		// 알림 반환
		return NotiDto.CheckNotiResponse.builder()
				.notiId(notification.getNotiId())
				.whether(notification.getWhether())
				.build();
	}

	@Transactional
	public NotiDto.DeleteNotiResponseDto deleteOneNotice(Long notiId) {
		// 존재하는 알림인지 확인
		if (notificationRepo.countByNotiId(notiId) != 1)
			throw new CustomException(NOTIFICATION_NOT_FOUND);
		// 삭제
		notificationRepo.deleteById(notiId);
		// 삭제 되었는지 확인
		if (notificationRepo.countByNotiId(notiId) != 0)
			// 삭제 실패시 "삭제 실패" 예외 처리
			throw new CustomException(DELETE_FAIL);
		return new NotiDto.DeleteNotiResponseDto(notiId);
	}

	@Transactional
	public void deleteReadNotification(Long userId) {
		// 나의 읽은 알림들 로드
		List<NotificationEntity> notifications = notificationRepo.findByReceiverIdAndWhether(userId, true);

		// 알림을 못 찾을 경우 "알림 없음" 예외 처리
		if (notifications == null || notifications.isEmpty())
			throw new CustomException(NOTIFICATION_NOT_FOUND);

		for(NotificationEntity notification : notifications){
			// 읽은 알림 삭제
			this.deleteOneNotice(notification.getNotiId());
		}
	}

	@Transactional
	public void deleteAllNotice(Long userId) {
		// 내 알림들 로드
		List<NotificationEntity> notifications = notificationRepo.findByReceiverId(userId);
		// 내 알림이 없을 경우 "알림 없음" 예외 처리
		if (notifications == null || notifications.isEmpty())
			throw new CustomException(NOTIFICATION_NOT_FOUND);

		for(NotificationEntity notification : notifications){
			// 알림 삭제
			this.deleteOneNotice(notification.getNotiId());
		}
	}
}
