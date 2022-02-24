package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.UserDto;

import sch.cqre.api.jwt.JwtFilter;
import sch.cqre.api.jwt.TokenProvider;
import sch.cqre.api.repository.UserDAO;
import sch.cqre.api.repository.UserRepository;

import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserDAO userDao;
    private final JsonMessager jsonMessager;


    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;


    public String signupValidChk(UserDto form){

        /*
        회원가입 입력 폼을 체크하는 로직
        "fine" = 유효한 값
        "message" = 유효하지 않음 (회원가입 불가)
         */

        if (!Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", form.getEmail())){
            return "EmailWrong"; //이메일 양식 오류
        }
            if (!(form.getNickname().length() > 1 && form.getNickname().length() <= 10)){
                return "NicknameWrong"; //닉네임은 2자이상 10자 이하
            }
            if (!(form.getStudentId() > 20000000 && form.getStudentId() < 29999999)){
                return "StudentIdWrong"; //학번 잘못 기재함
            }
        if (!Pattern.matches("^[a-zA-Z0-9]{10,15}$", form.getPassword())){
            return "PasswordWrong"; //비밀번호는 숫자+영문 조합으로 8~20자
        }

            return "fine";
        }

        public String signupDuplicateChk(UserDto form) {

        /*
        중복가입 체크 로직
        "fine" = 중복되는 값 없음 (이메일, 닉네임, 학번)
        "message" = 중복되는 값 있음 (회원가입 불가)
         */

            if (userRepository.countByStudentId(form.getStudentId()) != 0)
                return "studentId_duplicate";
            if (userRepository.countByEmail(form.getEmail()) != 0)
                return "email_duplicate";
            if (userRepository.countByNickname(form.getNickname()) != 0)
                return "nickname_duplicate";


            return "fine";

        }

    //Join
    @Transactional
    public UserEntity createUser(UserDto form){
        return userDao.add(form);
    }


    //Join
    @Transactional
    public ResponseEntity loginProc(String email, String password){

        UserEntity userInfo = userRepository.findOnceByEmail(email);

        if (email.equals("") || password.equals("")){
            return jsonMessager.err("notValidInput");
        }

        if (userRepository.countByEmail(email) == 0){
            return jsonMessager.err("notFoundAccount");
        }

        if (!passwordEncoder.matches(password, userInfo.getPassword())){

            return jsonMessager.err("idPasswordNotMatched");
        }

        String jwt = tokenProvider.createToken(String.valueOf(userInfo.getUserId()), userInfo.getEmail(), userInfo.getRole());
        log.warn("created Token : " + jwt);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Baerer ".concat(jwt));
        return new ResponseEntity(userInfo, httpHeaders, HttpStatus.OK);
    }


    public String getEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String getRole(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

    //Modify



    //회원탈퇴


}