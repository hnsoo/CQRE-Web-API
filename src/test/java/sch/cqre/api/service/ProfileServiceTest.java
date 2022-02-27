package sch.cqre.api.service;

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
		UserEntity testUser = profileService.setProfile(1,"C:\\Users\\khs\\Documents");
		//Then
		UserEntity findUser = userRepo.findById(1).get();
		assertEquals(testUser.getProfile(), findUser.getProfile());
	}
}