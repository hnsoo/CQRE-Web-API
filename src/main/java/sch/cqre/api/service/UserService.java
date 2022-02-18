package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private UserDto memberDao;

    //Login
    public boolean getLoginChk() throws Exception{
        return true;
    }


    //Join
    public Long createUser(UserDto form){
       UserEntity user = form.toEntity();
       userRepository.save(user);
       return user.getUserId();
    }




    //Modify



    //회원탈퇴


}