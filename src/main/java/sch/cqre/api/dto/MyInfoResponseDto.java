package sch.cqre.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sch.cqre.api.domain.UserEntity;

@Getter
@NoArgsConstructor
public class MyInfoResponseDto {
	private Integer studentId;
	private String email;
	private String nickname;

	public MyInfoResponseDto(UserEntity userEntity) {
		this.studentId = userEntity.getStudentId();
		this.email = userEntity.getEmail();
		this.nickname = userEntity.getNickname();
	}
}
