package sch.cqre.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sch.cqre.api.domain.NotificationEntity;

@Getter
@NoArgsConstructor
public class CheckNotificationResponseDto {
	private Integer notiId;
	private boolean whether;

	public CheckNotificationResponseDto(NotificationEntity notification) {
		this.notiId = notification.getNotiId();
		this.whether = notification.getWhether();
	}
}
