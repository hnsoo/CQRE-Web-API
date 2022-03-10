package sch.cqre.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.AccountDto;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.exception.ErrorCode;
import sch.cqre.api.jwt.JwtFilter;
import sch.cqre.api.jwt.Role;
import sch.cqre.api.jwt.TokenProvider;
import sch.cqre.api.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private ModelMapper modelMapper;


    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    @Transactional
    public UserEntity signUpProc(AccountDto.SignupRequest signupRequest){
        //회원 중복 검사
        if (userRepository.existByStudentId(signupRequest.getStudentId()))
            throw new CustomException(ErrorCode.DUPLICATE_STUDENTID);
        if (userRepository.existByEmail(signupRequest.getEmail()))
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        if (userRepository.existByNickname(signupRequest.getNickname()))
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);

        //패스워드 암호화
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        //signRequest -> UserEntity 객체 변경
        UserEntity signupForm = modelMapper.map(signupRequest, UserEntity.class);

        //실질적인 회원가입
        userRepository.save(signupForm);
        return signupForm;
    }


    //Join
    @Transactional
    public ResponseEntity loginProc(AccountDto.LoginRequest loginRequestDto){
        UserEntity userInfo = userRepository.findOneByEmail(loginRequestDto.getEmail());

        if (userInfo == null) throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), userInfo.getPassword())) throw new CustomException(ErrorCode.LOGIN_FAIL);

        String jwt = tokenProvider.createToken(String.valueOf(userInfo.getUserId()), userInfo.getEmail(), userInfo.getRole());
        log.info("login Success {}", userInfo.getEmail());
        log.info("created Token : " + jwt);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Baerer ".concat(jwt));
        AccountDto.LoginResponse loginResponse = modelMapper.map(userInfo, AccountDto.LoginResponse.class);

        return new ResponseEntity(loginResponse, httpHeaders, HttpStatus.OK);
    }

    //현재 사용중인 토큰의 주인(권한)을 불러오는 함수
    public String getRole(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

    public UserEntity getMyInfo(){
        return userRepository.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public boolean chkAdmin(){
        return getRole() == Role.define.role_ADMIN || getRole() == Role.define.role_MANAGER;
    }


    //회원탈퇴


}