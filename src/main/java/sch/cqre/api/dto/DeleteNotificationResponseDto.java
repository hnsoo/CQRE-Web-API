package sch.cqre.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteNotificationResponseDto {
	private Integer notiId;

	public DeleteNotificationResponseDto(Integer notiId) {
		this.notiId = notiId;
	}
}
