package sch.cqre.api.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import sch.cqre.api.domain.UserEntity;

@Getter
@Setter
@Slf4j
public class SecurityUser extends User {

    /*

    SpringSecurity가 제공하는 User를 우리가 정의한 User로 사용하기 위함


     */

    private UserEntity member;

    public SecurityUser(UserEntity member) {
        super(member.getEmail(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));


        log.info("SecurityUser UserEntity.uid = {}", member.getUserId());
        log.info("SecurityUser UserEntity.username = {}", member.getEmail());
        log.info("SecurityUser UserEntity.password = {}", member.getPassword());
        log.info("SecurityUser UserEntity.role = {}", member.getRole().toString());

        this.member = member;
    }

}
