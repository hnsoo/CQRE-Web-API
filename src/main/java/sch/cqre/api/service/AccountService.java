package sch.cqre.api.service;

import static sch.cqre.api.exception.ErrorCode.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.DeleteUserResponseDto;
import sch.cqre.api.dto.MyInfoDto;
import sch.cqre.api.dto.PasswordResponseDto;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.UserRepository;

@Service
@AllArgsConstructor
public class AccountService {
	private final UserRepository userRepo;

	PasswordEncoder passwordEncoder;

	public MyInfoDto searchById(Integer userId) {
		UserEntity userEntity = this.userRepo.findById(userId)
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
		return new MyInfoDto(userEntity);
	}

	public MyInfoDto searchByEmail(String email) {
		UserEntity userEntity = this.userRepo.findByEmail(email)
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
		return new MyInfoDto(userEntity);
	}

	// 비밀번호 확인
	public PasswordResponseDto checkPassword(MyInfoDto myInfo, String pw) {
		// 내 정보 로드
		UserEntity user = this.userRepo.findById(myInfo.getUserId())
			// 못 찾으면 없음 예외 처리
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

		// DB 비밀번호 정보와 지급한 비밀번호 서로 비교
		if (!user.getPassword().equals(passwordEncoder.encode(pw)))
			throw new CustomException(INVALID_PASSWORD);
		return new PasswordResponseDto(myInfo.getUserId());
	}

	@Transactional
	public ChangePWResponseDto changePassword(UserEntity user) {
		// 비밀번호 해시값 생성
		createPassword()
		userEntity.setPassword();
	}

	public DeleteUserResponseDto withdrawal(Integer userId) {
		// 존재하는 유저인지 확인
		if (userRepo.countByUserId(userId) != 1)
			// 없으면 "유저 없음" 예외 처리
			throw new CustomException(MEMBER_NOT_FOUND);
		// 삭제
		userRepo.deleteById(userId);
		// 삭제 되었는지 확인
		if (userRepo.countByUserId(userId) != 0)
			// 삭제 실패시 "삭제 실패" 예외 처리
			throw new CustomException(FAIL_DELETE);
		// 삭제 성공시 삭제한 유저 아이디 반환
		return new DeleteUserResponseDto(userId);
	}


}
