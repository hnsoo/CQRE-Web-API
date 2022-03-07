package sch.cqre.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteNotificationResponseDto {
	private Long notiId;

	public DeleteNotificationResponseDto(Long notiId) {
		this.notiId = notiId;
	}
}
