/*

package sch.cqre.api.service;


import lombok.RequiredArgsConstructor;
import me.kimchi.JwtTest.entity.Authority;
import me.kimchi.JwtTest.entity.User;
import me.kimchi.JwtTest.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        return userRepository.findOneWithAuthoritiesByUsername(userName)
                .map(user -> createUser(userName, user))
                .orElseThrow(() ->new UsernameNotFoundException("there is no User By "+userName));
    }

    private org.springframework.security.core.userdetails.User createUser(String userName, User user){
        if(!user.isActivated()){
            throw new RuntimeException(userName+ "-> not Activated");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }





}

*/

