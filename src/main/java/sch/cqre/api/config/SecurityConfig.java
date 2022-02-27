package sch.cqre.api.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.jwt.*;
import sch.cqre.api.service.CustomUserDetailsService;

import java.util.*;


/*
 * @PreAuthorize를 method 단위로 추가하기 위해 사용
 * */

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;



    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER > ROLE_GUEST");
        return roleHierarchy;
    }

    @Bean
    public SecurityExpressionHandler<FilterInvocation> expressionHandler() {
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        webSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
        return webSecurityExpressionHandler;
    }


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


                /* 일반 회원이면 접근 가능한 */
                .authorizeRequests()
                .antMatchers("/api/v1/post/**").hasRole("USER")
                .antMatchers("/api/v1/file/**").hasRole("USER")
                // .antMatchers("/api/v1/board/**").hasRole("USER")

                /* 로그인 했으면 접속 가능한 페이지 */
                .antMatchers("/mypage").hasRole("GUEST")

                /* 관리자만 접속 가능한 페이지 */
                .antMatchers("/admin").hasRole("ADMIN")


                /* 그냥 모두 접속 가능한 페이지 */
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/project").permitAll()
                .antMatchers("/board").permitAll()
                .antMatchers("/product").permitAll()
                .antMatchers("/contact").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                //.accessDecisionManager(accessDecisionManager())
                .expressionHandler(expressionHandler())
                .anyRequest().authenticated()


                .and()
                // .httpBasic().disable() //restapi이므로 필요업슴
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
                //   .userDetailsService(customUserDetailsService) //springSecurity 로그인할때 CustomUserDetailsService를 사용하게 변경


                .apply(new JwtSecurityConfig(tokenProvider));

    }


}

