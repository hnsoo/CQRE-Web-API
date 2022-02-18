package sch.cqre.api.service.my;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.repository.UserRepository;

@Service
@AllArgsConstructor
public class AccountService {
	private final UserRepository userRepo;

	public UserEntity searchById(Integer id) {3
		UserEntity result = this.userRepo.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return result;
	}
	@Transactional
	public UserEntity setProfile(Integer userid, String path) {
		UserEntity userEntity = this.userRepo.findById(userid)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		userEntity.setProfile(path);
		return userEntity;
	}
	@Transactional
	public UserEntity setEmail(Integer userId, String email) {
		UserEntity userEntity = this.userRepo.findById(userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		userEntity.setEmail(email);
		return userEntity;
	}
	public void changePassword(Integer userId) {}
	public void withdrawal(Integer userId) {
		this.userRepo.deleteById(userId);
	}
}
