package sch.cqre.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomExeption extends RuntimeException{
    private final ErrorCode errorCode;
}
