package sch.cqre.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.jwt.*;
import sch.cqre.api.service.CustomUserDetailsService;

import java.util.Optional;


/*
 * @PreAuthorize를 method 단위로 추가하기 위해 사용
 * */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;







    //added
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("testAdmin").password("testAdmin").roles("ADMIN"))
                .withUser(users.username("testUser").password("testUser").roles("USER"));
    }

     */


    @Override
    public void configure(WebSecurity web) throws Exception {
                web
                .ignoring()
                .antMatchers("/favicon.ico"

                );
    }


    /* TODO :: https://daddyprogrammer.org/post/636/springboot2-springsecurity-authentication-authorization/
    */



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//rest api라 필요업슴
                .httpBasic().disable() //restapi이므로 필요업슴
                .exceptionHandling()// 예외 처리 진입점
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증 실패 진입점
                .accessDeniedHandler(jwtAccessDeniedHandler) // 인가 실패 진입점


                .and()
                .headers()
                .frameOptions()
                .sameOrigin()


                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)



                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                /* 로그인 했으면 접속 가능한 페이지 */
                .antMatchers("/mypage").hasAnyRole(Role.define.ALL_ACCOUNT)
                .antMatchers("/logout").hasAnyRole(Role.define.ALL_ACCOUNT)
                /* 관리자만 접속 가능한 페이지 */
                .antMatchers("/admin").hasAnyRole(Role.define.ONLY_ADMIN)
                /* 그냥 모두 접속 가능한 페이지 */
                .antMatchers("/about").permitAll()
                .antMatchers("/project").permitAll()
                .antMatchers("/board").permitAll()
                .antMatchers("/product").permitAll()
                .antMatchers("/contact").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()

                .and()
                .userDetailsService(customUserDetailsService) //springSecurity 로그인할때 CustomUserDetailsService를 사용하게 변경


                .apply(new JwtSecurityConfig(tokenProvider));

    }


}

