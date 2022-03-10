package sch.cqre.api.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                       // .status(errorCode.getHttpStatus().value())
                       // .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getDetail())
                        .build()
                );
    }

    public static ResponseEntity<ErrorResponse> toResponseValid(String message){
        return ResponseEntity
                .status(ErrorCode.INVALID_INPUT.getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.INVALID_INPUT.name())
                        .message(message)
                        .build()
                );
    }

}
