package sch.cqre.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import sch.cqre.api.jwt.JwtAccessDeniedHandler;
import sch.cqre.api.jwt.JwtAuthenticationEntryPoint;
import sch.cqre.api.jwt.JwtSecurityConfig;
import sch.cqre.api.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
/*
 * @PreAuthorize를 method 단위로 추가하기 위해 사용
 * */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

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
                .csrf().disable()
                //rest api라 필요업슴

                .httpBasic().disable()
                //restapi이므로 필요업슴

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()

                .headers()
                .frameOptions()
                .sameOrigin()
                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()


                .authorizeRequests()

                .antMatchers("/api/v1/**").permitAll()
             //   .antMatchers("/api/authenticate").permitAll()

                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()

                //인가된 유저만 접속
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/board").hasRole("USER")

       //          .antMatchers("*").permitAll()
                .anyRequest().authenticated()
                .and()

                .apply(new JwtSecurityConfig(tokenProvider))
        ;
    }


}

