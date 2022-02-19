package sch.cqre.api.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sch.cqre.api.dto.UserDto;


@Repository
public class UserVO {


    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public boolean invaildForm(UserDto form){

        /*
        회원가입 입력 폼을 체크하는 로직
        true = 유효한 값
        false = 유효하지 않은 값 (회원가입 불가)
         */

        final int passwordMin = 7;
        final int passwordMax = 10;
        final int nicknameMin = 3;
        final int nicknameMax = 10;


        if (form.getEmail() == ""){
            logger.warn("[err] 이메일 공백");
            return false;
        }
        if (form.getNickname() == ""){
            logger.warn("[err] 닉네임 공백");
            return false;
        }

        if (form.getNickname().length() < nicknameMin || form.getNickname().length() > nicknameMax ){
            logger.warn("[err] 닉네임 길이 부적합");
            return false;
        }

        if (form.getPassword().length() < passwordMin || form.getPassword().length() > passwordMax ){
            logger.warn("[err] 비밀번호 길이 부적합");
            return false;
        }


        return true;
    }
}
