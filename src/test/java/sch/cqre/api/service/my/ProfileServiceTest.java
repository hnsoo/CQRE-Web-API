package sch.cqre.api.service.my;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import sch.cqre.api.model.entity.UserEntity;
import sch.cqre.api.repository.UserRepository;

@SpringBootTest
@Transactional
class ProfileServiceTest {
	@Autowired ProfileService profileService;
	@Autowired UserRepository userRepo;
	@Test
	void setProfile() {
		//given
		//When
		UserEntity testUser = profileService.changeProfile(1L,"C:\\Users\\khs\\Documents");
		//Then
		UserEntity findUser = userRepo.findById(1L).get();
		assertEquals(testUser.getProfile(), findUser.getProfile());
	}
}