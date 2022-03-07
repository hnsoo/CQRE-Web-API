package sch.cqre.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    CANNOT_FOLLOW_MYSELF(HttpStatus.BAD_REQUEST, "자기 자신은 팔로우 할 수 없습니다"),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "로그인에 실패 했습니다"),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력값입니다"),
    NOT_MY_POST(HttpStatus.BAD_REQUEST, "나의 게시물이 아닙니다"),
    MY_POST(HttpStatus.BAD_REQUEST, "나의 게시물에는 불가능합니다"),
            //게시물//
    //modifyError, notMyPostError, modifyError, modifyError



    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "권한이 없습니다"),
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "로그아웃 된 사용자입니다"),
    NOT_FOLLOW(HttpStatus.NOT_FOUND, "팔로우 중이지 않습니다"),
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "게시물을 찾을 수 없습니다"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재합니다"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이메일이 이미 존재합니다"),
    DUPLICATE_STUDENTID(HttpStatus.CONFLICT, "학번이 이미 존재합니다"),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "닉네임 이미 존재합니다"),
    POST_MODIFY_ERROR(HttpStatus.CONFLICT, "게시물 수정 실패했습니다"),
    DUPLICATE_POST(HttpStatus.CONFLICT, "중복된 게시물 id가 있습니다"),
    DUPLICATE_REACTION(HttpStatus.CONFLICT, "리액션은 한번만 가능합니다"),

    /* 500 Internal Server : 서버 처리 실패 오류 */
    POST_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "작성 실패했습니다"),
    DELETE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "삭제 실패했습니다"),
    TAG_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "TAG등록 실패했습니다"),

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}