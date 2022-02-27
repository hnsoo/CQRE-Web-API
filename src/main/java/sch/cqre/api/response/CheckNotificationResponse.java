package sch.cqre.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckNotificationResponse {
	private Integer notiId;
	private Integer notiPost;
	private String status;
	private String message;

	public CheckNotificationResponse(Integer notiId, Integer notiPost, String status, String message) {
		this.notiId = notiId;
		this.notiPost = notiPost;
		this.status = status;
		this.message = message;
	}
}
