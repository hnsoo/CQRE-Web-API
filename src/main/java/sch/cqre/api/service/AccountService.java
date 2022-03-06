package sch.cqre.api.service;

import static sch.cqre.api.exception.ErrorCode.*;

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

	// 테스트 용
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
		// System.out.println(passwordEncoder.encode(pw)+"	안녕");
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
		UserEntity user = this.userRepo.findById(myInfo.getUserId())
				.orElseThrow(()->new CustomException(MEMBER_NOT_FOUND));
		user.setPassword(passwordEncoder.encode(pwOne));
		return new PasswordResponseDto(myInfo.getUserId());
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
