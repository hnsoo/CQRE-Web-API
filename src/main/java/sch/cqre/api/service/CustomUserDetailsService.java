

package sch.cqre.api.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.jwt.SecurityUser;
import sch.cqre.api.repository.UserRepository;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

/*
       로그인하려는 email이 DB에 있는지 확인하는 loadUserByUsername
       유효하다면, UserEntity가 아닌 커스텀 유저인 SecurityUser를 반환

 */

    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> findMember = Optional.ofNullable(userRepository.findOneByEmail(email));
        if (!findMember.isPresent()) throw new UsernameNotFoundException("존재하지 않는 email 입니다.");

        log.info("loadUserByUsername member.email = {}", email);
        // log.info("loadUserByUsername member.email = {}", findMember.get().);

        return new SecurityUser(findMember.get());
    }

/*
    @Override
    public UserDetails loadUserByUid(String uid) throws UsernameNotFoundException {
        Optional<UserEntity> findMember = Optional.ofNullable(userRepository.findOnceByEmail(uid));
        if (!findMember.isPresent()) throw new UsernameNotFoundException("존재하지 않는 uid");

        log.info("loadUserByUsername member.uid = {}", uid);

        return new SecurityUser(findMember.get());
    }

 */


}



