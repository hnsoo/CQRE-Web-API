package sch.cqre.api.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.UserDto;

/*

        TODO : USerDto @Lazy를 빈 순환 참조로 인해 임시방편으로 했음.
                난중에 수정바람
 */

@Repository
public class UserDAO {

    private final @Lazy UserRepository userRepository;
    public UserDAO(@Lazy UserDAO userDao, UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
    public UserEntity add( @Lazy UserDto form){
        //user테이블에 회원정보를 넣습니다.
        UserEntity user = form.toEntity();
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity modify(@Lazy UserDto form){
        //user테이블 -> 회원정보를 수정합니다.
        UserEntity user = form.toEntity();
        return userRepository.save(user);
    }



}
