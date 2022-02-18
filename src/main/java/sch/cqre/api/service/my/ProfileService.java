package sch.cqre.api.service.my;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.repository.UserRepository;

@Service
@AllArgsConstructor
public class ProfileService {
	private final UserRepository userRepo;

	public UserEntity setProfile(Integer userid, String path) {
		UserEntity userEntity = this.userRepo.findById(userid)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		userEntity.setProfile(path);
		return this.userRepo.save(userEntity);
	}
}
