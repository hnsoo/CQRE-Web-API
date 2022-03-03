package sch.cqre.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteUserResponseDto {
	private Integer userId;

	public DeleteUserResponseDto(Integer userId) {
		this.userId = userId;
	}
}
