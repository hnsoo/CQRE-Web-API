package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.UserDto;

import sch.cqre.api.repository.UserDAO;
import sch.cqre.api.repository.UserRepository;
import sch.cqre.api.repository.UserVO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDAO userDao;
    private final UserVO userVo;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());



    //private SqlSessionTemplate session;

    public boolean checkEmailDuplicate(String email){
        return false;
    }

    //Login
    public boolean getLoginChk() throws Exception{
        return true;
    }


    public boolean existId(String id){

    //    UserVO user = session.selectOne("userDB.selectUser", id);
       return false;
    }

    //Join
    @Transactional
    public Long createUser(UserDto form){
      //  if (userVo.invaildForm(form)) //회원가입 값 유효성 확인
        userDao.accountDuplicationChk(form);
        return userDao.add(form);
    }



    //Modify



    //회원탈퇴


}