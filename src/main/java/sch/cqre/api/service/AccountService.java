package sch.cqre.api.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.DeleteUserResponseDto;
import sch.cqre.api.dto.MyInfoDto;
import sch.cqre.api.dto.PasswordResponseDto;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.UserRepository;

import static sch.cqre.api.exception.ErrorCode.DIFFERENT_PASSWORD;
import static sch.cqre.api.exception.ErrorCode.INVALID_PASSWORD;

@Service
@AllArgsConstructor
public class AccountService {
	private final UserRepository userRepo;

	PasswordEncoder passwordEncoder;

	// // 테스트 용
	// public MyInfoDto searchById(Integer userId) {
	// 	UserEntity userEntity = this.userRepo.findById(userId)
	// 		.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
	// 	return new MyInfoDto(userEntity);
	// }
	//
	// public MyInfoDto searchByEmail(String email) {
	// 	UserEntity userEntity = this.userRepo.findByEmail(email)
	// 		.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
	// 	return new MyInfoDto(userEntity);
	// }

	// 비밀번호 확인
	public PasswordResponseDto checkPassword(MyInfoDto myInfo, String pw) {
		// 내 정보 로드
		UserEntity user = this.userRepo.findOne(myInfo.getUserId());

		// DB 비밀번호 정보와 지급한 비밀번호 서로 비교
		if (!passwordEncoder.matches(pw, user.getPassword()))
			throw new CustomException(INVALID_PASSWORD);
		return new PasswordResponseDto(myInfo.getUserId());
	}

	@Transactional
	// 비밀번호 재설정시 오타 확인
	public PasswordResponseDto changePassword(MyInfoDto myInfo, String pwOne, String pwTwo) {
		// 입력한 두 개의 비밀번호가 일치하는지 비교
		if (!pwOne.equals(pwTwo))
			throw new CustomException(DIFFERENT_PASSWORD);
		UserEntity user = this.userRepo.findOne(myInfo.getUserId());
		user.setPassword(passwordEncoder.encode(pwOne));
		return new PasswordResponseDto(myInfo.getUserId());
	}

	public DeleteUserResponseDto withdrawal(Long userId) {
		// 삭제
		userRepo.deleteOne(userId);
		// 삭제 성공시 삭제한 유저 아이디 반환
		return new DeleteUserResponseDto(userId);
	}


}
