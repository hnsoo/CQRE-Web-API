package sch.cqre.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.jwt.Role;

@Repository
public class UserDAO {

    /* TODO : @LAZY 해결
        빈 오류 임시방편

       */


    private final UserRepository userRepository;



    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    public UserDAO(@Lazy UserDAO userDao, UserRepository userRepository) {
        this.userRepository = userRepository;
    } */


    /*
    public boolean accountDuplicationChk(UserDto form){

        boolean emailChk = userRepository.existByEmail(form.getEmail());
        boolean nicknameChk = userRepository.existsByNickname(form.getNickname());
        boolean studentIdChk = userRepository.existsByStudentId(form.getStudentId());

        if (emailChk) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        if (nicknameChk) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }

        if (studentIdChk) {
            throw new IllegalStateException("이미 존재하는 학번입니다.");
        }

        return false;

    } */

    @Transactional
    public UserEntity add( UserDto form){
        //user테이블에 회원정보를 넣습니다.
        //form.setRole( Role.define.GUEST);
        UserEntity user = form.toEntity();
        user.setRole(Role.define.role_GUEST);
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity modify(UserDto form){
        //user테이블 -> 회원정보를 수정합니다.
        UserEntity user = form.toEntity();
        //UserEntity.builder()
        return userRepository.save(user);
    }



}
