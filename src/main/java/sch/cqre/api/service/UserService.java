package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.UserDto;

import sch.cqre.api.repository.UserDAO;
import sch.cqre.api.repository.UserRepository;
import sch.cqre.api.repository.UserVO;
//import sch.cqre.api.validator.EmailCheckValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDao;
    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    //private final EmailCheckValidator emailCheckValidator;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());



    //private SqlSessionTemplate session;


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





    //Login
    public boolean getLoginChk() throws Exception{
        return true;
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

        if (userRepository.countByEmail(email) == 0){
            return ResponseEntity.badRequest().body("notFoundAccount");
        }

        if (!passwordEncoder.matches(password, userInfo.getPassword())){

            return ResponseEntity.badRequest().body("idPasswordNotMatched");
        }

       // return userDao.add(form);
        /*
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("user_id", String.valueOf(userInfo.getUserId()));
        header.add("student_id", String.valueOf(userInfo.getStudentId()));
        header.add("email", userInfo.getEmail());
        header.add("nickname", userInfo.getNickname());
        header.add("user_type", userInfo.getUserType()); */
        return new ResponseEntity(userInfo, HttpStatus.OK);
    }

    /* 회원가입 시, 유효성 체크 */
    /*
    @Transactional(readOnly = true)
    public Map < String, String > validateHandling(Errors errors) {
        Map< String, String > validatorResult = new HashMap< >(); /* 유효성 검사에 실패한 필드 목록을 받음 */
       /* for (FieldError error: errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
    */




    //Modify



    //회원탈퇴


}