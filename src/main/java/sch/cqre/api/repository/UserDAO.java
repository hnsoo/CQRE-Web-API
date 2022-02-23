package sch.cqre.api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.jwt.Role;

@Repository
public class UserDAO {

    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


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
