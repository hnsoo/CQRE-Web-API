package sch.cqre.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sch.cqre.api.domain.UserEntity;

@Getter
@NoArgsConstructor
public class MyInfoDto {
	private Long userId;
	private Long studentId;
	private String email;
	private String nickname;

	public MyInfoDto(UserEntity userEntity) {
		this.userId = userEntity.getUserId();
		this.studentId = userEntity.getStudentId();
		this.email = userEntity.getEmail();
		this.nickname = userEntity.getNickname();
	}
}
