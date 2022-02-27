package sch.cqre.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteNotificationResponse {
	private Integer notiId;
	private String status;
	private String message;

	public DeleteNotificationResponse(Integer notiId, String status, String message) {
		this.notiId = notiId;
		this.status = status;
		this.message = message;
	}
}
