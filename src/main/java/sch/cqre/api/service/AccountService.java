package sch.cqre.api.service;

import static sch.cqre.api.exception.ErrorCode.*;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.DeleteUserResponseDto;
import sch.cqre.api.dto.MyInfoResponseDto;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.UserRepository;

@Service
@AllArgsConstructor
public class AccountService {
	private final UserRepository userRepo;

	public MyInfoResponseDto searchById(Integer userId) {
		UserEntity userEntity = this.userRepo.findById(userId)
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
		return new MyInfoResponseDto(userEntity);
	}

	public MyInfoResponseDto searchByEmail(String email) {
		UserEntity userEntity = this.userRepo.findByEmail(email)
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
		return new MyInfoResponseDto(userEntity);
	}

	@Transactional
	public UserEntity setEmail(Integer userId, String email) {
		UserEntity userEntity = this.userRepo.findById(userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		userEntity.setEmail(email);
		return userEntity;
	}
	public void changePassword(Integer userId) {}

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

	//현재 사용중인 토큰의 주인(이메일)을 불러오는 함수
	public String getEmail(){
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
