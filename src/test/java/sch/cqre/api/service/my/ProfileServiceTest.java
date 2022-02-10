package sch.cqre.api.service.my;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.repository.UserRepository;

@SpringBootTest
@Transactional
class ProfileServiceTest {
	@Autowired ProfileService profileService;
	@Autowired UserRepository userRepo;
	@Test
	void setProfile() {
		//given: 추후 로그인 완성되면 DB 생성 테스트 추가
		//When
		UserEntity testUser = profileService.changeProfile(1L,"C:\\Users\\khs\\Documents");
		//Then
		UserEntity findUser = userRepo.findById(1L).get();
		assertEquals(testUser.getProfile(), findUser.getProfile());
	}
}