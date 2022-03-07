package sch.cqre.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    BOOK_NOT_EXIST(NOT_FOUND, "해당 책을 찾을 수 없습니다."),
    BOOK_ID_NOT_EXIST(NOT_FOUND, "해당 책 ID를 찾을 수 없습니다."),
    SUPPLY_NOT_EXIST(NOT_FOUND, "해당 비품을 찾을 수 없습니다."),
    SUPPLY_ID_NOT_EXIST(NOT_FOUND, "해당 비품 ID를 찾을 수 없습니다."),
    NOT_REMAINED(NOT_FOUND, "남아있는 수량이 없습니다."),


    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RENT(CONFLICT, "중복 대여입니다."),

    /* 500 INTERNAL_SERVER_ERROR : 일반적인 서버 에러 메세지로 요청 사항을 이행할 수 없는 상태 */
    FAIL_DELETE(INTERNAL_SERVER_ERROR, "삭제에 실패했습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String detail;
}
