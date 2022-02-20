package sch.cqre.api.dto.mypage;

import lombok.Data;
import sch.cqre.api.domain.UserEntity;

@Data
public class MyInfoDTO {
	private Integer userId;
	private String email;
	private String profile;

	public MyInfoDTO(UserEntity userEntity) {
		this.userId = userEntity.getUserId();
		this.email = userEntity.getEmail();
	}
}
