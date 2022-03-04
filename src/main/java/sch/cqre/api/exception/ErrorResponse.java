package sch.cqre.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
	private final LocalDateTime timestamp = LocalDateTime.now();
	private final String code;
	private final String message;

	public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(ErrorResponse.builder()
				.code(errorCode.name())
				.message(errorCode.getDetail())
				.build()
			);
	}
}